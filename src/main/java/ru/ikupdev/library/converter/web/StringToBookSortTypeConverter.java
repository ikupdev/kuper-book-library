package ru.ikupdev.library.converter.web;

import org.springframework.core.convert.converter.Converter;
import ru.ikupdev.library.bean.type.gbook.BookSortType;
import ru.ikupdev.library.bean.type.gbook.KeywordType;

/**
 * @author Ilya V. Kupriyanov
 * @version 12.02.2022
 */

public class StringToBookSortTypeConverter implements Converter<String, BookSortType> {
    @Override
    public BookSortType convert(String source) {
            return BookSortType.valueOf(source.toUpperCase());
    }
}
