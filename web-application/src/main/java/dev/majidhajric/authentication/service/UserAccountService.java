package dev.majidhajric.authentication.service;

import dev.majidhajric.authentication.model.UserAccount;
import dev.majidhajric.authentication.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserAccountService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("loadUserByUsername: {}", username);
        UserAccount userAccount = userAccountRepository.findByEmail(username);
        if (userAccount == null) {
            throw new UsernameNotFoundException("User not found by email: " + username);
        }
        return userAccount;
    }

    public UserDetails createUserAccount(UserAccount userAccount) {
        log.debug("createUserAccount: {}", userAccount);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setEnabled(true);
        return userAccountRepository.save(userAccount);
    }
}
