package ru.ikupdev.library;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Ilya V. Kupriyanov
 * @version 06.01.2022
 */
@Sql("/sql/data.sql")
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = {
        PostgresInitializer.Initializer.class,
        RandomPortInitializer.class
})
@AutoConfigureMockMvc
@Transactional
public abstract class AbstractIntegrationTest {
    @RegisterExtension
    public static WireMockExtension wireMock = new WireMockExtension();
    @Autowired
    protected TestComponents components;
    @BeforeAll
    static void beforeAll() {
        PostgresInitializer.container.start();
        WireMockUtil.stubs();
    }

}

