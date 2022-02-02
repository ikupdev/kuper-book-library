package ru.ikupdev.library.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.security.jwt.JwtUserDetails;
import ru.ikupdev.library.security.jwt.JwtUserFactory;
import ru.ikupdev.library.service.IUserService;
import ru.ikupdev.library.service.impl.UserService;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {
    private final IUserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUserDetails jwtUserDetails = JwtUserFactory.create(user);
        log.info("IN loadByUsername - user with username: {} successfully loaded", username);
        return jwtUserDetails;
    }

}
