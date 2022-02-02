package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;
    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> user;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", users=" + user +
                '}';
    }

}
