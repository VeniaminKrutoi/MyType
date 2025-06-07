package com.example.mytype.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TypeText {
    private String text;
    private String author;
    private String sourceLink;
    private int wordSize;
}
