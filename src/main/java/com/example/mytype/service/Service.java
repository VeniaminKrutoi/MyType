package com.example.mytype.service;

import com.example.mytype.model.TypeText;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public interface Service {
    List<TypeText> findAllTexts();
    TypeText getById(Long id);
    TypeText findByText(String text);
    long getTextCount();
    TypeText getByIndex(long index);
    String save(Map<String, String> data);
}
