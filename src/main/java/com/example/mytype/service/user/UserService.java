package com.example.mytype.service.user;

import com.example.mytype.model.User;

import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public interface UserService {
    List<User> findAll();
    User findById(long id);
    long count();
    List<User> findFromTo(long from, long to);
    User findByIndex(long index);
    User save(Map<String, String> data);
    User findByEmail(String email);
    User checkEmailAndPassword(Map<String, String> data);
    User findByUsername(String username);
    User update(Long id, Map<String, String> data);
    void delete(Long id);
}


//w            wwwwwwwwwwwwwww
//w            w
//wwwwwwwwwwwwwwwwwwwwwwwwwww
//            w             w
//wwwwwwwwwwwww             w