package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.model.ClothType;
import pl.javagda25.ewardrobe.model.Outfit;
import pl.javagda25.ewardrobe.repository.ClothRepository;
import pl.javagda25.ewardrobe.repository.OutfitRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OutfitService {
    private final OutfitRepository outfitRepository;
    private final ClothRepository clothRepository;

    public void save(Outfit outfit) {
        outfitRepository.save(outfit);
    }

    public void addClothToOutfit(Long outfitId, Long clothId) {
        Optional<Outfit> optionalOutfit = outfitRepository.findById(outfitId);
        Optional<Cloth> optionalCloth = clothRepository.findById(clothId);
        if (optionalCloth.isPresent() && optionalOutfit.isPresent()) {
            Cloth cloth = optionalCloth.get();
            Outfit outfit = optionalOutfit.get();

            ClothType clTypeGiven = cloth.getClothType();
            if (!outfit.checkIfContains(clTypeGiven)) {
                outfit.getClothSet().add(cloth);
                outfitRepository.save(outfit);
            }
        }
    }

    public Outfit findById(Long outfitId) {
        Optional<Outfit> optionalOutfit = outfitRepository.findById(outfitId);
        if (optionalOutfit.isPresent()) {
            return optionalOutfit.get();
        }
        throw new EntityNotFoundException("not found, id " + outfitId);
    }
}
