package ru.ikupdev.library.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookResponseDto;
import ru.ikupdev.library.dto.BookUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.dto.filter.BookFilter;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.repository.BookRepository;
import ru.ikupdev.library.service.IBookService;
import ru.ikupdev.library.util.MapperUtil;
import ru.ikupdev.library.util.OrderUtil;
import ru.ikupdev.library.util.QPredicates;

import java.util.*;

import static ru.ikupdev.library.model.QBook.book;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.02.2022
 */
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle(BookService.class.getName());
    private final BookRepository bookRepository;
    private final MapperUtil mapperUtil;

    @Override
    public Book findByVolumeIdOrElseNull(String volumeId) {
        return bookRepository.findByVolumeId(volumeId).orElse(null);
    }

    @Override
    public Book findByVolumeIdOrElseThrow(String volumeId) {
        return bookRepository.findByVolumeId(volumeId).orElseThrow(() ->
                new NotFoundException(String.format(BUNDLE.getString("book.not.found"), volumeId)));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public RestResponseDto<List<BookResponseDto>> getBookResponseDtoList(MultiValueMap<String, String> parameters, @PageableDefault Pageable pageable) {
        BookFilter filter = BookFilter.builder()
                .title(parameters.getFirst("title"))
                .subtitle(parameters.getFirst("subtitle"))
                .description((parameters.getFirst("description")))
                .language(parameters.getFirst("language"))
                .authors(parameters.getFirst("authors"))
                .userId(parameters.getFirst("userId") == null ? null :
                        Long.valueOf(Objects.requireNonNull(parameters.getFirst("userId"))))
                .bookshelfId(parameters.getFirst("bookshelfId") == null ? null :
                        Long.valueOf(Objects.requireNonNull(parameters.getFirst("bookshelfId"))))
                .build();
        Predicate predicate = QPredicates.builder()
                .add(filter.getTitle(), book.title::containsIgnoreCase)
                .add(filter.getSubtitle(), book.subtitle::containsIgnoreCase)
                .add(filter.getDescription(), book.description::containsIgnoreCase)
                .add(filter.getLanguage(), book.language::containsIgnoreCase)
                .add(filter.getAuthors(), book.authors::containsIgnoreCase)
                .add(filter.getBookshelfId(), book.bookshelfs.any().id::eq)
                .add(filter.getUserId(), book.bookshelfs.any().user.id::eq)
                .buildAnd();
        QPageRequest qPageRequest = OrderUtil.booksOrder(pageable);
        Page<Book> bookPage = predicate == null ? bookRepository.findAll(qPageRequest) : bookRepository.findAll(predicate, qPageRequest);
        List<BookResponseDto> bookResponseDtos = MapperUtil.convertList(bookPage.getContent(), mapperUtil::convertBookToBookResponseDto);
        Page<BookResponseDto> bookResponseDtoPage = new PageImpl<>(bookResponseDtos, bookPage.getPageable(), bookPage.getTotalElements());
        return RestResponseDto.fromPage(bookResponseDtoPage);
    }

    @Override
    public Book findById(Long id) {
        return getBookByIdOrElseThrow(id);
    }

    @Override
    public void delete(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else throw new NotFoundException(String.format(BUNDLE.getString("book.by.id.not.found"), id));
    }

    @Override
    public Book update(Long bookId, BookUpdateDto dto) {
        Book bookForUpdate = getBookByIdOrElseThrow(bookId);
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
        return bookRepository.save(bookForUpdate);
    }

    @Override
    public List<Long> deleteOrphanBooks() {
        List<Long> orphanBookIds = bookRepository.findOrphanBookIds();
        orphanBookIds.forEach(bookRepository::deleteById);
        return orphanBookIds;
    }

    private Book getBookByIdOrElseThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BUNDLE.getString("book.by.id.not.found"), id)));
    }

}