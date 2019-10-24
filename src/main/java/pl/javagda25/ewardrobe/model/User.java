package pl.javagda25.ewardrobe.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String login;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE)
    private List<Cloth> clothList;
}
