package pl.javagda25.ewardrobe.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.javagda25.ewardrobe.model.Occasion;
import pl.javagda25.ewardrobe.model.OccasionName;
import pl.javagda25.ewardrobe.model.Season;
import pl.javagda25.ewardrobe.model.SeasonName;
import pl.javagda25.ewardrobe.repository.OccasionRepository;
import pl.javagda25.ewardrobe.repository.SeasonRepository;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private OccasionRepository occasionRepository;
    private SeasonRepository seasonRepository;

    @Autowired
    public DataInitializer(OccasionRepository occasionRepository, SeasonRepository seasonRepository) {
        this.occasionRepository = occasionRepository;
        this.seasonRepository = seasonRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (OccasionName occasionName : OccasionName.values()) {
            addDefaultOccasion(occasionName);
        }

        for (SeasonName seasonName : SeasonName.values()) {
            addDefaultSeason(seasonName);
        }
    }

    private void addDefaultSeason(SeasonName seasonName) {
        if (!seasonRepository.existsBySeasonName(seasonName)) {
            Season season = new Season();
            season.setSeasonName(seasonName);
            seasonRepository.save(season);
        }
    }

    private void addDefaultOccasion(OccasionName occasionName) {
        if (!occasionRepository.existsByOccasionName(occasionName)) {
            Occasion occasion = new Occasion();
            occasion.setOccasionName(occasionName);
            occasionRepository.save(occasion);
        }
    }
}
