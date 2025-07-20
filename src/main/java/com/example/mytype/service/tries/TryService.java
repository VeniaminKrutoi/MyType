package com.example.mytype.service.tries;

import com.example.mytype.model.TypeText;
import com.example.mytype.model.TypeTry;
import com.example.mytype.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TryService {
    List<TypeTry> findAll();
    TypeTry save(TypeTry typeTry);
    TypeTry update(TypeTry typeTry);
    void delete(TypeTry typeTry);

    List<TypeTry> findByUser(User user);
    List<TypeTry> findByText(TypeText text);
}
