package com.example.mytype.controler;

import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.model.TypeText;
import com.example.mytype.service.text.TextService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/save")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class newTextController {
    private TextService service;

    @PostMapping("/update")
    public TypeText updateChecked(HttpSession session, @RequestBody Map<String, String> data) {
        if (!UsersController.isAdmin(session)) {
            throw new WrongDataException("Отказ в доступе");
        }

        try {
            return service.update(Long.valueOf(data.get("id")), data);
        } catch (NumberFormatException e) {
            throw new WrongDataException("id");
        }
    }
}
