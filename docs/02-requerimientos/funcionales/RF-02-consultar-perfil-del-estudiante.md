# RF-02 — Consultar el perfil del estudiante vinculado

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Persona encargada legal |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-05 y RF-06 |

## Necesidad

Necesita ubicar el contexto del estudiante (grado, grupo, materias y docentes) sin esperar a que el estudiante traiga el cuaderno o el reporte.

## Requisito

El Portal deberá mostrar a la persona encargada la lista de estudiantes con vínculo activo y, para el que elija, su grado, grupo, periodo, materias y docentes.

## Criterios de aceptación

- Solo aparecen los estudiantes con vínculo activo de esa persona encargada.
- Al cambiar de estudiante, toda la información mostrada corresponde únicamente al elegido.
- Una persona encargada sin vínculos activos no ve ningún perfil.

## Origen verificable

- **Evidencia:** [E-10, E-12](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-02, AC-08 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-01, P-02](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-01 — Consultar progreso académico](../../03-casos-de-uso/CU-01-consultar-progreso-academico.md): `TP-CU01-FB`, `TP-CU01-A01`, `TP-CU01-A02` en [CU-01-consultar-progreso-academico.feature](../../04-pruebas/CU-01-consultar-progreso-academico.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
