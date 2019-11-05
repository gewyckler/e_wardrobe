package pl.javagda25.ewardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javagda25.ewardrobe.model.Outfit;

@Repository
public interface OutfitRepository extends JpaRepository<Outfit, Long> {
}
