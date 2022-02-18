package ru.ikupdev.library.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@Data
@ToString
public class BookshelfRequestDto {
    @ApiModelProperty(notes = "Наименование книжной полки", required = true)
    @NotBlank
    @Size(max = 512)
    private String bookshelfName;
    @ApiModelProperty(notes = "Описание книжной полки")
    @Size(max = 2048)
    private String description;
}
