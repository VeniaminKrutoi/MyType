package com.example.mytype.exceptions;

public class WrongDataException extends RuntimeException {
    public WrongDataException(String field) {
        super("Неверно заполнено %s".formatted(field));
    }
}
