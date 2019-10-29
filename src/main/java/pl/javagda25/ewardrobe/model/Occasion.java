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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long occasionId;

    @Enumerated(EnumType.STRING)
    private OccasionName occasionName;

    @ManyToMany(mappedBy = "occasion", fetch = FetchType.EAGER)
    private Set<Cloth> clothListOccasion = new HashSet<>();
}
