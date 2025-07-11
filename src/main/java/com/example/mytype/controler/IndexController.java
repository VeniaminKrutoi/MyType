package com.example.mytype.controler;

import com.example.mytype.model.TypeText;
import com.example.mytype.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class IndexController {
    private final Service service;

    @PostMapping
    public Map<String, Object> getText(@RequestBody Map<String, Object> text_key) {
        long key = (Integer) text_key.get("key");
        long count = service.getTextCount();

        if (count == 0) {
            return Map.of();
        }

        long index = key % count;

        try {
            TypeText typeText = service.getByIndex(index);
            return typeText.getMap();
        } catch (Exception e) {
            return Map.of();
        }
    }
}
