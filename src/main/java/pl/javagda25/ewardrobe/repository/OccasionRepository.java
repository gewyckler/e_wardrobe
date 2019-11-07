package pl.javagda25.ewardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javagda25.ewardrobe.model.Occasion;
import pl.javagda25.ewardrobe.model.OccasionName;

@Repository
public interface OccasionRepository extends JpaRepository<Occasion, Long> {
    boolean existsByOccasionName(OccasionName name);
}
