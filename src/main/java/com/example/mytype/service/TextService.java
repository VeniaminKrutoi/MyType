package com.example.mytype.service;

import com.example.mytype.model.TypeText;

import java.util.List;

@org.springframework.stereotype.Service
public class TextService implements Service {
    @Override
    public List<TypeText> findAllTexts() {
        return List.of(
                TypeText.builder().text("СЛОВНО...").author("Денис Чернухин").build()
        );
    }
}
