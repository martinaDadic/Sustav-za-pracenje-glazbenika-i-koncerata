package hr.fer.spgk_backend.controllers;

import hr.fer.spgk_backend.models.Concert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {

    @GetMapping
    public List<Concert> getAll() {
        return List.of();
    }
}
