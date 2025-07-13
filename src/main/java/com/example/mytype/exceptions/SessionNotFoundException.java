package com.example.mytype.exceptions;

public class SessionNotFoundException extends RuntimeException {
    public SessionNotFoundException(String sessionId, String info) {
        super("Сессия %s. ошибка в %s".formatted(sessionId, info));
    }

    public String getClientMessage(){
        return "Проблема с сервером. Попробуйте заново.";
    }
}
