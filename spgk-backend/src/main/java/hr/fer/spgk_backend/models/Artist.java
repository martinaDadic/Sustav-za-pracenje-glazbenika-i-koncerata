package hr.fer.spgk_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "izvodac")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idizvodaca")
    private Long id;

    @Column(name = "nazivizvodaca", nullable = false)
    private String name;

    @Column(name = "opisizvodaca")
    private String description;
}
