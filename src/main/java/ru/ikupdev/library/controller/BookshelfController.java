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
import ru.ikupdev.library.dto.BookshelfRequestDto;
import ru.ikupdev.library.dto.BookshelfUpdateDto;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Bookshelf;
import ru.ikupdev.library.service.IBookshelfService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PATH + "/manage/user/{user-id}")
@Api(value = "Bookshelf controller", tags = {"7. Api управления книжными полками"})
public class BookshelfController {
    private final IBookshelfService bookshelfService;

    @ApiOperation(value = "Получить список книжных полок пользователя")
    @GetMapping("/bookshelf/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "Название книжной полки", dataTypeClass = String.class, paramType = "query", defaultValue = "Название"),
            @ApiImplicitParam(name = "description", value = "Описание книжной полки", dataTypeClass = Status.class, paramType = "query", defaultValue = "Описание"),
            @ApiImplicitParam(name = "size", value = "Количество записей на странице", dataTypeClass = String.class, paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", value = "Сортировка", dataTypeClass = String.class, paramType = "query", defaultValue = "name,ASC")
    })
    public RestResponseDto<List<Bookshelf>> getBookshelfList(
            @ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
            @ApiIgnore @RequestParam MultiValueMap<String, String> parameters,
            @ApiIgnore @PageableDefault Pageable pageable) {
        return bookshelfService.getBookshelfList(userId, parameters, pageable);
    }

    @ApiOperation(value = "Добавить новую книжную полку")
    @PostMapping("/bookshelf/new")
    public RestResponseDto<Bookshelf> addNewBookshelf(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
                                                      @Validated @RequestBody BookshelfRequestDto bookshelfRequestDto) {
        return bookshelfService.addNewBookshelf(userId, bookshelfRequestDto);
    }

    @ApiOperation(value = "Получить книжную полку")
    @GetMapping("/bookshelf/{bookshelf-id}")
    public RestResponseDto<Bookshelf> getBookshelf(
            @ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId) {
        return bookshelfService.getBookshelf(bookshelfId);
    }

    @ApiOperation(value = "Обновить книжную полку")
    @PatchMapping("/bookshelf/{bookshelf-id}/update")
    public RestResponseDto<Bookshelf> updateBookshelf(@ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId,
                                                      @Validated @RequestBody BookshelfUpdateDto bookshelfUpdateDto) {
        Bookshelf bookshelf = bookshelfService.updateBookshelf(bookshelfId, bookshelfUpdateDto);
        return new RestResponseDto<>(bookshelf);
    }

    @ApiOperation(value = "Удалить книжную полку")
    @DeleteMapping("/bookshelf/{bookshelf-id}/delete")
    public ResponseEntity deleteBookshelf(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
                                          @ApiParam(value = "id книжной полки", required = true, example = "1") @PathVariable("bookshelf-id") Long bookshelfId) {
        bookshelfService.deleteBookshelf(userId, bookshelfId);
        return ResponseEntity.ok().build();
    }

}
