package ru.ikupdev.library.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.dto.UserViewDto;
import ru.ikupdev.library.service.IRoleService;
import ru.ikupdev.library.service.ISignUpService;
import ru.ikupdev.library.service.IUserService;

import java.util.Date;
import java.util.HashSet;
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
    private final IUserService userService;
    private final IRoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserViewDto signUp(UserRequestDto dto) {
        if (userService.findByLoginOrElseNull(dto.getLogin()) != null)
            throw new IllegalArgumentException(String.format(BUNDLE.getString("signup.exist.login"), dto.getLogin()));
        if (userService.findByEmailOrElseNull(dto.getEmail()) != null)
            throw new IllegalArgumentException(String.format(BUNDLE.getString("signup.exist.email"), dto.getEmail()));
        User user = User.builder()
                .login(dto.getLogin())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .hashPassword(passwordEncoder.encode(dto.getPassword()))
                .roles(new HashSet<>())
                .created(new Date())
                .updated(new Date())
                .status(Status.ACTIVE)
                .build();
        user.addRole(roleService.findByRoleName("ROLE_USER"));
        UserViewDto userViewDto = userService.saveNew(user);
        log.info(BUNDLE.getString("signup.log.saved"), userViewDto);
        return new UserViewDto(userViewDto.getId(), userViewDto.getLogin());
    }
}
