package ru.ikupdev.library;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import ru.ikupdev.library.service.impl.UserService;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
@Component
@Getter
public class TestComponents {
    @Value("${server.servlet.context-path}")
    protected String contextPath;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    private UserService userService;
}
