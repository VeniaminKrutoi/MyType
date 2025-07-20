package com.example.mytype.model;

import jakarta.persistence.*;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "type_text")
public class TypeText {
    public static final int TITLE_LEN = 256;
    public static final int TEXT_LEN = 1000;
    public static final int AUTHOR_LEN = 256;
    public static final int SOURCE_LINK_LEN = 256;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = TITLE_LEN)
    private String title;

    @Column(nullable = false, unique = true, length = TEXT_LEN)
    private String text;

    @Column(length = AUTHOR_LEN)
    private String author;

    @Column(length = SOURCE_LINK_LEN)
    private String sourceLink;

    @Column(nullable = false)
    private boolean checked;

    public TypeText() {

    }

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("text", text);
        map.put("author", author);
        map.put("sourceLink", sourceLink);
        map.put("checked", checked);
        return map;
    }
}
