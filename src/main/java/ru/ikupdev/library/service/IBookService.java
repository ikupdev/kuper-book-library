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
public interface IBookService {
    Book findByVolumeIdOrElseNull(String volumeId);

    Book findByVolumeIdOrElseThrow(String volumeId);

    Book saveBook(Book book);

    RestResponseDto<List<Book>> findBooks(MultiValueMap<String, String> parameters, Pageable pageable);

    Book findById(Long id);

    void delete(Long id);

    Book update(Long bookId, BookUpdateDto bookUpdate);

    List<Long> deleteOrphanBooks();
}
