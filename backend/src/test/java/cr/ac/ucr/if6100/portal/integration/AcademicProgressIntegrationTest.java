package cr.ac.ucr.if6100.portal.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

/**
 * Verifica los escenarios de docs/04-pruebas/CU-01-consultar-progreso-academico.feature contra la
 * API real, usando los datos ficticios de las migraciones 002 y 003.
 */
@DisplayName("CU-01 — Consultar progreso académico")
class AcademicProgressIntegrationTest extends IntegrationTestSupport {
    private static final String SOFIA = "11111111-1111-4111-8111-111111111111";
    private static final String LUCIA = "44444444-4444-4aaa-8aaa-444444444444";
    private static final String MATEO_PENDING_LINK = "22222222-2222-4222-8222-222222222222";
    private static final String MULTIPLICATION = "44444444-4444-4444-8444-444444444444";
    private static final String PUBLISHED_PERIOD = "2026-II";
    private static final String EMPTY_PERIOD = "2026-I";
    private static final double CURRENT_GRADE = 85.0;
    private static final double PREVIOUS_GRADE = 70.0;

    @Test
    @DisplayName("TP-CU01-FB — Flujo básico")
    void guardianSeesLinkedStudentsAndOnlyPublishedAssessments() throws Exception {
        mvc().perform(as(GUARDIAN, get("/api/v1/guardian/students")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.students[*].id", containsInAnyOrder(SOFIA, LUCIA)))
            .andExpect(jsonPath("$.students[0].latestPublications").isArray());
        int authorizedBefore = logs(GUARDIAN, SOFIA, "AUTHORIZED");

        mvc().perform(as(GUARDIAN, progress(SOFIA, PUBLISHED_PERIOD)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.student.id").value(SOFIA))
            .andExpect(jsonPath("$.assessments[*].title", hasItem("Práctica de multiplicación (demo)")))
            .andExpect(jsonPath("$.assessments[*].title", not(hasItem("Lectura guiada (borrador demo)"))))
            .andExpect(jsonPath("$.consultedAt").isString());

        assertThat(logs(GUARDIAN, SOFIA, "AUTHORIZED")).isEqualTo(authorizedBefore + 1);
    }

    @Test
    @DisplayName("TP-CU01-A01 — Sin vínculos activos")
    void guardianWithoutLinksSeesNoStudentsAndTheQueryIsLogged() throws Exception {
        int before = count("SELECT COUNT(*) FROM consultation_log WHERE guardian_username = ? "
            + "AND outcome = 'NO_ACTIVE_LINKS'", EMPTY_GUARDIAN);

        mvc().perform(as(EMPTY_GUARDIAN, get("/api/v1/guardian/students")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.students").isEmpty());

        assertThat(count("SELECT COUNT(*) FROM consultation_log WHERE guardian_username = ? "
            + "AND outcome = 'NO_ACTIVE_LINKS'", EMPTY_GUARDIAN)).isEqualTo(before + 1);
    }

    @Test
    @DisplayName("TP-CU01-A02 — Estudiante no autorizado")
    void deniesUnlinkedAndUnknownStudentsWithTheSameAnswer() throws Exception {
        String unknown = UUID.randomUUID().toString();
        int deniedBefore = logs(GUARDIAN, MATEO_PENDING_LINK, "DENIED");

        mvc().perform(as(GUARDIAN, progress(MATEO_PENDING_LINK, PUBLISHED_PERIOD)))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value("ACCESS_DENIED"))
            .andExpect(jsonPath("$.student").doesNotExist());
        mvc().perform(as(GUARDIAN, progress(unknown, PUBLISHED_PERIOD)))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value("ACCESS_DENIED"));

        assertThat(logs(GUARDIAN, MATEO_PENDING_LINK, "DENIED")).isEqualTo(deniedBefore + 1);
    }

    @Test
    @DisplayName("TP-CU01-A03 — Periodo sin publicaciones")
    void periodWithoutPublicationsReturnsAnEmptyList() throws Exception {
        int authorizedBefore = logs(GUARDIAN, SOFIA, "AUTHORIZED");

        mvc().perform(as(GUARDIAN, progress(SOFIA, EMPTY_PERIOD)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.period").value(EMPTY_PERIOD))
            .andExpect(jsonPath("$.assessments").isEmpty());

        assertThat(logs(GUARDIAN, SOFIA, "AUTHORIZED")).isEqualTo(authorizedBefore + 1);
    }

    @Test
    @DisplayName("TP-CU01-A04 — Calificación corregida")
    void correctedGradeShowsCurrentValueAndPreviousVersion() throws Exception {
        String assessment = "$.assessments[?(@.id == '" + MULTIPLICATION + "')]";

        mvc().perform(as(GUARDIAN, progress(SOFIA, PUBLISHED_PERIOD)))
            .andExpect(status().isOk())
            .andExpect(jsonPath(assessment + ".grade", hasItem(CURRENT_GRADE)))
            .andExpect(jsonPath(assessment + ".previousVersions[*].grade", hasItem(PREVIOUS_GRADE)));
    }

    @Test
    @DisplayName("TP-CU01-A05 — Sesión inválida")
    void requestWithoutValidCredentialsGetsNoDataAndIsNotLogged() throws Exception {
        int before = count("SELECT COUNT(*) FROM consultation_log");

        mvc().perform(progress(SOFIA, PUBLISHED_PERIOD))
            .andExpect(status().isUnauthorized())
            .andExpect(jsonPath("$.code").value("AUTHENTICATION_REQUIRED"))
            .andExpect(jsonPath("$.student").doesNotExist());
        mvc().perform(progress(SOFIA, PUBLISHED_PERIOD)
                .header("Authorization", "Basic ZW5jYXJnYWRhLmRlbW86Y2xhdmUtdmVuY2lkYQ=="))
            .andExpect(status().isUnauthorized());

        assertThat(count("SELECT COUNT(*) FROM consultation_log")).isEqualTo(before);
    }

    private MockHttpServletRequestBuilder progress(String studentId, String period) {
        return get("/api/v1/guardian/students/{id}/progress", studentId).param("period", period);
    }

    private int logs(String guardian, String studentId, String outcome) {
        return count("SELECT COUNT(*) FROM consultation_log WHERE guardian_username = ? "
            + "AND student_id = ? AND outcome = ?", guardian, UUID.fromString(studentId), outcome);
    }
}
