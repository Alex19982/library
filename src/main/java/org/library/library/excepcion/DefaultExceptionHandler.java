package org.library.library.excepcion;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@ControllerAdvice
public class DefaultExceptionHandler { //Сделать автовыравнивание кода
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleApiError(ResourceNotFoundException exception, HttpServletRequest request) {
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND.value(), request.getRequestURI(), ZonedDateTime.now(), List.of());
        log.error(error.toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> handleConflictException(ConflictException exception, HttpServletRequest request) {
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.CONFLICT.value(), request.getRequestURI(), ZonedDateTime.now(), List.of());
        log.error(error.toString());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException exception, HttpServletRequest request) {
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), ZonedDateTime.now(), List.of());
        log.error(error.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
