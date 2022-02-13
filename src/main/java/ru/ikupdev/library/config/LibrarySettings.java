package ru.ikupdev.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Ilya V. Kupriyanov
 * @version 13.02.2022
 */
@Configuration
@ConfigurationProperties(prefix = "library")
@Validated
public class LibrarySettings {
    @NotNull
    @Valid
    private String schemaName;
    @NotNull
    @Valid
    private JwtSettings jwt;

    public static class JwtSettings {
        @NotNull
        @Valid
        private TokenSettings token;

        public TokenSettings getToken() {
            return token;
        }

        public void setToken(TokenSettings token) {
            this.token = token;
        }
    }

    public static class TokenSettings {
        @NotBlank
        @Valid
        private String secret;
        @NotBlank
        @Valid
        private String expired;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getExpired() {
            return expired;
        }

        public void setExpired(String expired) {
            this.expired = expired;
        }
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public JwtSettings getJwt() {
        return jwt;
    }

    public void setJwt(JwtSettings jwt) {
        this.jwt = jwt;
    }

}
