package com.example.mytype.service;

import com.example.mytype.model.TypeText;
import com.example.mytype.repository.TypeTextRep;
import lombok.AllArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class TextService implements Service {
    private final TypeTextRep repository;

    @Override
    public List<TypeText> findAllTexts() {
        return repository.findAll();
    }

    @Override
    public TypeText getById(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public TypeText findByText(String text) {
        return repository.findByText(text);
    }

    public long getTextCount(){
        try {
            long a = repository.count();
            System.out.println(a);
            return a;
        } catch (Exception ex) {
            return -1;
        }
    }

    @Override
    public TypeText getByIndex(long index) {
        return repository.getByIndex(index);
    }

}
