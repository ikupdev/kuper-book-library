package ru.ikupdev.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.model.Bookshelf;
import ru.ikupdev.library.service.IBookService;
import ru.ikupdev.library.service.IBookshelfBookService;
import ru.ikupdev.library.service.IBookshelfService;
import ru.ikupdev.library.service.IGBookService;

import java.util.Date;
import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.02.2022
 */
@Service
@RequiredArgsConstructor
public class BookshelfBookService implements IBookshelfBookService {
    private final IGBookService gBookService;
    private final IBookService bookService;
    private final IBookshelfService bookshelfService;

    @Override
    public RestResponseDto<Book> addBookToBookshelf(Long userId, Long bookshelfId, String volumeId) {
        Bookshelf bookshelf = bookshelfService.getBookshelf(bookshelfId).getData();
        Book existBook = bookService.findByVolumeIdOrElseNull(volumeId);
        Book book = existBook != null ? existBook : gBookService.getBookByVolumeId(volumeId).getData();
        if (!bookshelf.getBooks().contains(book)) {
            bookshelf.addBook(book);
            bookshelfService.saveBookshelf(bookshelf);
        }
        return new RestResponseDto<>(book);
    }

    @Override
    public RestResponseDto<Book> getBookById(Long bookId) {
        return new RestResponseDto<>(getBookOrElseThrow(bookId));
    }

    @Override
    public RestResponseDto<List<Book>> findBooks(MultiValueMap<String, String> parameters,
                                                 Pageable pageable) {
        return bookService.findBooks(parameters, pageable);
    }

    @Override
    public void deleteFromBookshelf(Long bookshelfId, Long bookId) {
        Book book = bookService.findById(bookId);
        Bookshelf bookshelf = bookshelfService.getBookshelf(bookshelfId).getData();
        bookshelf.removeBook(book);
        bookshelfService.saveBookshelf(bookshelf);
    }

    @Override
    public RestResponseDto<Book> update(Long bookId, BookUpdateDto dto) {
        Book bookForUpdate = getBookOrElseThrow(bookId);
        if (dto.getVolumeId() != null) bookForUpdate.setVolumeId(dto.getVolumeId());
        if (dto.getTitle() != null) bookForUpdate.setTitle(dto.getTitle());
        if (dto.getSubtitle() != null) bookForUpdate.setSubtitle(dto.getSubtitle());
        if (dto.getDescription() != null) bookForUpdate.setDescription(dto.getDescription());
        if (dto.getPageCount() != null) bookForUpdate.setPageCount(dto.getPageCount());
        if (dto.getLanguage() != null) bookForUpdate.setLanguage(dto.getLanguage());
        if (dto.getImageLink() != null) bookForUpdate.setImageLink(dto.getImageLink());
        if (dto.getPreviewLink() != null) bookForUpdate.setPreviewLink(dto.getPreviewLink());
        if (dto.getInfoLink() != null) bookForUpdate.setInfoLink(dto.getInfoLink());
        if (dto.getCanonicalVolumeLink() != null) bookForUpdate.setCanonicalVolumeLink(dto.getCanonicalVolumeLink());
        if (dto.getAuthors() != null) bookForUpdate.setAuthors(dto.getAuthors());
        if (dto.getSearchInfo() != null) bookForUpdate.setSearchInfo(dto.getSearchInfo());
        if (dto.getEpubDownloadLink() != null) bookForUpdate.setEpubDownloadLink(dto.getEpubDownloadLink());
        if (dto.getPdfDownloadLink() != null) bookForUpdate.setPdfDownloadLink(dto.getPdfDownloadLink());
        if (dto.getWebReaderLink() != null) bookForUpdate.setWebReaderLink(dto.getWebReaderLink());
        if (dto.getBuyLink() != null) bookForUpdate.setBuyLink(dto.getBuyLink());
        bookForUpdate.setUpdated(new Date());
        return new RestResponseDto<>(bookService.saveBook(bookForUpdate));
    }

    private Book getBookOrElseThrow(Long id) {
        return bookService.findById(id);
    }

}