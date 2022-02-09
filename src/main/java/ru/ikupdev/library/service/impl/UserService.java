package ru.ikupdev.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.exception.ResourceConflictException;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.dto.UserUpdateDto;
import ru.ikupdev.library.repository.UserRepository;
import ru.ikupdev.library.service.IUserService;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(UserService.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserView save(@Validated User user) {
        if (userRepository.findByLogin(user.getLogin()).isPresent())
            throw new ResourceConflictException(String.format(BUNDLE.getString("exist.login"), user.getLogin()));
        User savedUser = userRepository.save(user);
        return new UserView(savedUser.getId(), savedUser.getLogin());
    }

    @Override
    public UserView update(Long id, UserUpdateDto userUpdateDto) {
        User userForUpdate = getUserOrElseThrow(id);
        if (userUpdateDto.getLogin() != null) userForUpdate.setLogin(userUpdateDto.getLogin());
        if (userUpdateDto.getFirstName() != null) userForUpdate.setFirstName(userUpdateDto.getFirstName());
        if (userUpdateDto.getLastName() != null) userForUpdate.setLastName(userUpdateDto.getLastName());
        if (userUpdateDto.getEmail() != null) userForUpdate.setEmail(userUpdateDto.getEmail());
        if (userUpdateDto.getPassword() != null) userForUpdate.setHashPassword(passwordEncoder.encode(userUpdateDto.getPassword()));
        userForUpdate.setUpdated(new Date());
        User saved = userRepository.save(userForUpdate);
        return new UserView(id, saved.getLogin());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByLogin(String name) {
        return userRepository.findByLogin(name).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(Long id) {
        return getUserOrElseThrow(id);
    }

    @Override
    public void delete(Long id) {
        User user = getUserOrElseThrow(id);
        user.setStatus(Status.DELETED);
        user.setUpdated(new Date());
        userRepository.save(user);
    }

    private User getUserOrElseThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BUNDLE.getString("not.found.id"), id)));
    }
}
