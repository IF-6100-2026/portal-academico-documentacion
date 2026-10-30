# RF-20 — Registrar la resolución de la apelación

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Comité de Evaluación |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-29 |

## Necesidad

El Comité emite y comunica la resolución. Necesita dejarla registrada con su fundamento y que llegue a la persona encargada.

## Requisito

El Portal deberá permitir al Comité registrar la resolución con resultado, fundamento, fecha e integrantes que la emiten, y avisarla a la persona encargada.

## Criterios de aceptación

- La apelación pasa a «resuelta» y la persona encargada recibe un aviso.
- Si la resolución modifica la calificación, el cambio se hace por la corrección (RF-09) y el caso guarda la referencia.
- La apelación pasa a «cerrada» cuando la persona encargada consulta la resolución.

## Origen verificable

- **Evidencia:** [E-11](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** SP-01, AC-05 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-04](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-07 — Gestionar apelación de una calificación](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md): `TP-CU07-FB`, `TP-CU07-A06`, `TP-CU07-A08` en [CU-07-gestionar-apelacion-de-calificacion.feature](../../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
