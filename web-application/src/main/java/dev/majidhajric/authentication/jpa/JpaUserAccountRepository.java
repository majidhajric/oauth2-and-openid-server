package dev.majidhajric.authentication.jpa;

import dev.majidhajric.authentication.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserAccountRepository extends JpaRepository<UserAccountEntity, String> {
    UserAccountEntity findByEmail(String email);
}
