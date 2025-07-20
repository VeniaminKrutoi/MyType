package com.example.mytype.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.ManyToAny;

@Entity
@Data
@AllArgsConstructor
public class TypeTry {
    public TypeTry(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    @Transient
    @ManyToAny
    private TypeText text;

    private Integer spm;
    private Long time;
}
