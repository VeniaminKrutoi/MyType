package com.example.mytype.service.user;

import com.example.mytype.model.User;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public interface UserServ {
    List<User> findAll();
    User findById(long id);
    long count();
    List<User> findFromTo(long from, long to);
    User findByIndex(long index);
    String save(Map<String, String> data);
    User findByEmail(String email);
    String checkEmailAndPassword(Map<String, String> data);
}
