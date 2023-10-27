package dev.majidhajric.authentication.service;

import dev.majidhajric.authentication.command.RegisterUserAccountCommand;
import dev.majidhajric.authentication.exception.UserAccountExistsException;
import dev.majidhajric.authentication.exception.WeakPasswordException;
import dev.majidhajric.authentication.model.Role;
import dev.majidhajric.authentication.model.UserAccount;
import dev.majidhajric.authentication.repository.UserAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
@Service
public class RegisterUserAccountService {


    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public UserAccount createUserAccount(RegisterUserAccountCommand command) {
        if (userAccountRepository.findByEmail(command.getEmail()) != null) {
            throw new UserAccountExistsException();
        }
        // TODO: validate password
        if (command.getPassword().length() < 8) {
            throw new WeakPasswordException();
        }

        String encodedPassword = passwordEncoder.encode(command.getPassword());
        Role role = new Role("ROLE_NEW_USER");
        UserAccount userAccount = UserAccount.builder()
                .email(command.getEmail())
                .password(encodedPassword)
                .roles(Collections.singleton(role))
                .enabled(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .build();
        log.debug("createUserAccount: {}", userAccount);
        return userAccountRepository.save(userAccount);
    }
}
