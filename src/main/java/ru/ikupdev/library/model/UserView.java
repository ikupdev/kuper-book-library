package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView {
    @ApiModelProperty(notes = "Идентификатор", required = true, example = "1")
    Long id;
    @ApiModelProperty(notes = "Название", example = "login")
    String login;
}
