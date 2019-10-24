package pl.javagda25.ewardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javagda25.ewardrobe.model.Cloth;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {
}
