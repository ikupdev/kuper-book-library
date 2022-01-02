package ru.ikupdev.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ikupdev.library.dto.RestResponseDto;
import ru.ikupdev.library.exception.NotFoundException;

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

//    @ExceptionHandler(value = {ConflictEntityException.class})
//    public ResponseEntity handleException(ConflictEntityException ex) {
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(RestResponseDto.createMessage(ex.getMessage()));
//    }
//
//    @ExceptionHandler(value = {AccessException.class})
//    public ResponseEntity handleAccessException(AccessException ex) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(RestResponseDto.createMessage(ex.getMessage()));
//    }

    // обработка ошибки: could not execute statement; SQL [n/a]; constraint [external_division_name_key]
    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity handleAccessException(DataIntegrityViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(RestResponseDto.createMessage("Запись с таким уникальным значением уже существует: " + ex.getMessage()));
    }

    // https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity handleException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RestResponseDto.createMessages(ex, messageSourceAccessor));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResponseDto.createMessage(ex.getMessage()));
    }
    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity handleException(IllegalArgumentException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RestResponseDto.createMessage(ex.getMessage()));
    }
}
