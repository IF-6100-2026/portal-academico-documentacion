# RF-21 — Controlar el estado y el plazo de la apelación

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-27 y RF-28 |

## Necesidad

La Dirección supervisa y el plazo de resolución depende de la situación. Necesita saber en qué estado está cada caso y cuáles vencieron.

## Requisito

El Portal deberá conservar el historial de estados de cada apelación y marcarla como vencida, con aviso a la Dirección, cuando se supere su plazo de resolución.

## Criterios de aceptación

- Cada cambio de estado conserva estado anterior, estado nuevo, responsable y fecha.
- Una apelación vencida no se resuelve ni se cierra de forma automática.
- La Dirección ve las apelaciones vencidas en su panel de supervisión.

## Origen verificable

- **Evidencia:** [E-06, E-08](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** SP-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-04, P-05](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-07 — Gestionar apelación de una calificación](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md): `TP-CU07-A07` en [CU-07-gestionar-apelacion-de-calificacion.feature](../../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature)
- [CU-08 — Supervisar el seguimiento académico](../../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md): `TP-CU08-FB` en [CU-08-supervisar-seguimiento-academico.feature](../../04-pruebas/CU-08-supervisar-seguimiento-academico.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).

> **Nota:** Los estados presentada, en revisión, pendiente de información, resuelta y cerrada son una propuesta (supuesto S-03).
