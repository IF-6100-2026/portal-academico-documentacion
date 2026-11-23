package cr.ac.ucr.if6100.portal.integration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Base de las pruebas de integración: aplicación completa con seguridad real y PostgreSQL.
 * Sin Docker disponible las pruebas se omiten en lugar de fallar.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgresTestConfiguration.class)
@Testcontainers(disabledWithoutDocker = true)
abstract class IntegrationTestSupport {
    protected static final String GUARDIAN = "encargada.demo";
    protected static final String EMPTY_GUARDIAN = "sin-vinculos.demo";
    protected static final String DIRECTOR = "direccion.demo";
    protected static final String TEACHER = "docente.demo";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate jdbc;

    @Value("${app.demo-users.guardian.password}")
    private String guardianPassword;

    @Value("${app.demo-users.empty-guardian.password}")
    private String emptyGuardianPassword;

    @Value("${app.demo-users.director.password}")
    private String directorPassword;

    @Value("${app.demo-users.teacher.password}")
    private String teacherPassword;

    protected MockMvc mvc() {
        return mvc;
    }

    protected MockHttpServletRequestBuilder as(String username, MockHttpServletRequestBuilder request) {
        String credentials = username + ":" + passwordOf(username);
        String token = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
        return request.header(HttpHeaders.AUTHORIZATION, "Basic " + token);
    }

    protected int count(String sql, Object... params) {
        Integer total = jdbc.queryForObject(sql, Integer.class, params);
        return total == null ? 0 : total;
    }

    private String passwordOf(String username) {
        return switch (username) {
            case GUARDIAN -> guardianPassword;
            case EMPTY_GUARDIAN -> emptyGuardianPassword;
            case DIRECTOR -> directorPassword;
            case TEACHER -> teacherPassword;
            default -> throw new IllegalArgumentException("Cuenta de demostración desconocida: " + username);
        };
    }
}
