package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank
    @Column(name = "login")
    private String login;
    @NotBlank
    @Column(name = "first_name")
    private String firstName;
    @NotBlank
    @Column(name = "last_name")
    private String lastName;
    @NotBlank
    @Column(name = "email")
    private String email;
    @JsonIgnore
    @NotBlank
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
