package ru.ikupdev.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.exception.ResourceConflictException;
import ru.ikupdev.library.model.Role;
import ru.ikupdev.library.repository.RoleRepository;
import ru.ikupdev.library.service.IRoleService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.02.2022
 */
@Service
@Slf4j
@AllArgsConstructor
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new NotFoundException("Role with name " + roleName + " not found!"));
    }

    @Override
    public Role saveRole(@Validated Role role) {
        if (roleRepository.findByName(role.getName()).isPresent())
            throw new ResourceConflictException("Role with name " + role.getName() + "already exist!");
        Role saved = roleRepository.save(role);
        log.info("Role {} successfully registered", role);
        return saved;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Role with id " + id + " not found!"));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
