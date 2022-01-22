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
public class UserView {
    private String firstName;
    private String lastName;

    public static UserView from(User user) {
        return UserView.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
