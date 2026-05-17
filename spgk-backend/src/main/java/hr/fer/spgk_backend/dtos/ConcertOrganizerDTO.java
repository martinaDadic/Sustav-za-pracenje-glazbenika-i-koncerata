package hr.fer.spgk_backend.dtos;

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

}
