package com.example.mytype.exceptions;

public class TooBigException extends RuntimeException {
    public TooBigException(String field, int len) {
        super("Слишком много символов для %s. Максимальное количество символов - %d".formatted(field, len));
    }
}
