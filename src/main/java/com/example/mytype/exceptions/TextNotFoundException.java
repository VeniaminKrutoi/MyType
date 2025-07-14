package com.example.mytype.exceptions;

public class TextNotFoundException extends RuntimeException {
    public TextNotFoundException(String message) {
        super("Текста нет" + message);
    }
}
