package ru.ikupdev.library.transfer;

import lombok.Data;
import ru.ikupdev.library.type.Role;
import ru.ikupdev.library.type.State;

/**
 * @author Ilya V. Kupriyanov
 * @version 04.01.2022
 */
@Data
public class UserTO {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private State state;
    private Role role;
}
