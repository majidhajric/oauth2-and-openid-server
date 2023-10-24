package dev.majidhajric.authentication.repository;

import dev.majidhajric.authentication.entity.UserAccountEntity;
import dev.majidhajric.authentication.jpa.JpaUserAccountRepository;
import dev.majidhajric.authentication.model.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DefaultUserAccountRepository implements UserAccountRepository {

    private final JpaUserAccountRepository jpaUserAccountRepository;

    @Override
    public UserAccount findByEmail(String email) throws UsernameNotFoundException {
        log.debug("findByEmail: {}", email);
        return convert(jpaUserAccountRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    private UserAccount convert(UserAccountEntity accountEntity) {
        return UserAccount.builder()
                .id(accountEntity.getId())
                .email(accountEntity.getEmail())
                .password(accountEntity.getPassword())
                .enabled(accountEntity.isEnabled())
                .accountNonExpired(accountEntity.isAccountNonExpired())
                .credentialsNonExpired(accountEntity.isCredentialsNonExpired())
                .accountNonLocked(accountEntity.isAccountNonLocked())
                .build();
    }
}
