package ru.ikupdev.library.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookshelfRequestDto;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Bookshelf;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
public interface IUserBookshelfService {
    RestResponseDto<Bookshelf> addNewBookshelf(Long userId, BookshelfRequestDto dto);

    RestResponseDto<List<Bookshelf>> getUserBookshelfList(Long userId, MultiValueMap<String, String> parameters, Pageable pageable);

    void updateBookshelf(Long bookshelfId, BookshelfUpdateDto dto);

    void deleteBookshelf(Long userId, Long bookshelfId);
}
