package ru.ikupdev.library.service;

import ru.ikupdev.library.model.Role;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
public interface IRoleService {
    Role findByRoleName(String roleName);

    Role saveRole(Role role);

    List<Role> findAll();

    Role findById(Long id);

    void delete(Long id);

}
