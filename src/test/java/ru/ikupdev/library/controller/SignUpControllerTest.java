package ru.ikupdev.library.controller;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ikupdev.library.AbstractIntegrationTest;
import ru.ikupdev.library.model.User;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 06.02.2022
 */
public class SignUpControllerTest extends AbstractIntegrationTest {

    @Test
    void givenCorrectRegistrationData_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + "/sign-up/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/sign_up.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.login", is("geronimo")));
        User user = components.getUserService().findByLoginOrElseNull("geronimo");
        assertEquals("geronimo", user.getLogin());
        assertEquals("Dr", user.getFirstName());
        assertEquals("Who", user.getLastName());
        assertEquals("dr-who@yandex.ru", user.getEmail());
        assertNotNull(user.getHashPassword());
    }

    @Test
    void givenIncorrectRegistrationData_whenGetMethodArgumentNotValidException_then400ClientError() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + "/sign-up/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/sign_up_incorrect.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.validationErrors", notNullValue()));
    }
}
