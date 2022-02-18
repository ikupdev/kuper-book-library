package ru.ikupdev.library.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@ToString
public abstract class BaseEntity implements Serializable {
    @ApiModelProperty(notes = "Primary key", required = true, example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
