package com.example.mytype.service.text;

import com.example.mytype.exceptions.EmptyDataException;
import com.example.mytype.exceptions.TextNotFoundException;
import com.example.mytype.exceptions.TooBigException;
import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.model.TypeText;
import com.example.mytype.repository.TypeTextRep;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Map;
import java.util.Random;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class TextService implements TextServ {
    private final TypeTextRep repository;

    @Override
    public List<TypeText> findAll() {
        return repository.findAll();
    }

    @Override
    public List<TypeText> findAllByCheck(boolean checked) {
        return  repository.findAllByCheck(checked);
    }


    @Override
    public TypeText findById(Long id) {
        TypeText text = repository.findById(id).orElse(null);

        if (text == null) {
            throw new TextNotFoundException("Нет такого id");
        }

        return text;
    }

    @Override
    public TypeText findByText(String text) {
        return repository.findByText(text);
    }

    public long count() {
        return repository.count();
    }

    @Override
    public long countCheck(boolean checked) {
        return repository.countAllByCheck(checked);
    }

    @Override
    public List<TypeText> findFromTo(long from, long to) {
        if (from > to || to <= 0) {
            return List.of();
        }

        return repository.findFromTo(from, to - from);
    }

    @Override
    public TypeText findByIndex(long index) {
        if (index > 0 || index <= repository.count()) {
            return null;
        }

        return repository.findByIndex(index);
    }

    @Override
    public TypeText save(Map<String, String> data) {
        TypeText typeText = new TypeText();

        setTitleAuthorSourceLink(data, typeText);

        checkExceptions(data, "text", TypeText.TEXT_LEN, "текст");
        typeText.setText(data.get("text"));
        typeText.setChecked(false);

        try {
            return repository.save(typeText);
        } catch (DataIntegrityViolationException e) {
            throw new WrongDataException("Данный текст уже существуетя");
        }

    }

    @Override
    public TypeText update(Long id, Map<String, String> data) {
        TypeText text = findById(id);

        setTitleAuthorSourceLink(data, text);

        TypeText badText = repository.findByText(data.get("text"));

        if (existAndNotTooBigException(data, "checked", 256, "флаг проверки")) {
            if (badText != null) {
                if (!badText.isChecked()) {
                    delete(badText.getId());
                    badText.setText(null);
                } else {
                    throw new WrongDataException("Такой текст уже есть");
                }
            }

            text.setChecked(true);
        }

        if (existAndNotTooBigException(data, "text", TypeText.TEXT_LEN, "текст")) {
            if (badText != null) {
                throw new WrongDataException("Такой текст уже есть");
            }

            text.setText(data.get("text"));
        }

        try {
            return repository.save(text);
        } catch (DataIntegrityViolationException ex) {
            throw new WrongDataException("Данный текст уже существует");
        }
    }

    private void setTitleAuthorSourceLink(Map<String, String> data, TypeText text) {
        if (existAndNotTooBigException(data, "title", TypeText.TITLE_LEN, "название")) {
            text.setTitle(data.get("title"));
        }

        if (existAndNotTooBigException(data, "author", TypeText.AUTHOR_LEN, "автор")) {
            text.setAuthor(data.get("author"));
        }

        if (existAndNotTooBigException(data, "sourceLink", TypeText.SOURCE_LINK_LEN, "источник")) {
            text.setSourceLink(data.get("sourceLink"));
        }
    }

    @Override
    public void delete(Long id) {
        if (repository.findById(id).isEmpty()) {
            throw new WrongDataException("id");
        }

        repository.deleteById(id);
    }

    @Override
    public long getRandomIndex() {
        long count = countCheck(true);
        Random random = new Random();
        try {
            return random.nextLong(0, count);
        } catch (Exception ex) {
            return -1;
        }
    }

    private static boolean existAndNotTooBigException(
            Map<String, String> data,
            String attribute,
            int attLen,
            String errorMessage
    ) {
        if (dataEmpty(data, attribute)) {
            return false;
        }

        if (tooBig(data.get(attribute), attLen)) {
            throw new TooBigException(errorMessage, attLen);
        }

        return true;
    }

    private static void checkExceptions(Map<String, String> data, String attribute, int attLen, String errorMessage) {
        if (dataEmpty(data, attribute)) {
            throw new EmptyDataException(errorMessage);
        }

        if (tooBig(data.get(attribute), attLen)) {
            throw new TooBigException(errorMessage, attLen);
        }
    }

    private static boolean dataEmpty(Map<String, String> data, String key) {
        return data.containsKey(key) && data.get(key) != null && !data.get(key).isEmpty();
    }

    private static boolean tooBig(String value, int len) {
        return value.length() > len;
    }
}
