package dev.majidhajric.authentication.validation;

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
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private final ConfigProperties.PasswordPolicy getPasswordPolicy;

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        log.debug("Validating password: {}", password);
        List<Rule> rules = new LinkedList<>();
        rules.add(new LengthRule(getPasswordPolicy.getMinLength(), getPasswordPolicy.getMaxLength()));
        if (getPasswordPolicy.getUpperCaseCount() > 0) {
            rules.add(new CharacterRule(EnglishCharacterData.UpperCase, getPasswordPolicy.getUpperCaseCount()));
        }
        if (getPasswordPolicy.getLowerCaseCount() > 0) {
            rules.add(new CharacterRule(EnglishCharacterData.LowerCase, getPasswordPolicy.getLowerCaseCount()));
        }
        if (getPasswordPolicy.getDigitCount() > 0) {
            rules.add(new CharacterRule(EnglishCharacterData.Digit, getPasswordPolicy.getDigitCount()));
        }
        if (getPasswordPolicy.getSpecialCount() > 0) {
            rules.add(new CharacterRule(EnglishCharacterData.Special, getPasswordPolicy.getSpecialCount()));
        }
        rules.add(new IllegalSequenceRule(EnglishSequenceData.Alphabetical, getPasswordPolicy.getMinLength() - 1, false));
        rules.add(new IllegalSequenceRule(EnglishSequenceData.Numerical, getPasswordPolicy.getMinLength() - 1, false));
        rules.add(new IllegalSequenceRule(EnglishSequenceData.USQwerty, getPasswordPolicy.getMinLength() - 1, false));
        rules.add(new WhitespaceRule());
        PasswordValidator validator = new PasswordValidator(rules);

        PasswordData passwordData = new PasswordData(password);
        RuleResult result = validator.validate(passwordData);
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Invalid password: " + String.join(",", validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}
