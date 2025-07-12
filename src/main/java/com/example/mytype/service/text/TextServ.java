package com.example.mytype.service.text;

import com.example.mytype.model.TypeText;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public interface TextServ {
    List<TypeText> findAll();
    TypeText findById(Long id);
    TypeText findByText(String text);
    long count();
    List<TypeText> findFromTo(long from, long to);
    TypeText findByIndex(long index);
    String save(Map<String, String> data);
}
