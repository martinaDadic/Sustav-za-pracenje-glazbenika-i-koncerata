package hr.fer.spgk_backend.controllers;

import hr.fer.spgk_backend.dtos.ConcertDetailDTO;
import hr.fer.spgk_backend.dtos.ConcertRequestDTO;
import hr.fer.spgk_backend.dtos.ConcertSummaryDTO;
import hr.fer.spgk_backend.services.ConcertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/concerts")
public class ConcertController {
    private final ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    /*@PostMapping
    public ResponseEntity<ConcertDetailDTO> create(@RequestBody ConcertRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(concertService.create(dto));
    }*/

    @GetMapping
    public List<ConcertSummaryDTO> getAll() {
        return concertService.getAll();
    }

    @GetMapping("/{id}")
    public ConcertDetailDTO getById(@PathVariable Long id) {
        return concertService.getById(id);
    }

   /* @PutMapping("/{id}")
    public ResponseEntity<ConcertDetailDTO> update(@PathVariable Long id, @RequestBody ConcertRequestDTO dto) {
        return ResponseEntity.ok(concertService.update(id, dto));
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        concertService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
