package pl.javagda25.ewardrobe.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountRoleId;

    private String name;
}
