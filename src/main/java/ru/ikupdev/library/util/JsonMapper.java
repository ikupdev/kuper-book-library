package ru.ikupdev.library.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class JsonMapper {
    private final ObjectMapper mapper;

    
    public <T> T readObject(String json, Class<T> type) throws RuntimeException {
        if (!StringUtils.hasText(json)) {
            throw new RuntimeException("JSON объект отсутствует!");
        }
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения JSON объекта.", e);
        }
    }

    
    public <T> List<T> readList(String json, Class<T> type) throws RuntimeException {
        if (!StringUtils.hasText(json)) {
            throw new RuntimeException("JSON объект отсутствует!");
        }
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(ArrayList.class, type));
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения JSON объекта.", e);
        }
    }

    
    public JsonNode readNode(String json) throws RuntimeException {
        if (!StringUtils.hasText(json)) {
            throw new RuntimeException("JSON объект отсутствует!");
        }
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения JSON объекта.", e);
        }
    }

    
    public String writeObject(Object object) throws RuntimeException {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сериализации JSON объекта.", e);
        }
    }

    
    public <T> T copyObject(Object object, Class<T> type) throws RuntimeException {
        return readObject(writeObject(object), type);
    }

    
    public <T> T updateObject( T valueToUpdate, Object overrides) throws RuntimeException {
        try {
            return mapper.updateValue(valueToUpdate, overrides);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка обновления JSON объекта.", e);
        }
    }
}
