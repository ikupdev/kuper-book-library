package ru.ikupdev.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.BookshelfRequestDto;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.exception.ResourceConflictException;
import ru.ikupdev.library.model.Bookshelf;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.service.IBookshelfService;
import ru.ikupdev.library.service.IUserBookshelfService;
import ru.ikupdev.library.service.IUserService;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@Service
@RequiredArgsConstructor
public class UserBookshelfService implements IUserBookshelfService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(UserBookshelfService.class.getName());
    private final IUserService userService;
    private final IBookshelfService bookshelfService;

    @Override
    public RestResponseDto<Bookshelf> addNewBookshelf(Long userId, BookshelfRequestDto dto) {
        if (bookshelfService.getBookshelfByBookshelfNameOrElseNull(dto.getBookshelfName()) != null)
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
        Bookshelf savedBookshelf= bookshelfService.findByBookshelfName(bookshelf.getBookshelfName());
        return new RestResponseDto<>(savedBookshelf);
    }

    @Override
    public RestResponseDto<List<Bookshelf>> getUserBookshelfList(Long userId, MultiValueMap<String, String> parameters, Pageable pageable) {
        userService.findById(userId);
        parameters.add("userId", userId.toString());
        return bookshelfService.findBookshelfs(parameters, pageable);
    }

    @Override
    public void updateBookshelf(Long bookshelfId, BookshelfUpdateDto dto) {
        Bookshelf bookshelf = bookshelfService.findById(bookshelfId);
        if (dto.getBookshelfName() != null) bookshelf.setBookshelfName(dto.getBookshelfName());
        bookshelf.setDescription(dto.getDescription());
        bookshelfService.saveBookshelf(bookshelf);
    }

    @Override
    public void deleteBookshelf(Long userId, Long bookshelfId) {
        User user = userService.findById(userId);
        Bookshelf bookshelf = bookshelfService.findById(bookshelfId);
        user.removeBookshelf(bookshelf);
        userService.save(user);
    }

}
