# RF-17 — Presentar una apelación sobre una calificación

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Persona encargada legal |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-25 |

## Necesidad

Hoy escribe la apelación en el cuaderno de recados. Necesita presentarla desde la calificación, adjuntar respaldo y conocer el estado del caso.

## Requisito

El Portal deberá permitir a la persona encargada apelar una calificación publicada dentro del plazo configurado, indicando los puntos apelados, el motivo y el respaldo.

## Criterios de aceptación

- La apelación recibe número, fecha, solicitante, calificación apelada, estado «presentada» y plazo de resolución.
- No se admite si la calificación no está publicada, si venció el plazo de presentación o si ya existe una apelación abierta equivalente.
- La información incompleta impide presentarla y se indica qué falta.

## Origen verificable

- **Evidencia:** [E-07, E-11](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-04 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-04](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-07 — Gestionar apelación de una calificación](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md): `TP-CU07-FB`, `TP-CU07-A02`, `TP-CU07-A03`, `TP-CU07-A04` en [CU-07-gestionar-apelacion-de-calificacion.feature](../../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature)
- [CU-06 — Comunicarse sobre un estudiante](../../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md): `TP-CU06-A05` en [CU-06-comunicarse-sobre-un-estudiante.feature](../../04-pruebas/CU-06-comunicarse-sobre-un-estudiante.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
