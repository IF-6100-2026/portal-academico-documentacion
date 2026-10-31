# CU-06-ComunicarseSobreUnEstudiante («Comunicarse sobre un estudiante»)

| Campo | Detalle |
|---|---|
| **Descripción** | Hoy la persona encargada y la docente se comunican por el cuaderno de recados (E-07), y la directora pidió mensajería (E-10). Este caso de uso permite conversar sobre un estudiante y guarda el historial en el Portal.<br><br>La mensajería es para la comunicación ordinaria. Una inconformidad con una calificación se tramita como apelación ([CU-07](CU-07-gestionar-apelacion-de-calificacion.md)), con el Comité de Evaluación. La falta de información se tramita como queja ([CU-12](CU-12-presentar-queja-por-informacion-no-recibida.md)), con la Dirección. Los flujos A05 y A06 hacen esa separación. |
| **Estereotipo y paquete** | «Épica 2» Comunicación entre la escuela y la persona encargada |
| **Precondiciones** | 1. Quien inicia tiene una cuenta activa.<br>2. Existe un vínculo activo entre la persona encargada y el estudiante, y la docente está asignada al grupo del estudiante. |
| **Postcondiciones** | El mensaje y la respuesta quedan asociados al estudiante con autor, destinatario y fecha, y el historial no se modifica.<br>**Garantía mínima** (si no termina con éxito): Nadie fuera del vínculo o de la asignación accede a la conversación. |
| **Actores** | A-01 Persona encargada legal y A-02 Docente (principales: cualquiera de los dos inicia y el otro responde) |
| **Relaciones del caso de uso** | Asociado con los actores A-01 Persona encargada legal, A-02 Docente. Ninguna relación «include» o «extend». Los flujos A05 y A06 orientan hacia CU-07 y CU-12, que son casos independientes. |

## Flujo básico

1. Quien inicia solicita escribir un mensaje sobre un estudiante.
2. El Portal valida el vínculo de la persona encargada y la asignación de la docente (RS-03) (A01).
3. Quien inicia indica el asunto y el contenido (A05, A06).
4. El Portal valida que el contenido no esté vacío y que el destinatario siga autorizado (A02, A03).
5. El Portal guarda el mensaje en la conversación del estudiante y genera un aviso para el destinatario.
6. El destinatario abre la conversación.
7. El Portal vuelve a validar la autorización, muestra el historial completo y registra la lectura; quien envió el mensaje ve que fue leído, con fecha y hora (RS-02).
8. El destinatario responde (A04).
9. El Portal agrega la respuesta con autor y fecha, sin alterar los mensajes previos, y avisa a quien inició.

## Flujos alternos

- **A01 — Vínculo o asignación no autorizados (paso 2).** El Portal rechaza el acceso sin revelar contenido ni participantes. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — El destinatario dejó de estar autorizado (paso 4).** El Portal no envía el mensaje, lo conserva como no enviado e informa el motivo. El caso de uso termina.
- **A03 — Contenido vacío (paso 4).** El Portal no envía el mensaje e indica qué falta. Quien inicia puede retomar el paso 3.
- **A04 — Sin respuesta (paso 8).** La conversación queda pendiente, sin respuestas inventadas ni cambios en el mensaje original. El aviso del mensaje sigue el tratamiento de CU-05 y el caso de uso termina.
- **A05 — El mensaje expresa inconformidad con una calificación (paso 3).** Quien inicia es la persona encargada y marca que el asunto es una calificación con la que no está de acuerdo. El Portal le informa que las inconformidades con una calificación se resuelven como apelación con el Comité de Evaluación y le ofrece iniciar CU-07 desde esa calificación. Si la persona acepta, se inicia CU-07. El mensaje no se convierte en apelación y el caso de uso termina.
- **A06 — El mensaje reporta información no recibida (paso 3).** Quien inicia es la persona encargada y marca que el asunto es información que no recibió. El Portal le ofrece presentar una queja ante la Dirección con CU-12. Si la persona acepta, se inicia CU-12 y el caso de uso termina.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Mensajería institucional](prototipos/stitch-5/screen.png)
- **Métricas:** complejidad media (9 pasos y 6 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-14](../02-requerimientos/funcionales/RF-14-intercambiar-mensajes.md); para orientar hacia la queja o la apelación (A05 y A06): [RF-15](../02-requerimientos/funcionales/RF-15-presentar-queja-por-informacion-no-recibida.md), [RF-17](../02-requerimientos/funcionales/RF-17-presentar-apelacion.md)
- **Pruebas:** [CU-06-comunicarse-sobre-un-estudiante.feature](../04-pruebas/CU-06-comunicarse-sobre-un-estudiante.feature)
- **Issue:** _por crear_ <!-- issue:CU-06 -->
- **Equivale en el PC1 a:** UC-06
