# CU-12-PresentarQuejaPorInformacionNoRecibida («Presentar queja por información no recibida»)

| Campo | Detalle |
|---|---|
| **Descripción** | Algunas personas encargadas se han quejado de no recibir la información a tiempo (E-14). Hoy esa queja no tiene un trámite documentado: en el BPMN aparece como AC-10 y AC-11. Este caso de uso le da un trámite formal, atendido por la Dirección, que supervisa el seguimiento (E-06).<br><br>**Diferencia con la apelación:** la queja reclama información que no llegó. La apelación ([CU-07](CU-07-gestionar-apelacion-de-calificacion.md)) cuestiona una calificación que sí llegó; la resuelve el Comité de Evaluación y puede cambiar la nota. La queja nunca cambia una calificación. |
| **Estereotipo y paquete** | «Épica 2» Comunicación entre la escuela y la persona encargada |
| **Precondiciones** | 1. La persona encargada tiene un vínculo activo con el estudiante. |
| **Postcondiciones** | La queja queda atendida, con el hallazgo de la Dirección, su respuesta y las publicaciones relacionadas, y la persona encargada recibe la respuesta.<br>**Garantía mínima** (si no termina con éxito): La queja queda registrada con fecha y solicitante, y no modifica información académica. |
| **Actores** | A-01 Persona encargada legal (principal: presenta la queja); A-04 Dirección (principal: la atiende) |
| **Relaciones del caso de uso** | Asociado con los actores A-01 Persona encargada legal, A-04 Dirección. Ninguna relación «include» o «extend». Puede iniciarse desde [CU-06](CU-06-comunicarse-sobre-un-estudiante.md) (A06). Las quejas abiertas aparecen en [CU-08](CU-08-supervisar-seguimiento-academico.md). |

## Flujo básico

1. La persona encargada solicita presentar una queja e indica el estudiante, el tipo de información esperada (evaluación, calificación o reporte del periodo) y el periodo o la materia.
2. El Portal valida el vínculo (RS-03) (A01).
3. El Portal muestra lo publicado que coincide con esos criterios y si ya fue consultado (RS-01).
4. La persona encargada confirma que no encontró lo que esperaba y describe la queja (A02, A04).
5. El Portal registra la queja como `abierta`, con número, fecha y solicitante, y avisa a la Dirección.
6. La Dirección abre la queja; el Portal le muestra las publicaciones relacionadas y el registro de consultas (RF-25, RS-02).
7. La Dirección registra el hallazgo (no publicada, publicada sin consultar o consultada) y la respuesta (A03).
8. El Portal cambia la queja a `atendida` y genera un aviso con la respuesta para la persona encargada.

## Flujos alternos

- **A01 — Sin vínculo con el estudiante (paso 2).** El Portal rechaza la queja sin mostrar información del estudiante. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — La información ya está publicada (paso 4).** La persona encargada encuentra en el paso 3 lo que buscaba y la abre. El Portal registra la consulta y no registra la queja. El caso de uso termina.
- **A03 — La información no estaba publicada (paso 7).** La Dirección registra el hallazgo «no publicada» y solicita a la docente que la publique. El Portal avisa a la docente y deja la queja `en atención`. La docente publica con CU-03 o CU-04; el Portal liga la publicación a la queja. El flujo continúa en el paso 8.
- **A04 — La queja es una inconformidad con una calificación (paso 4).** La persona encargada describe que no está de acuerdo con una calificación que sí recibió. El Portal le informa que eso se tramita como apelación ante el Comité de Evaluación y le ofrece iniciar CU-07. No se registra la queja y el caso de uso termina.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-01](../02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md), [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** Pendiente; se diseñará antes de construir el caso en el PC3
- **Métricas:** complejidad media (8 pasos y 4 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-15](../02-requerimientos/funcionales/RF-15-presentar-queja-por-informacion-no-recibida.md), [RF-16](../02-requerimientos/funcionales/RF-16-atender-queja-por-informacion-no-recibida.md), [RF-25](../02-requerimientos/funcionales/RF-25-consultar-registro-de-consultas.md)
- **Pruebas:** [CU-12-presentar-queja-por-informacion-no-recibida.feature](../04-pruebas/CU-12-presentar-queja-por-informacion-no-recibida.feature)
- **Issue:** [Issue](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/13) <!-- issue:CU-12 -->
- **Equivale en el PC1 a:** Nuevo. El número CU-11 no se reutiliza porque correspondía a la bitácora retirada (UC-11).
