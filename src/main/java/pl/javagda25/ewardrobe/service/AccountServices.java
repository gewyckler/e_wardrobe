package pl.javagda25.ewardrobe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.javagda25.ewardrobe.model.Account;
import pl.javagda25.ewardrobe.model.AccountRole;
import pl.javagda25.ewardrobe.repository.AccountRepository;
import pl.javagda25.ewardrobe.repository.AccountRoleRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class AccountServices {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private AccountRoleService accountRoleService;
    private AccountRoleRepository accountRoleRepository;

    @Autowired
    public AccountServices(AccountRepository accountRepository, PasswordEncoder passwordEncoder,
                           AccountRoleService accountRoleService, AccountRoleRepository accountRoleRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRoleService = accountRoleService;
        this.accountRoleRepository = accountRoleRepository;
    }

    public boolean register(Account account) {
        if (accountRepository.existByUsername(account.getLogin())) {
            return false;
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setAccountRoles(accountRoleService.getDefaultRoles());

        accountRepository.save(account);
        return true;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public void deleteById(Long acountId) {
        if (accountRepository.existsById(acountId)) {
            Account account = accountRepository.getOne(acountId);
            if (!account.isAdmin()) {
                accountRepository.deleteById(acountId);
            }
        }
    }

    public Optional<Account> findById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public void toggleLock(Long accountId) {
        if (accountRepository.existsById(accountId)) {
            Account account = accountRepository.getOne(accountId);
            if (!account.isAdmin()) {
                account.setLocked(!account.isLocked());
                accountRepository.save(account);
            }
        }
    }

    public void editRole(Long accuntId, HttpServletRequest request) {
        if (accountRepository.existsById(accuntId)) {
            Account account = accountRepository.getOne(accuntId);

            Map<String, String[]> formParameters = request.getParameterMap();
            Set<AccountRole> newColelctionOfRoles = new HashSet<>();

            for (String roleName : formParameters.keySet()) {
                String[] values = formParameters.get(roleName);

                if (values[0].equals("on")) {
                    Optional<AccountRole> accountRoleOptional = accountRoleRepository.findByName(roleName);

                    if (accountRoleOptional.isPresent()) {
                        newColelctionOfRoles.add(accountRoleOptional.get());
                    }
                }
                account.setAccountRoles(newColelctionOfRoles);
                accountRepository.save(account);
            }
        }
    }
}
