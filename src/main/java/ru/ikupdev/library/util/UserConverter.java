package ru.ikupdev.library.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.transfer.UserTO;

/**
 * @author Ilya V. Kupriyanov
 * @version 04.01.2022
 */
@Component
@RequiredArgsConstructor
public class UserConverter {
    private final PasswordEncoder passwordEncoder;

    public User fromUserTO(UserTO userTO, boolean encodePassword) {
        User user = User.builder()
                .firstName(userTO.getFirstName())
                .lastName(userTO.getLastName())
                .login(userTO.getLogin())
                .state(userTO.getState())
                .role(userTO.getRole())
                .build();
        if (encodePassword) user.setHashPassword(passwordEncoder.encode(userTO.getPassword()));
        return user;
    }
}
