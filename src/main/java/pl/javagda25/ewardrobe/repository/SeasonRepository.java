package pl.javagda25.ewardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javagda25.ewardrobe.model.Season;

import java.util.Optional;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
}
