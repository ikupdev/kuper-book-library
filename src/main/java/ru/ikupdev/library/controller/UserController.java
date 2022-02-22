package ru.ikupdev.library.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.dto.UserUpdateDto;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.service.IUserService;

import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

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

    @ApiOperation(value = "Обновить пользователя")
    @PatchMapping("/{user-id}")
    public ResponseEntity<UserView> updateUser(@ApiParam(value = "id пользователя", required = true, example = "1") @PathVariable("user-id") Long userId,
                                               @ApiParam(value = "Данные пользователя", required = true) @RequestBody UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.update(userId, userUpdateDto));
    }

}
