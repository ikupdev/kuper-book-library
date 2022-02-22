package ru.ikupdev.library.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.UserViewDto;
import ru.ikupdev.library.model.User;

import java.util.Date;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
@SpringBootTest
@AutoConfigureMockMvc
class MapperUtilTest {
    @Autowired
    private MapperUtil mapperUtil;

    @Test
    void convertToUserViewDto() {
        User user = User.builder()
                .id(1L)
                .login("Kuper")
                .firstName("Ilya")
                .lastName("Kupriyanov")
                .status(Status.ACTIVE)
                .created(new Date())
                .updated(new Date())
                .build();
        UserViewDto userViewDto = mapperUtil.convertUserToUserViewDto(user);
        Assert.assertEquals(Long.valueOf(1), userViewDto.getId());
        Assert.assertEquals("Kuper", userViewDto.getLogin());
    }
}