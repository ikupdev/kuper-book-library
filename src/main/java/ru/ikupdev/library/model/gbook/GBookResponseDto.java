package ru.ikupdev.library.model.gbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GBookResponseDto {
    private String kind;
    private Long totalItems;
    private List<GBookItem> items;
    private GError error;
}
