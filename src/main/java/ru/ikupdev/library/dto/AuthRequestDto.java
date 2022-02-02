package ru.ikupdev.library.dto;

import lombok.Data;

/**
 * @author Ilya V. Kupriyanov
 * @version 19.01.2022
 */
@Data
public class AuthRequestDto {
    private String username;
    private String password;
}
