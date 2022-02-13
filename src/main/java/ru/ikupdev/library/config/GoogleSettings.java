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
@ConfigurationProperties(prefix = "google")
@Validated
public class GoogleSettings {
    @NotNull
    @Valid
    private ApiSettings api;

    public static class ApiSettings {
        @NotBlank
        @Valid
        private String url;
        @NotBlank
        @Valid
        private String key;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    public ApiSettings getApi() {
        return api;
    }

    public void setApi(ApiSettings api) {
        this.api = api;
    }
}
