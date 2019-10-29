package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Occasion;
import pl.javagda25.ewardrobe.repository.OccasionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OccasionService {
    private final OccasionRepository occasionRepository;

    public List<Occasion> getAll() {
        return occasionRepository.findAll();
    }
}
