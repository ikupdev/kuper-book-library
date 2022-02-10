package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @author Ilya V. Kupriyanov
 * @version 09.02.2022
 */
@MappedSuperclass
@Getter
@Setter
@ToString(callSuper = true)
public class DatedEntity extends BaseEntity {
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
}
