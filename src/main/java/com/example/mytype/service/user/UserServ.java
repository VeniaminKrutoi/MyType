package com.example.mytype.service.user;

import com.example.mytype.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public interface UserServ {
    List<User> findAll();
    User findById(long id);
    long count();
    List<User> findFromTo(long from, long to);
    User findByIndex(long index);
    ResponseEntity<String> save(Map<String, String> data);
    User findByEmail(String email);
    void checkEmailAndPassword(Map<String, String> data);
    User findByUsername(String username);
}
