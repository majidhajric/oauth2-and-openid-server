package dev.majidhajric.authentication.service;

import dev.majidhajric.authentication.command.RegisterUserAccountCommand;
import dev.majidhajric.authentication.exception.UserAccountExistsException;
import dev.majidhajric.authentication.exception.WeakPasswordException;
import dev.majidhajric.authentication.model.Role;
import dev.majidhajric.authentication.model.UserAccount;
import dev.majidhajric.authentication.repository.UserAccountRepository;
import dev.majidhajric.authentication.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Service
public class RegisterUserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final JwtUtil jwtUtil;

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
        UserAccount saved = userAccountRepository.save(userAccount);
        sendConfirmationEmail(saved);
        return saved;
    }

    private void sendConfirmationEmail(UserAccount userAccount) {
        String subject = "Please confirm your email";
        Map<String, Object> claims = Map.of(
                Claims.SUBJECT, userAccount.getEmail(),
                Claims.ISSUED_AT, System.currentTimeMillis());
        String token = jwtUtil.generateToken(claims);
        String link = "http://localhost:9000/confirm-email?token=" + token;
        emailService.sendTemplate(userAccount.getEmail(), subject, "email/confirm-email", Map.of("link", link));
    }

    public UserAccount confirmEmail(String token) {
        String subject = jwtUtil.parseToken(token).getSubject();
        UserAccount userAccount = userAccountRepository.findByEmail(subject);
        userAccount.setEnabled(true);
        UserAccount saved = userAccountRepository.save(userAccount);
        log.debug("confirmEmail: {}", saved);
        return saved;
    }
}
