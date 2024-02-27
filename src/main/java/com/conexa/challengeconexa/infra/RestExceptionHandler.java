package com.conexa.challengeconexa.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    private ErrorHandler EntityNotFound(Exception exception) {
        var errorHandler = new ErrorHandler("Error", exception.getMessage());

        return errorHandler;
    }
}
