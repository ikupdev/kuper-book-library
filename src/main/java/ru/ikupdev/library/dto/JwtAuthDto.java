package ru.ikupdev.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ilya V. Kupriyanov
 * @version 30.01.2022
 */
@Data
@AllArgsConstructor
public class JwtAuthDto {
    private String login;
    private String token;
}
