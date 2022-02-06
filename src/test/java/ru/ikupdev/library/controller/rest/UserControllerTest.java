package ru.ikupdev.library.controller.rest;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ikupdev.library.AbstractIntegrationTest;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ikupdev.library.config.AppConstants.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 06.02.2022
 */
public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    void testGetAllUsers() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get(contextPath + API_V1_PATH + "/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(contextPath))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].status", is("ACTIVE")))
                .andExpect(jsonPath("$.data[0].login", is("kuper")))
                .andExpect(jsonPath("$.data[0].firstName", is("Ilya")))
                .andExpect(jsonPath("$.data[0].lastName", is("Kupriyanov")))
                .andExpect(jsonPath("$.data[0].email", is("kup-92@yandex.ru")))
                .andExpect(jsonPath("$.data[0].role[0].name", is("ROLE_USER")));
    }

}
