package pl.javagda25.ewardrobe.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Season;
import pl.javagda25.ewardrobe.repository.SeasonRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    public List<Season> getAll() {
        return seasonRepository.findAll();
    }
}
