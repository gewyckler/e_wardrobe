package pl.javagda25.ewardrobe.specification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import pl.javagda25.ewardrobe.model.Cloth;
import pl.javagda25.ewardrobe.model.dto.SearchCriteria;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ClothSpecification implements Specification<Cloth> {

    @Autowired
    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate
            (Root<Cloth> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicateList = new ArrayList<>();
        if (criteria.getBrandName() != null) {
            predicateList.add(builder.equal(
                    root.<String>get("brand"), criteria.getBrandName()));
        }
        if (criteria.getClothType() != null) {
            predicateList.add(builder.equal(
                    root.<String>get("clothType"), criteria.getClothType()));
        }
        if (criteria.getSeasonId() != null) {
            predicateList.add(builder.equal(
                    root.<String>get("season"), criteria.getSeasonId()));
        }
        if (criteria.getSeasonId() != null) {
            predicateList.add(builder.equal(
                    root.<String>get("occasion"), criteria.getOccasionId()));
        }
        if (predicateList.isEmpty()) {
            return null;
        }
        Predicate finalPredicate = predicateList.get(0);
        predicateList.remove(0);
        while (!predicateList.isEmpty()) {
            Predicate predicate = predicateList.get(0);
            predicateList.remove(0);
            finalPredicate = builder.and(finalPredicate, predicate);
        }
        return finalPredicate;
    }
}