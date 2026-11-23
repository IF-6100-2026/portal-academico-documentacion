package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.GroupCommand;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.Result;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.TeacherCommand;
import cr.ac.ucr.if6100.portal.handlers.commands.ManageAcademicCatalogHandler.TeacherUpdate;
import cr.ac.ucr.if6100.portal.persistence.entity.PortalUserEntity;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ManageAcademicCatalogHandlerImplTest {
    @Mock
    private PortalUserRepository userRepository;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ManageAcademicCatalogHandlerImpl handler;

    @Test
    void deniesCatalogMutationToActorWithoutDirectorRole() {
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("docente.demo", "DIRECTOR"))
            .thenReturn(Optional.empty());

        Result result = handler.registerGroup("docente.demo",
            new GroupCommand("Matemática", "Cuarto", "4-A", "2026-II"));

        assertThat(result).isInstanceOf(Result.Forbidden.class);
        verifyNoInteractions(jdbcTemplate);
    }

    @Test
    void rejectsPeriodOutsideSupportedFormatWithoutWriting() {
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("direccion.demo", "DIRECTOR"))
            .thenReturn(Optional.of(org.mockito.Mockito.mock(PortalUserEntity.class)));

        Result result = handler.registerGroup("direccion.demo",
            new GroupCommand("Matemática", "Cuarto", "4-A", "2026-III"));

        assertThat(result).isEqualTo(new Result.InvalidField("period"));
        verifyNoInteractions(jdbcTemplate);
    }

    @Test
    void rejectsTeacherAccountThatDoesNotExist() {
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("direccion.demo", "DIRECTOR"))
            .thenReturn(Optional.of(org.mockito.Mockito.mock(PortalUserEntity.class)));
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("no-existe", "TEACHER"))
            .thenReturn(Optional.empty());

        Result result = handler.registerTeacher("direccion.demo",
            new TeacherCommand("no-existe", "Docente Demo"));

        assertThat(result).isEqualTo(new Result.NotFound("teacherAccount"));
        verifyNoInteractions(jdbcTemplate);
    }

    @Test
    void rejectsBlankTeacherNameBeforeLoadingProfile() {
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("direccion.demo", "DIRECTOR"))
            .thenReturn(Optional.of(org.mockito.Mockito.mock(PortalUserEntity.class)));

        Result result = handler.updateTeacher("direccion.demo", "docente.demo",
            new TeacherUpdate("  ", true));

        assertThat(result).isEqualTo(new Result.InvalidField("displayName"));
        verifyNoInteractions(jdbcTemplate);
    }
}
