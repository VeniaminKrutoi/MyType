package com.example.mytype.service;

import com.example.mytype.model.TypeText;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service {
    List<TypeText> findAllTexts();
    TypeText getById(Long id);
    TypeText findByText(String text);
    long getTextCount();
    TypeText getByIndex(long index);
}
