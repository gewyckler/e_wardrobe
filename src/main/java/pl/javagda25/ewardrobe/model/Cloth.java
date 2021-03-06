package pl.javagda25.ewardrobe.model;

import lombok.*;

import javax.persistence.*;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cloth {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long clothId;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ClothType clothType;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Brand brand;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] photo;

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Account account;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Occasion> occasion = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    private Season season;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "clothSet", fetch = FetchType.EAGER)
    private Set<Outfit> outfit = new HashSet<>();

    public String convertBinImageToString() {
        if (photo != null && photo.length > 0) {
            return Base64.getEncoder().encodeToString(photo);
        }
        return "";
    }
}
