package ru.ikupdev.library.controller.rest;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ikupdev.library.AbstractIntegrationTest;
import ru.ikupdev.library.service.impl.AuthService;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 05.02.2022
 */
public class AuthControllerTest extends AbstractIntegrationTest {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(AuthService.class.getName());

    @Test
    void givenCorrectCreditionals_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + "/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/auth.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.login", is("petr_petrov")))
                .andExpect(jsonPath("$.token", notNullValue()));
    }

    @Test
    void givenWrongLogin_whenGetUsernameNotFoundException_thenInternalServerError() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + "/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/auth_wrong_login.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message", is(BUNDLE.getString("auth.invalid.login"))));
    }

    @Test
    void givenWrongPassword_whenGetUsernameNotFoundException_thenInternalServerError() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + "/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/auth_wrong_password.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message", is(BUNDLE.getString("auth.invalid.login"))));
    }

}
