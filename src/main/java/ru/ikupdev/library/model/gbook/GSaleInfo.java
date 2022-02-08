package ru.ikupdev.library.model.gbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GSaleInfo {
    private String country;
    private String saleability;
    private Boolean isEbook;
}
