package ru.ikupdev.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponseDto<T> {
    private T data;
    private Long totalRows;
    private Integer totalPages;
    @ApiModelProperty(notes = "Сообщение об ошибке", hidden = true, example = "message")
    private String message;
    @ApiModelProperty(notes = "Ошибки валидации", hidden = true)
    private Set<String> validationErrors;


    public RestResponseDto(T data) {
        this.data = data;
    }

    public RestResponseDto(Set<String> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public static <L> RestResponseDto<List<L>> fromPage(Page<L> page) {
        RestResponseDto<List<L>> response = new RestResponseDto<>();
        response.setData(page.getContent());
        response.setTotalRows(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        return response;
    }

    public static RestResponseDto createMessage(String message) {
        RestResponseDto response = new RestResponseDto<>();
        response.setMessage(message);
        return response;
    }

    public static RestResponseDto createMessages(MethodArgumentNotValidException exception, MessageSourceAccessor messageSourceAccessor) {
        Set<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> {
                    String localizedFieldName = localizeFieldName(error, messageSourceAccessor);
                    return localizedFieldName + " - " + error.getDefaultMessage();
                })
                .collect(Collectors.toCollection(HashSet::new));
        return new RestResponseDto(errorMessages);
    }

    private static String localizeFieldName(FieldError error, MessageSourceAccessor messageSourceAccessor) {
        StringBuilder fieldKey = new StringBuilder();
        fieldKey.append("field.")
                .append(error.getObjectName().toLowerCase())
                .append(".")
                .append(error.getField().toLowerCase());
        return messageSourceAccessor.getMessage(fieldKey.toString(), error.getField());
    }

}

