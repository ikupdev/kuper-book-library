package ru.ikupdev.library.converter.web;

import org.springframework.core.convert.converter.Converter;
import ru.ikupdev.library.bean.type.gbook.KeywordType;

/**
 * @author Ilya V. Kupriyanov
 * @version 12.02.2022
 */

public class StringToKeywordTypeConverter implements Converter<String, KeywordType> {
    @Override
    public KeywordType convert(String source) {
            return KeywordType.valueOf(source.toUpperCase());
    }
}
