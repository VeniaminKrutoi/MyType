package com.example.mytype.service.user;

import com.example.mytype.exceptions.EmptyDataException;
import com.example.mytype.exceptions.TooBigException;
import com.example.mytype.exceptions.UserNotFoundException;
import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.model.User;
import com.example.mytype.repository.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<String> save(Map<String, String> data) {
        User user = new User();

        if (dataEmpty(data, "username")) {
            throw new EmptyDataException("пользователь");
        }

        final String username = data.get("username");

        if (tooBig(username, User.USERNAME_LEN)) {
            throw new TooBigException("пользователь", User.USERNAME_LEN);
        }

        if (dataEmpty(data, "email")) {
            throw new EmptyDataException("почта");
        }

        final String email = data.get("email");

        if (tooBig(username, User.EMAIL_LEN)) {
            throw new TooBigException("почта", User.EMAIL_LEN);
        }

        if (dataEmpty(data, "password")) {
            throw new EmptyDataException("пароль");
        }

        final String password = data.get("password");

        if (tooBig(username, User.PASSWORD_LEN)) {
            throw new TooBigException("пароль", User.PASSWORD_LEN);
        }

        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setTime(0L);
        user.setRole("USER");

        repository.save(user);

        return ResponseEntity.ok().build();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void checkEmailAndPassword(Map<String, String> data) {
        final String email = data.get("email");
        final String password = data.get("password");

        User user = repository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException(email);
        }

        if (!user.getPassword().equals(password)) {
            throw new WrongDataException("пароль");
        }
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    private static boolean dataEmpty(Map<String, String> data, String key) {
        return !data.containsKey(key) || data.get(key) == null || data.get(key).isEmpty();
    }

    private static boolean tooBig(String value, int len) {
        return value.length() > len;
    }
}
