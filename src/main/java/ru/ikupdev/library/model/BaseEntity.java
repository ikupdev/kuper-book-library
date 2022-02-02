package ru.ikupdev.library.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.ikupdev.library.bean.type.Status;
import ru.ikupdev.library.converter.LocalDateConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@MappedSuperclass
@Data
public class BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = LocalDateConverter.class)
    @CreatedDate
    @Column(name = "created")
    private LocalDate created;
    @Convert(converter = LocalDateConverter.class)
    @LastModifiedDate
    @Column(name = "updated")
    private LocalDate updated;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;

}
