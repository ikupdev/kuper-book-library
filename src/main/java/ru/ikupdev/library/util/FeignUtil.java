package ru.ikupdev.library.util;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import ru.ikupdev.library.exception.FeignNotFoundException;
import ru.ikupdev.library.exception.FeignServerErrorException;

import java.util.concurrent.Callable;

/**
 * @author Ilya V. Kupriyanov
 * @version 21.02.2022
 */
@Slf4j
public class FeignUtil {

    public static <T> T feignLog(Callable<T> runnable) {
        try {
            return runnable.call();
        } catch (Exception e) {
            if (e instanceof FeignException) {
                int status = ((FeignException) e).status();
                log.error(e.getMessage(), e);
                if (status >= 400 && status <= 499) {
                    throw new FeignNotFoundException(status, String.format("Not found: %s", e.getMessage()), e.getCause());
                } else {
                    throw new FeignServerErrorException(status, String.format("Server error: %s", e.getMessage()), e.getCause());
                }
            } else {
                throw new RuntimeException(e.getMessage(), e.getCause());
            }
        }
    }

}
