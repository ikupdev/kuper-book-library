package ru.ikupdev.library.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@MappedSuperclass
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {
    @ApiModelProperty(notes = "Primary key", required = true, example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
