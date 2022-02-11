package ru.ikupdev.library.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ikupdev.library.bean.type.Status;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFilter {
    private String firstName;
    private String lastName;
    private Status status;
}
