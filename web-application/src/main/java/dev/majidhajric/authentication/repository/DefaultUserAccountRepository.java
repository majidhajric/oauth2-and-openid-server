package dev.majidhajric.authentication.repository;

import dev.majidhajric.authentication.entity.RoleEntity;
import dev.majidhajric.authentication.entity.UserAccountEntity;
import dev.majidhajric.authentication.jpa.JpaPrivilegeRepository;
import dev.majidhajric.authentication.jpa.JpaRoleRepository;
import dev.majidhajric.authentication.jpa.JpaUserAccountRepository;
import dev.majidhajric.authentication.model.Privilege;
import dev.majidhajric.authentication.model.Role;
import dev.majidhajric.authentication.model.UserAccount;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Component
public class DefaultUserAccountRepository implements UserAccountRepository {

    private final JpaUserAccountRepository jpaUserAccountRepository;

    private final JpaRoleRepository jpaRoleRepository;

    private final JpaPrivilegeRepository jpaPrivilegeRepository;

    @Transactional
    @Override
    public UserAccount findByEmail(String email) {
        log.debug("findByEmail: {}", email);
        return toModel(jpaUserAccountRepository.findByEmail(email));
    }

    @Transactional
    @Override
    public UserAccount save(UserAccount userAccount) {
        return toModel(jpaUserAccountRepository.save(toEntity(userAccount)));
    }

    private UserAccountEntity toEntity(UserAccount userAccount) {
        if (userAccount == null) {
            return null;
        }
        UserAccountEntity userAccountEntity = jpaUserAccountRepository.findByEmail(userAccount.getEmail());
        if (userAccountEntity == null) {
            userAccountEntity = new UserAccountEntity();
        }
        userAccountEntity.setEmail(userAccount.getEmail());
        userAccountEntity.setPassword(userAccount.getPassword());
        userAccountEntity.setEnabled(userAccount.isEnabled());
        userAccountEntity.setAccountNonExpired(userAccount.isAccountNonExpired());
        userAccountEntity.setCredentialsNonExpired(userAccount.isCredentialsNonExpired());
        userAccountEntity.setAccountNonLocked(userAccount.isAccountNonLocked());
        userAccountEntity.setRoles(userAccount.getRoles().stream().map(
                role -> {
                    RoleEntity roleEntity = jpaRoleRepository.findByAuthority(role.getAuthority());
                    roleEntity.setAuthority(role.getAuthority());
                    roleEntity.setPrivileges(role.getPrivileges().stream().map(
                            privilege -> {
                                return jpaPrivilegeRepository.findByAuthority(privilege.getAuthority());
                            }
                    ).collect(Collectors.toSet()));
                    return roleEntity;
                }
        ).collect(Collectors.toSet()));
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
                .roles(accountEntity.getRoles().stream().map(re -> {
                    Role role = new Role();
                    role.setAuthority(re.getAuthority());
                    role.setPrivileges(re.getPrivileges().stream().map(
                            pe -> {
                                Privilege privilege = new Privilege();
                                privilege.setAuthority(pe.getAuthority());
                                return privilege;
                            }
                    ).collect(Collectors.toSet()));
                    return role;
                }).collect(Collectors.toSet()))
                .build();
    }
}
