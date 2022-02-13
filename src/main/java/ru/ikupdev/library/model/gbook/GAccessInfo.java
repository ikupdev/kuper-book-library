package ru.ikupdev.library.model.gbook;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author Ilya V. Kupriyanov
 * @version 12.02.2022
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GAccessInfo {
    private String country;
    private String webReaderLink;
    private GEpub epub;
    private GPdf pdf;
}
