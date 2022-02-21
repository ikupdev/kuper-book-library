package ru.ikupdev.library.service;

import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.Book;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
public interface IGBookService {
    RestResponseDto<List<Book>> getBookVolumes(MultiValueMap<String, String> parameters);

    RestResponseDto<Book> getBookByVolumeId(String volumeId);
}
