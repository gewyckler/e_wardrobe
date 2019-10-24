package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    @Enumerated
    private SeasonName seasonName;
    private LocalDate startDate;
    private LocalDate endDate;
//    @Formula("(SELECT exists (SELECT * FROM Season WHERE now() BETWEEN startDate AND endDate) as date_result)")
//    @Formula("(Select 1 FROM Season WHERE GETDATE() between startDate AND endDate)")
//    private Boolean inSeason;

    @OneToMany(mappedBy = "clothId", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Cascade(value = org.hibernate.annotations.CascadeType.REMOVE)
    private List<Cloth> clothListSeason;
}
