package ru.ikupdev.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;

/**
 * @author Ilya V. Kupriyanov
 * @version 22.02.2022
 */
@Getter
@Setter
@NoArgsConstructor
public class BookshelfResponseDto {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date created;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    private Date updated;
    private String name;
    private String description;
}
