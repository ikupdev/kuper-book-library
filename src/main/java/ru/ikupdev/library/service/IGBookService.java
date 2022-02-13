package ru.ikupdev.library.service;

import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.model.gbook.GBookResponseDto;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
public interface IGBookService {
    GBookResponseDto getRawBookVolumes(MultiValueMap<String, String> parameters);
}
