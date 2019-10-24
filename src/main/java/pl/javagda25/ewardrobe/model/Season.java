package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seasonId;

    @Enumerated
    private SeasonName seasonName;

    @Formula("(SELECT exists (SELECT * FROM season WHERE dayofyear() BETWEEN season_name.getStartDay() AND season_name.getEndDay()))")
    private Boolean inSeason;

    @OneToMany(mappedBy = "clothId", fetch = FetchType.EAGER)
    private List<Cloth> clothListSeason;
}
