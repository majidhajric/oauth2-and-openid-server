package dev.majidhajric.authify.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    @org.springframework.web.bind.annotation.GetMapping({"", "/", "/index", "/index.html"})
    public String index() {
        return "index";
    }
}
