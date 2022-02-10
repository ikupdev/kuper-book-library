package ru.ikupdev.library.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.exception.ResourceConflictException;
import ru.ikupdev.library.model.Role;
import ru.ikupdev.library.repository.RoleRepository;
import ru.ikupdev.library.service.IRoleService;
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

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new NotFoundException(String.format(BUNDLE.getString("role.not.found.name"), roleName)));
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
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format(BUNDLE.getString("role.not.found.id"), id)));
    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
