package ru.ikupdev.library.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.ikupdev.library.bean.type.Status;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author Ilya V. Kupriyanov
 * @version 09.02.2022
 */
@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
public class StatusedDatedEntity extends DatedEntity {
        @NotNull
        @ApiModelProperty(notes = "Статус", required = true, example = "ACTIVE")
        @Enumerated(value = EnumType.STRING)
        @Column(name = "status")
        private Status status;
}
