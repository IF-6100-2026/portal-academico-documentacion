# RF-07 — Registrar las calificaciones del grupo

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Docente |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-12 |

## Necesidad

Hoy registra las notas en la plataforma y en el registro físico. Necesita registrar las calificaciones del grupo en una sola operación.

## Requisito

El Portal deberá permitir a la docente registrar, en una misma pantalla, la calificación y la observación de cada estudiante del grupo para una evaluación.

## Criterios de aceptación

- Cada valor queda asociado a un estudiante y a la evaluación común.
- Un valor fuera de la escala o un valor obligatorio vacío impide publicar el conjunto y se señala cada valor por corregir.
- Los valores incompletos pueden guardarse como borrador.

## Origen verificable

- **Evidencia:** [E-01, E-10](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-01](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-04 — Publicar calificación](../../03-casos-de-uso/CU-04-publicar-calificacion.md): `TP-CU04-FB`, `TP-CU04-A03` en [CU-04-publicar-calificacion.feature](../../04-pruebas/CU-04-publicar-calificacion.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
