package ru.ikupdev.library.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.dto.*;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.service.ISignUpService;
import ru.ikupdev.library.service.IUserService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static ru.ikupdev.library.config.LibraryConst.ADMIN_PATH;
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(API_V1_PATH + ADMIN_PATH + "/user")
@Api(value = "User controller", tags = {"4. Api пользователей (админский доступ)"})
public class AdminUserController {
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
    public RestResponseDto<List<UserResponseDto>> getUsers(
            @ApiIgnore @RequestParam MultiValueMap<String, String> parameters,
            @ApiIgnore Pageable pageable) {
        return userService.getUsers(parameters, pageable);
    }

    @ApiOperation(value = "Получить пользователя по id")
    @GetMapping("/{user-id}")
    public UserResponseDto getUserById(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId) {
        return userService.getUserResponseDtoById(userId);
    }

    @ApiOperation(value = "Добавить пользователя")
    @PostMapping
    public ResponseEntity<UserViewDto> addUser(@ApiParam(value = "Данные пользователя", required = true) @Validated @RequestBody UserRequestDto dto) {
        UserViewDto userViewDto = signUpService.signUp(dto);
        return ResponseEntity.ok(userViewDto);
    }

    @ApiOperation(value = "Обновить пользователя")
    @PatchMapping("/{user-id}")
    public ResponseEntity<UserViewDto> updateUser(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
                                                  @ApiParam(value = "Данные пользователя", required = true) @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.update(userId, userUpdateDto));
    }

    @ApiOperation(value = "Удалить пользователя")
    @DeleteMapping("/{user-id}")
    public ResponseEntity deleteUser(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }

}
