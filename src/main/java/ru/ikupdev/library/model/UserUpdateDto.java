package ru.ikupdev.library.model;

import lombok.Data;
import lombok.ToString;
import ru.ikupdev.library.model.Role;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Data
@ToString(of = {"login", "firstName", "lastName", "email", "role", "status"})
public class UserUpdateDto {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Role> role;
    private String status;
}
