package hr.fer.spgk_backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertRequestDTO {
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private Long artistId;
    private Long organizerId;
    private String country;
    private String city;
    private String postalCode;
    private String address;
}
