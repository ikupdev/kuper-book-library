package ru.ikupdev.library.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author Ilya V. Kupriyanov
 * @version 09.02.2022
 */
@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public abstract class DatedEntity extends BaseEntity {
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
        @ApiModelProperty(notes = "Дата создания", required = true, example = "2022-01-01'T'10:00:00.000")
        @CreatedDate
        @Column(name = "created")
        private Date created;
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "UTC")
        @ApiModelProperty(notes = "Дата обновления", required = true, example = "2022-01-01'T'10:00:00.000")
        @LastModifiedDate
        @Column(name = "updated")
        private Date updated;
}
