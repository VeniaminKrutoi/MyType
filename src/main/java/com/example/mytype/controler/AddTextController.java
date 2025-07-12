package com.example.mytype.controler;

import com.example.mytype.service.text.TextServ;
import com.example.mytype.service.text.TextService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/add")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class AddTextController {
    private final TextService service;

    @PostMapping
    public String add(@RequestBody Map<String, String> data) {
        return service.save(data);
    }
}
