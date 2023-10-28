package dev.majidhajric.authentication.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
@ConfigurationProperties(prefix = "configuration")
public class ConfigProperties {

    private Boolean emailAsUsername = true;

    private Boolean rememberMe = true;

    private Boolean requireEmailVerification = false;

    private Boolean requirePasswordConfirmation = true;

    private Boolean disableSocialSignIn = false;

    private Boolean enableMultiFactor = true;

    private PasswordPolicy passwordPolicy = new PasswordPolicy();

    @Bean
    public PasswordPolicy getPasswordPolicy() {
        return passwordPolicy;
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class PasswordPolicy {
        private Integer minLength = 8;
        private Integer maxLength = 64;
        private Integer upperCaseCount = 1;
        private Integer lowerCaseCount = 1;
        private Integer digitCount = 1;
        private Integer specialCount = 1;
    }
}
