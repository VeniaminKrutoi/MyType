package com.example.mytype.service.tries;

import com.example.mytype.model.TypeText;
import com.example.mytype.model.TypeTry;
import com.example.mytype.model.User;
import com.example.mytype.repository.TypeTryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TryServiceImpl implements TryService {
    private final TypeTryRepository repository;


    @Override
    public List<TypeTry> findAll() {
        return repository.findAll();
    }

    @Override
    public TypeTry save(TypeTry typeTry) {
        return repository.save(typeTry);
    }

    @Override
    public TypeTry update(TypeTry typeTry) {
        return repository.save(typeTry);
    }

    @Override
    public void delete(TypeTry typeTry) {
        repository.delete(typeTry);
    }

    @Override
    public List<TypeTry> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public List<TypeTry> findByText(TypeText text) {
        return repository.findByText(text);
    }
}
