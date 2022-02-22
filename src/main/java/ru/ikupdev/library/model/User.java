package ru.ikupdev.library.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.ikupdev.library.bean.type.Status;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;


/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"login", "firstName", "lastName", "email"})
@Entity
@Table(name = "user")
public class User extends DatedEntity {
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
    @Email
    @Column(name = "email")
    private String email;
    @NotBlank
    @Column(name = "hash_password")
    private String hashPassword;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "user_role", joinColumns =
            {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();
    @NotNull
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bookshelf> bookshelves = new ArrayList<>();

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }

    public void addBookshelf(Bookshelf bookshelf) {
        bookshelves.add(bookshelf);
        bookshelf.setUser(this);
    }

    public void removeBookshelf(Bookshelf bookshelf) {
        bookshelves.remove(bookshelf);
        bookshelf.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                email.equals(user.email) &&
                Objects.equals(hashPassword, user.hashPassword) &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName, email, status);
    }
}
