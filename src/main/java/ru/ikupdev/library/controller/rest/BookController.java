package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.BookUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.service.IBookService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PATH + "/book")
@Api(value = "Book controller", tags = {"6. Api по книгам"})
public class BookController {
    private final IBookService bookService;

    @ApiOperation(value = "Получить список книг")
    @GetMapping(value = "/list")
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
            @ApiIgnore @RequestParam MultiValueMap<String, String> parameters,
            @ApiIgnore Pageable pageable) {
        return bookService.findBooks(parameters, pageable);
    }

    @ApiOperation(value = "Получить книгу по id")
    @GetMapping("/{book-id}")
    public Book getBookById(@ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId) {
        return bookService.findById(bookId);
    }

    @ApiOperation(value = "Добавить книгу")
    @PostMapping
    public ResponseEntity<Long> addBook(@ApiParam(value = "Данные книги", required = true) @Validated @RequestBody Book book) {
        bookService.saveBook(book);
        return ResponseEntity.ok(book.getId());
    }

    @ApiOperation(value = "Обновить книгу")
    @PatchMapping("/{book-id}")
    public ResponseEntity<Book> updateBook(@ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId,
                                               @ApiParam(value = "Данные книги", required = true) @Validated @RequestBody BookUpdateDto bookUpdateDto) {
        return ResponseEntity.ok(bookService.update(bookId, bookUpdateDto));
    }

    @ApiOperation(value = "Удалить книгу")
    @DeleteMapping("/{book-id}")
    public ResponseEntity<Object> deleteBook(@ApiParam(value = "id книги", required = true, example = "1") @PathVariable("book-id") Long bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok().build();
    }

}
