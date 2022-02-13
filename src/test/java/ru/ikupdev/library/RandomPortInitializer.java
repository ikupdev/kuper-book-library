package ru.ikupdev.library;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.util.SocketUtils;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
public class RandomPortInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    public static final int REST_API_PORT = SocketUtils.findAvailableTcpPort();

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext,
                "google.api.url=http://localhost:" + REST_API_PORT + "/");
    }
}
