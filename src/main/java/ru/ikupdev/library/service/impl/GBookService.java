package ru.ikupdev.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.bean.type.GBookKeywords;
import ru.ikupdev.library.feign.GoogleBooksApiClient;
import ru.ikupdev.library.model.gbook.GBookParams;
import ru.ikupdev.library.model.gbook.GBookResponseDto;
import ru.ikupdev.library.service.IGBookService;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Service
@RequiredArgsConstructor
public class GBookService implements IGBookService {

    @Value("${google.api.key}")
    private String gApiKey; //todo make as class!

    private final GoogleBooksApiClient booksApiClient;

    @Override
    public GBookResponseDto getBookVolumes(GBookKeywords keyword, String keyQuery) {
        GBookParams params = new GBookParams(keyword + ":" + keyQuery, gApiKey);
        return booksApiClient.getBookVolumes(params);
    }

}
