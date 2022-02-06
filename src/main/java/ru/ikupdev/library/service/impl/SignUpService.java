package ru.ikupdev.library.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.service.ISignUpService;

import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.02.2022
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SignUpService implements ISignUpService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(SignUpService.class.getName());
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserView signUp(UserRequestDto dto) {
        if (userService.findByLogin(dto.getLogin()) != null)
            throw new IllegalArgumentException(String.format(BUNDLE.getString("exist.login"), dto.getLogin()));
        if (userService.findByEmail(dto.getEmail()) != null)
            throw new IllegalArgumentException(String.format(BUNDLE.getString("exist.email"), dto.getEmail()));
        User user = User.builder()
                .login(dto.getLogin())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .role(roleService.findByRoleName("ROLE_USER"))
                .hashPassword(passwordEncoder.encode(dto.getPassword()))
                .build();
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setStatus(Status.ACTIVE);
        UserView userView = userService.save(user);
        log.info(BUNDLE.getString("log.saved"), userView);
        return new UserView(userView.getId(), userView.getLogin());
    }
}
