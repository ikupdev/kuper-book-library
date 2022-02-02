package ru.ikupdev.library.service;

import ru.ikupdev.library.dto.AuthRequestDto;
import ru.ikupdev.library.dto.JwtAuthDto;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
public interface IAuthService {
    JwtAuthDto login(AuthRequestDto authRequestDto);
}
