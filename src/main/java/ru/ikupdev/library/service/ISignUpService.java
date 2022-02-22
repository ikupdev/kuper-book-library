package ru.ikupdev.library.service;

import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.dto.UserViewDto;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
public interface ISignUpService {
    UserViewDto signUp(UserRequestDto dto);
}
