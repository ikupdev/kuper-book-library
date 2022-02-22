package ru.ikupdev.library.dto;

import lombok.*;

/**
 * @author Ilya V. Kupriyanov
 * @version 11.12.2021
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserViewDto {
    Long id;
    String login;
}
