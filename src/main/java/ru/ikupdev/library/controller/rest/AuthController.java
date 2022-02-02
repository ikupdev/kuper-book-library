package ru.ikupdev.library.controller.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ikupdev.library.dto.AuthRequestDto;
import ru.ikupdev.library.dto.JwtAuthDto;
import ru.ikupdev.library.service.IAuthService;

import static ru.ikupdev.library.config.AppConstants.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + "/auth")
public class AuthController {
    private final IAuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok().body(authService.login(authRequestDto));
    }

}
