package hr.fer.spgk_backend.repositories;

import hr.fer.spgk_backend.models.ConcertOrganizer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertOrganizerRepository extends JpaRepository<ConcertOrganizer, Long> {
}
