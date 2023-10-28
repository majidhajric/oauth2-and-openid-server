package dev.majidhajric.authentication.web;

import dev.majidhajric.authentication.command.RegisterUserAccountCommand;
import dev.majidhajric.authentication.exception.UserAccountExistsException;
import dev.majidhajric.authentication.model.UserAccount;
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
import org.springframework.web.bind.annotation.RequestParam;

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
        if (result.hasErrors()) {
            user.setPassword("");
            return "register";
        }
        try {
            registerUserAccountService.createUserAccount(user);
            authenticationService.authenticate(user.getEmail(), user.getPassword());
        } catch (UserAccountExistsException e) {
            result.rejectValue("email", "validation.account.exists", "Account already exists");
            return "register";
        } catch (Exception e) {
            log.error("Failed to register user: {}", user.getEmail(), e);
            result.reject("registration.failure", "Registration failed");
            return "register";
        }
        return "redirect:/";
    }

    @GetMapping("/confirm-email")
    public String confirmEmail(@RequestParam String token, Model model) {
        UserAccount userAccount = registerUserAccountService.confirmEmail(token);
        return "email-confirmed";
    }
}
