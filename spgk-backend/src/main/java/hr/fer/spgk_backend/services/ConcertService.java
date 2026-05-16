package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.models.Concert;
import hr.fer.spgk_backend.repositories.ConcertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConcertService {
    private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<Concert> getAll() {
        return concertRepository.findAll();
    }
}
