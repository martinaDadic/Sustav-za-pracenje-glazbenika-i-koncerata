package hr.fer.spgk_backend.repositories;

import hr.fer.spgk_backend.models.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
    @Query("SELECT COUNT(c) > 0 FROM Concert c WHERE " +
            "c.location.city = :city AND " +
            "c.location.postalCode = :postalCode AND " +
            "c.location.address = :address AND " +
            "c.dateTime = :dateTime AND " +
            "(:excludeId IS NULL OR c.id <> :excludeId)")
    boolean existsByLocationAndDateTime(
            @Param("city") String city,
            @Param("postalCode") String postalCode,
            @Param("address") String address,
            @Param("dateTime") LocalDateTime dateTime,
            @Param("excludeId") Long excludeId
    );
}
