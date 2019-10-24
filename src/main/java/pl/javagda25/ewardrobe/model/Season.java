package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Enumerated(EnumType.STRING)
    private SeasonName seasonName;

    public Boolean inSeason() {
        int currentDay = LocalDate.now().getDayOfYear();
        if (currentDay <= seasonName.getEndDay() && currentDay >= seasonName.getStartDay()) {
            return true;
        }
        return false;
    }

    public Season(SeasonName seasonName) {
        this.seasonName = seasonName;
    }

    @OneToMany(mappedBy = "clothId", fetch = FetchType.EAGER)
    private List<Cloth> clothListSeason;
}
