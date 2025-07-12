package com.example.mytype.service.user;

import com.example.mytype.model.User;
import com.example.mytype.repository.UserRep;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class UserService implements UserServ {
    private final UserRep repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<User> findFromTo(long from, long to) {
        if (from > to || to <= 0) {
            return List.of();
        }

        return repository.findFromTo(from, to);
    }

    @Override
    public User findByIndex(long index) {
        if (index < 0 || index >= count()) {
            return null;
        }

        return repository.findByIndex(index);
    }

    @Override
    public String save(Map<String, String> data) {
        User user = new User();

        if (dataEmpty(data, "username")) {
            return "Имя не должно быть пустое";
        }

        final String username = data.get("username");

        if (tooBig(username, User.USERNAME_LEN)) {
            return "Имя слишком длинное";
        }

        if (dataEmpty(data, "email")) {
            return "Почта не должна быть пустой";
        }

        final String email = data.get("email");

        if (tooBig(username, User.EMAIL_LEN)) {
            return "Почта слишком длинная";
        }

        if (dataEmpty(data, "password")) {
            return "Пароль не должен быть пустым";
        }

        final String password = data.get("password");

        if (tooBig(username, User.PASSWORD_LEN)) {
            return "Пароль слишком длинный";
        }

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setTime(0L);

        repository.save(user);

        return "success";
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public String checkEmailAndPassword(Map<String, String> data) {
        final String email = data.get("email");
        final String password = data.get("password");

        User user = repository.findByEmail(email);

        if (user == null) {
            return "Такого пользователя не существует";
        }

        if (!user.getPassword().equals(password)) {
            return "Неверный пароль";
        }

        return "success";
    }

    private static boolean dataEmpty(Map<String, String> data, String key) {
        return !data.containsKey(key) || data.get(key) == null || data.get(key).isEmpty();
    }

    private static boolean tooBig(String value, int len) {
        return value.length() > len;
    }
}
