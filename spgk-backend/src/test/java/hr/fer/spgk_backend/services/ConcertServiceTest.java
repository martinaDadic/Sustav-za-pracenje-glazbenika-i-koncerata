package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.dtos.ConcertRequestDTO;
import hr.fer.spgk_backend.repositories.ConcertRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {
    @Mock
    private ConcertRepository concertRepository;
    @InjectMocks
    private ConcertService concertService;

    @Test
    void testIfGetByIdThrowsNotFoundWhenConcertDoesNotExist() {
        when(concertRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> concertService.getById(1L));
    }

    @Test
    void testIfFindByIdThrowsNotFoundWhenConcertDoesNotExist() {
        when(concertRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> concertService.deleteById(1L));
    }

    @Test
    void testIfCreateThrowsBadRequestWhenConcertExistsAtSameLocationAndTime() {
        ConcertRequestDTO dto = new ConcertRequestDTO(
                "Rock Night", "Witness the best rock night in your life!", LocalDateTime.now().plusDays(1),
                1L, 1L, "Croatia", "Zagreb", "10000", "Jarun bb"
        );

        when(concertRepository.existsByLocationAndDateTime(
                dto.getCity(), dto.getPostalCode(), dto.getAddress(),
                dto.getDateTime(), null
        )).thenReturn(true);

        assertThrows(ResponseStatusException.class, () -> concertService.create(dto));
    }
}
