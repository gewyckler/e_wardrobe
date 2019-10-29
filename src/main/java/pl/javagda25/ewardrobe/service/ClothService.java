package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.model.Occasion;
import pl.javagda25.ewardrobe.model.Season;
import pl.javagda25.ewardrobe.repository.ClothRepository;
import pl.javagda25.ewardrobe.repository.OccasionRepository;
import pl.javagda25.ewardrobe.repository.SeasonRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
    private final OccasionRepository occasionRepository;
    private final SeasonRepository seasonRepository;

    public void addCloth(Cloth cloth, Long occasionId, Long seasonId, MultipartFile file) {
        Optional<Season> optionalSeason = seasonRepository.findById(seasonId);
        Optional<Occasion> optionalOccasion = occasionRepository.findById(occasionId);
        if (!optionalOccasion.isPresent()) {
            throw new EntityNotFoundException("ocassion, id " + occasionId);
        }
        if (!optionalSeason.isPresent()) {
            throw new EntityNotFoundException("season, id " + seasonId);
        }

        try {
            cloth.getOccasion().add(optionalOccasion.get());
            cloth.setSeason(optionalSeason.get());

            cloth.setPhoto(file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        clothRepository.save(cloth);
    }

    public List<Cloth> getAll() {
        return clothRepository.findAll();
    }

    public Optional<Cloth> getById(Long clothId) {
        return clothRepository.findById(clothId);
    }

    public void deleteById(Long clothId) {
        clothRepository.deleteById(clothId);
    }
}
