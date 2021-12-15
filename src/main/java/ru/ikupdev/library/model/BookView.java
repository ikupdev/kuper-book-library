package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookView {
    @ApiModelProperty(notes = "Идентификатор", required = true)
    Long id;
    @ApiModelProperty(notes = "Название")
    Long name;

}
