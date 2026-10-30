# RF-25 — Consultar el registro de consultas

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-35 (reorientado) |

## Necesidad

Para atender una queja o supervisar, la Dirección necesita saber qué se publicó y quién lo consultó y cuándo.

## Requisito

El Portal deberá permitir a la Dirección consultar, por estudiante o por publicación, quién consultó cada aviso o registro académico y en qué fecha y hora.

## Criterios de aceptación

- El resultado muestra persona, rol, recurso consultado, fecha y hora.
- El registro es de solo lectura.
- La consulta de la Dirección también queda registrada.

## Origen verificable

- **Evidencia:** [E-06, E-14](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-11 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-03, P-05](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-08 — Supervisar el seguimiento académico](../../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md): `TP-CU08-FB` en [CU-08-supervisar-seguimiento-academico.feature](../../04-pruebas/CU-08-supervisar-seguimiento-academico.feature)
- [CU-12 — Presentar queja por información no recibida](../../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md): `TP-CU12-FB` en [CU-12-presentar-queja-por-informacion-no-recibida.feature](../../04-pruebas/CU-12-presentar-queja-por-informacion-no-recibida.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
