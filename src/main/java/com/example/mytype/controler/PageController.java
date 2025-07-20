package com.example.mytype.controler;


import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.model.TypeText;
import com.example.mytype.model.User;
import com.example.mytype.service.text.TextServiceImpl;
import com.example.mytype.service.user.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class PageController {
    private final TextServiceImpl textService;
    private final UserServiceImpl userService;

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

        return "redirect:/profile/" + session.getAttribute("userId");
    }

    @GetMapping("/auth")
    public String auth() {
        return "forward:/auth.html";
    }

    @GetMapping("/profile/{id}")
    public String profiles(Model model, HttpSession session, @PathVariable String id) {
        User user = userService.findById(Long.parseLong(id));

        if (!UsersController.isUserOwnInfo(session, id) && !UsersController.isAdmin(session)) {
            user.setEmail(null);
            user.setPassword(null);
        }

        model.addAttribute("user", Map.of(
                "user", user,
                "own", UsersController.isUserOwnInfo(session, id),
                "admin", UsersController.isAdmin(session)
        ));

        return "/profile";
    }

    @GetMapping("/leaderboard")
    public String getAllUsers(
            Model model,
            HttpSession session,
            @RequestParam(required = false) Long from,
            @RequestParam(required = false) Long to
    ) {
        if (from == null && to == null) {
            return "redirect:/leaderboard?from=0&to=20";
        }

        if (from == null || to == null) {
            throw new WrongDataException("Начало или конец заданы неправильно");
        }

        List<User> list = userService.findFromTo(from, to);

        model.addAttribute("leaderboard", Map.of(
                "page", to / 20,
                "user", list.stream()
                        .peek(user -> user.setPassword(null))
                        .peek(user -> user.setEmail(null))
                        .toList(),
                "count", userService.count()
        ));

        return "/leaderboard";
    }

    @GetMapping("/save")
    public String checkText(Model model, HttpSession session) {
        if (!UsersController.isAdmin(session)) {
            throw new WrongDataException("Недоступно");
        }

        model.addAttribute("texts", textService.findAllByCheck(false));

        return "/newTexts";
    }

}
