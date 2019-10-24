package pl.javagda25.ewardrobe.model;

import javax.persistence.*;

public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private ClothType clothType;

    private Brand brand;

    private Season season;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private Occasion occasion;
    private String photo;

}
