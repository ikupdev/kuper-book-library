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
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 06.02.2022
 */
public class UserControllerTest extends AbstractIntegrationTest {

    @Test
    void givenGetAllUsers_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + "/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.data.*", hasSize(5)))
                .andExpect(jsonPath("$.data[0].id", is(1)))
                .andExpect(jsonPath("$.data[0].status", is("ACTIVE")))
                .andExpect(jsonPath("$.data[0].login", is("petr_petrov")))
                .andExpect(jsonPath("$.data[0].firstName", is("Петр")))
                .andExpect(jsonPath("$.data[0].lastName", is("Петров")))
                .andExpect(jsonPath("$.data[0].email", is("petrov@yandex.ru")))
                .andExpect(jsonPath("$.data[0].role[0].name", is("ROLE_USER")))
                .andReturn();
    }

    @Test
    void givenGetUsersPageWithSpecifiedParameters_then200Ok() throws Exception {
        String params = "?lastName=Иванов&page=0&size=10&sort=firstName,ASC&status=ACTIVE";
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + "/user/list" + params)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.data.*", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(5)))
                .andExpect(jsonPath("$.data[0].status", is("ACTIVE")))
                .andExpect(jsonPath("$.data[0].login", is("vasily_ivanov")))
                .andExpect(jsonPath("$.data[0].firstName", is("Василий")))
                .andExpect(jsonPath("$.data[0].lastName", is("Иванов")))
                .andExpect(jsonPath("$.data[0].email", is("ivanov3@yandex.ru")))
                .andExpect(jsonPath("$.data[0].role[0].name", is("ROLE_USER")))
                .andExpect(jsonPath("$.data[0].role[1].name", is("ROLE_ADMIN")))
                .andExpect(jsonPath("$.data[1].id", is(2)))
                .andExpect(jsonPath("$.data[1].status", is("ACTIVE")))
                .andExpect(jsonPath("$.data[1].login", is("ivan_ivanov")))
                .andExpect(jsonPath("$.data[1].firstName", is("Иван")))
                .andExpect(jsonPath("$.data[1].lastName", is("Иванов")))
                .andExpect(jsonPath("$.data[1].email", is("ivanov1@yandex.ru")))
                .andExpect(jsonPath("$.data[1].role[0].name", is("ROLE_USER")));
    }

}
