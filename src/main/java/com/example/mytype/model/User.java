package com.example.mytype.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    public static final int USERNAME_LEN = 256;
    public static final int EMAIL_LEN = 128;
    public static final int PASSWORD_LEN = 256;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = USERNAME_LEN)
    private String username;

    @Column(unique = true, nullable = false, length = EMAIL_LEN)
    private String email;

    @Column(length = PASSWORD_LEN)
    private String password;

    private Integer typeResults;
    private Integer typeCount;

    private Long time;

    @Column(nullable = false)
    private String role;
}
