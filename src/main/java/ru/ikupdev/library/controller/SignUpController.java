package ru.ikupdev.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ikupdev.library.dto.JwtAuthDto;
import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.service.ISignUpService;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + "/sign-up")
@Api(value = "Sign up controller", tags = {"1. Регистрация пользователя"})
public class SignUpController {
    private final ISignUpService signUpService;

    @ApiOperation(value = "Регистрация пользователя", response = JwtAuthDto.class)
    @PostMapping("/user")
    public ResponseEntity<UserView> signUp(@ApiParam(value = "Данные пользователя", required = true) @Validated @RequestBody UserRequestDto dto) {
        UserView userView = signUpService.signUp(dto);
        return ResponseEntity.ok(userView);
    }

}
