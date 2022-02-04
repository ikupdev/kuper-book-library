package ru.ikupdev.library.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Ilya V. Kupriyanov
 * @version 19.01.2022
 */
@Data
public class AuthRequestDto {
    @NotNull
    private String login;
    @NotNull
    private String password;
}
