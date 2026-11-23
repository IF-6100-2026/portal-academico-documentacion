package cr.ac.ucr.if6100.portal.handlers.commands.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import cr.ac.ucr.if6100.portal.handlers.commands.RegisterStudentHandler.Command;
import cr.ac.ucr.if6100.portal.handlers.commands.RegisterStudentHandler.Result;
import cr.ac.ucr.if6100.portal.persistence.entity.PortalUserEntity;
import cr.ac.ucr.if6100.portal.persistence.entity.StudentEntity;
import cr.ac.ucr.if6100.portal.persistence.repository.PortalUserRepository;
import cr.ac.ucr.if6100.portal.persistence.repository.StudentRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegisterStudentHandlerImplTest {
    @Mock
    private PortalUserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private RegisterStudentHandlerImpl handler;

    @Test
    void deniesRequestWhenActorIsNotAnActiveDirector() {
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("docente", "DIRECTOR"))
            .thenReturn(Optional.empty());

        Result result = handler.handle(new Command("docente", "Ana Demo", "Primer grado",
            "A", "Docente Demo"));

        assertThat(result).isInstanceOf(Result.Forbidden.class);
        verifyNoInteractions(studentRepository);
    }

    @Test
    void savesNormalizedStudentForAnActiveDirector() {
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("direccion", "DIRECTOR"))
            .thenReturn(Optional.of(mock(PortalUserEntity.class)));
        when(studentRepository.save(any(StudentEntity.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Result result = handler.handle(new Command("direccion", " Ana Demo ", " Primer grado ",
            " A ", " Docente Demo "));

        assertThat(result).isInstanceOf(Result.Created.class);
        Result.Created created = (Result.Created) result;
        assertThat(created.student().fullName()).isEqualTo("Ana Demo");
        assertThat(created.student().gradeLevel()).isEqualTo("Primer grado");
        assertThat(created.student().groupName()).isEqualTo("A");
        assertThat(created.student().teacherName()).isEqualTo("Docente Demo");
        assertThat(created.student().active()).isTrue();
        assertThat(created.student().createdBy()).isEqualTo("direccion");
        assertThat(created.student().createdAt()).isNotNull();
        assertThat(created.student().id()).isNotNull();
        verify(studentRepository).save(any(StudentEntity.class));
    }

    @Test
    void rejectsBlankRequiredFieldWithoutSaving() {
        when(userRepository.findByUsernameAndRoleNameAndActiveTrue("direccion", "DIRECTOR"))
            .thenReturn(Optional.of(mock(PortalUserEntity.class)));

        Result result = handler.handle(new Command("direccion", "  ", "Primer grado", "A",
            "Docente Demo"));

        assertThat(result).isEqualTo(new Result.InvalidField("fullName"));
        verifyNoInteractions(studentRepository);
    }
}
