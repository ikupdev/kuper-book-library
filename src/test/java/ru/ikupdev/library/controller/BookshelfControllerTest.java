package ru.ikupdev.library.controller;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ikupdev.library.AbstractIntegrationTest;
import ru.ikupdev.library.model.Bookshelf;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
class BookshelfControllerTest extends AbstractIntegrationTest {

    @Test
    void givenGetBookshelfList_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(
                        components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/list")
                        .param("name", "Название")
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.data.*", hasSize(2)))
                .andExpect(jsonPath("$.data[0].name", is("Название 1")))
                .andExpect(jsonPath("$.data[1].name", is("Название 2")));
    }

    @Test
    void givenGetBookshelf_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/3")
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.name", is("Книжная полка 3")));
    }

    @Test
    void givenAddNewBookshelf_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/new_bookshelf.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.name", is("Полка №4")));
        Optional<Bookshelf> newBookshelf = components.getBookshelfRepository().findByName("Полка №4");
        assertTrue(newBookshelf.isPresent());
        assertEquals("Полка №4", newBookshelf.get().getName());
    }

    @Test
    void givenUpdateBookshelf_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.patch(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/3/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/update_bookshelf.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.name", is("Книжная полка 3 обнов")))
                .andExpect(jsonPath("$.data.description", is("Описание книжной полки 4")));
        Optional<Bookshelf> updatedBookshelf = components.getBookshelfRepository().findById(3L);
        assertTrue(updatedBookshelf.isPresent());
        assertEquals("Книжная полка 3 обнов", updatedBookshelf.get().getName());
        assertEquals("Описание книжной полки 4", updatedBookshelf.get().getDescription());
    }

    @Test
    void givenDeleteBookshelf_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.delete(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/3/delete")
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful());
        Assert.assertEquals(2, components.getBookshelfRepository().count());
    }

}