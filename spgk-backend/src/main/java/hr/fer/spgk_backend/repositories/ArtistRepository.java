package hr.fer.spgk_backend.repositories;

import hr.fer.spgk_backend.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
