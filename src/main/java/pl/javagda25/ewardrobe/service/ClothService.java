package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.javagda25.ewardrobe.model.*;
import pl.javagda25.ewardrobe.repository.*;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClothService {

    private final ClothRepository clothRepository;
    private final OccasionRepository occasionRepository;
    private final SeasonRepository seasonRepository;
    private final BrandRepository brandRepository;
    private final ClothTypeRepository clothTypeRepository;

    public void addCloth(Cloth cloth, Long clothTypeId, Long brandId, MultipartFile file, Long occasionId, Long seasonId) {

        Optional<ClothType> optionalClothType = clothTypeRepository.findById(clothTypeId);
        Optional<Season> optionalSeason = seasonRepository.findById(seasonId);
        Optional<Occasion> optionalOccasion = occasionRepository.findById(occasionId);
        Optional<Brand> optionalBrand = brandRepository.findById(brandId);

        if (!optionalOccasion.isPresent()) {
            throw new EntityNotFoundException("ocassion, id " + occasionId);
        }
        if (!optionalSeason.isPresent()) {
            throw new EntityNotFoundException("season, id " + seasonId);
        }
        if (!optionalBrand.isPresent()) {
            throw new EntityNotFoundException("brand, id " + brandId);
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

            cloth.setClothType(optionalClothType.get());
            cloth.setBrand(optionalBrand.get());
            cloth.getOccasion().add(optionalOccasion.get());
            cloth.setSeason(optionalSeason.get());
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        clothRepository.save(cloth);
    }

    public List<Cloth> getAll(Long brandId, Long clothTypeId, Long occasionId, Long seasonId) {
        List<Cloth> filterList = clothRepository.findAll();
//        return clothRepository.findAll(new ClothSpecification(new SearchCriteria(brandName, clothType, occasionId, seasonId)));
//        return clothRepository.getMyClothesYo(String.valueOf(brandName), seasonId, String.valueOf(clothType), occasionId);
        if (brandId != null || clothTypeId != null || occasionId != null || seasonId != null) {
            if (brandId != null) {
                Brand brand = brandRepository.getOne(brandId);
                filterList = new ArrayList<>(brand.getBrandClothList());
//                filterList = filterList.stream().filter(brand -> brand.getBrand().equals(brandName)).collect(Collectors.toList());
            }
            if (clothTypeId != null) {
                ClothType clothType = clothTypeRepository.getOne(clothTypeId);
                filterList = new ArrayList<>(clothType.getClothList());
//                filterList = filterList.stream().filter(type -> type.getClothType().equals(clothType)).collect(Collectors.toList());
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
