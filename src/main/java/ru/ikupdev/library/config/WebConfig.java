package ru.ikupdev.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.ikupdev.library.converter.web.StringToBookSaleTypeEnumConverter;
import ru.ikupdev.library.converter.web.StringToKeywordTypeConverter;

/**
 * @author Ilya V. Kupriyanov
 * @version 12.02.2022
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToKeywordTypeConverter());
        registry.addConverter(new StringToBookSaleTypeEnumConverter());
        registry.addConverter(new StringToBookSaleTypeEnumConverter());
    }
}
