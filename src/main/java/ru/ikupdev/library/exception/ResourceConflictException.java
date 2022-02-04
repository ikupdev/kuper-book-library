package ru.ikupdev.library.exception;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.02.2022
 */
public class ResourceConflictException extends RuntimeException {

    public ResourceConflictException(String message) {
        super(message);
    }
}
