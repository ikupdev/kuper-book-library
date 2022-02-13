package ru.ikupdev.library.model.gbook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GBookParams {
    private String q;
    private String key;
    private String filter;
    private String orderBy;
    private String maxResults;
    private String startIndex;
}
