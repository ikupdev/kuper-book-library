package ru.ikupdev.library.listener;

import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
@Component
public class LoginAttemptsLogger {

    @EventListener
    public void auditEventHappened(AuditApplicationEvent auditApplicationEvent) {
        AuditEvent auditEvent = auditApplicationEvent.getAuditEvent();
        System.out.println("Principal " + auditEvent.getPrincipal()
                + " - " + auditEvent.getType());
        WebAuthenticationDetails details =
                (WebAuthenticationDetails) auditEvent.getData().get("details");
        System.out.println("Remote IP address: "
                + details.getRemoteAddress());
        System.out.println("  Session Id: " + details.getSessionId());
    }

    @EventListener
    public void auditEventHappened(AbstractAuthenticationEvent event) {
        if (event instanceof InteractiveAuthenticationSuccessEvent) return;
        Authentication authentication = event.getAuthentication();
        String auditMessageBuilder = "Login attempt with username: " +
                authentication.getName() + " " +
                authentication.getAuthorities() + " " +
                authentication.getCredentials() + " " +
                authentication.getPrincipal() + "\n" +
                "SUCCESS: " + authentication.isAuthenticated();
        System.out.println(auditMessageBuilder);

    }

}
