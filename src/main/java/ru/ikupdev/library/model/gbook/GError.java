package ru.ikupdev.library.model.gbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author Ilya V. Kupriyanov
 * @version 08.02.2022
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GError {
    private Integer code;
    private String message;
    private List<String> errors;
}
