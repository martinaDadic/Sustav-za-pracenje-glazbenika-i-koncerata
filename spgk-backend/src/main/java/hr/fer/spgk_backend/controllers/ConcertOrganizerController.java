package hr.fer.spgk_backend.controllers;

import hr.fer.spgk_backend.dtos.ConcertOrganizerDTO;
import hr.fer.spgk_backend.models.ConcertOrganizer;
import hr.fer.spgk_backend.services.ConcertOrganizerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/organizers")
public class ConcertOrganizerController {
    private final ConcertOrganizerService concertOrganizerService;

    public ConcertOrganizerController(ConcertOrganizerService concertOrganizerService) {
        this.concertOrganizerService = concertOrganizerService;
    }

    @GetMapping
    public ResponseEntity<List<ConcertOrganizerDTO>> getConcertOrganizers() {
        return ResponseEntity.ok(concertOrganizerService.findAll());
    }

}
