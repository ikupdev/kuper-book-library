package ru.ikupdev.library;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MvcResult;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
@Service
public class TestDataConverter {
    @Autowired
    private MappingJackson2HttpMessageConverter messageConverter;

    @SneakyThrows
    public String asJsonString(Object o) {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        messageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

    @SneakyThrows
    public <T> T deserialize(MvcResult result, Class<T> valueType) {
        String content = result.getResponse().getContentAsString();
        return messageConverter.getObjectMapper().readValue(content, valueType);
    }
}
