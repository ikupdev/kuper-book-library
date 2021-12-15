package ru.ikupdev.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ikupdev.library.form.UserForm;
import ru.ikupdev.library.type.Role;
import ru.ikupdev.library.type.State;

import javax.persistence.*;

/**
 * @author Ilya V. Kupriyanov
 * @version 18/04/2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String login;

    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    public static User from(UserForm userForm){
        return User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .build();
    }

}
