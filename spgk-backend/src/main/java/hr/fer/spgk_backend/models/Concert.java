package hr.fer.spgk_backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "koncert")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idkoncerta")
    private Long id;

    @Column(name = "nazivkoncerta", nullable = false)
    private String name;

    @Column(name = "vrijemeodrzava", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "opiskoncerta")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idorgkoncerata", nullable = false)
    private ConcertOrganizer organizer;

    @ManyToOne
    @JoinColumn(name = "idlokacije", nullable = false)
    private Location location;

    @ManyToOne
    @JoinColumn(name = "idizvodaca", nullable = false)
    private Artist artist;

}
