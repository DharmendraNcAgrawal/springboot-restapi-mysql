package com.rest.oracleconnection.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ErrorEntityBuilder errorBuilder;

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<ErrorEntity> handleEmployeeException(EmployeeException ex, WebRequest request) {
        log.error("Error", ex);
        ErrorEntity error;
        String errorCode = ex.getMessage();
        switch (errorCode) {
            case ErrorCodes.RESOURCE_NOT_FOUND:
                error = errorBuilder.build(ErrorCodes.RESOURCE_NOT_FOUND);
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            default:
                error = errorBuilder.build(ErrorCodes.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorEntity> handleException(Throwable ex) {
        log.error("Error", ex);
        ErrorEntity error = errorBuilder.build(ErrorCodes.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
