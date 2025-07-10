package com.example.mytype.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Entity
@Table(name="type_text")
@AllArgsConstructor
public class TypeText {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private String author;
    private String sourceLink;

    public TypeText() {

    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("text", text);
        map.put("author", author);
        map.put("sourceLink", sourceLink);
        return map;
    }
}
