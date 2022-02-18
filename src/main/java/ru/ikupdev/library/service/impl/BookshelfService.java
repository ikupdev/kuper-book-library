package ru.ikupdev.library.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.dto.filter.BookshelfFilter;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.model.Bookshelf;
import ru.ikupdev.library.repository.BookshelfRepository;
import ru.ikupdev.library.service.IBookshelfService;
import ru.ikupdev.library.util.OrderUtil;
import ru.ikupdev.library.util.QPredicates;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static ru.ikupdev.library.model.QBookshelf.bookshelf;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@Service
@RequiredArgsConstructor
public class BookshelfService implements IBookshelfService {
    private final BookshelfRepository bookshelfRepository;
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle(BookshelfService.class.getName());

    @Override
    public Bookshelf findByBookshelfName(String bookshelfName) {
        return getBookshelfByBookshelfNameOrElseThrow(bookshelfName);
    }

    @Override
    public Bookshelf saveBookshelf(Bookshelf bookshelf) {
        return bookshelfRepository.save(bookshelf);
    }

    @Override
    public RestResponseDto<List<Bookshelf>> findBookshelfs(MultiValueMap<String, String> parameters, @PageableDefault Pageable pageable) {
        BookshelfFilter filter = BookshelfFilter.builder()
                .bookshelfName(parameters.getFirst("bookshelfName"))
                .description((parameters.getFirst("description")))
                .userId(parameters.getFirst("userId") == null ? null : Long.valueOf(Objects.requireNonNull(parameters.getFirst("userId"))))
                .build();
        Predicate predicate = QPredicates.builder()
                .add(filter.getBookshelfName(), bookshelf.bookshelfName::containsIgnoreCase)
                .add(filter.getDescription(), bookshelf.description::containsIgnoreCase)
                .add(filter.getUserId(), bookshelf.user.id::eq)
                .buildAnd();
        QPageRequest qPageRequest = OrderUtil.bookshelfOrder(pageable);
        Page<Bookshelf> page = predicate == null ? bookshelfRepository.findAll(qPageRequest) : bookshelfRepository.findAll(predicate, qPageRequest);
        return RestResponseDto.fromPage(page);
    }

    @Override
    public Bookshelf findById(Long id) {
        return getBookshelfByIdOrElseThrow(id);
    }

    @Override
    public void delete(Long id) {
        findById(id);
        bookshelfRepository.deleteById(id);
    }

    @Override
    public Bookshelf update(Long bookshelfId, BookshelfUpdateDto dto) {
        Bookshelf bookshelf = getBookshelfByIdOrElseThrow(bookshelfId);
        if (dto.getBookshelfName() != null) bookshelf.setBookshelfName(dto.getBookshelfName());
        bookshelf.setDescription((dto.getDescription()));
        bookshelf.setUpdated(new Date());
        return bookshelfRepository.save(bookshelf);
    }

    public Bookshelf getBookshelfByBookshelfNameOrElseNull(String bookshelfName) {
        return bookshelfRepository.findByBookshelfName(bookshelfName).orElse(null);
    }

    private Bookshelf getBookshelfByIdOrElseThrow(Long id) {
        return bookshelfRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BUNDLE.getString("bookshelf.by.id.not.found"), id)));
    }

    private Bookshelf getBookshelfByBookshelfNameOrElseThrow(String bookshelfName) {
        return bookshelfRepository.findByBookshelfName(bookshelfName).orElseThrow(() ->
                new NotFoundException(String.format(BUNDLE.getString("bookshelf.by.name.not.found"), bookshelfName)));
    }

}