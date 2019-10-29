package pl.javagda25.ewardrobe.model;

import lombok.Getter;

@Getter
public enum SeasonName {
    ALL_YEAR(1, 365),
    SPRING(80, 171),
    SUMMER(172, 263),
    AUTUMN(264, 361),
    WINTER(362, 79),
    SPRING_SUMMER(80, 263),
    AUTUMN_WINTER(264, 79);

    private Integer startDay;
    private Integer endDay;

    SeasonName(Integer startDay, Integer endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }
}
