package dev.majidhajric.authentication.config;

import dev.majidhajric.authentication.entity.UserAccountEntity;
import dev.majidhajric.authentication.jpa.JpaUserAccountRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan(basePackageClasses = JpaUserAccountRepository.class)
@EnableJpaRepositories(basePackageClasses = JpaUserAccountRepository.class)
@EnableTransactionManagement
@EntityScan(basePackageClasses = UserAccountEntity.class)
@Configuration(proxyBeanMethods = false)
public class JpaConfig {

    @Profile({"dev", "test"})
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE + 3)
    SecurityFilterChain h2ConsoleSecurityFilterChain(HttpSecurity http) throws
            Exception {
        PathRequest.H2ConsoleRequestMatcher requestMatcher = PathRequest.toH2Console();

        http.securityMatcher(new AntPathRequestMatcher("/h2-console/**"));
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
}
