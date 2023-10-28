package dev.majidhajric.authentication.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = RegisterUserCommandValidator.class)
@Target({TYPE})
@Retention(RUNTIME)
public @interface ValidRegisterUserCommand {

    String message() default "This isn't correct";

    Class[] groups() default {};

    Class[] payload() default {};
}