package com.example.mytype.repository;

import com.example.mytype.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRep extends JpaRepository<User, Long> {
    User findById(long id);
    User findByEmail(String email);
    User findByUsername(String username);

    @Query(value = "SELECT * FROM type_text ORDER BY id LIMIT 1 OFFSET :index", nativeQuery = true)
    User findByIndex(@Param("index") long index);

    @Query(value = "SELECT * FROM type_text ORDER BY id LIMIT to - from OFFSET :from", nativeQuery = true)
    List<User> findFromTo(@Param("from") long from, @Param("to") long to);


}
