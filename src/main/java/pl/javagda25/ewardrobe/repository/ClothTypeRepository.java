package pl.javagda25.ewardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javagda25.ewardrobe.model.ClothType;

@Repository
public interface ClothTypeRepository extends JpaRepository<ClothType, Long> {
    boolean existsByName(String name);
}
