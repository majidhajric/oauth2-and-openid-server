package dev.majidhajric.authentication.config;

import dev.majidhajric.authentication.handler.OAuth2SuccessLoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;

@RequiredArgsConstructor
@Configuration
public class OAuth2ClientConfig {

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(Arrays.asList(
                CommonOAuth2Provider.GOOGLE.getBuilder("google")
                        .clientId("931830073995-vpp9ns0qv4hu76nvogt9au6mcehaaif4.apps.googleusercontent.com")
                        .clientSecret("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")
                        .scope("openid", "email", "profile")
                        .build(),
                CommonOAuth2Provider.GITHUB.getBuilder("github")
                        .clientId("4f3365e3e27d32d642b9")
                        .clientSecret("a641c7b6f43cc11313b5b5a645f0d6254ba14fcc")
                        .build()));
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler(OAuth2AuthorizedClientService authorizedClientService) {
        return new OAuth2SuccessLoginHandler(authorizedClientService);
    }
}
