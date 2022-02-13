package ru.ikupdev.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.bean.type.gbook.BookSaleType;
import ru.ikupdev.library.bean.type.gbook.KeywordType;
import ru.ikupdev.library.feign.GoogleBooksApiClient;
import ru.ikupdev.library.model.gbook.GBookParams;
import ru.ikupdev.library.model.gbook.GBookResponseDto;
import ru.ikupdev.library.service.IGBookService;

import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Service
@RequiredArgsConstructor
public class GBookService implements IGBookService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(GBookService.class.getName());

    @Value("${google.api.key}")
    private String gApiKey;

    private final GoogleBooksApiClient booksApiClient;

    @Override
    public GBookResponseDto getRawBookVolumes(MultiValueMap<String, String> parameters) {
        String keyword = parameters.getFirst("keyword") == null ?
                null : KeywordType.getParamName(KeywordType.valueOf(parameters.getFirst("keyword").toUpperCase()));
        String keyQuery = parameters.getFirst("keyQuery");
        if (keyword == null || keyQuery == null)
            throw new IllegalArgumentException(BUNDLE.getString("keyword.keyQuery.empty"));
        GBookParams params = GBookParams.builder()
                .q(keyword + ":" + keyQuery)
                .key(gApiKey)
                .filter(parameters.getFirst("saleType") == null ?
                        null : BookSaleType.getParamName(BookSaleType.valueOf(parameters.getFirst("saleType").toUpperCase())))
                .orderBy(parameters.getFirst("sortType"))
                .maxResults(parameters.getFirst("maxResults"))
                .startIndex(parameters.getFirst("startIndex"))
                .build();
        GBookResponseDto bookVolumes = booksApiClient.getBookVolumes(params);
        return bookVolumes;
    }

}
