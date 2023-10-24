package dev.majidhajric.authentication.web;

import dev.majidhajric.authentication.dto.RegisterUserAccountDTO;
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

    @GetMapping({"/register", "/register.html"})
    public String registration(Model model) {
        model.addAttribute("user", new RegisterUserAccountDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") RegisterUserAccountDTO user, BindingResult result, Model model) {
        log.debug("register new user: {}", user);
        return "redirect:/login";
    }
}
