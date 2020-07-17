package pl.javagda25.ewardrobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Season;
import pl.javagda25.ewardrobe.repository.SeasonRepository;

import java.util.List;

@Service
public class SeasonService {
    @Autowired
    private SeasonRepository seasonRepository;

    public List<Season> getAll() {
        return seasonRepository.findAll();
    }
}
