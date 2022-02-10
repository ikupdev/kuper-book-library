package ru.ikupdev.library;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static ru.ikupdev.library.RandomPortInitializer.REST_API_PORT;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
class WireMockExtension extends WireMockServer implements BeforeAllCallback, AfterAllCallback {

    public WireMockExtension() {
        super(REST_API_PORT);
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        this.start();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) {
        this.stop();
        this.resetAll();
    }
}
