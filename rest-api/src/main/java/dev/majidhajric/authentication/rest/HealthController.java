package dev.majidhajric.authentication.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/public/health")
    public String publicHealth() {
        return "OK";
    }

    @GetMapping("/admin/health")
    public ResponseEntity privateHealth() {
        Map<String, String> health = Map.of("status", "OK");
        return ResponseEntity.ok(health);
    }
}
