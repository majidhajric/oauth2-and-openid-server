package dev.majidhajric.authentication.config;

import dev.majidhajric.authentication.handler.OAuth2SuccessLoginHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.web.SecurityFilterChain;

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
    public OAuth2UserService oauth2UserService() {
        return new DefaultOAuth2UserService();
    }

    @Bean
    public OAuth2SuccessLoginHandler oAuth2SuccessLoginHandler() {
        return new OAuth2SuccessLoginHandler(oauth2UserService(), authorizedClientService());
    }

    @Bean
    public SecurityFilterChain oauth2ClientFilterChain(HttpSecurity http) throws Exception {
        http.oauth2Login(auth -> auth
                .loginPage("/login")
                .successHandler(oAuth2SuccessLoginHandler()));
        return http.build();
    }
}
