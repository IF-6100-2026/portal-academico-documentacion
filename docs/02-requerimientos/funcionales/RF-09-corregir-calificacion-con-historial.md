# RF-09 — Corregir una calificación publicada con historial

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Docente |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-15 |

## Necesidad

Una apelación puede terminar en un cambio de calificación. La docente necesita corregirla sin borrar lo que se había publicado.

## Requisito

El Portal deberá permitir a la docente corregir una calificación publicada mediante una nueva versión con motivo, conservando la versión anterior.

## Criterios de aceptación

- La versión anterior sigue consultable y nunca se sobrescribe.
- La nueva versión guarda autor, fecha, motivo y, si existe, la apelación que la origina.
- No se aplica una corrección sobre una versión desactualizada, sin motivo o sin cambio efectivo.

## Origen verificable

- **Evidencia:** [E-01, E-11](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** SP-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-04](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-09 — Corregir una calificación publicada](../../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md): `TP-CU09-FB`, `TP-CU09-A02`, `TP-CU09-A03` en [CU-09-corregir-calificacion-publicada.feature](../../04-pruebas/CU-09-corregir-calificacion-publicada.feature)
- [CU-07 — Gestionar apelación de una calificación](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md): `TP-CU07-A06` en [CU-07-gestionar-apelacion-de-calificacion.feature](../../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
