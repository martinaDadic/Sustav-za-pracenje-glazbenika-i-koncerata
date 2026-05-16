package hr.fer.spgk_backend.repositories;

import hr.fer.spgk_backend.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
