package ru.ikupdev.library.controller;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ikupdev.library.AbstractIntegrationTest;
import ru.ikupdev.library.model.User;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
class UserControllerTest extends AbstractIntegrationTest {

    @Test
    void givenUpdateUserThen200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.patch(components.getContextPath() + API_V1_PATH + "/user/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/update_user.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.login", is("vasily_vasiliev1234")));
        Optional<User> updatedUser = components.getUserRepository().findById(5L);
        assertTrue(updatedUser.isPresent());
        assertEquals("vasily_vasiliev1234", updatedUser.get().getLogin());
        assertEquals("vasiliyev1234@google.com", updatedUser.get().getEmail());
    }
}