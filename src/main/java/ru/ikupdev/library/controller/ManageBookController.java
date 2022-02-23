package ru.ikupdev.library.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.BookResponseDto;
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
@Api(value = "Manage book controller", tags = {"8. Api управления книгами"})
public class ManageBookController {
    private final IBookshelfBookService bookshelfBookService;

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
    public RestResponseDto<List<BookResponseDto>> getBookList(
            @ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
            @ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId,
            @ApiIgnore @RequestParam MultiValueMap<String, String> parameters,
            @ApiIgnore @PageableDefault Pageable pageable) {
        parameters.add("userId", userId.toString());
        parameters.add("bookshelfId", bookshelfId.toString());
        return bookshelfBookService.getBookResponseDtoList(parameters, pageable);
    }

    @ApiOperation(value = "Добавить книгу по volumeId")
    @PostMapping(value = "/book/{volume-id}/new", consumes = "*/*")
    public RestResponseDto<BookResponseDto> addBookByVolumeId(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
                                                   @ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId,
                                                   @ApiParam(value = "volume id книги", required = true, example = "mF2GBwAAQBAJ") @PathVariable("volume-id") String volumeId) {
        return bookshelfBookService.addBookToBookshelf(userId, bookshelfId, volumeId);
    }

    @ApiOperation(value = "Получить книгу по id")
    @GetMapping("/book/{book-id}")
    public RestResponseDto<BookResponseDto> getBook(@ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId) {
        return bookshelfBookService.getBookById(bookId);
    }

    @ApiOperation(value = "Обновить книгу")
    @PatchMapping("/book/{book-id}/update")
    public RestResponseDto<BookResponseDto> updateBook(@ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId,
                                           @ApiParam(value = "Данные книги", required = true) @Validated @RequestBody BookUpdateDto bookUpdateDto) {
        return bookshelfBookService.update(bookId, bookUpdateDto);
    }

    @ApiOperation(value = "Удалить книгу из полки")
    @DeleteMapping("/book/{book-id}/delete")
    public ResponseEntity deleteBook(@ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId,
                                             @ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId) {
        bookshelfBookService.deleteFromBookshelf(bookshelfId, bookId);
        return ResponseEntity.ok().build();
    }

}
