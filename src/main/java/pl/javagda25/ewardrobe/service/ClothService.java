package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.repository.ClothRepository;

@Service
@AllArgsConstructor
public class ClothService {

    private final ClothRepository clothRepository;
}