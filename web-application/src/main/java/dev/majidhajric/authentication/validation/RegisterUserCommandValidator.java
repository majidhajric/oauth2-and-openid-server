package dev.majidhajric.authentication.validation;

import dev.majidhajric.authentication.command.RegisterUserAccountCommand;
import dev.majidhajric.authentication.config.ConfigProperties;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class RegisterUserCommandValidator implements ConstraintValidator<ValidRegisterUserCommand, Object> {

    private final ConfigProperties.PasswordPolicy getPasswordPolicy;

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        log.debug("Validating value: {}", value);
        RegisterUserAccountCommand command = (RegisterUserAccountCommand) value;
        PasswordData passwordData = new PasswordData();
        passwordData.setUsername(command.getEmail());
        passwordData.setPassword(command.getPassword());

        List<Rule> rules = new LinkedList<>();
        rules.add(new UsernameRule());

        PasswordValidator validator = new PasswordValidator(rules);

        RuleResult result = validator.validate(passwordData);
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Password can't be same as username")
                .addConstraintViolation();
        return false;
    }
}
