package com.example.mytype.repository;

import com.example.mytype.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
    User findByEmail(String email);
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users ORDER BY id LIMIT 1 OFFSET :index", nativeQuery = true)
    User findByIndex(@Param("index") long index);

    @Query(value = "SELECT * FROM users u JOIN (SELECT user_id, AVG(spm) AS avg_spm FROM type_tries GROUP BY user_id) t ON u.id = t.user_id ORDER BY t.avg_spm DESC LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<User> findFromTo(@Param("limit") long limit, @Param("offset") long offset);
}
