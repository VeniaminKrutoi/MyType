package com.example.mytype.service;

import com.example.mytype.model.TypeText;

import java.util.List;

@org.springframework.stereotype.Service
public interface Service {
    List<TypeText> findAllTexts();
}
