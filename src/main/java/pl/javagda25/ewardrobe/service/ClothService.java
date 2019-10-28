package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.repository.ClothRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;

    public void addCloth(Cloth cloth) {
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
