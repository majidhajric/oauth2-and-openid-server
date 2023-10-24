package dev.majidhajric.authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class AuthorizationConfig {
}
