package ru.ikupdev.library.form;

import lombok.Data;

/**
 * @author Ilya V. Kupriyanov
 * @version 21/04/2021
 */
@Data
public class UserForm {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
