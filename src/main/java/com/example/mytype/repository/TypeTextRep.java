package com.example.mytype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mytype.model.TypeText;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypeTextRep extends JpaRepository<TypeText,Long>{
    TypeText findByText(String text);

    @Query(value = "SELECT * FROM type_text ORDER BY id LIMIT 1 OFFSET :index", nativeQuery = true)
    TypeText getByIndex(@Param("index") long index);
}