package ru.ikupdev.library.dto.filter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ilya V. Kupriyanov
 * @version 10.02.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFilter {
    private String title;
    private String subtitle;
    private String description;
    private String language;
    private String authors;
    private Long userId;
    private Long bookshelfId;
}
