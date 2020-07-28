//package pl.javagda25.ewardrobe.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.Cascade;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.Size;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Account {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long accountId;
//
//    @NotEmpty
//    private String login;
//
//    @NotEmpty
//    @Size(min = 5)
//    private String password;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
//    @Cascade(value = org.hibernate.annotations.CascadeType.DETACH)
//    private Set<AccountRole> accountRoles;
//
//    private boolean isLocked;
//
//    @OneToMany(mappedBy = "clothId", fetch = FetchType.EAGER)
//    private List<Cloth> clothListUser;
//
//    public boolean isAdmin() {
//        return accountRoles.stream()
//                .anyMatch(accountRole -> accountRole.getName().equals("ADMIN"));
//    }
//}
