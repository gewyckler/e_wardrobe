package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.javagda25.ewardrobe.model.*;
import pl.javagda25.ewardrobe.repository.ClothRepository;
import pl.javagda25.ewardrobe.repository.OccasionRepository;
import pl.javagda25.ewardrobe.repository.SeasonRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            // edytujesz ciuch i nie ustawiles zadnego pliku
            if (file.isEmpty() && cloth.getClothId() != null) {
                Optional<Cloth> clothOptional = clothRepository.findById(cloth.getClothId());
                if (clothOptional.isPresent()) {
                    Cloth oldCloth = clothOptional.get();
                    if (oldCloth.getPhoto() != null && oldCloth.getPhoto().length > 0) {
                        // ustaw stare zdjęcie
                        cloth.setPhoto(oldCloth.getPhoto());
                    }
                }
            } else {
                cloth.setPhoto(file.getBytes());
            }

            cloth.getOccasion().add(optionalOccasion.get());
            cloth.setSeason(optionalSeason.get());
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        clothRepository.save(cloth);
    }

    public List<Cloth> getAll(Brand brandName, ClothType clothType, Long occasionId, Long seasonId) {
        List<Cloth> filterList = clothRepository.findAll();
//        return clothRepository.findAll(new ClothSpecification(new SearchCriteria(brandName, clothType, occasionId, seasonId)));
//        return clothRepository.getMyClothesYo(String.valueOf(brandName), seasonId, String.valueOf(clothType), occasionId);
        if (brandName != null || clothType != null || occasionId != null || seasonId != null) {
            if (brandName != null) {
                filterList = filterList.stream().filter(brand -> brand.getBrand().equals(brandName)).collect(Collectors.toList());
            }
            if (clothType != null) {
                filterList = filterList.stream().filter(type -> type.getClothType().equals(clothType)).collect(Collectors.toList());
            }
            if (occasionId != null) {
                Occasion occasion = occasionRepository.getOne(occasionId);
                filterList = new ArrayList(occasion.getClothListOccasion());
            }
            if (seasonId != null) {
                Season season = seasonRepository.getOne(seasonId);
                filterList = new ArrayList(season.getClothListSeason());
            }
            return filterList;
        }
        return filterList;
    }

    public Optional<Cloth> getById(Long clothId) {
        return clothRepository.findById(clothId);
    }

    public void deleteById(Long clothId) {
        clothRepository.deleteById(clothId);
    }

    public List<Cloth> getAllNoFilter() {
        return clothRepository.findAll();
    }
}
