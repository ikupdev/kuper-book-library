package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ikupdev.library.dto.AuthRequestDto;
import ru.ikupdev.library.dto.JwtAuthDto;
import ru.ikupdev.library.service.IAuthService;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + "/auth")
@Api(value = "Login controller", tags = {"2. Авторизация пользователя"})
public class AuthController {
    private final IAuthService authService;

    @ApiOperation(value = "Авторизация пользователя", response = JwtAuthDto.class)
    @PostMapping("/login")
    public ResponseEntity<JwtAuthDto> login(@Validated @RequestBody AuthRequestDto dto) {
        return ResponseEntity.ok().body(authService.login(dto));
    }

}
