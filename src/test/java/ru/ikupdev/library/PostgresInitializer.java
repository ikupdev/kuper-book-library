package ru.ikupdev.library;

import lombok.experimental.UtilityClass;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.01.2022
 */
@UtilityClass
public class PostgresInitializer {
    public static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:13")
            .withUsername("test_user")
            .withPassword("test_password")
            .withDatabaseName("test_db")
            .withInitScript("sql/schema.sql");

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + container.getJdbcUrl(),
                    "spring.datasource.username=" + container.getUsername(),
                    "spring.datasource.password=" + container.getPassword(),
                    "spring.liquibase.user=" + container.getUsername(),
                    "spring.liquibase.password=" + container.getPassword()
            ).applyTo(applicationContext.getEnvironment());
        }
    };

}
