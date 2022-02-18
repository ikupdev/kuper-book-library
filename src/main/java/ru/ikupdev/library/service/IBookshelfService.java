package ru.ikupdev.library.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Bookshelf;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
public interface IBookshelfService {
    Bookshelf findByBookshelfName(String bookshelfName);

    Bookshelf saveBookshelf(Bookshelf bookshelf);

    RestResponseDto<List<Bookshelf>> findBookshelfs(MultiValueMap<String, String> parameters, Pageable pageable);

    Bookshelf findById(Long id);

    void delete(Long id);

    Bookshelf update(Long bookId, BookshelfUpdateDto bookshelfUpdateDto);

    Bookshelf getBookshelfByBookshelfNameOrElseNull(String bookshelfName);
}
