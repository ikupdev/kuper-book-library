package ru.ikupdev.library.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ikupdev.library.model.gbook.GBookParams;
import ru.ikupdev.library.model.gbook.GBookResponseDto;

/**
 * @author Ilya V. Kupriyanov
 * @version 27.10.2021
 */
@FeignClient(name = "gBookClient", url = "${google.api.books.url}")
public interface GoogleBooksApiClient {
    @GetMapping("/volumes")
    GBookResponseDto getBookVolumes(@SpringQueryMap GBookParams params);
}
