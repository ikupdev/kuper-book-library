package ru.ikupdev.library.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
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
    @NotNull
    @Valid
    private TaskSettings task;

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

    public static class TaskSettings {
        @NotNull
        @Valid
        private CronSetting removeOrphanBooks;
        @NotNull
        @Valid
        private LibrarySettings.SyncSettings syncGoogleBookApi;

        public CronSetting getRemoveOrphanBooks() {
            return removeOrphanBooks;
        }

        public void setRemoveOrphanBooks(CronSetting removeOrphanBooks) {
            this.removeOrphanBooks = removeOrphanBooks;
        }

        public SyncSettings getSyncGoogleBookApi() {
            return syncGoogleBookApi;
        }

        public void setSyncGoogleBookApi(SyncSettings syncGoogleBookApi) {
            this.syncGoogleBookApi = syncGoogleBookApi;
        }
    }


    public static class CronSetting {

        private String cron;

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }
    }


    public static class SyncSettings {

        private String cron;
        @NotBlank
        @DecimalMin(value = "1")
        @Valid
        private String batchSize;

        public String getCron() {
            return cron;
        }

        public void setCron(String cron) {
            this.cron = cron;
        }

        public String getBatchSize() {
            return batchSize;
        }

        public void setBatchSize(String batchSize) {
            this.batchSize = batchSize;
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

    public TaskSettings getTask() {
        return task;
    }

    public void setTask(TaskSettings task) {
        this.task = task;
    }
}
