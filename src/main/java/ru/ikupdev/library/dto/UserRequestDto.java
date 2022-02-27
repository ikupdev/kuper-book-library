package ru.ikupdev.library.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author Ilya V. Kupriyanov
 * @version 03.02.2022
 */
@Data
@Builder
@ToString(of = {"login", "firstName", "lastName", "email"})
public class UserRequestDto {
    @NotBlank
    private String login;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
