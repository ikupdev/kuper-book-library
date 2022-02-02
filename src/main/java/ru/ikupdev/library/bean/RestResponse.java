package ru.ikupdev.library.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 *
 * RestResponse Class
 *
 * @author Anton A. Slepov
 * @version 02/10/2020
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
public class RestResponse<T> implements Serializable {
    private int status;
    private T data;
    private Long totalRows;
    private List<String> errors;
    private String message;

    public RestResponse(T data) {
        this.data = data;
    }

    public static <L> RestResponse<List<L>> create(Page<L> page) {
        RestResponse<List<L>> response = new RestResponse<>();
        response.setData(page.getContent());
        response.setTotalRows(page.getTotalElements());
        return response;
    }

    public static RestResponse createMessage(String message) {
        RestResponse response = new RestResponse<>();
        response.setMessage(message);
        return response;
    }
}
