package ru.ikupdev.library.service;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.dto.UserResponseDto;
import ru.ikupdev.library.model.User;
import ru.ikupdev.library.dto.UserViewDto;
import ru.ikupdev.library.dto.UserUpdateDto;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
public interface IUserService {
    UserViewDto saveNew(User user);

    void save(User user);

    UserViewDto update(Long id, UserUpdateDto userUpdateDto);

    RestResponseDto<List<UserResponseDto>> getUsers(MultiValueMap<String, String> parameters, Pageable pageable);

    User findByLoginOrElseNull(String name);

    User findByEmailOrElseNull(String email);

    User getUserById(Long id);

    UserResponseDto getUserResponseDtoById(Long id);

    void delete(Long id);
}
