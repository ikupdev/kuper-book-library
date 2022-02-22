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
import org.springframework.validation.annotation.Validated;
import ru.ikupdev.library.dto.*;
import ru.ikupdev.library.dto.filter.BookshelfFilter;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.exception.ResourceConflictException;
import ru.ikupdev.library.model.Bookshelf;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.repository.BookshelfRepository;
import ru.ikupdev.library.service.IBookshelfService;
import ru.ikupdev.library.service.IUserService;
import ru.ikupdev.library.util.MapperUtil;
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
    private final MapperUtil mapperUtil;
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle(BookshelfService.class.getName());

    @Override
    public RestResponseDto<BookshelfResponseDto> addNewBookshelf(Long userId, BookshelfRequestDto dto) {
        if (getBookshelfByNameOrElseNull(dto.getName()) != null)
            throw new ResourceConflictException(String.format(BUNDLE.getString("bookshelf.exist.name"), dto.getName()));
        User user = userService.getUserById(userId);
        Bookshelf bookshelf = Bookshelf.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .created(new Date())
                .updated(new Date())
                .build();
        user.addBookshelf(bookshelf);
        userService.save(user);
        Bookshelf savedBookshelf= findByName(bookshelf.getName());
        return new RestResponseDto<>(mapperUtil.convertBookshelfToBookshelfResponseDto(savedBookshelf));
    }

    @Override
    public void saveBookshelf(@Validated Bookshelf bookshelf) {
        bookshelfRepository.save(bookshelf);
    }

    @Override
    public Bookshelf findByName(String name) {
        return getBookshelfByNameOrElseThrow(name);
    }

    @Override
    public RestResponseDto<List<BookshelfResponseDto>> getBookshelfList(Long userId, MultiValueMap<String, String> parameters, @PageableDefault Pageable pageable) {
        userService.getUserById(userId);
        parameters.add("userId", userId.toString());
        BookshelfFilter filter = BookshelfFilter.builder()
                .name(parameters.getFirst("name"))
                .description((parameters.getFirst("description")))
                .userId(parameters.getFirst("userId") == null ? null : Long.valueOf(Objects.requireNonNull(parameters.getFirst("userId"))))
                .build();
        Predicate predicate = QPredicates.builder()
                .add(filter.getName(), bookshelf.name::containsIgnoreCase)
                .add(filter.getDescription(), bookshelf.description::containsIgnoreCase)
                .add(filter.getUserId(), bookshelf.user.id::eq)
                .buildAnd();
        QPageRequest qPageRequest = OrderUtil.bookshelfOrder(pageable);
        Page<Bookshelf> bookshelfPage = predicate == null ? bookshelfRepository.findAll(qPageRequest) : bookshelfRepository.findAll(predicate, qPageRequest);
        List<BookshelfResponseDto> bookshelfResponseDtoList = MapperUtil.convertList(bookshelfPage.getContent(), mapperUtil::convertBookshelfToBookshelfResponseDto);
        Page<BookshelfResponseDto> userResponseDtoPage = new PageImpl<>(bookshelfResponseDtoList, bookshelfPage.getPageable(), bookshelfPage.getTotalElements());
        return RestResponseDto.fromPage(userResponseDtoPage);
    }

    @Override
    public Bookshelf getById(Long bookshelfId) {
        return getBookshelfByIdOrElseThrow(bookshelfId);
    }

    @Override
    public RestResponseDto<BookshelfResponseDto> getByIdBookshelfResponseDto(Long id) {
        return new RestResponseDto<>(mapperUtil.convertBookshelfToBookshelfResponseDto(getById(id)));
    }

    @Override
    public void deleteBookshelf(Long userId, Long bookshelfId) {
        User user = userService.getUserById(userId);
        Bookshelf bookshelf = getById(bookshelfId);
        user.removeBookshelf(bookshelf);
        userService.save(user);
    }

    @Override
    public RestResponseDto<BookshelfResponseDto> updateBookshelf(Long bookshelfId, BookshelfUpdateDto dto) {
        Bookshelf bookshelf = getBookshelfByIdOrElseThrow(bookshelfId);
        if (dto.getName() != null) bookshelf.setName(dto.getName());
        bookshelf.setDescription((dto.getDescription()));
        bookshelf.setUpdated(new Date());
        return new RestResponseDto<>(mapperUtil.convertBookshelfToBookshelfResponseDto(bookshelfRepository.save(bookshelf)));
    }

    public Bookshelf getBookshelfByNameOrElseNull(String name) {
        return bookshelfRepository.findByName(name).orElse(null);
    }

    private Bookshelf getBookshelfByIdOrElseThrow(Long id) {
        return bookshelfRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BUNDLE.getString("bookshelf.by.id.not.found"), id)));
    }

    private Bookshelf getBookshelfByNameOrElseThrow(String name) {
        return bookshelfRepository.findByName(name).orElseThrow(() ->
                new NotFoundException(String.format(BUNDLE.getString("bookshelf.by.name.not.found"), name)));
    }

}