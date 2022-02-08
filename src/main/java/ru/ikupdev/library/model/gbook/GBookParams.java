package ru.ikupdev.library.model.gbook;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ilya V. Kupriyanov
 * @version 07.02.2022
 */
@Data
@AllArgsConstructor
public class GBookParams {
    private String q;
    private String key;
}
