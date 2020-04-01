package io.swagger.service;

import io.swagger.repository.AccountRepository;
import io.swagger.model.Account;
import io.swagger.model.AccountRole;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;

@Service
public class AccountService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(
            AccountRepository accountRepository,
            PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account register(Account account) throws AuthenticationException {
        validateInputAccount(account);
        Account existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount != null) {
            throw new AuthenticationException(AuthenticationError.AccountAlreadyExists);
        }

        if (!StringUtils.isEmpty(account.getEmail())) {
            existingAccount = accountRepository.findByEmail(account.getEmail());
            if (existingAccount != null) {
                throw new AuthenticationException(AuthenticationError.AccountAlreadyExists);
            }
        }

        account.setAccountRole(AccountRole.USER);
        account.setCreateEpoch(Instant.now().getEpochSecond());
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        return account;
    }

    public Account findAccountByUsername(String username) throws AuthenticationException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new AuthenticationException(AuthenticationError.NoSuchAccount);
        }
        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        log.info("load account " + account.toString());
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getAccountRole().getRole()));

        return new User(account.getUsername(), account.getPassword(), authorities);

    }

    private void validateInputAccount(Account account) throws AuthenticationException {
        // We can only register customer now.
        if (AccountRole.ADMIN.equals(account.getAccountRole())) {
            throw new AuthenticationException(AuthenticationError.AccountTypeNotAllowed);
        }
        if (StringUtils.isEmpty(account.getUsername())
                || StringUtils.isEmpty(account.getPassword())) {
            throw new AuthenticationException(AuthenticationError.InvalidInput);
        }
    }



    public static class AuthenticationException extends Exception {
        @Getter
        private AuthenticationError authenticationError;

        public AuthenticationException(AuthenticationError authenticationError) {
            this.authenticationError = authenticationError;
        }
    }

    public enum AuthenticationError {
        NoSuchAccount("NoSuchAccount"),
        InvalidPassword("InvalidPassword"),
        AccountAlreadyExists("AccountAlreadyExists"),
        AccountTypeNotAllowed("AccountTypeNotAllowed"),
        InvalidInput("InvalidInput");

        final String errorDesc;

        AuthenticationError(String errorDesc) {
            this.errorDesc = errorDesc;
        }
    }
}
