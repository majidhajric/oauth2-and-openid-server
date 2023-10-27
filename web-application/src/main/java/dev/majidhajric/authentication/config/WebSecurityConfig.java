package dev.majidhajric.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(mvcMatcherBuilder.pattern("/favicon.**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/css/**"), mvcMatcherBuilder.pattern("/js/**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/login?**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/oauth2/login/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/login/oauth2/code/**")).permitAll()
                                .requestMatchers(new AntPathRequestMatcher("/oauth2/authorization/**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/logout**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/register**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/error**")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/account**")).authenticated()
                                .requestMatchers(mvcMatcherBuilder.pattern("/")).permitAll()
                                .requestMatchers(mvcMatcherBuilder.pattern("/index")).permitAll()
                                .anyRequest().authenticated())
                .formLogin(config -> config
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .permitAll())
                .logout(config -> config
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                        .expiredUrl("/login?expired=true"));
        http
                .oauth2Login(auth -> auth
                        .loginPage("/login")
                        .successHandler(authenticationSuccessHandler)
                        .defaultSuccessUrl("/"));
        http
                .anonymous(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedDoubleSlash(true);
        return (web) -> web.httpFirewall(firewall);
    }

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher();
    }
}
