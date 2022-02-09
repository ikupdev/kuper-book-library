package ru.ikupdev.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
@Data
@AllArgsConstructor
public class JwtAuthDto {
    @NotBlank
    private String login;
    @NotBlank
    private String token;
}
