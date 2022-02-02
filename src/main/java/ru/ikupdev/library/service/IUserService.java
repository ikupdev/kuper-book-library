package ru.ikupdev.library.service;

import ru.ikupdev.library.model.User;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
public interface IUserService {
    User register(User user);

    List<User> getAll();

    User findByUsername(String name);

    User findById(Long id);

    void delete(Long id);
}
