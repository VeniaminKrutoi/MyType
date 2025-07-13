package com.example.mytype.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userInfo) {
        super("Пользователь %s не найден".formatted(userInfo));
    }
}
