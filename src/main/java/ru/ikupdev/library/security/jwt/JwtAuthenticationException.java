package ru.ikupdev.library.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
public class JwtAuthenticationException extends AuthenticationException {
    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
