package ru.ikupdev.library.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookshelfRequestDto;
import ru.ikupdev.library.dto.BookshelfResponseDto;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Bookshelf;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
public interface IBookshelfService {
    RestResponseDto<BookshelfResponseDto> addNewBookshelf(Long userId, BookshelfRequestDto dto);

    void saveBookshelf(Bookshelf bookshelf);

    Bookshelf findByName(String name);

    RestResponseDto<List<BookshelfResponseDto>> getBookshelfList(Long userId, MultiValueMap<String, String> parameters, Pageable pageable);

    Bookshelf getById(Long id);

    RestResponseDto<BookshelfResponseDto> getByIdBookshelfResponseDto(Long id);

    void deleteBookshelf(Long userId, Long bookshelfId);

    RestResponseDto<BookshelfResponseDto> updateBookshelf(Long bookId, BookshelfUpdateDto bookshelfUpdateDto);

    Bookshelf getBookshelfByNameOrElseNull(String name);
}
