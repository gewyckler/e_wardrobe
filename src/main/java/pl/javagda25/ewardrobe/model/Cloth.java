package pl.javagda25.ewardrobe.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clothId;

    @Enumerated
    private ClothType clothType;

    @Enumerated(value = EnumType.STRING)
    private Brand brand;

    @Lob
    @Column(columnDefinition = "BLOB")
    private Byte[] photo;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Occasion> occasion;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Season season;

}
