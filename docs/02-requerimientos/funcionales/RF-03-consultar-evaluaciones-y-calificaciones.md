# RF-03 — Consultar evaluaciones y calificaciones publicadas

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Persona encargada legal |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-07 |

## Necesidad

Hoy las evaluaciones están en el cuaderno, las notas en la plataforma y en el registro físico, y el resumen en el reporte del periodo. Necesita verlas en un solo lugar.

## Requisito

El Portal deberá mostrar, por periodo y materia, las evaluaciones asignadas y las calificaciones publicadas del estudiante elegido, con su fecha de publicación y su versión vigente.

## Criterios de aceptación

- Solo se muestran registros publicados; los borradores nunca aparecen.
- Cada calificación muestra la evaluación de origen, la fecha y, si fue corregida, que existe una versión anterior.
- Un periodo sin publicaciones muestra un resultado vacío con el estudiante y el periodo correctos.

## Origen verificable

- **Evidencia:** [E-01, E-02, E-03, E-10](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-01, AC-06, AC-09 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-01](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-01 — Consultar progreso académico](../../03-casos-de-uso/CU-01-consultar-progreso-academico.md): `TP-CU01-FB`, `TP-CU01-A03`, `TP-CU01-A04` en [CU-01-consultar-progreso-academico.feature](../../04-pruebas/CU-01-consultar-progreso-academico.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
