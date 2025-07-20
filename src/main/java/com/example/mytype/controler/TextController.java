package com.example.mytype.controler;

import com.example.mytype.exceptions.WrongDataException;
import com.example.mytype.model.TypeText;
import com.example.mytype.service.text.TextServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/texts")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class TextController {
    private final TextServiceImpl service;

    @PostMapping
    public TypeText add(@RequestBody Map<String, String> data) {
        return service.save(data);
    }

    @GetMapping
    public List<TypeText> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public TypeText get(@PathVariable("id") String id) {
        try {
            return service.findById(Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new WrongDataException("Неверный id");
        }
    }

    @GetMapping("unchecked")
    public List<TypeText> findAllByUncheck() {
        return service.findAllByCheck(false);
    }

    @GetMapping("checked")
    public List<TypeText> findAllByCheck() {
        return service.findAllByCheck(true);
    }

    @PatchMapping("/{id}")
    public TypeText update(@PathVariable("id") String id, @RequestBody Map<String, String> data) {
        try {
            return service.update(Long.parseLong(id), data);
        } catch (NumberFormatException e) {
            throw new WrongDataException("Неверный id");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        try {
            service.delete(Long.parseLong(id));
        } catch (NumberFormatException e) {
            throw new WrongDataException("Неверный id");
        }
    }
}
