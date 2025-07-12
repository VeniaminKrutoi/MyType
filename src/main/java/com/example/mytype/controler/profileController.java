package com.example.mytype.controler;

import com.example.mytype.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class profileController {
    private UserService service;

    @PostMapping("/register")
    public String addUser(@RequestBody Map<String, String> user) {
        return service.save(user);
    }

    @PostMapping("/login")
    public String getUser(@RequestBody Map<String, String> data) {
        return service.checkEmailAndPassword(data);
    }
}
