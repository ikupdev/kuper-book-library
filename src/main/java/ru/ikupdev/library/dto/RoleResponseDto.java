package ru.ikupdev.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
@Data
@NoArgsConstructor
public class RoleResponseDto {
    private Long id;
    private String name;
}
