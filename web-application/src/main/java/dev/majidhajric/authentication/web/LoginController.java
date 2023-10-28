package dev.majidhajric.authentication.web;

import dev.majidhajric.authentication.repository.DefaultClientRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Controller
public class LoginController {

    private final DefaultClientRegistrationRepository clientRegistrationRepository;

    @GetMapping({"", "/login", "/login?**", "/login.html"})
    public String login(Model model) {
        List<String> clients = clientRegistrationRepository.findAll()
                .stream()
                .map(ClientRegistration::getRegistrationId)
                .map(String::toLowerCase)
                .toList();
        model.addAttribute("clients", clients);
        return "login";
    }
}
