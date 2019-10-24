package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String login;

    @OneToMany(mappedBy = "clothId", fetch = FetchType.EAGER)
    private List<Cloth> clothListUser;
}
