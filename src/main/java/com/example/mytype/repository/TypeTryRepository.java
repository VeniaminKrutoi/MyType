package com.example.mytype.repository;

import com.example.mytype.model.TypeText;
import com.example.mytype.model.TypeTry;
import com.example.mytype.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeTryRepository extends JpaRepository<TypeTry, Long> {
    List<TypeTry> findByText(TypeText text);
    List<TypeTry> findByUser(User user);

}
