package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ikupdev.library.bean.type.gbook.BookSaleType;
import ru.ikupdev.library.bean.type.gbook.BookSortType;
import ru.ikupdev.library.bean.type.gbook.KeywordType;
import ru.ikupdev.library.model.gbook.GBookResponseDto;
import ru.ikupdev.library.service.IGBookService;
import springfox.documentation.annotations.ApiIgnore;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + "/book")
@Api(value = "Login controller", tags = {"5. Api google по книгам"})
public class BookController {
    private final IGBookService gBookService;

    @ApiOperation(value = "Получить список книг по ключевым словам", response = GBookResponseDto.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyword", value = "Фильтр поиска", dataTypeClass = KeywordType.class, defaultValue = "intitle", paramType = "query", required = true),
            @ApiImplicitParam(name = "keyQuery", value = "Ключевые слова", dataTypeClass = String.class, defaultValue = "Rowling", paramType = "query", required = true),
            @ApiImplicitParam(name = "saleType", value = "Тип книг", dataTypeClass = BookSaleType.class, defaultValue = "free_ebooks", paramType = "query"),
            @ApiImplicitParam(name = "sortType", value = "Тип сортировки", dataTypeClass = BookSortType.class, defaultValue = "newest", paramType = "query"),
            @ApiImplicitParam(name = "maxResults", value = "Кол-во элементов в выдаче", dataTypeClass = String.class, defaultValue = "10", paramType = "query"),
            @ApiImplicitParam(name = "startIndex", value = "Начальный элемент в выдаче", dataTypeClass = String.class, defaultValue = "0", paramType = "query")
    })

    @ApiImplicitParam(name = "keyword", value = "INTITLE", dataTypeClass = KeywordType.class, paramType = "query", example = "intitle")
    @GetMapping("/raw/list")
    public GBookResponseDto getBooks(@ApiIgnore @RequestParam MultiValueMap<String, String> parameters) {
        return gBookService.getRawBookVolumes(parameters);
    }

}
