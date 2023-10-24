package dev.majidhajric.authentication.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping({"", "/login", "/login?**", "/login.html"})
    public String login() {
        return "login";
    }
}
