package ru.ikupdev.library.converter;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * @author Ilya V. Kupriyanov
 * @version 31.01.2022
 */
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDate attribute) {
        return attribute != null ? Timestamp.valueOf(attribute.atStartOfDay()) : null;
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp dbData) {
        return dbData != null ? dbData.toLocalDateTime().toLocalDate() : null;
    }

}