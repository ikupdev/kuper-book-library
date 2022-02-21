package ru.ikupdev.library.exception;

import feign.FeignException;
import lombok.Getter;

/**
 * @author Ilya V. Kupriyanov
 * @version 21/02/2022
 */
@Getter
public class FeignNotFoundException extends FeignException {

    public FeignNotFoundException(int status, String message, Throwable cause) {
        super(status, message, cause);
    }
}