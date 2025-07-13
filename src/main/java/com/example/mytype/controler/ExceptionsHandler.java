package com.example.mytype.controler;

import com.example.mytype.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EmptyDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleEmptyDataException(EmptyDataException ex) {
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(TooBigException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleTooBigException(TooBigException ex) {
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(WrongDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleWrongDataException(WrongDataException ex) {
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleUserNotFoundException(UserNotFoundException ex) {
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String handleSessionNotFoundException(SessionNotFoundException ex) {
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleException(Exception ex) {
        System.out.println(ex.getMessage());
        return ex.getMessage();
    }
}
