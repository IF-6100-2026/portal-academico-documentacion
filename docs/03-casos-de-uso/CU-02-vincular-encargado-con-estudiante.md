# CU-02-VincularEncargadoConEstudiante («Vincular encargado con estudiante»)

| Campo | Detalle |
|---|---|
| **Descripción** | Que la Dirección registre qué personas encargadas están autorizadas para consultar a cada estudiante, de modo que el Portal entregue la información solo a quien corresponde. Que sea la Dirección quien vincula es una propuesta (supuesto S-02). |
| **Estereotipo y paquete** | «Épica 1» Registro y consulta de notas y evaluaciones |
| **Precondiciones** | 1. La persona encargada y el estudiante están registrados en el Portal.<br>2. La Dirección cuenta con el respaldo institucional que autoriza la relación. |
| **Postcondiciones** | El vínculo queda activo, suspendido o finalizado según lo autorizado, con su respaldo y su responsable. El cambio rige desde la siguiente consulta de la persona encargada.<br>**Garantía mínima** (si no termina con éxito): Ningún vínculo queda activo sin respaldo, y cada decisión queda registrada. |
| **Actores** | A-04 Dirección (principal) |
| **Relaciones del caso de uso** | Asociado con los actores A-04 Dirección. Ninguna relación «include» o «extend». Un vínculo activo es precondición de [CU-01](CU-01-consultar-progreso-academico.md), [CU-06](CU-06-comunicarse-sobre-un-estudiante.md), [CU-07](CU-07-gestionar-apelacion-de-calificacion.md) y [CU-12](CU-12-presentar-queja-por-informacion-no-recibida.md). |

## Flujo básico

1. La Dirección solicita registrar un vínculo.
2. El Portal valida que el usuario tenga el rol de Dirección (RS-03) (A01).
3. La Dirección indica la persona encargada, el estudiante y el tipo de relación (A06).
4. El Portal verifica que ambas personas estén activas y que no exista un vínculo equivalente (A02, A03, A04).
5. La Dirección registra la referencia del respaldo que autoriza la relación.
6. El Portal valida que la información de respaldo esté completa (A05).
7. El Portal activa el vínculo y lo deja disponible en la cuenta de la persona encargada.
8. El Portal registra la actuación con responsable y fecha.

## Flujos alternos

- **A01 — Usuario sin rol de Dirección (paso 2).** El Portal rechaza la solicitud. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — Persona inexistente o inactiva (paso 4).** El Portal indica qué condición impide vincular, sin crear el vínculo. El caso de uso termina.
- **A03 — Vínculo equivalente ya activo (paso 4).** El Portal conserva el vínculo existente sin duplicarlo e informa su fecha de activación. El caso de uso termina.
- **A04 — Restricción sobre la relación (paso 4).** La Dirección indica que existe una restricción institucional sobre la relación (por ejemplo, de custodia). El Portal deja la solicitud pendiente, sin acceso, con el motivo y el responsable. El caso de uso termina.
- **A05 — Respaldo incompleto (paso 6).** El Portal no activa el vínculo e indica qué falta. La Dirección puede completar la solicitud desde el paso 5 en otro momento.
- **A06 — Suspender o finalizar un vínculo (paso 3).** La Dirección elige un vínculo activo e indica la acción, el motivo y la fecha. El Portal cambia el estado y conserva el anterior, el motivo, la fecha y el responsable. El nuevo estado rige desde la siguiente solicitud de la persona encargada. El flujo continúa en el paso 8.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** Sin prototipo en esta fase
- **Métricas:** complejidad media (8 pasos y 6 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-01](../02-requerimientos/funcionales/RF-01-vincular-encargado-con-estudiante.md)
- **Pruebas:** [CU-02-vincular-encargado-con-estudiante.feature](../04-pruebas/CU-02-vincular-encargado-con-estudiante.feature)
- **Issue:** [Issue](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/4) <!-- issue:CU-02 -->
- **Equivale en el PC1 a:** UC-02 (el actor principal era el Administrador institucional)
