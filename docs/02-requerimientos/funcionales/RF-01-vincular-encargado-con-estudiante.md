# RF-01 — Vincular a una persona encargada con un estudiante

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-03 |

## Necesidad

Hoy la información llega a quien recibe el cuaderno o el reporte de manos del estudiante. Para que el Portal entregue la información solo a quien corresponde, la Dirección necesita dejar registrado qué personas encargadas están autorizadas para cada estudiante.

## Requisito

El Portal deberá permitir a la Dirección registrar, suspender y finalizar el vínculo entre una persona encargada y un estudiante, con la referencia del respaldo que lo autoriza.

## Criterios de aceptación

- Un vínculo pasa a activo solo si tiene persona encargada, estudiante, respaldo y responsable.
- Una solicitud equivalente a un vínculo activo no crea un duplicado.
- Suspender o finalizar un vínculo conserva el estado anterior, la fecha, el motivo y el responsable, y rige desde la siguiente consulta.

## Origen verificable

- **Evidencia:** [E-06, E-12](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-02, AC-08 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-02](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-02 — Vincular encargado con estudiante](../../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md): `TP-CU02-FB`, `TP-CU02-A02`, `TP-CU02-A03`, `TP-CU02-A04`, `TP-CU02-A05`, `TP-CU02-A06` en [CU-02-vincular-encargado-con-estudiante.feature](../../04-pruebas/CU-02-vincular-encargado-con-estudiante.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).

> **Nota:** Que sea la Dirección quien vincula es una propuesta (supuesto S-02).
