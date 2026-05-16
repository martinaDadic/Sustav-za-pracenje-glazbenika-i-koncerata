package hr.fer.spgk_backend.controllers;

import hr.fer.spgk_backend.dtos.ArtistDTO;
import hr.fer.spgk_backend.models.Artist;
import hr.fer.spgk_backend.services.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getAll() {
        return ResponseEntity.ok().body(artistService.findAll());
    }
}
