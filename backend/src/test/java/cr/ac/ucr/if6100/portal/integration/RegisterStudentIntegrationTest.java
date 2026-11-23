package cr.ac.ucr.if6100.portal.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/** Verifica los escenarios de docs/04-pruebas/CU-16-registrar-estudiante.feature contra la API real. */
@DisplayName("CU-16 — Registrar estudiante")
class RegisterStudentIntegrationTest extends IntegrationTestSupport {
    private static final String STUDENTS_URL = "/api/v1/director/students";

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("TP-CU16-FB — Registrar ficha válida")
    void directorRegistersAnActiveStudent() throws Exception {
        String fullName = uniqueName();

        String body = mvc().perform(as(DIRECTOR, post(STUDENTS_URL))
                .contentType(MediaType.APPLICATION_JSON)
                .content(request(fullName, "A")))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.fullName").value(fullName))
            .andExpect(jsonPath("$.groupName").value("A"))
            .andExpect(jsonPath("$.active").value(true))
            .andExpect(jsonPath("$.createdBy").value(DIRECTOR))
            .andReturn().getResponse().getContentAsString();

        UUID id = UUID.fromString(objectMapper.readTree(body).get("id").asText());
        assertThat(count("SELECT COUNT(*) FROM student WHERE id = ? AND active", id)).isEqualTo(1);
    }

    @Test
    @DisplayName("TP-CU16-A01 — Faltan datos requeridos")
    void rejectsStudentWithoutGroupAndPointsToTheField() throws Exception {
        String fullName = uniqueName();

        mvc().perform(as(DIRECTOR, post(STUDENTS_URL))
                .contentType(MediaType.APPLICATION_JSON)
                .content(request(fullName, "")))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code").value("INVALID_FIELD"))
            .andExpect(jsonPath("$.message").value(containsString("groupName")));

        assertThat(studentsNamed(fullName)).isZero();
    }

    @Test
    @DisplayName("TP-CU16-A02 — Actor sin permiso")
    void teacherCannotRegisterStudents() throws Exception {
        String fullName = uniqueName();

        mvc().perform(as(TEACHER, post(STUDENTS_URL))
                .contentType(MediaType.APPLICATION_JSON)
                .content(request(fullName, "A")))
            .andExpect(status().isForbidden())
            .andExpect(jsonPath("$.code").value("ACCESS_DENIED"))
            .andExpect(jsonPath("$.fullName").doesNotExist());

        assertThat(studentsNamed(fullName)).isZero();
    }

    @Test
    @DisplayName("TP-CU16-A03 — Alta separada del vínculo familiar")
    void newStudentIsNotVisibleToAnyGuardianUntilLinked() throws Exception {
        JsonNode created = register(uniqueName());
        String id = created.get("id").asText();

        assertThat(count("SELECT COUNT(*) FROM guardian_student_link WHERE student_id = ?",
            UUID.fromString(id))).isZero();
        mvc().perform(as(GUARDIAN, get("/api/v1/guardian/students")))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.students[*].id", not(hasItem(id))));
    }

    @Test
    @DisplayName("TP-CU16-A04 — Coincidencia de nombre sin identificador institucional")
    void sameNameDoesNotBlockASecondRecord() throws Exception {
        String fullName = uniqueName();

        JsonNode first = register(fullName);
        JsonNode second = register(fullName);

        assertThat(second.get("id").asText()).isNotEqualTo(first.get("id").asText());
        assertThat(studentsNamed(fullName)).isEqualTo(2);
    }

    private JsonNode register(String fullName) throws Exception {
        String body = mvc().perform(as(DIRECTOR, post(STUDENTS_URL))
                .contentType(MediaType.APPLICATION_JSON)
                .content(request(fullName, "A")))
            .andExpect(status().isCreated())
            .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(body);
    }

    private String request(String fullName, String groupName) throws Exception {
        return objectMapper.writeValueAsString(new StudentRequest(fullName, "Primer grado", groupName,
            "Docente Demo (dato ficticio)"));
    }

    private int studentsNamed(String fullName) {
        return count("SELECT COUNT(*) FROM student WHERE full_name = ?", fullName);
    }

    private String uniqueName() {
        return "Ana Demo " + UUID.randomUUID() + " (dato ficticio)";
    }

    private record StudentRequest(String fullName, String gradeLevel, String groupName, String teacherName) { }
}
