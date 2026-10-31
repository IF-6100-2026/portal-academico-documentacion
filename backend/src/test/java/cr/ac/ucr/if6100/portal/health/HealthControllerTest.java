package cr.ac.ucr.if6100.portal.health;

import cr.ac.ucr.if6100.portal.config.WebConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HealthController.class)
@Import(WebConfig.class)
@TestPropertySource(properties = "app.cors.allowed-origin=http://localhost:5173")
class HealthControllerTest {
    @Autowired MockMvc mvc;

    @Test void exposesTheFrontendContract() throws Exception {
        mvc.perform(get("/api/v1/health"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("UP"))
            .andExpect(jsonPath("$.service").value("portal-academico-api"))
            .andExpect(jsonPath("$.timestamp").isString());
    }

    @Test void permitsConfiguredOrigin() throws Exception {
        mvc.perform(options("/api/v1/health")
            .header("Origin", "http://localhost:5173")
            .header("Access-Control-Request-Method", "GET"))
            .andExpect(status().isOk())
            .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:5173"));
    }

    @Test void rejectsOtherOrigin() throws Exception {
        mvc.perform(options("/api/v1/health")
            .header("Origin", "https://untrusted.example")
            .header("Access-Control-Request-Method", "GET"))
            .andExpect(status().isForbidden())
            .andExpect(header().doesNotExist("Access-Control-Allow-Origin"));
    }
}
