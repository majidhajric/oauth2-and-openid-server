package dev.majidhajric.authentication.web;

import dev.majidhajric.authentication.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final UserAccountService userAccountService;

    @GetMapping({"/account", "/account.html"})
    public String account(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("userAccount", userDetails);
        return "account";
    }
}
