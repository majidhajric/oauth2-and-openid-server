package dev.majidhajric.authify.web;

import dev.majidhajric.authify.AuthifyApplication;
import dev.majidhajric.authify.config.WebSecurityConfig;
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

@WebMvcTest(IndexController.class)
@AutoConfigureMockMvc
@ContextConfiguration(classes = AuthifyApplication.class)
@Import(WebSecurityConfig.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Description("should return index page")
    void index() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk());
    }
}