package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.ClothType;
import pl.javagda25.ewardrobe.repository.ClothTypeRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClothTypeService {
    private final ClothTypeRepository clothTypeRepository;

    public List<ClothType> getAll() {
        return clothTypeRepository.findAll();
    }
}
