package ru.ikupdev.library.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
@Api(value = "Book controller", tags = {"1. Книги"})
public class BookController {

//    @ApiOperation(value = "получить отклонение", response = ItemView.class)
//    @GetMapping(value = "/deviation/{id}")
//    public ResponseEntity<ItemView> getDeviationById(@ApiParam(value = "id книги", example = "1", required = true) @PathVariable(value = "id") Long id) {
//        return ok().body(new ItemView());
//    }

}
