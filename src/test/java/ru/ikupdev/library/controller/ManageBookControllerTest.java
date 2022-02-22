package ru.ikupdev.library.controller;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.ikupdev.library.AbstractIntegrationTest;
import ru.ikupdev.library.model.Book;
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
class ManageBookControllerTest extends AbstractIntegrationTest {

    @Test
    void givenGetBookList_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(
                        components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/1/book/list")
                        .param("title", "Potter")
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.data.*", hasSize(1)))
                .andExpect(jsonPath("$.data[0].title", is("Harry Potter et la coupe de feu")));
    }

    @Test
    void givenAddBookByVolumeId_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.post(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/1/book/DDDDDDDDDDDD/new")
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.title", is("Very Good Lives")));
        Optional<Book> newBook = components.getBookRepository().findByVolumeId("DDDDDDDDDDDD");
        assertTrue(newBook.isPresent());
        assertEquals("Very Good Lives", newBook.get().getTitle());
    }

    @Test
    void givenGetBook_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.get(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/1/book/2")
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.volumeId", is("9lLrAAAACAAJ")))
                .andExpect(jsonPath("$.data.title", is("Conversations with J. K. Rowling")));
    }

    @Test
    void givenUpdateBook_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.patch(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/1/book/2/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(IOUtils.toByteArray(new ClassPathResource("files/update_book.json").getInputStream()))
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.data.title", is("Conversations with J. K. Rowling 1234")))
                .andExpect(jsonPath("$.data.authors", is("J. K. Rowling 1234")));
        Optional<Book> updatedBook = components.getBookRepository().findById(2L);
        assertTrue(updatedBook.isPresent());
        assertEquals("Conversations with J. K. Rowling 1234", updatedBook.get().getTitle());
        assertEquals("J. K. Rowling 1234", updatedBook.get().getAuthors());
    }

    @Test
    void givenDeleteBook_then200Ok() throws Exception {
        components.getMockMvc()
                .perform(MockMvcRequestBuilders.delete(components.getContextPath() + API_V1_PATH + "/manage/user/1/bookshelf/1/book/2/delete")
                        .contextPath(components.getContextPath()))
                .andExpect(status().is2xxSuccessful());
        Assert.assertEquals(1, components.getBookshelfRepository().findById(1L).get().getBooks().size());
        Assert.assertEquals(0, components.getBookRepository().findById(2L).get().getBookshelfs().size());
    }

}
