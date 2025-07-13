package com.example.mytype.controler;

import com.example.mytype.exceptions.UserNotFoundException;
import com.example.mytype.service.user.UserService;
import com.example.mytype.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class profileController {
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<String> regUser(@RequestBody Map<String, String> user, HttpSession session) {
        session.setAttribute("user", user.get("username"));
        return service.save(user);
    }

    @PostMapping("/login")
    public void logUser(@RequestBody Map<String, String> user, HttpSession session) {
        service.checkEmailAndPassword(user);
        session.setAttribute("user", user.get("username"));
    }

    @GetMapping("/me")
    public User getUser(HttpSession session) {
        if (session.getAttribute("user") != null) {
            throw new UserNotFoundException("пользователь");
        }

        User user = service.findByUsername(session.getAttribute("user").toString());

        if  (user == null) {
            throw new UserNotFoundException("пользователь");
        }

        return user;
    }

    @PostMapping
    public Map<String, Object> updateUser(@RequestBody Map<String, String> data) {
        return Map.of();
    }
}
