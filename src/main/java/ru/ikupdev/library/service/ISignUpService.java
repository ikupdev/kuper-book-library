package ru.ikupdev.library.service;

import ru.ikupdev.library.dto.UserRequestDto;
import ru.ikupdev.library.model.UserView;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
public interface ISignUpService {
    UserView signUp(UserRequestDto dto);
}
