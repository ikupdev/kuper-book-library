package ru.ikupdev.library.exception;

/**
 * @author Ilya V. Kupriyanov
 * @version 02.02.2022
 */
public class ClientDataException extends RuntimeException {

    public ClientDataException(String message) {
        super(message);
    }
}
