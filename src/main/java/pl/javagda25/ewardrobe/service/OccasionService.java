package pl.javagda25.ewardrobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Occasion;
import pl.javagda25.ewardrobe.repository.OccasionRepository;

import java.util.List;

@Service
public class OccasionService {
    @Autowired
    private OccasionRepository occasionRepository;

    public List<Occasion> getAll() {
        return occasionRepository.findAll();
    }

    public void addOccasion(Occasion newOccasion) {
        newOccasion.setOccasionName(newOccasion.getOccasionName().toUpperCase());
        occasionRepository.save(newOccasion);
    }
}
