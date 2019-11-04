package pl.javagda25.ewardrobe.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.javagda25.ewardrobe.model.Brand;
import pl.javagda25.ewardrobe.model.ClothType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCriteria {
    Brand brandName;
    ClothType clothType;
    Long occasionId;
    Long seasonId;
}
