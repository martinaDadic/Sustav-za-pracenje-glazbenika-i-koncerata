package hr.fer.spgk_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orgkoncerata")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertOrganizer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idorgkoncerata")
    private Long id;

    @Column(name = "emailadresa", nullable = false, unique = true)
    private String email;

    @Column(name = "hashlozinke", nullable = false)
    private String passwordHash;

    @Column(name = "ime", nullable = false)
    private String firstName;

    @Column(name = "prezime", nullable = false)
    private String lastName;

    @Column(name = "korisnickoime", nullable = false, unique = true)
    private String username;
}
