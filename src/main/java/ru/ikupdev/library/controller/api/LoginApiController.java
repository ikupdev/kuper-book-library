package ru.ikupdev.library.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ikupdev.library.dto.TokenDto;
import ru.ikupdev.library.form.LoginForm;
import ru.ikupdev.library.service.ILoginService;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@RequestMapping("/api")
public class LoginApiController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok().body(loginService.login(loginForm));
    }
}
