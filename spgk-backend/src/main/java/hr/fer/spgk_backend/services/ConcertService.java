package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.dtos.AttendeeDTO;
import hr.fer.spgk_backend.dtos.ConcertDetailDTO;
import hr.fer.spgk_backend.dtos.ConcertRequestDTO;
import hr.fer.spgk_backend.dtos.ConcertSummaryDTO;
import hr.fer.spgk_backend.models.Artist;
import hr.fer.spgk_backend.models.Concert;
import hr.fer.spgk_backend.models.ConcertOrganizer;
import hr.fer.spgk_backend.models.Location;
import hr.fer.spgk_backend.repositories.ArtistRepository;
import hr.fer.spgk_backend.repositories.ConcertOrganizerRepository;
import hr.fer.spgk_backend.repositories.ConcertRepository;
import hr.fer.spgk_backend.repositories.LocationRepository;
import org.springframework.stereotype.Service;

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
        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        ConcertOrganizer organizer = concertOrganizerRepository.findById(dto.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

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

    public ConcertDetailDTO update(Long id, ConcertRequestDTO dto) {
        Concert concert = concertRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Concert not found"));

        Artist artist = artistRepository.findById(dto.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        ConcertOrganizer organizer = concertOrganizerRepository.findById(dto.getOrganizerId())
                .orElseThrow(() -> new RuntimeException("Organizer not found"));

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

    public void deleteById(Long id) {
        concertRepository.deleteById(id);
    }
}
