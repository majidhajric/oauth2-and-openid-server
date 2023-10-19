package dev.majidhajric.authify.web;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {

    @org.springframework.web.bind.annotation.GetMapping({"", "/login", "/login?**", "/login.html"})
    public String login() {
        return "login";
    }
}
