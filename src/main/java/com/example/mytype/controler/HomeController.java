package com.example.mytype.controler;

import com.example.mytype.exceptions.SessionNotFoundException;
import com.example.mytype.model.TypeText;
import com.example.mytype.model.User;
import com.example.mytype.service.text.TextServiceImpl;
import com.example.mytype.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class HomeController {
    private final TextServiceImpl textService;
    private final UserServiceImpl userService;

    @PostMapping
    public Map<String, Object> getText(@RequestBody Map<String, Object> text_key) {
        long key = (Integer) text_key.get("key");
        long count = textService.count();

        if (count == 0) {
            return Map.of();
        }

        long index = key % count;

        try {
            TypeText typeText = textService.findByIndex(index);
            return typeText.getMap();
        } catch (Exception e) {
            return Map.of();
        }
    }


    @PostMapping("/login")
    public User loginUser(HttpSession session, @RequestBody Map<String, String> user) {
        User loginUser = userService.checkEmailAndPassword(user);
        session.setAttribute("userId", loginUser.getId());
        session.setAttribute("role", loginUser.getRole());

        System.out.println(session.getAttribute("userId"));

        return loginUser;
    }

    @GetMapping("/close")
    public void closeSession(HttpSession session) {
        session.removeAttribute("userId");
    }

    @PostMapping("/save")
    public User saveUserResults(HttpSession session, Map<String, String> data) {
        if (session.getAttribute("userId") == null) {
            throw new SessionNotFoundException(session.getId(), "пользователь");
        }

        try {
            return userService.update((Long) session.getAttribute("userId"), data);
        } catch (NumberFormatException e) {
            throw new SessionNotFoundException(session.getId(), "Id не long");
        }
    }
}