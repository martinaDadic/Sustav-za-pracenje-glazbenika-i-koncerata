package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.dtos.ConcertDetailDTO;
import hr.fer.spgk_backend.dtos.ConcertRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class ConcertIntegrationTest {

    @Autowired
    private ConcertService concertService;

    @Test
    void testSavingAndRetrievingOfTestConcert() {
        ConcertRequestDTO dto = new ConcertRequestDTO(
                "test concert",
                "test description",
                LocalDateTime.now(),
                1L,
                1L,
                "test country",
                "test city",
                "test postal code",
                "test address"
        );

        ConcertDetailDTO concert = concertService.create(dto);
        assertNotNull(concert);

        ConcertDetailDTO savedConcert = concertService.getById(concert.getId());
        assertNotNull(savedConcert);
    }
}