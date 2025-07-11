package com.example.mytype.service;

import com.example.mytype.model.TypeText;
import com.example.mytype.repository.TypeTextRep;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Map;

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

    public long getTextCount() {
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

    @Override
    public String save(Map<String, String> data) {
        TypeText typeText = new TypeText();

        if (infoExist(data, "title")) {
            final String title = data.get("title");

            if (tooBig(title, TypeText.TITLE_LEN)) {
                return "Название текста слишком длинное";
            }

            typeText.setText(data.get("title"));
        }

        if (infoExist(data, "author")) {
            final String author = data.get("author");

            if (tooBig(author, TypeText.AUTHOR_LEN)) {
                return "Имя автора слишком длинное";
            }

            typeText.setAuthor(data.get("author"));
        }

        if (infoExist(data, "sourceLink")) {
            final String sourceLink = data.get("sourceLink");

            if (tooBig(sourceLink, TypeText.SOURCE_LINK_LEN)) {
                return "Ссылка на ресурс слишком длинная";
            }

            typeText.setSourceLink(data.get("sourceLink"));
        }

        if (!infoExist(data, "text")) {
            return "Текст обязан присутствовать";
        }

        final String text = data.get("text");

        if (tooBig(text, TypeText.TEXT_LEN)) {
            return "Текст слишком длинный";
        }

        typeText.setText(text);

        try {
            repository.save(typeText);
        } catch (DataIntegrityViolationException ex) {
            return "Данный текст уже существует";
        }

        return "success";
    }

    private boolean infoExist(Map<String, String> data, String str) {
        return data.containsKey(str) && data.get(str) != null && !data.get(str).isEmpty();
    }

    private boolean tooBig(String str, int len) {
        return str.length() > len;
    }
}
