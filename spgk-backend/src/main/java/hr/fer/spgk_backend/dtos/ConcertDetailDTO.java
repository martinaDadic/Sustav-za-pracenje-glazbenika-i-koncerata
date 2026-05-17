package hr.fer.spgk_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertDetailDTO {
    private Long id;
    private String name;
    private LocalDateTime dateTime;
    private String description;
    private ArtistDTO artist;
    private String country;
    private String city;
    private String address;
    private ConcertOrganizerDTO organizer;
    private List<AttendeeDTO> attendees;
    private String postalCode;
}
