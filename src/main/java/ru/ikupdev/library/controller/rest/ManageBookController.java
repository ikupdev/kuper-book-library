package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.BookUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.service.IBookshelfBookService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PATH + "/manage/user/{user-id}/bookshelf/{bookshelf-id}")
@Api(value = "Bookshelf-book controller", tags = {"7. Api по книгам в рамках книжной полки"})
public class ManageBookController {
    private final IBookshelfBookService bookshelfBookService;

    @ApiOperation(value = "Добавить книгу по volumeId")
    @GetMapping("/book/{volume-id}/new")
    public RestResponseDto<Book> addBookByVolumeId(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
                                                   @ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId,
                                                   @ApiParam(value = "volume id книги", required = true, example = "mF2GBwAAQBAJ") @PathVariable("volume-id") String volumeId) {
        return bookshelfBookService.addBookToBookshelf(userId, bookshelfId, volumeId);
    }

    @ApiOperation(value = "Получить список книг")
    @GetMapping(value = "/book/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "Название", dataTypeClass = String.class, paramType = "query", defaultValue = "Potter"),
            @ApiImplicitParam(name = "subtitle", value = "Подзаголовок", dataTypeClass = String.class, paramType = "query", defaultValue = "Potter"),
            @ApiImplicitParam(name = "description", value = "Описание", dataTypeClass = Status.class, paramType = "query", defaultValue = "Potter"),
            @ApiImplicitParam(name = "language", value = "Язык", dataTypeClass = String.class, paramType = "query", defaultValue = "en"),
            @ApiImplicitParam(name = "authors", value = "Автор", dataTypeClass = String.class, paramType = "query", defaultValue = "Rowling"),
            @ApiImplicitParam(name = "size", value = "Количество записей на странице", dataTypeClass = String.class, paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", value = "Сортировка", dataTypeClass = String.class, paramType = "query", defaultValue = "title,ASC")
    })
    public RestResponseDto<List<Book>> getBooks(
            @ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
            @ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId,
            @ApiIgnore @RequestParam MultiValueMap<String, String> parameters,
            @ApiIgnore @PageableDefault Pageable pageable) {
        parameters.add("userId", userId.toString());
        parameters.add("bookshelfId", bookshelfId.toString());
        return bookshelfBookService.findBooks(parameters, pageable);
    }

    @ApiOperation(value = "Получить книгу по id")
    @GetMapping("/book/{book-id}")
    public RestResponseDto<Book> getBookById(@ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId) {
        return bookshelfBookService.getBookById(bookId);
    }

    @ApiOperation(value = "Обновить книгу")
    @PatchMapping("/book/{book-id}/update")
    public ResponseEntity<Book> updateBook(@ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId,
                                           @ApiParam(value = "Данные книги", required = true) @Validated @RequestBody BookUpdateDto bookUpdateDto) {
        return ResponseEntity.ok(bookshelfBookService.update(bookId, bookUpdateDto));
    }

    @ApiOperation(value = "Удалить книгу из полки")
    @DeleteMapping("/book/{book-id}/delete")
    public ResponseEntity<Object> deleteBook(@ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId,
                                             @ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId) {
        bookshelfBookService.delete(bookshelfId, bookId);
        return ResponseEntity.ok().build();
    }

}
