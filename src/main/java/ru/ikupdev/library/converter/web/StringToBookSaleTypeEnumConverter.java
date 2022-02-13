package ru.ikupdev.library.converter.web;

import org.springframework.core.convert.converter.Converter;
import ru.ikupdev.library.bean.type.gbook.BookSaleType;

/**
 * @author Ilya V. Kupriyanov
 * @version 12.02.2022
 */

public class StringToBookSaleTypeEnumConverter implements Converter<String, BookSaleType> {
    @Override
    public BookSaleType convert(String source) {
            return BookSaleType.valueOf(source.toUpperCase());
    }
}
