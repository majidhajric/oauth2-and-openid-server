package dev.majidhajric.authify.rest;

import dev.majidhajric.authify.RestApiConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jwt.JwtDecoder;

@SpringBootApplication
@Import(RestApiConfig.class)
public class TestApplication {


    @MockBean
    private JwtDecoder jwtDecoder;
}
