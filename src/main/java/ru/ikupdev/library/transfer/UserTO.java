package ru.ikupdev.library.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.ikupdev.library.model.User;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
@Data
@AllArgsConstructor
@Builder
public class UserTO {
    private String firstName;
    private String lastName;

    public static UserTO from(User user) {
        return UserTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
