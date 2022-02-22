package ru.ikupdev.library.service;

import ru.ikupdev.library.dto.RoleRequestDto;
import ru.ikupdev.library.dto.RoleResponseDto;
import ru.ikupdev.library.model.Role;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
public interface IRoleService {
    Role findByRoleName(String roleName);

    Role saveRole(Role role);

    RoleResponseDto saveRoleDto(RoleRequestDto role);

    List<Role> getAll();

    List<RoleResponseDto> getAllRoleResponseDto();

    Role getById(Long id);

    RoleResponseDto getByIdRoleResponseDto(Long id);

    void delete(Long id);

}
