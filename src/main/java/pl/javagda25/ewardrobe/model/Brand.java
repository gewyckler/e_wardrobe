package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brandId;

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    private List<Cloth> brandClothList;

    @Column(nullable = false)
    private String name;
}
