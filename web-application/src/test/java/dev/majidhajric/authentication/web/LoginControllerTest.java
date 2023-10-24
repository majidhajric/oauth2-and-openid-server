package dev.majidhajric.authentication.web;

import dev.majidhajric.authentication.WebApplication;
import dev.majidhajric.authentication.config.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = WebApplication.class)
@Import(WebSecurityConfig.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Description("should return login page")
    void login() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk());
    }

}