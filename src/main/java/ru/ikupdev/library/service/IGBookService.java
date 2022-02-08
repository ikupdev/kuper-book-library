package ru.ikupdev.library.service;

import ru.ikupdev.library.bean.type.GBookKeywords;
import ru.ikupdev.library.model.gbook.GBookResponseDto;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
public interface IGBookService {
    GBookResponseDto getBookVolumes(GBookKeywords keyword, String keyQuery);
}
