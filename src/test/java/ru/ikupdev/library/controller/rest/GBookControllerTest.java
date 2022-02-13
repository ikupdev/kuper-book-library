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
 * @version 10.02.2022
 */
class GBookControllerTest extends AbstractIntegrationTest {

    @Test
    void testGetBooks() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + "/gbook/raw/list")
                        .param("keyword", "inauthor")
                        .param("keyQuery", "Rowling")
                        .param("key", "AIzaSyDIo47O3Sy9s4zOfyXvL_zSRxpMf2XeD3s")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.totalItems", is(139)))
                .andExpect(jsonPath("$.items.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.items.*", hasSize(10)));
    }
}