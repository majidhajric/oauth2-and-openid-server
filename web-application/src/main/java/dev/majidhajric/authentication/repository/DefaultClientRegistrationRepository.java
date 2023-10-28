package dev.majidhajric.authentication.repository;

import dev.majidhajric.authentication.entity.ClientRegistrationEntity;
import dev.majidhajric.authentication.jpa.JpaClientRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Component
public class DefaultClientRegistrationRepository implements ClientRegistrationRepository {

    private final JpaClientRegistrationRepository jpaClientRegistrationRepository;

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        log.debug("findByRegistrationId: {}", registrationId);
        ClientRegistrationEntity entity = jpaClientRegistrationRepository.findByRegistrationId(registrationId);
        if (entity != null) {
            return toModel(entity);
        }
        return null;
    }

    public Collection<ClientRegistration> findAll() {
        return jpaClientRegistrationRepository.findAll().stream().map(this::toModel).toList();
    }

    private ClientRegistration toModel(ClientRegistrationEntity entity) {
        return ClientRegistration
                .withRegistrationId(entity.getRegistrationId())
                .clientId(entity.getClientId())
                .clientSecret(entity.getClientSecret())
                .clientName(entity.getClientName())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(entity.getClientAuthenticationMethod().getValue()))
                .authorizationGrantType(new AuthorizationGrantType(entity.getAuthorizationGrantType().getValue()))
                .redirectUri(entity.getRedirectUri())
                .scope(entity.getScopes())
                .userInfoUri(entity.getUserInfoUri())
                .authorizationUri(entity.getAuthorizationUri())
                .tokenUri(entity.getTokenUri())
                .issuerUri(entity.getIssuerUri())
                .jwkSetUri(entity.getJwkSetUri())
                .userInfoAuthenticationMethod(null)
                .userNameAttributeName(entity.getUserNameAttributeName())
                .build();
    }
}
