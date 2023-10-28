package dev.majidhajric.authentication.jpa;

import dev.majidhajric.authentication.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByAuthority(String authority);
}
