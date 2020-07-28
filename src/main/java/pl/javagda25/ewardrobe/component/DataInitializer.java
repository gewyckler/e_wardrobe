package pl.javagda25.ewardrobe.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.javagda25.ewardrobe.model.*;
//import pl.javagda25.ewardrobe.repository.AccountRepository;
//import pl.javagda25.ewardrobe.repository.AccountRoleRepository;
import pl.javagda25.ewardrobe.repository.OccasionRepository;
import pl.javagda25.ewardrobe.repository.SeasonRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private OccasionRepository occasionRepository;
    private SeasonRepository seasonRepository;
//    private AccountRepository accountRepository;
//    private AccountRoleRepository accountRoleRepository;
//    private PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(OccasionRepository occasionRepository, SeasonRepository seasonRepository/*,*/
                           /*AccountRepository accountRepository, AccountRoleRepository accountRoleRepository,
                           PasswordEncoder passwordEncoder*/) {
        this.occasionRepository = occasionRepository;
        this.seasonRepository = seasonRepository;
//        this.accountRepository = accountRepository;
//        this.accountRoleRepository = accountRoleRepository;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (OccasionName occasionName : OccasionName.values()) {
            addDefaultOccasion(occasionName);
        }

        for (SeasonName seasonName : SeasonName.values()) {
            addDefaultSeason(seasonName);
        }
//        addDefaultRole("USER");
//        addDefaultRole("ADMIN");
//
//        addDefaultUser("admin", "admin", "ADMIN", "USER");
//        addDefaultUser("user", "user", "USER");
    }

//    private void addDefaultUser(String username, String password, String... role) {
//        if (!accountRepository.existByUsername(username)) {
//            Account newAccount = new Account();
//            newAccount.setLogin(username);
//            newAccount.setPassword(passwordEncoder.encode(password));
//
//            Set<AccountRole> userRoles = findRoles(role);
//            newAccount.setAccountRoles(userRoles);
//
//            accountRepository.save(newAccount);
//        }
//    }
//
//    private Set<AccountRole> findRoles(String[] roles) {
//        Set<AccountRole> accountRoles = new HashSet<>();
//        for (String role : roles) {
//            accountRoleRepository.findByName(role).ifPresent(accountRoles::add);
//        }
//        return accountRoles;
//    }
//
//    private void addDefaultRole(String role) {
//        if (!accountRoleRepository.existsByName(role)) {
//            AccountRole newRole = new AccountRole();
//            newRole.setName(role);
//            accountRoleRepository.save(newRole);
//        }
//    }

    private void addDefaultSeason(SeasonName seasonName) {
        if (!seasonRepository.existsBySeasonName(seasonName)) {
            Season season = new Season();
            season.setSeasonName(seasonName);
            seasonRepository.save(season);
        }
    }

    private void addDefaultOccasion(OccasionName occasionName) {
        if (!occasionRepository.existsByOccasionName(occasionName)) {
            Occasion occasion = new Occasion();
            occasion.setOccasionName(occasionName);
            occasionRepository.save(occasion);
        }
    }
}
