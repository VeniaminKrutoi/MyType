package com.example.mytype.service.text;

import com.example.mytype.model.TypeText;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public interface TextServ {
    List<TypeText> findAll();
    List<TypeText> findAllByCheck(boolean checked);
    TypeText findById(Long id);
    TypeText findByText(String text);
    long count();
    long countCheck(boolean checked);
    List<TypeText> findFromTo(long from, long to);
    TypeText findByIndex(long index);
    TypeText save(Map<String, String> data);
    TypeText update(Long id, Map<String, String> data);
    void delete(Long id);
    long getRandomIndex();
}
