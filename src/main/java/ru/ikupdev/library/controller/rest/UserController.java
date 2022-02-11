package ru.ikupdev.library.controller.rest;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.dto.UserUpdateDto;
import ru.ikupdev.library.service.ISignUpService;
import ru.ikupdev.library.service.IUserService;
import springfox.documentation.annotations.ApiIgnore;

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
    private final IUserService userService;
    private final ISignUpService signUpService;

    @ApiOperation(value = "Получить список пользователей")
    @GetMapping(value = "/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "firstName", value = "Имя", dataTypeClass = String.class, paramType = "query", defaultValue = "Ivan"),
            @ApiImplicitParam(name = "lastName", value = "Фамилия", dataTypeClass = String.class, paramType = "query", defaultValue = "Ivanov"),
            @ApiImplicitParam(name = "status", value = "Статус", dataTypeClass = Status.class, paramType = "query", defaultValue = "ACTIVE"),
            @ApiImplicitParam(name = "page", value = "Номер страницы", dataTypeClass = String.class, paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "size", value = "Количество записей на странице", dataTypeClass = String.class, paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(name = "sort", value = "Сортировка", dataTypeClass = String.class, paramType = "query", defaultValue = "lastName,ASC")
    })
    public RestResponseDto<List<User>> getUsers(
            @ApiIgnore @RequestParam MultiValueMap<String, String> parameters,
            @ApiIgnore Pageable pageable) {
        return userService.getUsers(parameters, pageable);
    }

    @ApiOperation(value = "Получить пользователя по id")
    @GetMapping("/{user-id}")
    public User getUserById(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId) {
        return userService.findById(userId);
    }

    @ApiOperation(value = "Добавить пользователя")
    @PostMapping
    public ResponseEntity<UserView> addUser(@ApiParam(value = "Данные пользователя", required = true) @Validated @RequestBody UserRequestDto dto) {
        UserView userView = signUpService.signUp(dto);
        return ResponseEntity.ok(userView);
    }

    @ApiOperation(value = "Обновить пользователя")
    @PatchMapping("/{user-id}")
    public ResponseEntity<UserView> updateUser(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
                                               @ApiParam(value = "Данные пользователя", required = true) @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.update(userId, userUpdateDto));
    }

    @ApiOperation(value = "Удалить пользователя")
    @DeleteMapping("/{user-id}")
    public ResponseEntity<Object> deleteUser(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
