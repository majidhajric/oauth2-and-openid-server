package dev.majidhajric.authentication.command;

import dev.majidhajric.authentication.validation.ValidPassword;
import dev.majidhajric.authentication.validation.ValidRegisterUserCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ValidRegisterUserCommand
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterUserAccountCommand {

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @ValidPassword
    private String password;
}
