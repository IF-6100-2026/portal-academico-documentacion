# RF-24 — Consultar el estado del seguimiento

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-31 y RF-32 |

## Necesidad

La Dirección supervisa, pero hoy la información está en varios medios. Necesita ver en un solo lugar lo pendiente.

## Requisito

El Portal deberá mostrar a la Dirección, por periodo y grupo, los avisos sin consultar, las alertas, las quejas abiertas y las apelaciones por estado y plazo.

## Criterios de aceptación

- Cada total permite llegar a los avisos, quejas o apelaciones que lo componen.
- Los borradores no se incluyen.
- Un filtro sin datos muestra un resultado vacío con los criterios aplicados.

## Origen verificable

- **Evidencia:** [E-06](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-11, SP-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-05](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-08 — Supervisar el seguimiento académico](../../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md): `TP-CU08-FB`, `TP-CU08-A02`, `TP-CU08-A03` en [CU-08-supervisar-seguimiento-academico.feature](../../04-pruebas/CU-08-supervisar-seguimiento-academico.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
