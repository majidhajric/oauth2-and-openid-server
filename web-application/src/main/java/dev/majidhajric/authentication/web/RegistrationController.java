package dev.majidhajric.authentication.web;

import dev.majidhajric.authentication.dto.RegisterUserAccountDTO;
import dev.majidhajric.authentication.model.UserAccount;
import dev.majidhajric.authentication.service.UserAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    private final UserAccountService userAccountService;

    @GetMapping({"/register", "/register.html"})
    public String registration(Model model) {
        model.addAttribute("user", new RegisterUserAccountDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegisterUserAccountDTO user, BindingResult result, Model model) {
        log.debug("register new user: {}", user);
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            result.rejectValue("password", null,
                    "Passwords do not match");
        }

        try {
            userAccountService.loadUserByUsername(user.getEmail());
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        } catch (UsernameNotFoundException e) {

        }

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }

        userAccountService.createUserAccount(convert(user));
        return "redirect:/login";
    }

    private UserAccount convert(RegisterUserAccountDTO user) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(user.getEmail());
        userAccount.setPassword(user.getPassword());
        return userAccount;
    }
}
