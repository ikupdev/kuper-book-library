package ru.ikupdev.library.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
@Getter
@Setter
@NoArgsConstructor
public class RoleResponseDto {
    private Long id;
    private String name;
}
