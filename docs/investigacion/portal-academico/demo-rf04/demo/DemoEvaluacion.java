import cr.ac.ucr.if6100.portal.evaluacion.domain.DatosEvaluacion;
import cr.ac.ucr.if6100.portal.evaluacion.domain.ErrorValidacion;
import cr.ac.ucr.if6100.portal.evaluacion.domain.ValidadorEvaluacion;
import java.time.LocalDate;
import java.util.List;

public class DemoEvaluacion {
    private static final LocalDate ASIGNACION = LocalDate.of(2026, 11, 4);
    private static final ValidadorEvaluacion VALIDADOR = new ValidadorEvaluacion();

    public static void main(String[] args) {
        mostrar("CA-01: entrega posterior", datos(ASIGNACION.plusDays(3)), List.of());
        mostrar("CA-01: entrega el mismo día", datos(ASIGNACION), List.of());
        mostrar("CA-02: sin fecha de entrega", datos(null), List.of());
        mostrar("CA-03: entrega anterior", datos(ASIGNACION.minusDays(1)), List.of("fechaEntrega"));
        mostrar("CA-04: faltan campos", new DatosEvaluacion(" ", null, null, null, ""),
                List.of("nombre", "tipo", "fechaAsignacion", "instrucciones"));
        mostrar("CA-04: no hay datos", null, List.of("evaluacion"));
        System.out.println("\n6 escenarios comprobados. Esta demo no guarda datos ni implementa todo RF-04.");
    }

    private static DatosEvaluacion datos(LocalDate entrega) {
        return new DatosEvaluacion("Práctica 1", "Tarea", ASIGNACION, entrega, "Resolver los ejercicios.");
    }

    private static void mostrar(String nombre, DatosEvaluacion datos, List<String> camposEsperados) {
        List<ErrorValidacion> errores = VALIDADOR.validar(datos);
        List<String> campos = errores.stream().map(ErrorValidacion::campo).toList();
        System.out.println("\n" + nombre);
        if (errores.isEmpty()) {
            System.out.println("  ACEPTADA");
        } else {
            errores.forEach(error -> System.out.println("  " + error.campo() + ": " + error.mensaje()));
        }
        if (!campos.equals(camposEsperados)) {
            throw new IllegalStateException("Resultado inesperado: " + campos);
        }
        System.out.println("  Resultado esperado: OK");
    }
}
