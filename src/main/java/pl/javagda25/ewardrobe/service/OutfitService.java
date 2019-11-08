package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.model.ClothType;
import pl.javagda25.ewardrobe.model.Outfit;
import pl.javagda25.ewardrobe.repository.ClothRepository;
import pl.javagda25.ewardrobe.repository.OutfitRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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
        if (optionalCloth.isPresent()) {
            Cloth cloth = optionalCloth.get();

            if (optionalOutfit.isPresent()) {
                Outfit outfit = optionalOutfit.get();
                outfit.setName(optionalOutfit.get().getName());

                ClothType clTypeGiven = cloth.getClothType();
                if (!outfit.checkIfContains(clTypeGiven)) {
                    outfit.getClothSet().add(cloth);
                    outfitRepository.save(outfit);
                }
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

    public void deleteIfNull() {
        List<Outfit> outfitList = outfitRepository.findAll();
        for (Outfit outfit : outfitList) {
            if (outfit.getClothSet().isEmpty()) {
                outfitRepository.deleteById(outfit.getOutfitId());
            }
        }
    }

    public void removeClothFromOutfit(Long outfitId, Long clothId) {
        Optional<Outfit> optionalOutfit = outfitRepository.findById(outfitId);
        Optional<Cloth> optionalCloth = clothRepository.findById(clothId);
        if (optionalOutfit.isPresent()) {
            Outfit outfit = optionalOutfit.get();
            outfit.getClothSet().remove(optionalCloth.get());
            outfitRepository.save(outfit);
        }
    }

    public List<Outfit> findAll() {
        return outfitRepository.findAll();
    }

    public void deteById(Long outfitId) {
        outfitRepository.deleteById(outfitId);
    }
}
