package dev.majidhajric.authentication.repository;

import dev.majidhajric.authentication.entity.UserAccountEntity;
import dev.majidhajric.authentication.jpa.JpaUserAccountRepository;
import dev.majidhajric.authentication.model.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class DefaultUserAccountRepository implements UserAccountRepository {

    private final JpaUserAccountRepository jpaUserAccountRepository;

    @Override
    public UserAccount findByEmail(String email) {
        log.debug("findByEmail: {}", email);
        return toModel(jpaUserAccountRepository.findByEmail(email));
    }

    @Override
    public UserAccount save(UserAccount userAccount) {
        return toModel(jpaUserAccountRepository.save(toEntity(userAccount)));
    }

    private UserAccountEntity toEntity(UserAccount userAccount) {
        if (userAccount == null) {
            return null;
        }
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setEmail(userAccount.getEmail());
        userAccountEntity.setPassword(userAccount.getPassword());
        userAccountEntity.setEnabled(userAccount.isEnabled());
        userAccountEntity.setAccountNonExpired(userAccount.isAccountNonExpired());
        userAccountEntity.setCredentialsNonExpired(userAccount.isCredentialsNonExpired());
        userAccountEntity.setAccountNonLocked(userAccount.isAccountNonLocked());
        return userAccountEntity;
    }

    private UserAccount toModel(UserAccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        }
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
