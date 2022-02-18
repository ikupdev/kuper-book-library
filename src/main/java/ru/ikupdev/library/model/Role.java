package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@Entity
@Table(name = "role")
public class Role extends BaseEntity {
    @ApiModelProperty(notes = "Наименование", required = true, example = "name")
    @NotBlank
    @Column(name = "name")
    private String name;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}
