package ru.ikupdev.library.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ilya V. Kupriyanov
 * @version 16.02.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookshelfFilter {
    private String bookshelfName;
    private String description;
    private Long userId;
}
