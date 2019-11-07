package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

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

    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Cloth> clothSet = new HashSet<>();

    public boolean checkIfContains(ClothType givenType) {
        return clothSet.stream().anyMatch(cloth -> cloth.getClothType().equals(givenType));
    }
}
