package hr.fer.spgk_backend.dtos;

import hr.fer.spgk_backend.models.ConcertOrganizer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOrganizerDTO {
    private Long id;
    private String firstName;
    private String lastName;

    public static ConcertOrganizerDTO fromOrganizer(ConcertOrganizer organizer) {
        return new ConcertOrganizerDTO(organizer.getId(), organizer.getFirstName(), organizer.getLastName());
    }

}
