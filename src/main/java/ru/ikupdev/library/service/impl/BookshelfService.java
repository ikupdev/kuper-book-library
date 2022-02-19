package ru.ikupdev.library.service.impl;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookshelfRequestDto;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.dto.filter.BookshelfFilter;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.exception.ResourceConflictException;
import ru.ikupdev.library.model.Bookshelf;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.repository.BookshelfRepository;
import ru.ikupdev.library.service.IBookshelfService;
import ru.ikupdev.library.service.IUserService;
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
    private final IUserService userService;
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle(BookshelfService.class.getName());

    @Override
    public RestResponseDto<Bookshelf> addNewBookshelf(Long userId, BookshelfRequestDto dto) {
        if (getBookshelfByBookshelfNameOrElseNull(dto.getBookshelfName()) != null)
            throw new ResourceConflictException(String.format(BUNDLE.getString("bookshelf.exist.name"), dto.getBookshelfName()));
        User user = userService.findById(userId);
        Bookshelf bookshelf = Bookshelf.builder()
                .bookshelfName(dto.getBookshelfName())
                .description(dto.getDescription())
                .created(new Date())
                .updated(new Date())
                .build();
        user.addBookshelf(bookshelf);
        userService.save(user);
        Bookshelf savedBookshelf= findByBookshelfName(bookshelf.getBookshelfName());
        return new RestResponseDto<>(savedBookshelf);
    }

    @Override
    public Bookshelf findByBookshelfName(String bookshelfName) {
        return getBookshelfByBookshelfNameOrElseThrow(bookshelfName);
    }

    @Override
    public RestResponseDto<List<Bookshelf>> getBookshelfList(Long userId, MultiValueMap<String, String> parameters, @PageableDefault Pageable pageable) {
        userService.findById(userId);
        parameters.add("userId", userId.toString());
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
    public RestResponseDto<Bookshelf> getBookshelf(Long bookshelfId) {
        return new RestResponseDto<>(findById(bookshelfId));
    }

    @Override
    public Bookshelf findById(Long bookshelfId) {
        return getBookshelfByIdOrElseThrow(bookshelfId);
    }

    @Override
    public void deleteBookshelf(Long userId, Long bookshelfId) {
        User user = userService.findById(userId);
        Bookshelf bookshelf = findById(bookshelfId);
        user.removeBookshelf(bookshelf);
        userService.save(user);
    }

    @Override
    public Bookshelf updateBookshelf(Long bookshelfId, BookshelfUpdateDto dto) {
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