package ru.ikupdev.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.model.Role;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.repository.RoleRepository;
import ru.ikupdev.library.repository.UserRepository;
import ru.ikupdev.library.service.IUserService;

import java.util.ArrayList;
import java.util.List;

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
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER")
                .orElseThrow(()-> new IllegalArgumentException("Role not found!!"));
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setHashPassword(passwordEncoder.encode(user.getHashPassword()));
        user.setRole(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN register - user: {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        return result;
    }

    @Override
    public User findByUsername(String name) {
        User result = userRepository.findByUsername(name)
                .orElseThrow(()-> new IllegalArgumentException("User not found by login!"));
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);
        if (result == null) {
            log.warn("IN findById : User not found by id: {}", id);
            return null;
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
