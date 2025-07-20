package com.example.mytype.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mytype.model.TypeText;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeTextRepository extends JpaRepository<TypeText, Long> {
    TypeText findByText(String text);

    @Query(value = "SELECT * FROM type_text ORDER BY id LIMIT 1 OFFSET :index", nativeQuery = true)
    TypeText findByIndex(@Param("index") long index);

    @Query(value = "SELECT * FROM type_text ORDER BY id LIMIT :to - :from OFFSET :from", nativeQuery = true)
    List<TypeText> findFromTo(@Param("from") long from, @Param("to") long to);

    @Query(value = "SELECT * FROM type_text WHERE checked = :check", nativeQuery = true)
    List<TypeText> findAllByCheck(@Param("check") boolean checked);

    @Query(value = "SELECT COUNT(*) FROM type_text WHERE checked = :check", nativeQuery = true)
    long countAllByCheck(@Param("check") boolean checked);
}