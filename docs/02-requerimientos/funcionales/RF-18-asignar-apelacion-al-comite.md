# RF-18 — Asignar la apelación al Comité de Evaluación

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Comité de Evaluación |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-26 |

## Necesidad

En el proceso actual el Comité atiende las apelaciones junto con la docente y la Dirección. Necesita recibir cada caso con su expediente completo.

## Requisito

El Portal deberá asignar cada apelación presentada al Comité de Evaluación y avisar a la docente de la evaluación y a la Dirección.

## Criterios de aceptación

- El Comité, la docente y la Dirección reciben aviso de la apelación.
- El expediente incluye la calificación apelada, los puntos, el motivo y el respaldo.
- Solo el Comité, la docente de la evaluación, la Dirección y la persona encargada que apeló pueden consultar el caso.

## Origen verificable

- **Evidencia:** [E-07](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** SP-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-04](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-07 — Gestionar apelación de una calificación](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md): `TP-CU07-FB` en [CU-07-gestionar-apelacion-de-calificacion.feature](../../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
