package dev.majidhajric.authentication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

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
}
