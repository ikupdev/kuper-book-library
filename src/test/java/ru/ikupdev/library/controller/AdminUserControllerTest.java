package ru.ikupdev.library.controller;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ikupdev.library.AbstractIntegrationTest;
import ru.ikupdev.library.model.User;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ikupdev.library.config.LibraryConst.ADMIN_PATH;
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 06.02.2022
 */
public class AdminUserControllerTest extends AbstractIntegrationTest {

    @Test
    void givenGetAllUsers_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + ADMIN_PATH + "/user/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.data.*", hasSize(6)));
    }

    @Test
    void givenGetUsersPageWithSpecifiedParameters_then200Ok() throws Exception {
        String params = "?lastName=Иванов&page=0&size=10&sort=firstName,ASC&status=ACTIVE";
        MvcResult mvcResult = components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + ADMIN_PATH + "/user/list" + params)
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.data.*", hasSize(2)))
                .andExpect(jsonPath("$.data[0].id", is(7)))
                .andExpect(jsonPath("$.data[0].status", is("ACTIVE")))
                .andExpect(jsonPath("$.data[0].login", is("vasily_ivanov")))
                .andExpect(jsonPath("$.data[0].firstName", is("Василий")))
                .andExpect(jsonPath("$.data[0].lastName", is("Иванов")))
                .andExpect(jsonPath("$.data[0].email", is("ivanov3@yandex.ru")))
                .andExpect(jsonPath("$.data[1].id", is(4)))
                .andExpect(jsonPath("$.data[1].status", is("ACTIVE")))
                .andExpect(jsonPath("$.data[1].login", is("ivan_ivanov")))
                .andExpect(jsonPath("$.data[1].firstName", is("Иван")))
                .andExpect(jsonPath("$.data[1].lastName", is("Иванов")))
                .andExpect(jsonPath("$.data[1].email", is("ivanov1@yandex.ru")))
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
    }

    @Test
    void givenGetUserById_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + ADMIN_PATH + "/user/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.login", is("Admin")));
    }

    @Test
    void givenAddUser_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + ADMIN_PATH + "/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/add_user.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(8)))
                .andExpect(jsonPath("$.login", is("austin-powers")));
        Optional<User> addedUser = components.getUserRepository().findById(8L);
        assertTrue(addedUser.isPresent());
        assertEquals("austin-powers", addedUser.get().getLogin());
    }

    @Test
    void givenUpdateUser_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.patch(components.getContextPath() + API_V1_PATH + ADMIN_PATH + "/user/5")
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

    @Test
    void givenDeleteUser_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.delete(components.getContextPath() + API_V1_PATH + ADMIN_PATH + "/user/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful());
        assertEquals(6, components.getUserRepository().count());
    }

}
