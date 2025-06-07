package com.example.mytype.controler;

import com.example.mytype.model.TypeText;
import com.example.mytype.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/prikols")
@AllArgsConstructor
public class Controller {
    private final Service service;

    @GetMapping
    public List<TypeText> findAllTexts() {
        return service.findAllTexts();
    }
}
