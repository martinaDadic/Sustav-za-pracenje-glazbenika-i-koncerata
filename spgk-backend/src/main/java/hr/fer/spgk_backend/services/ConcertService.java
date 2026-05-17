package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.dtos.AttendeeDTO;
import hr.fer.spgk_backend.dtos.ConcertDetailDTO;
import hr.fer.spgk_backend.dtos.ConcertRequestDTO;
import hr.fer.spgk_backend.dtos.ConcertSummaryDTO;
import hr.fer.spgk_backend.models.*;
import hr.fer.spgk_backend.repositories.ArtistRepository;
import hr.fer.spgk_backend.repositories.ConcertOrganizerRepository;
import hr.fer.spgk_backend.repositories.ConcertRepository;
import hr.fer.spgk_backend.repositories.LocationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConcertService {
    private final ConcertRepository concertRepository;
    private final ConcertOrganizerRepository concertOrganizerRepository;
    private final ArtistRepository artistRepository;
    private final LocationRepository locationRepository;

    public ConcertService(ConcertRepository concertRepository, ArtistRepository artistRepository, LocationRepository locationRepository, ConcertOrganizerRepository concertOrganizerRepository) {
        this.concertRepository = concertRepository;
        this.concertOrganizerRepository = concertOrganizerRepository;
        this.artistRepository = artistRepository;
        this.locationRepository = locationRepository;
    }

    public ConcertDetailDTO create(ConcertRequestDTO dto) {
        validateConcertLocationAndTime(dto, null);

        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));

        ConcertOrganizer organizer = concertOrganizerRepository.findById(dto.getOrganizerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found"));

        Location location = new Location();
        getLocationFromDTO(location, dto);
        locationRepository.save(location);

        Concert concert = new Concert();
        getConcertFromDTO(concert, dto, artist, organizer, location);

        concertRepository.save(concert);

        return getById(concert.getId());
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Concert not found"));

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

    public ConcertDetailDTO update(Long id, ConcertRequestDTO dto) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Concert not found"));

        validateConcertLocationAndTime(dto, id);

        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Artist not found"));

        ConcertOrganizer organizer = concertOrganizerRepository.findById(dto.getOrganizerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found"));

        Location location = concert.getLocation();
        getLocationFromDTO(location, dto);
        locationRepository.save(location);

        getConcertFromDTO(concert, dto, artist, organizer, location);

        concertRepository.save(concert);

        return getById(concert.getId());
    }

    private void getConcertFromDTO(Concert concert, ConcertRequestDTO dto, Artist artist, ConcertOrganizer organizer, Location location) {
        concert.setName(dto.getName());
        concert.setDescription(dto.getDescription());
        concert.setDateTime(dto.getDateTime());
        concert.setArtist(artist);
        concert.setOrganizer(organizer);
        concert.setLocation(location);
    }

    private void getLocationFromDTO(Location location, ConcertRequestDTO dto) {
        location.setCountry(dto.getCountry());
        location.setCity(dto.getCity());
        location.setPostalCode(dto.getPostalCode());
        location.setAddress(dto.getAddress());
    }

    private void validateConcertLocationAndTime(ConcertRequestDTO dto, Long excludeId) {
        if (concertRepository.existsByLocationAndDateTime(
                dto.getCity(),
                dto.getPostalCode(),
                dto.getAddress(),
                dto.getDateTime(),
                excludeId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A concert already exists at this location and time");
        }
    }

    public void deleteById(Long id) {
        concertRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Concert not found"));
        concertRepository.deleteById(id);
    }

    public List<ConcertSummaryDTO> searchConcerts(String term) {
        return concertRepository.search(term).stream()
                .map(concert -> new ConcertSummaryDTO(
                        concert.getId(),
                        concert.getName(),
                        concert.getDateTime(),
                        concert.getLocation().getCountry(),
                        concert.getArtist().getName()
                ))
                .collect(Collectors.toList());
    }
}
