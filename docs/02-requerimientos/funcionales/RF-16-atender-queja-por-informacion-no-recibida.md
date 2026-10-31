# RF-16 — Atender una queja por información no recibida

| Campo | Detalle |
|---|---|
| Épica | 2. Comunicación entre la escuela y la persona encargada |
| Actor | Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | Nuevo |

## Necesidad

La Dirección supervisa el seguimiento y necesita responder la queja con evidencia de qué se publicó y quién lo consultó.

## Requisito

El Portal deberá permitir a la Dirección registrar el hallazgo y la respuesta de una queja, solicitar a la docente que publique lo faltante y cerrar la queja como atendida.

## Criterios de aceptación

- La respuesta conserva el hallazgo (no publicada, publicada sin consultar o consultada), la acción, el responsable y la fecha.
- Si la información no estaba publicada, la queja queda vinculada a la publicación que la resuelve.
- La persona encargada recibe un aviso con la respuesta.

## Origen verificable

- **Evidencia:** [E-06, E-14](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-11 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-02, P-05](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-12 — Presentar queja por información no recibida](../../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md): `TP-CU12-FB`, `TP-CU12-A03` en [CU-12-presentar-queja-por-informacion-no-recibida.feature](../../04-pruebas/CU-12-presentar-queja-por-informacion-no-recibida.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
