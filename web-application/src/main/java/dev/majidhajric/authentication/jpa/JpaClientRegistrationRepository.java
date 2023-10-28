package dev.majidhajric.authentication.jpa;

import dev.majidhajric.authentication.entity.ClientRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaClientRegistrationRepository extends JpaRepository<ClientRegistrationEntity, Long> {

    ClientRegistrationEntity findByRegistrationId(String registrationId);
}
