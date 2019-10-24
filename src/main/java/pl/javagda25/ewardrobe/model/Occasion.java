package pl.javagda25.ewardrobe.model;

import javax.persistence.*;
import java.util.List;

public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "occasion", fetch = FetchType.EAGER)
    private List<Cloth> clothListOccasion;
}
