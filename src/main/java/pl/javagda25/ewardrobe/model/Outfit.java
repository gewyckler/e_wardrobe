package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Outfit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long outfitId;

    private String name;

    @ManyToMany(mappedBy = "outfit", fetch = FetchType.EAGER)
    private Set<Cloth> clothSet = new HashSet<>();

    public boolean checkIfContains(ClothType type) {
        return clothSet.stream().anyMatch(cloth -> cloth.getClothType() == type);
    }
}
