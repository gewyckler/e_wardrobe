package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Occasion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long occasionId;

    private String occasionName;

    @ManyToMany(mappedBy = "occasion", fetch = FetchType.EAGER)
    private Set<Cloth> clothListOccasion = new HashSet<>();
}
