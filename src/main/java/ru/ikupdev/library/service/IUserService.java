package ru.ikupdev.library.service;

import ru.ikupdev.library.model.User;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.model.UserUpdateDto;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
public interface IUserService {
    UserView save(User user);

    UserView update(Long id, UserUpdateDto userUpdateDto);

    List<User> getAll();

    User findByLogin(String name);

    User findByEmail(String email);

    User findById(Long id);

    void delete(Long id);
}
