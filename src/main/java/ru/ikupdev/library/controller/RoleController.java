package ru.ikupdev.library.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.dto.RoleRequestDto;
import ru.ikupdev.library.dto.RoleResponseDto;
import ru.ikupdev.library.service.IRoleService;

import java.util.List;

import static ru.ikupdev.library.config.LibraryConst.ADMIN_PATH;
import static ru.ikupdev.library.config.LibraryConst.API_V1_PATH;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.02.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping(API_V1_PATH + ADMIN_PATH + "/role")
@Api(value = "Role controller", tags = {"5. Api ролей пользователей"})
public class RoleController {
    private final IRoleService roleService;

    @ApiOperation(value = "Получить все роли", response = RestResponseDto.class)
    @GetMapping("/list")
    public RestResponseDto<List<RoleResponseDto>> findAllRoles() {
        List<RoleResponseDto> allRoles = roleService.getAllRoleResponseDto();
        return new RestResponseDto<>(allRoles);
    }

    @ApiOperation(value = "Получить роль по id", response = RestResponseDto.class)
    @GetMapping("/{id}")
    public RestResponseDto<RoleResponseDto> findRoleById(@ApiParam(value = "id роли", required = true, example = "1") @PathVariable("id") Long id) {
        return new RestResponseDto<>(roleService.getByIdRoleResponseDto(id));
    }

    @ApiOperation(value = "Добавить роль", response = RestResponseDto.class)
    @PostMapping
    public RestResponseDto<RoleResponseDto> addRole(@ApiParam(value = "Данные роли", required = true) @Validated @RequestBody RoleRequestDto roleRequestDto) {
        return new RestResponseDto<>(roleService.saveRoleDto(roleRequestDto));
    }

    @ApiOperation(value = "Удалить роль")
    @DeleteMapping("/{id}")
    public void deleteRole(@ApiParam(value = "id роли", required = true, example = "1") @PathVariable("id") Long id) {
        roleService.delete(id);
    }

}
