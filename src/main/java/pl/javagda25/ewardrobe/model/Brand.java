package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brandId;

    @OneToMany(mappedBy = "clothId", fetch = FetchType.EAGER)
    private List<Cloth> brandClothList = new ArrayList<>();

    private String name;

    private HashSet<String> usedBrandNames = new HashSet<>();
}
