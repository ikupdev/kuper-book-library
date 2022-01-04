package ru.ikupdev.library.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ikupdev.library.dto.TokenDto;
import ru.ikupdev.library.form.LoginForm;
import ru.ikupdev.library.model.Token;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.repository.TokenRepository;
import ru.ikupdev.library.repository.UserRepository;

import java.util.Optional;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@Component
public class LoginService implements ILoginService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = userRepository.findUserByLogin(loginForm.getLogin());
        if (userCandidate.isPresent()) {
            User user = userCandidate.get();
            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .tokenValue(RandomStringUtils.random(10, true, true))
                        .build();
                tokenRepository.save(token);
                return TokenDto.from(token);
            } else {
                throw new IllegalArgumentException("Incorrect password!");
            }
        } else {
            throw new IllegalArgumentException("User not found!");
        }
    }
}
