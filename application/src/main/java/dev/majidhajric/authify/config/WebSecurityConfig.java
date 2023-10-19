package dev.majidhajric.authify.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/index")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/index.html")).permitAll()
                                .anyRequest().authenticated())
                .formLogin(config -> config.loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/"))
                .logout(config -> config.logoutSuccessUrl("/"));
        http.anonymous(Customizer.withDefaults());
        return http.build();
    }
}