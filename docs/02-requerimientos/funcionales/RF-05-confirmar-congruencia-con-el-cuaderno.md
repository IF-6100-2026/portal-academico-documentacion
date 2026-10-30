# RF-05 — Confirmar la congruencia con el cuaderno antes de publicar

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Docente |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-10 y RF-13 |

## Necesidad

Mientras el cuaderno de la asignatura siga siendo el medio oficial, lo publicado debe coincidir con lo anotado en él.

## Requisito

El Portal deberá exigir que la docente confirme que la evaluación, la calificación o la corrección coincide con lo anotado en el cuaderno de la asignatura antes de publicarla.

## Criterios de aceptación

- Sin confirmación, el registro queda como borrador, no se publica y no genera avisos.
- La confirmación conserva quién la hizo y cuándo.
- La regla se aplica igual a la evaluación asignada, a la calificación y a cada corrección.

## Origen verificable

- **Evidencia:** [E-01, E-02](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-01](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-03 — Publicar evaluación asignada](../../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md): `TP-CU03-A04` en [CU-03-publicar-evaluacion-asignada.feature](../../04-pruebas/CU-03-publicar-evaluacion-asignada.feature)
- [CU-04 — Publicar calificación](../../03-casos-de-uso/CU-04-publicar-calificacion.md): `TP-CU04-A04` en [CU-04-publicar-calificacion.feature](../../04-pruebas/CU-04-publicar-calificacion.feature)
- [CU-09 — Corregir una calificación publicada](../../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md): `TP-CU09-A04` en [CU-09-corregir-calificacion-publicada.feature](../../04-pruebas/CU-09-corregir-calificacion-publicada.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).

> **Nota:** El Portal no puede revisar el cuaderno: la confirmación es una declaración trazable de la docente.
