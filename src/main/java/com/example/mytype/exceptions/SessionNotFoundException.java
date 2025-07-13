package com.example.mytype.exceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String sessionId, String info) {
        super("Сессия %s не имеет %s".formatted(sessionId, info));
    }
}
