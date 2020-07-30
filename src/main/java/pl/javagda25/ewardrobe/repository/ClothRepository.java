package pl.javagda25.ewardrobe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.javagda25.ewardrobe.model.*;

import java.util.List;

@Repository
public interface ClothRepository extends JpaRepository<Cloth, Long> {

    List<Cloth> findAllByBrand(Brand brand);

    List<Cloth> findAllByClothType(ClothType clothType);

    List<Cloth> findAllByOccasion(Occasion occasion);

    List<Cloth> findAllBySeason(Season season);

    @Query(value = "select cloth_id, brand, cloth_type, photo, season_season_id, user_user_id from cloth_occasion co " +
            "join cloth cl on cl.cloth_id = co.cloth_list_occasion_cloth_id " +
            "join occasion occ on co.occasion_occasion_id = occ.occasion_id " +
            "join season sea on sea.season_id = cl.season_season_id " +
            "where cl.brand like ?1 and sea.season_id like ?2 and cl.cloth_type like ?3 ", nativeQuery = true)
    List<Cloth> getMyClothesYo(String brand, Long seasonId, String clothType, Long occasionId);
}
