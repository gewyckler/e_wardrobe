package pl.javagda25.ewardrobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Account;
import pl.javagda25.ewardrobe.model.AccountRole;
import pl.javagda25.ewardrobe.repository.AccountRepository;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);

        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();

            String[] roles = account.getAccountRoles()
                    .stream()
                    .map(AccountRole::getName)
                    .toArray(String[]::new);

            return User.builder()
                    .username(account.getLogin())
                    .password(account.getPassword())
                    .roles(roles)
                    .accountLocked(account.isLocked())
                    .build();
        }
        throw new UsernameNotFoundException("Username not found.");
    }
}
