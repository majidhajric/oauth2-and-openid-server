package dev.majidhajric.authentication.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidPassword {

    String message() default "This isn't correct";

    Class[] groups() default {};

    Class[] payload() default {};
}