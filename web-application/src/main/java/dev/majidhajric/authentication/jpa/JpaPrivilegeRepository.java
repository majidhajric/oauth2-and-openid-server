package dev.majidhajric.authentication.jpa;

import dev.majidhajric.authentication.entity.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPrivilegeRepository extends JpaRepository<PrivilegeEntity, Long> {

    PrivilegeEntity findByAuthority(String authority);
}
