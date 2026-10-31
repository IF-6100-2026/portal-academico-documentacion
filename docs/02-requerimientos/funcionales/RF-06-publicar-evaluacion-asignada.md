# RF-06 — Publicar la evaluación asignada

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Docente |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-11 |

## Necesidad

Necesita que las personas encargadas conozcan la evaluación sin depender de que el estudiante lleve el cuaderno.

## Requisito

El Portal deberá permitir publicar una evaluación confirmada para que la vean las personas encargadas con vínculo activo de cada estudiante del grupo.

## Criterios de aceptación

- Al publicar, la evaluación pasa de borrador a publicada con autor y fecha.
- Queda identificada como evaluación asignada y sin calificación.
- Repetir la publicación no duplica la evaluación ni sus avisos.

## Origen verificable

- **Evidencia:** [E-02, E-12, E-13](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-01, AC-02 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-02](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-03 — Publicar evaluación asignada](../../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md): `TP-CU03-FB`, `TP-CU03-A05` en [CU-03-publicar-evaluacion-asignada.feature](../../04-pruebas/CU-03-publicar-evaluacion-asignada.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
