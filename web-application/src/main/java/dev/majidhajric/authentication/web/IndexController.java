package dev.majidhajric.authentication.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @org.springframework.web.bind.annotation.GetMapping({"", "/", "/index", "/index.html"})
    public String index() {
        return "index";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
