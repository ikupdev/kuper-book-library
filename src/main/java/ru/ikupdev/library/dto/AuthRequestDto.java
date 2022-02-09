package ru.ikupdev.library.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Ilya V. Kupriyanov
 * @version 19.01.2022
 */
@Data
public class AuthRequestDto {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
}
