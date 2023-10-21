package dev.majidhajric.authify.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/favicon.**").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/css/**", "/js/**").permitAll()
                                .requestMatchers("/login", "/login?**", "/login.html").permitAll()
                                .requestMatchers( "/", "/index?**", "index.html").permitAll()
                                .anyRequest().authenticated())
                .formLogin(config -> config.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/"))
                .logout(config -> config.logoutSuccessUrl("/"));
        http.anonymous(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher();
    }
}
