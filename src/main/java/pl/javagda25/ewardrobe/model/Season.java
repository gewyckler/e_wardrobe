package pl.javagda25.ewardrobe.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    @Enumerated
    private SeasonName seasonName;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "clothId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    private List<Cloth> clothListSeason;
}
