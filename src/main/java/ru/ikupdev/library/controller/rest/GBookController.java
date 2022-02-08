package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ikupdev.library.bean.type.GBookKeywords;
import ru.ikupdev.library.model.gbook.GBookResponseDto;
import ru.ikupdev.library.service.IGBookService;

import static ru.ikupdev.library.config.AppConstants.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + "/gbook")
@Api(value = "Login controller", tags = {"5. Api google по книгам"})
public class GBookController {
    private final IGBookService gBookService;

    @ApiOperation(value = "Получить список книг по ключевым словам", response = GBookResponseDto.class)
    @ApiImplicitParam(name = "keyword", value = "intitle", dataTypeClass = GBookKeywords.class, paramType = "query", example = "intitle")
    @GetMapping("/list")
    public GBookResponseDto getBooks(@ApiParam(value = "Фильтр поиска", required = true) @RequestParam(value = "keyword", defaultValue = "intitle") GBookKeywords keyword,
                                     @ApiParam(value = "Ключевые слова", required = true) @RequestParam(value = "key_query") String keyQuery) {
        return gBookService.getBookVolumes(keyword, keyQuery);
    }

}
