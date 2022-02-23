package ru.ikupdev.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import ru.ikupdev.library.bean.type.Status;

import java.util.*;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;

    private Set<RoleViewDto> roles = new HashSet<>();
    private Status status;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date updated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto user = (UserResponseDto) o;
        return login.equals(user.login) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                email.equals(user.email) &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName, email, status);
    }
}


