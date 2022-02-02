package ru.ikupdev.library.transfer;

import lombok.Data;
import ru.ikupdev.library.bean.type.Status;

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
    private Status state;
//    private Role role;
}
