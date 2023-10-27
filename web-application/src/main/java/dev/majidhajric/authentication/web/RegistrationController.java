package dev.majidhajric.authentication.web;

import dev.majidhajric.authentication.command.RegisterUserAccountCommand;
import dev.majidhajric.authentication.exception.UserAccountExistsException;
import dev.majidhajric.authentication.exception.WeakPasswordException;
import dev.majidhajric.authentication.service.AuthenticationService;
import dev.majidhajric.authentication.service.RegisterUserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Slf4j
@Controller
public class RegistrationController {

    private final AuthenticationService authenticationService;

    private final RegisterUserAccountService registerUserAccountService;

    @GetMapping({"/register", "/register.html"})
    public String registration(Model model) {
        model.addAttribute("user", new RegisterUserAccountCommand());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegisterUserAccountCommand user, BindingResult result, Model model) {
        try {
            registerUserAccountService.createUserAccount(user);
            authenticationService.authenticate(user.getEmail(), user.getPassword());
        } catch (UserAccountExistsException e) {
            result.rejectValue("email", "validation.account.exists", "Account already exists");
        } catch (WeakPasswordException e) {
            result.rejectValue("password", "validation.password.weak", "Password is too weak");
        }

        if (result.hasErrors()) {
            model.addAttribute("command", user);
            return "register";
        }
        return "redirect:/";
    }
}
