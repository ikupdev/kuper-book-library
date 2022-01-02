package ru.ikupdev.library.exception;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
