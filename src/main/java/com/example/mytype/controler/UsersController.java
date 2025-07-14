package com.example.mytype.controler;

import com.example.mytype.exceptions.SessionNotFoundException;
import com.example.mytype.exceptions.UserNotFoundException;
import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.service.user.UserService;
import com.example.mytype.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class UsersController {
    private UserService service;

    @PostMapping
    public User regUser(HttpSession session, @RequestBody Map<String, String> user) {
        User createdUser = service.save(user);

        session.setAttribute("userId", createdUser.getId());
        System.out.println(createdUser);
        return createdUser;
    }

    @PostMapping("/count")
    public Map<String, Long> countUsers() {
        return Map.of("count", service.count());
    }

    @GetMapping
    public List<User> getAllUsers(
            HttpSession session,
            @RequestParam(required = false) Long from,
            @RequestParam(required = false) Long to
    ) {
        if (from == null && to == null) {
            return service.findAll()
                    .stream()
                    .peek(user -> user.setEmail(null))
                    .peek(user -> user.setPassword(null))
                    .collect(Collectors.toList());
        }

        if (from == null) {
            throw new WrongDataException("Неправильное начало");
        }

        if (to == null) {
            throw new WrongDataException("Неправильный конец");
        }

        return service.findFromTo(from, to);
    }

    @GetMapping("/{id}")
    public User getUser(HttpSession session, @PathVariable String id) {
        try {
            User user = service.findById((Long.parseLong(id)));

            if (user == null) {
                throw new UserNotFoundException("пользователь");
            }

            if (session.getAttribute("userId") == null || !session.getAttribute("userId").toString().equals(id)) {
                user.setEmail(null);
                user.setPassword(null);
            }

            return user;
        } catch (NumberFormatException e) {
            throw new WrongDataException("Неверный id");
        }
    }

    @PatchMapping("/{id}")
    public User updateUser(
            HttpSession session,
            @PathVariable String id,
            @RequestBody Map<String, String> data
    ) {
        if (session.getAttribute("userId") == null) {
            throw new SessionNotFoundException(session.getId(), "пользователь");
        }

        if (!session.getAttribute("userId").toString().equals(id)) {
            throw new SessionNotFoundException(session.getId(), "пользователь сессии не равен пользователю клиента");
        }

        return service.update((Long) session.getAttribute("userId"), data);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(HttpSession session, @PathVariable String id) {
        if (session.getAttribute("userId") == null) {
            throw new SessionNotFoundException(session.getId(), "пользователь");
        }

        if (!session.getAttribute("userId").toString().equals(id)) {
            throw new SessionNotFoundException(session.getId(), "пользователь сессии не равен пользователю клиента");
        }

        service.delete((Long) session.getAttribute("userId"));
        session.removeAttribute("userId");
    }
}
