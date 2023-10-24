package dev.majidhajric.authentication.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }
}
