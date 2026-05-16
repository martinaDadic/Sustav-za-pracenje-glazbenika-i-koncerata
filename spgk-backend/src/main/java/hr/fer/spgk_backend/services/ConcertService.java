package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.dtos.AttendeeDTO;
import hr.fer.spgk_backend.dtos.ConcertDetailDTO;
import hr.fer.spgk_backend.dtos.ConcertSummaryDTO;
import hr.fer.spgk_backend.models.Concert;
import hr.fer.spgk_backend.repositories.ConcertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertService {
    private final ConcertRepository concertRepository;

    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public List<ConcertSummaryDTO> getAll() {
        return concertRepository.findAll().stream()
                .map(concert -> new ConcertSummaryDTO(
                        concert.getId(),
                        concert.getName(),
                        concert.getDateTime(),
                        concert.getLocation().getCountry(),
                        concert.getArtist().getName()
                ))
                .collect(Collectors.toList());
    }

    public ConcertDetailDTO getById(Long id) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found"));

        List<AttendeeDTO> attendees = concert.getAttendees().stream()
                .map(attendee -> new AttendeeDTO(
                        attendee.getId(),
                        attendee.getFirstName(),
                        attendee.getLastName(),
                        attendee.getEmail(),
                        attendee.getLocation().getCity()
                ))
                .collect(Collectors.toList());

        return new ConcertDetailDTO(
                concert.getId(),
                concert.getName(),
                concert.getDateTime(),
                concert.getDescription(),
                concert.getArtist().getName(),
                concert.getLocation().getCountry(),
                concert.getLocation().getCity(),
                concert.getLocation().getAddress(),
                concert.getOrganizer().getFirstName(),
                concert.getOrganizer().getLastName(),
                concert.getOrganizer().getEmail(),
                attendees
        );
    }

    public void deleteById(Long id) {
        concertRepository.deleteById(id);
    }
}
