package ru.ikupdev.library.controller;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.bean.type.gbook.BookSaleType;
import ru.ikupdev.library.bean.type.gbook.BookSortType;
import ru.ikupdev.library.bean.type.gbook.KeywordType;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.model.gbook.GBookResponseDto;
import ru.ikupdev.library.service.IGBookService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + "/gbook")
@Api(value = "Login controller", tags = {"5. Api по книгам google"})
public class GBookController {
    private final IGBookService gBookService;

    @ApiOperation(value = "Получить данные книг по ключевым словам", response = RestResponseDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "Фильтр поиска", dataTypeClass = KeywordType.class, defaultValue = "intitle", paramType = "query", required = true),
            @ApiImplicitParam(name = "keyQuery", value = "Ключевые слова", dataTypeClass = String.class, defaultValue = "Potter", paramType = "query", required = true),
            @ApiImplicitParam(name = "saleType", value = "Тип книг", dataTypeClass = BookSaleType.class, defaultValue = "free_ebooks", paramType = "query"),
            @ApiImplicitParam(name = "sortType", value = "Тип сортировки", dataTypeClass = BookSortType.class, defaultValue = "newest", paramType = "query"),
            @ApiImplicitParam(name = "maxResults", value = "Кол-во элементов в выдаче", dataTypeClass = String.class, defaultValue = "10", paramType = "query"),
            @ApiImplicitParam(name = "startIndex", value = "Начальный элемент в выдаче", dataTypeClass = String.class, defaultValue = "0", paramType = "query")
    })
    @GetMapping("/list")
    public RestResponseDto<List<Book>> getBooks(@ApiIgnore @RequestParam MultiValueMap<String, String> parameters) {
        return gBookService.getBookVolumes(parameters);
    }

    @GetMapping("/volumeId/{volume-id}")
    @ApiOperation(value = "Получить данные книги по volumeId", response = RestResponseDto.class)
    public RestResponseDto<Book> getBookByVolumeId(@ApiParam(value = "volume id книги в Google Book API", required = true, example = "mF2GBwAAQBAJ") @PathVariable("volume-id") String volumeId) {
        return gBookService.getBookByVolumeId(volumeId);
    }

}
