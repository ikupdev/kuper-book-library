package ru.ikupdev.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
@Service
@Slf4j
@AllArgsConstructor
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public JwtAuthDto login(AuthRequestDto authRequestDto) {
        try {
            String username = authRequestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) throw new UsernameNotFoundException("User with username: " + username + "not found!");
            String token = jwtTokenProvider.createToken(username, user.getRole());
            return new JwtAuthDto(username, token);
        } catch (AuthenticationException e) {
            log.info("Invalid username or password for username {}", authRequestDto.getUsername());
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
