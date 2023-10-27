package dev.majidhajric.authentication.command;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterUserAccountCommand {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 6, max = 100)
    private String password;
}
