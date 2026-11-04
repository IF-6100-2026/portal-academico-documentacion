package cr.ac.ucr.if6100.portal.evaluacion.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidadorEvaluacionTest {
    private static final LocalDate ASIGNACION = LocalDate.of(2026, 11, 4);
    private final ValidadorEvaluacion validador = new ValidadorEvaluacion();

    @Test
    void ca01AceptaEntregaPosterior() {
        assertTrue(validador.validar(datosValidos(ASIGNACION.plusDays(3))).isEmpty());
    }

    @Test
    void ca01AceptaEntregaElMismoDia() {
        assertTrue(validador.validar(datosValidos(ASIGNACION)).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"2026-12-31, 2027-01-01", "2028-02-28, 2028-02-29"})
    void ca01AceptaFechasValidasAlCambiarDeAnioOEnBisiesto(String inicio, String entrega) {
        var datos = new DatosEvaluacion("Tarea", "Práctica", LocalDate.parse(inicio),
                LocalDate.parse(entrega), "Resolver los ejercicios.");
        assertTrue(validador.validar(datos).isEmpty());
    }

    @Test
    void ca02PermiteOmitirLaFechaDeEntrega() {
        assertTrue(validador.validar(datosValidos(null)).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({"2026-11-04, 2026-11-03", "2027-01-01, 2026-12-31"})
    void ca03RechazaEntregaAnteriorEIdentificaElCampo(String inicio, String entrega) {
        var datos = new DatosEvaluacion("Tarea", "Práctica", LocalDate.parse(inicio),
                LocalDate.parse(entrega), "Resolver los ejercicios.");

        List<ErrorValidacion> errores = validador.validar(datos);

        assertEquals(1, errores.size());
        assertEquals("fechaEntrega", errores.getFirst().campo());
        assertFalse(errores.getFirst().mensaje().isBlank());
    }

    @ParameterizedTest
    @MethodSource("camposObligatoriosInvalidos")
    void ca04IdentificaCadaCampoObligatorio(DatosEvaluacion datos, String campo) {
        List<ErrorValidacion> errores = validador.validar(datos);

        assertEquals(List.of(campo), errores.stream().map(ErrorValidacion::campo).toList());
        assertTrue(errores.stream().allMatch(error -> !error.mensaje().isBlank()));
    }

    static Stream<Arguments> camposObligatoriosInvalidos() {
        return Stream.of(
                Arguments.of(new DatosEvaluacion(null, "Tarea", ASIGNACION, null, "Resolver."), "nombre"),
                Arguments.of(new DatosEvaluacion("", "Tarea", ASIGNACION, null, "Resolver."), "nombre"),
                Arguments.of(new DatosEvaluacion(" \t\n", "Tarea", ASIGNACION, null, "Resolver."), "nombre"),
                Arguments.of(new DatosEvaluacion("Práctica", null, ASIGNACION, null, "Resolver."), "tipo"),
                Arguments.of(new DatosEvaluacion("Práctica", "", ASIGNACION, null, "Resolver."), "tipo"),
                Arguments.of(new DatosEvaluacion("Práctica", "Tarea", ASIGNACION, null, null), "instrucciones"),
                Arguments.of(new DatosEvaluacion("Práctica", "Tarea", ASIGNACION, null, "\u2003"), "instrucciones"),
                Arguments.of(new DatosEvaluacion("Práctica", "Tarea", null, ASIGNACION, "Resolver."), "fechaAsignacion")
        );
    }

    @Test
    void ca04InformaTodosLosCamposInvalidosEnUnaSolaValidacion() {
        var datos = new DatosEvaluacion(" ", null, null, ASIGNACION, "");

        List<ErrorValidacion> errores = validador.validar(datos);

        assertEquals(List.of("nombre", "tipo", "fechaAsignacion", "instrucciones"),
                errores.stream().map(ErrorValidacion::campo).toList());
    }

    @Test
    void ca04InformaCuandoNoSeRecibenDatos() {
        List<ErrorValidacion> errores = assertDoesNotThrow(() -> validador.validar(null));

        assertEquals(1, errores.size());
        assertEquals("evaluacion", errores.getFirst().campo());
        assertFalse(errores.getFirst().mensaje().isBlank());
    }

    private DatosEvaluacion datosValidos(LocalDate entrega) {
        return new DatosEvaluacion("Práctica 1", "Tarea", ASIGNACION, entrega,
                "Resolver los ejercicios del capítulo 2.");
    }
}
