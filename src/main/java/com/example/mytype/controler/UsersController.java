package com.example.mytype.controler;

import com.example.mytype.exceptions.SessionNotFoundException;
import com.example.mytype.exceptions.UserNotFoundException;
import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.service.user.UserServiceImpl;
import com.example.mytype.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class UsersController {
    private UserServiceImpl service;

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

    @GetMapping("/{id}")
    public User getUser(HttpSession session, @PathVariable String id) {
        try {
            User user = service.findById((Long.parseLong(id)));

            if (user == null) {
                throw new UserNotFoundException("пользователь");
            }

            if (!isUserOwnInfo(session, id) && !isAdmin(session)) {
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
        if (!isAdmin(session)) {
            data.remove("role");

            if (session.getAttribute("userId") == null) {
                throw new SessionNotFoundException(session.getId(), "пользователь");
            }

            if (!session.getAttribute("userId").toString().equals(id)) {
                throw new SessionNotFoundException(session.getId(), "пользователь сессии не равен пользователю клиента");
            }
        }

        return service.update((Long) session.getAttribute("userId"), data);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(HttpSession session, @PathVariable String id) {
        if (!isAdmin(session)) {
            if (session.getAttribute("userId") == null) {
                throw new SessionNotFoundException(session.getId(), "пользователь");
            }

            if (!session.getAttribute("userId").toString().equals(id)) {
                throw new SessionNotFoundException(session.getId(), "пользователь сессии не равен пользователю клиента");
            }
        }

        service.delete((Long) session.getAttribute("userId"));
        session.removeAttribute("userId");
    }

    public static boolean isUserOwnInfo(HttpSession session, String requestId) {
        return session.getAttribute("userId") != null
                && session.getAttribute("userId").toString().equals(requestId);
    }

    public static boolean isAdmin(HttpSession session) {
        return session.getAttribute("role") != null && session.getAttribute("role").equals("ADMIN");
    }
}
