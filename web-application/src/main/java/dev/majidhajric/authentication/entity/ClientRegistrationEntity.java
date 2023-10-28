package dev.majidhajric.authentication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ClientRegistrationEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;
    @ToString.Include
    @Column(unique = true, nullable = false)
    private String registrationId;
    @Column(nullable = false)
    private String clientId;
    @Column(nullable = false)
    private String clientSecret;
    @Column
    @Enumerated(EnumType.STRING)
    private ClientAuthenticationMethodEnum clientAuthenticationMethod;
    @Column
    @Enumerated(EnumType.STRING)
    private AuthorizationGrantTypeEnum authorizationGrantType;
    @Column
    private String redirectUri;
    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> scopes;
    @Column
    private String authorizationUri;
    @Column
    private String tokenUri;
    @Column
    private String userInfoUri;
    @Column
    private String userNameAttributeName;
    @Column
    private String jwkSetUri;
    @Column
    private String issuerUri;
    @Column
    private String clientName;

    @RequiredArgsConstructor
    @Getter
    public enum ClientAuthenticationMethodEnum {
        CLIENT_SECRET_BASIC("client_secret_basic"),
        CLIENT_SECRET_POST("client_secret_post"),
        CLIENT_SECRET_JWT("client_secret_jwt"),
        PRIVATE_KEY_JWT("private_key_jwt"),
        NONE("none");

        private final String value;
    }

    @RequiredArgsConstructor
    @Getter
    public enum AuthorizationGrantTypeEnum {
        AUTHORIZATION_CODE("authorization_code"),
        REFRESH_TOKEN("refresh_token"),
        CLIENT_CREDENTIALS("client_credentials"),
        JWT_BEARER("urn:ietf:params:oauth:grant-type:jwt-bearer"),
        DEVICE_CODE("urn:ietf:params:oauth:grant-type:device_code");
        private final String value;
    }
}
