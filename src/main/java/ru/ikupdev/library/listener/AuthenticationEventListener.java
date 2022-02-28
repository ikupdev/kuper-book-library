package ru.ikupdev.library.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
@Component
@Slf4j
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        if (event instanceof InteractiveAuthenticationSuccessEvent) return;
        Authentication authentication = event.getAuthentication();
        String auditMessage = "Login attempt with username: " +
                authentication.getName() + " " +
                authentication.getAuthorities() + " " +
                authentication.getCredentials() + " " +
                authentication.getPrincipal() + "\n" +
                "SUCCESS: " + authentication.isAuthenticated();
        log.info(auditMessage);
    }

}
