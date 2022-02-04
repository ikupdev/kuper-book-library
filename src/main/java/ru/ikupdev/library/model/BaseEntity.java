package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import ru.ikupdev.library.bean.type.Status;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Ilya V. Kupriyanov
 * @version 18.01.2022
 */
@MappedSuperclass
@Data
public abstract class BaseEntity {
    @ApiModelProperty(notes = "Primary key", required = true)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    @ApiModelProperty(notes = "Дата создания", required = true)
    @CreatedDate
    @Column(name = "created")
    private Date created;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
    @ApiModelProperty(notes = "Дата обновления", required = true)
    @LastModifiedDate
    @Column(name = "updated")
    private Date updated;
    @NotNull
    @ApiModelProperty(notes = "Статус", required = true)
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
