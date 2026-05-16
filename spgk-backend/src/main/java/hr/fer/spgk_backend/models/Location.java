package hr.fer.spgk_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lokacija")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlokacije")
    private Long id;

    @Column(name = "drzava", nullable = false)
    private String country;

    @Column(name = "grad", nullable = false)
    private String city;

    @Column(name = "postanskibroj", nullable = false)
    private String postalCode;

    @Column(name = "adresa", nullable = false)
    private String address;
}
