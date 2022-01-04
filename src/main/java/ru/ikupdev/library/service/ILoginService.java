package ru.ikupdev.library.service;

import ru.ikupdev.library.dto.TokenDto;
import ru.ikupdev.library.form.LoginForm;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.01.2022
 */
public interface ILoginService {
    TokenDto login(LoginForm loginForm);
}
