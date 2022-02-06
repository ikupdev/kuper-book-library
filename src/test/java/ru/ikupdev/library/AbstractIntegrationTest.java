package ru.ikupdev.library;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
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
@Transactional
public abstract class AbstractIntegrationTest {

    @BeforeAll
    static void init() {
        PostgresInitializer.container.start();
    }
}

