package com.example.mytype.controler;

import com.example.mytype.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EmptyDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleEmptyDataException(EmptyDataException ex) {
        logExceptions((RuntimeException) ex);
        return ex.getMessage();
    }

    @ExceptionHandler(TooBigException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleTooBigException(TooBigException ex) {
        logExceptions((RuntimeException) ex);
        return ex.getMessage();
    }

    @ExceptionHandler(WrongDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String handleWrongDataException(WrongDataException ex) {
        logExceptions((RuntimeException) ex);
        return ex.getMessage();
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleUserNotFoundException(UserNotFoundException ex) {
        logExceptions((RuntimeException) ex);
        return ex.getMessage();
    }

    @ExceptionHandler(TextNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String handleTextNotFoundException(TextNotFoundException ex) {
        logExceptions((RuntimeException) ex);
        return ex.getMessage();
    }

    @ExceptionHandler(SessionNotFoundException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String handleSessionNotFoundException(SessionNotFoundException ex) {
        logExceptions((RuntimeException) ex);
        return ex.getClientMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    String handleException(Exception ex) {
        System.out.println(ex.getMessage());
        System.out.println(Arrays.toString(ex.getStackTrace()));
        return "Ошибка на сервере. Попробуйте заново";
    }

    private static void logExceptions(RuntimeException exception) {
        System.out.println(exception.getMessage());
        System.out.println(Arrays.toString(exception.getStackTrace()));
    }
}
