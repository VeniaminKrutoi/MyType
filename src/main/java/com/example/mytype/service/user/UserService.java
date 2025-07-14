package com.example.mytype.service.user;

import com.example.mytype.exceptions.EmptyDataException;
import com.example.mytype.exceptions.TooBigException;
import com.example.mytype.exceptions.UserNotFoundException;
import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.model.User;
import com.example.mytype.repository.UserRep;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;

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
        return repository.findById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public List<User> findFromTo(long from, long to) {
        if (from > to || to < 0) {
            throw new WrongDataException("индекс начала или индекс конца");
        }

        if (from < 0) {
            from = 0;
        }

        final long count = repository.count();

        if (to > count) {
            to = count;
        }

        return repository.findFromTo(from, to);
    }

    @Override
    public User findByIndex(long index) {
        if (index < 0 || index >= count()) {
            throw new WrongDataException("индекс");
        }

        return repository.findByIndex(index);
    }

    @Override
    public User save(Map<String, String> data) {
        checkExceptions(data, "username", User.USERNAME_LEN, "пользователь");
        checkExceptions(data, "email", User.EMAIL_LEN, "почта");
        checkExceptions(data, "password", User.PASSWORD_LEN, "пароль");

        User user = new User();
        user.setUsername(data.get("username"));
        user.setEmail(data.get("email"));
        user.setPassword(data.get("password"));
        user.setTime(0L);
        user.setTypeResults(0);
        user.setTypeCount(0);
        user.setRole("USER");

        try {
            return repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new WrongDataException("Данный пользователь уже существует");
        }
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User checkEmailAndPassword(Map<String, String> data) {
        final String email = data.get("email");
        final String password = data.get("password");

        User user = repository.findByEmail(email);

        if (user == null) {
            throw new UserNotFoundException(email);
        }

        if (!user.getPassword().equals(password)) {
            throw new WrongDataException("пароль");
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public User update(Long id, Map<String, String> data) {
        User user = repository.findById(id).orElse(null);

        if (user == null) {
            throw new UserNotFoundException(data.get("username"));
        }

        if (existAndNotTooBigException(data, "username", User.USERNAME_LEN, "пользователь")) {
            if (repository.findByUsername(data.get("username")) != null) {
                throw new WrongDataException("Пользователь с таким именем уже существует");
            }
            user.setUsername(data.get("username"));
        }

        if (existAndNotTooBigException(data, "email", User.EMAIL_LEN, "почта")) {
            if (repository.findByEmail(data.get("email")) != null) {
                throw new WrongDataException("Пользователь с такой почтой уже существует");
            }
            user.setEmail(data.get("email"));
        }

        if (existAndNotTooBigException(data, "password", User.PASSWORD_LEN, "пароль")) {
            user.setPassword(data.get("password"));
        }

        if (existAndNotTooBigException(data, "typeResults", 256, "Знаки в минуту")
                && existAndNotTooBigException(data, "typeCount", 256, "Число испытаний")
                && existAndNotTooBigException(data, "time", 256, "Время")) {
            user.setTypeResults(Integer.parseInt(data.get("typeResults")));

            Integer typeResult = user.getTypeResults();
            if (typeResult == null) {
                typeResult = 0;
            }

            Integer typeCount = user.getTypeCount();
            if (typeCount == null) {
                typeCount = 0;
            }

            Long  time = user.getTime();
            if (time == null) {
                time = 0L;
            }

            int newTypeCount = typeCount + Integer.parseInt(data.get("typeCount"));
            int newTypeResult = ((typeResult * typeCount) + Integer.parseInt(data.get("typeResults")))
                    / newTypeCount;
            long newTime = time + Long.parseLong(data.get("time"));

            user.setTypeResults(newTypeResult);
            user.setTypeCount(newTypeCount);
            user.setTime(newTime);
        }

        if (existAndNotTooBigException(data, "role", 256, "Роль")) {
            user.setRole(data.get("role"));
        }

        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new WrongDataException("id");
        }

        repository.deleteById(id);
    }

    private static boolean existAndNotTooBigException(
            Map<String, String> data,
            String attribute,
            int attLen,
            String errorMessage
    ) {
        if (dataEmpty(data, attribute)) {
            return false;
        }

        if (tooBig(data.get(attribute), attLen)) {
            throw new TooBigException(errorMessage, attLen);
        }

        return true;
    }

    private static void checkExceptions(Map<String, String> data, String attribute, int attLen, String errorMessage) {
        if (dataEmpty(data, attribute)) {
            throw new EmptyDataException(errorMessage);
        }

        if (tooBig(data.get(attribute), attLen)) {
            throw new TooBigException(errorMessage, attLen);
        }
    }

    private static boolean dataEmpty(Map<String, String> data, String key) {
        return !data.containsKey(key) || data.get(key) == null || data.get(key).isEmpty();
    }

    private static boolean tooBig(String value, int len) {
        return value.length() > len;
    }
}
