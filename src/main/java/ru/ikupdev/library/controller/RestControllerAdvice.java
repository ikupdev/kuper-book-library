package ru.ikupdev.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.exception.ClientDataException;
import ru.ikupdev.library.exception.NotFoundException;
import ru.ikupdev.library.exception.ResourceConflictException;

import java.util.NoSuchElementException;

/**
 * @author Ilya V. Kupriyanov
 * @version 15.12.2021
 */
@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestControllerAdvice {
    private final MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(RestResponseDto.createMessage(ex.getMessage()));
    }

    @ExceptionHandler(value = {ConversionFailedException.class})
    public ResponseEntity<String> handleConversionFailedException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = {AccessException.class})
    public ResponseEntity handleAccessException(AccessException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(RestResponseDto.createMessage(ex.getMessage()));
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity handleAccessException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(RestResponseDto.createMessage("Запись с таким уникальным значением уже существует: " + ex.getMessage()));
    }

    @ExceptionHandler(value = {ResourceConflictException.class})
    public ResponseEntity handleException(ResourceConflictException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(RestResponseDto.createMessage(ex.getMessage()));
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handleException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestResponseDto.createMessages(ex, messageSourceAccessor));
    }

    @ExceptionHandler(value = {ClientDataException.class})
    public ResponseEntity handleException(ClientDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestResponseDto.createMessage(ex.getMessage()));
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity handleException(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResponseDto.createMessage(ex.getMessage()));
    }

    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity handleException(NoSuchElementException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResponseDto.createMessage(ex.getMessage()));
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity handleException(BadCredentialsException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(RestResponseDto.createMessage(ex.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResponseDto.createMessage(ex.getMessage()));
    }

}
