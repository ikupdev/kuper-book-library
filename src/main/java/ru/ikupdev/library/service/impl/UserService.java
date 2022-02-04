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
import ru.ikupdev.library.model.to.UserTO;
import ru.ikupdev.library.repository.RoleRepository;
import ru.ikupdev.library.repository.UserRepository;
import ru.ikupdev.library.service.IUserService;
import ru.ikupdev.library.util.JsonMapper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserView save(@Validated User user) {
        if (userRepository.findByLogin(user.getLogin()).isPresent())
            throw new ResourceConflictException("SAVE operation: User with login " + user.getLogin() + " already exist!");
        User savedUser = userRepository.save(user);
        return new UserView(savedUser.getId(), savedUser.getLogin());
    }

    @Override
    public UserView update(Long id, UserTO userTO) {
        User userForUpdate = getUserOrElseThrow(id);
        if (userTO.getLogin() != null) userForUpdate.setLogin(userTO.getLogin());
        if (userTO.getFirstName() != null) userForUpdate.setFirstName(userTO.getFirstName());
        if (userTO.getLastName() != null) userForUpdate.setLastName(userTO.getLastName());
        if (userTO.getEmail() != null) userForUpdate.setEmail(userTO.getEmail());
        if (userTO.getPassword() != null) userForUpdate.setHashPassword(passwordEncoder.encode(userTO.getPassword()));
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
                .orElseThrow(() -> new NotFoundException("User with id: " + id + " not found!"));
    }
}
