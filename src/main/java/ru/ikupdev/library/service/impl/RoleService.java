package ru.ikupdev.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.dto.RoleRequestDto;
import ru.ikupdev.library.dto.RoleResponseDto;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.exception.ResourceConflictException;
import ru.ikupdev.library.model.Role;
import ru.ikupdev.library.repository.RoleRepository;
import ru.ikupdev.library.service.IRoleService;
import ru.ikupdev.library.util.MapperUtil;

import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.02.2022
 */
@Service
@Slf4j
@AllArgsConstructor
public class RoleService implements IRoleService {
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(RoleService.class.getName());
    private final RoleRepository roleRepository;
    private final MapperUtil mapperUtil;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new NotFoundException(String.format(BUNDLE.getString("role.not.found.name"), roleName)));
    }

    @Override
    public RoleResponseDto saveRoleDto(RoleRequestDto roleRequestDto) {
        Role role = mapperUtil.convertRoleRequestDtoToRole(roleRequestDto);
        Role saved = saveRole(role);
        return mapperUtil.convertRoleToRoleResponseDto(saved);
    }

    @Override
    public Role saveRole(Role role) {
        if (roleRepository.findByName(role.getName()).isPresent())
            throw new ResourceConflictException(String.format(BUNDLE.getString("role.exist.name"), role.getName()));
        Role saved = roleRepository.save(role);
        log.info(BUNDLE.getString("role.log.saved"), role);
        return saved;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<RoleResponseDto> getAllRoleResponseDto() {
        return MapperUtil.convertList(getAll(), mapperUtil::convertRoleToRoleResponseDto);
    }

    @Override
    public Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format(BUNDLE.getString("role.not.found.id"), id)));
    }

    @Override
    public RoleResponseDto getByIdRoleResponseDto(Long id) {
        return mapperUtil.convertRoleToRoleResponseDto(getById(id));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
