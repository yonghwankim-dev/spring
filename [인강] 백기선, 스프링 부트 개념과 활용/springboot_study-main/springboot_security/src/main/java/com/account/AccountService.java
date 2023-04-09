package com.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


    public Account createAccount(String username, String password){
        Account account = Account.builder()
                                    .username(username)
                                    .password(passwordEncoder.encode(password))
                                    .build();
        return accountRepository.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByUsername(username);
        Account result = account.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(result.getUsername(), result.getPassword(), authorities());
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
