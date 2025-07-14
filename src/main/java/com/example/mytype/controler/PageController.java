package com.example.mytype.controler;


import com.example.mytype.model.TypeText;
import com.example.mytype.service.text.TextService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
public class PageController {
    private final TextService textService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String textId) {
        if (textId == null) {
            System.out.println(textService.getRandomIndex());
            return "redirect:/?textId=" + textService.getRandomIndex();
        }

        try {
            TypeText text = textService.findByIndex(Long.parseLong(textId));
            model.addAttribute("text", text);

            return "/index";
        } catch (NumberFormatException nfe) {
            return "redirect:/?textId=" + textService.getRandomIndex();
        }
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
