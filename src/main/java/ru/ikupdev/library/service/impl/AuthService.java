package ru.ikupdev.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.dto.AuthRequestDto;
import ru.ikupdev.library.dto.JwtAuthDto;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.security.jwt.JwtTokenProvider;
import ru.ikupdev.library.service.IAuthService;

import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
@Service
@Slf4j
@AllArgsConstructor
public class AuthService implements IAuthService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(AuthService.class.getName());
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public JwtAuthDto login(AuthRequestDto dto) {
        try {
            String login = dto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, dto.getPassword()));
            User user = userService.findByLogin(login);
            if (user == null)
                throw new UsernameNotFoundException(String.format(BUNDLE.getString("auth.not.found.login"), dto.getLogin()));
            String token = jwtTokenProvider.createToken(login, user.getRole());
            return new JwtAuthDto(login, token);
        } catch (AuthenticationException e) {
            log.info(BUNDLE.getString("auth.log.invalid.login"), dto.getLogin());
            throw new BadCredentialsException(BUNDLE.getString("auth.invalid.login"));
        }
    }

}
