package cr.ac.ucr.if6100.portal.evaluacion.domain;

import java.time.LocalDate;

public record DatosEvaluacion(
        String nombre,
        String tipo,
        LocalDate fechaAsignacion,
        LocalDate fechaEntrega,
        String instrucciones) {
}
