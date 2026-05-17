package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.dtos.ConcertOrganizerDTO;
import hr.fer.spgk_backend.repositories.ConcertOrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertOrganizerService {
    private final ConcertOrganizerRepository concertOrganizerRepository;

    public ConcertOrganizerService(ConcertOrganizerRepository concertOrganizerRepository) {
        this.concertOrganizerRepository = concertOrganizerRepository;
    }

    public List<ConcertOrganizerDTO> findAll() {
        return concertOrganizerRepository.findAll().stream()
                .map(concertOrganizer -> new ConcertOrganizerDTO(
                        concertOrganizer.getId(),
                        concertOrganizer.getFirstName(),
                        concertOrganizer.getLastName()
                )).collect(Collectors.toList());
    }
}
