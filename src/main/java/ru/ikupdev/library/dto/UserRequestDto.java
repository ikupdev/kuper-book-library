package ru.ikupdev.library.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author Ilya V. Kupriyanov
 * @version 03.02.2022
 */
@Data
@ToString(of = {"login", "firstName", "lastName", "email"})
public class UserRequestDto {
    @NotNull
    private String login;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
