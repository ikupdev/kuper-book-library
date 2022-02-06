package ru.ikupdev.library.controller.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ikupdev.library.AbstractIntegrationTest;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.service.impl.UserService;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

/**
 * @author Ilya V. Kupriyanov
 * @version 05.02.2022
 */

public class AuthControllerTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetAllUsers() {
        List<User> all = userService.getAll();
        assertThat(all, hasSize(1));
    }

}
