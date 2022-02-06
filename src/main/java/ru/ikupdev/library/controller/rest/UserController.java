package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.model.to.UserTO;
import ru.ikupdev.library.service.ISignUpService;
import ru.ikupdev.library.service.impl.UserService;

import java.util.List;

import static ru.ikupdev.library.config.AppConstants.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PATH + "/user")
@Api(value = "User controller", tags = {"3. Api пользователей"})
public class UserController {
    private final UserService userService;
    private final ISignUpService signUpService;

    @ApiOperation(value = "Получить список пользователей")
    @GetMapping("/list")
    public RestResponseDto<List<User>> getUsers() {
        return new RestResponseDto<>(userService.getAll());
    }

    @ApiOperation(value = "Получить пользователя по id")
    @GetMapping("/{user-id}")
    public User getUserById(@ApiParam(value = "id пользователя", required = true) @PathVariable("user-id") Long userId) {
        return userService.findById(userId);
    }

    @ApiOperation(value = "Добавить пользователя")
    @PostMapping
    public ResponseEntity<UserView> addUser(@Validated @RequestBody UserRequestDto dto) {
        UserView userView = signUpService.signUp(dto);
        return ResponseEntity.ok(userView);
    }

    @ApiOperation(value = "Обновить пользователя")
    @PatchMapping("/{user-id}")
    public ResponseEntity<UserView> updateUser(@PathVariable("user-id") Long userId,
                                             @RequestBody UserTO userTO) {
        return ResponseEntity.ok(userService.update(userId, userTO));
    }

    @ApiOperation(value = "Удалить пользователя")
    @DeleteMapping("/{user-id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user-id") Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
