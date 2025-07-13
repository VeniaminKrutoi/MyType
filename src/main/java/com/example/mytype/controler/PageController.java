package com.example.mytype.controler;


import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class PageController {


    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/add")
    public String add() {
        return "forward:/addText.html";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/auth";
        }

        model.addAttribute("user", Map.of(
                "id", "-1",
                "own", true
        ));

        return "/profile";
    }

    @GetMapping("/auth")
    public String auth() {
        return "forward:/auth.html";
    }

    @GetMapping("profile/{id}")
    public String profiles(Model model, HttpSession session, @PathVariable String id) {

        model.addAttribute("user", Map.of(
                "id", id,
                "own", session.getAttribute("userId") != null
                        && session.getAttribute("userId").toString().equals(id)
        ));

        return "/profile";
    }
}
