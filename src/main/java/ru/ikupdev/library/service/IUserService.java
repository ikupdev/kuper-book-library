package ru.ikupdev.library.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.model.UserView;
import ru.ikupdev.library.dto.UserUpdateDto;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
public interface IUserService {
    UserView saveNew(User user);

    void save(User user);

    UserView update(Long id, UserUpdateDto userUpdateDto);

    RestResponseDto<List<User>> getUsers(MultiValueMap<String, String> parameters, Pageable pageable);

    User findByLoginOrElseNull(String name);

    User findByEmailOrElseNull(String email);

    User findById(Long id);

    void delete(Long id);
}
