package com.example.mytype.controler;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {


    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }

    @GetMapping("/add")
    public String add() {
        return "forward:/add-text.html";
    }

    @GetMapping("/profile")
    public String profile() {
        return "forward:/profile/profile.html";
    }
}
