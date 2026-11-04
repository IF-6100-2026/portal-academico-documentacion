package cr.ac.ucr.if6100.portal.evaluacion.domain;

import java.util.ArrayList;
import java.util.List;

public final class ValidadorEvaluacion {

    public List<ErrorValidacion> validar(DatosEvaluacion datos) {
        if (datos == null) {
            return List.of(new ErrorValidacion("evaluacion", "Debe proporcionar los datos de la evaluación."));
        }

        List<ErrorValidacion> errores = new ArrayList<>();
        validarTexto(datos.nombre(), "nombre", "Debe indicar el nombre de la evaluación.", errores);
        validarTexto(datos.tipo(), "tipo", "Debe indicar el tipo de evaluación.", errores);
        if (datos.fechaAsignacion() == null) {
            errores.add(new ErrorValidacion("fechaAsignacion", "Debe indicar la fecha de asignación."));
        }
        validarTexto(datos.instrucciones(), "instrucciones", "Debe indicar las instrucciones.", errores);

        if (datos.fechaAsignacion() != null && datos.fechaEntrega() != null
                && datos.fechaEntrega().isBefore(datos.fechaAsignacion())) {
            errores.add(new ErrorValidacion("fechaEntrega",
                    "La fecha de entrega debe ser igual o posterior a la fecha de asignación."));
        }
        return List.copyOf(errores);
    }

    private void validarTexto(String valor, String campo, String mensaje, List<ErrorValidacion> errores) {
        if (valor == null || valor.isBlank()) {
            errores.add(new ErrorValidacion(campo, mensaje));
        }
    }
}
