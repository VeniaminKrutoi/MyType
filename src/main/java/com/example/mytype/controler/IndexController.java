package com.example.mytype.controler;

import com.example.mytype.model.TypeText;
import com.example.mytype.service.text.TextService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class IndexController {
    private final TextService service;

    @PostMapping
    public Map<String, Object> getText(@RequestBody Map<String, Object> text_key) {
        long key = (Integer) text_key.get("key");
        long count = service.count();

        if (count == 0) {
            return Map.of();
        }

        long index = key % count;

        try {
            TypeText typeText = service.findByIndex(index);
            return typeText.getMap();
        } catch (Exception e) {
            return Map.of();
        }
    }
}
