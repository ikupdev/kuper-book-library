package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.ikupdev.library.bean.type.Status;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {
    @ApiModelProperty(notes = "Primary key", required = true, example = "1")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    @ApiModelProperty(notes = "Дата создания", required = true, example = "2022-01-01'T'10:00:00.000")
    @CreatedDate
    @Column(name = "created")
    private Date created;
    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    @ApiModelProperty(notes = "Дата обновления", required = true, example = "2022-01-01'T'10:00:00.000")
    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;
    @NotBlank
    @ApiModelProperty(notes = "Статус", required = true, example = "ACTIVE")
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
