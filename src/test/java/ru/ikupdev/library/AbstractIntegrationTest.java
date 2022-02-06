package ru.ikupdev.library;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import ru.ikupdev.library.initializer.PostgresInitializer;

import javax.transaction.Transactional;

/**
 * @author Ilya V. Kupriyanov
 * @version 06.01.2022
 */
@Sql("/sql/data.sql")
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = {
        PostgresInitializer.Initializer.class
})
@AutoConfigureMockMvc
@Transactional
public abstract class AbstractIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;
    @Value("${server.servlet.context-path}")
    protected String contextPath;
    @BeforeAll
    static void init() {
        PostgresInitializer.container.start();
    }
}

