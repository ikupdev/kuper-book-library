package ru.ikupdev.library.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Book;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.02.2022
 */
public interface IBookshelfBookService {
    RestResponseDto<Book> addBookToBookshelf(Long userId, Long bookshelfId, String volumeId);

    RestResponseDto<Book> getBookById(Long bookId);

    RestResponseDto<List<Book>> findBooks(MultiValueMap<String, String> parameters,
                                          Pageable pageable);

    void deleteFromBookshelf(Long bookshelfId, Long id);

    RestResponseDto<Book>  update(Long bookId, BookUpdateDto bookUpdate);
}
