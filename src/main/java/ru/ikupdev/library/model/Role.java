package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */

@EqualsAndHashCode
@Data
@ToString(of = {"id", "name"})
@Entity
@Table(name = "role")
public class Role {
    @ApiModelProperty(notes = "Primary key", required = true, example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ApiModelProperty(notes = "name", required = true, example = "name")
    @NotNull
    @Column(name = "name")
    private String name;
    @JsonIgnore
//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<User> user;
}
