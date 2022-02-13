package ru.ikupdev.library.converter;

import org.springframework.stereotype.Component;
import ru.ikupdev.library.model.Book;
import ru.ikupdev.library.model.gbook.GBookResponseDto;

/**
 * @author Ilya V. Kupriyanov
 * @version 12.02.2022
 */
@Component
public class GBookToBookConverter {

    public Book convertGBookToBook(GBookResponseDto dto) {
        return null;
    }

}
