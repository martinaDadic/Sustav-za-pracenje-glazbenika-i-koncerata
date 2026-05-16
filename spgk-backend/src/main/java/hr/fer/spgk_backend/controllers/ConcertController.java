package hr.fer.spgk_backend.controllers;

import hr.fer.spgk_backend.dtos.ConcertDetailDTO;
import hr.fer.spgk_backend.dtos.ConcertSummaryDTO;
import hr.fer.spgk_backend.services.ConcertService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {
    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping
    public List<ConcertSummaryDTO> getAll() {
        return concertService.getAll();
    }

    @GetMapping("/{id}")
    public ConcertDetailDTO getById(@PathVariable Long id) {
        return concertService.getById(id);
    }


}
