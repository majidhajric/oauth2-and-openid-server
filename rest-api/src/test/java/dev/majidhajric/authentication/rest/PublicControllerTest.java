package dev.majidhajric.authentication.rest;

import dev.majidhajric.authentication.RestApiConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HealthController.class)
@AutoConfigureMockMvc
@Import(RestApiConfig.class)
@ContextConfiguration(classes = TestApplication.class)
class PublicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void publicHealth() throws Exception {

        mvc.perform(get("/api/public/health")).andExpect(status().isOk());
    }

    @Test
    void adminHealth() throws Exception {

        mvc.perform(get("/api/admin/health")).andExpect(status().isUnauthorized());
    }

    @Test
    void otherTest() throws Exception {
        mvc.perform(get("/api/public/other")).andExpect(status().isNotFound());
        mvc.perform(get("/other")).andExpect(status().isNotFound());
    }
}