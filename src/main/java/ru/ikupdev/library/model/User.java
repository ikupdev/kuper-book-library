package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"login", "firstName", "lastName", "email", "role"})
@Entity
@Table(name = "user")
public class User extends BaseEntity {
    @NotNull
    @Column(name = "login")
    private String login;
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @NotNull
    @Column(name = "hash_password")
    private String hashPassword;
    @Singular("role")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns =
            {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> role;
}
