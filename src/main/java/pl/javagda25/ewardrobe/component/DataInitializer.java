package pl.javagda25.ewardrobe.component;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.javagda25.ewardrobe.model.*;
import pl.javagda25.ewardrobe.model.enums.OccasionName;
import pl.javagda25.ewardrobe.model.enums.SeasonName;
import pl.javagda25.ewardrobe.repository.BrandRepository;
import pl.javagda25.ewardrobe.repository.OccasionRepository;
import pl.javagda25.ewardrobe.repository.SeasonRepository;

//import org.springframework.security.crypto.password.PasswordEncoder;
//import pl.javagda25.ewardrobe.repository.AccountRepository;
//import pl.javagda25.ewardrobe.repository.AccountRoleRepository;

@AllArgsConstructor
@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private final OccasionRepository occasionRepository;
    private final SeasonRepository seasonRepository;
    private final BrandRepository brandRepository;
//    private AccountRepository accountRepository;
//    private AccountRoleRepository accountRoleRepository;
//    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        for (OccasionName occasionName : OccasionName.values()) {
            addDefaultOccasion(String.valueOf(occasionName));
        }

        for (SeasonName seasonName : SeasonName.values()) {
            addDefaultSeason(seasonName);
        }

        addDefaultBrand("NO NAME");
        //TO DO: add defoult brand object!

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

    private void addDefaultOccasion(String occasionName) {
        if (!occasionRepository.existsByOccasionName(occasionName)) {
            Occasion occasion = new Occasion();
            occasion.setOccasionName(occasionName);
            occasionRepository.save(occasion);
        }
    }

    private void addDefaultBrand(String brandName) {
        if (!brandRepository.existsBrandByName(brandName)) {
            Brand newBrand = new Brand();
            newBrand.setName(brandName);
            brandRepository.save(newBrand);
        }
    }
}
