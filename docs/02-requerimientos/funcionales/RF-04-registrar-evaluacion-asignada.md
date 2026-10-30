# RF-04 — Registrar una evaluación asignada a un grupo

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Docente |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-08 y RF-09 |

## Necesidad

Hoy anota cada evaluación diaria en el cuaderno de la asignatura. Necesita registrarla una sola vez para todo el grupo, con fechas e instrucciones.

## Requisito

El Portal deberá permitir a la docente registrar una evaluación para un grupo y una materia asignados, con nombre, tipo, fecha de asignación, fecha de entrega cuando aplique e instrucciones.

## Criterios de aceptación

- La evaluación queda asociada a todos los estudiantes activos del grupo sin repetir su definición.
- Una fecha de entrega anterior a la de asignación o un dato obligatorio vacío impide continuar y se indica qué corregir.
- Los estudiantes inactivos en la fecha de la evaluación quedan excluidos y se informa a la docente.

## Origen verificable

- **Evidencia:** [E-02, E-10](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-01](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-03 — Publicar evaluación asignada](../../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md): `TP-CU03-FB`, `TP-CU03-A02`, `TP-CU03-A03` en [CU-03-publicar-evaluacion-asignada.feature](../../04-pruebas/CU-03-publicar-evaluacion-asignada.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
