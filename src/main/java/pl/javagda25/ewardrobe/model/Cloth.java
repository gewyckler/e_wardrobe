package pl.javagda25.ewardrobe.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clothId;

    @Enumerated
    private ClothType clothType;

    private Brand brand;


    private String photo;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Occasion occasion;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Season season;
}
