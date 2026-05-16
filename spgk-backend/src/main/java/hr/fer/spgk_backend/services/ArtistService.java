package hr.fer.spgk_backend.services;

import hr.fer.spgk_backend.dtos.ArtistDTO;
import hr.fer.spgk_backend.models.Artist;
import hr.fer.spgk_backend.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistDTO> findAll() {
        return artistRepository.findAll().stream()
                .map(artist -> new ArtistDTO(
                artist.getId(),
                        artist.getName()
        )).collect(Collectors.toList());
    }

}
