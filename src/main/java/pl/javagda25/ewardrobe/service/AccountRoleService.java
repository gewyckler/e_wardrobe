//package pl.javagda25.ewardrobe.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import pl.javagda25.ewardrobe.model.AccountRole;
//import pl.javagda25.ewardrobe.repository.AccountRoleRepository;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//@Service
//public class AccountRoleService {
//
//    @Autowired
//    private AccountRoleRepository accountRoleRepository;
//
//    @Value("USER, ADMIN")
//    private String[] defaultRoles;
//
//    public Set<AccountRole> getDefaultRoles() {
//        Set<AccountRole> accountRoles = new HashSet<>();
//
//        for (String roles : defaultRoles) {
//            Optional<AccountRole> optionalAccountRole = accountRoleRepository.findByName(roles);
//            optionalAccountRole.ifPresent(accountRoles::add);
//        }
//        return accountRoles;
//    }
//
//    public List<AccountRole> getAll() {
//        return accountRoleRepository.findAll();
//    }
//}
