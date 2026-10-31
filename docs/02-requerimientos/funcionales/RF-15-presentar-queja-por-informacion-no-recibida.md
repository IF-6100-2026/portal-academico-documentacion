# RF-15 — Presentar una queja por información no recibida

| Campo | Detalle |
|---|---|
| Épica | 2. Comunicación entre la escuela y la persona encargada |
| Actor | Persona encargada legal |
| Prioridad | Debe |
| Equivale en el PC1 a | Nuevo |

## Necesidad

Algunas personas encargadas se han quejado de no recibir la información a tiempo. Necesitan un medio formal para reportarlo que no se confunda con una apelación.

## Requisito

El Portal deberá permitir a la persona encargada registrar una queja por información académica no recibida, indicando el estudiante, el tipo de información esperada y el periodo o la materia.

## Criterios de aceptación

- Antes de registrar la queja, el Portal muestra lo publicado para ese criterio y si ya fue consultado.
- La queja queda abierta con número, fecha y solicitante, y la Dirección recibe un aviso.
- Una inconformidad con una calificación no se registra como queja; se remite a la apelación (CU-07).

## Origen verificable

- **Evidencia:** [E-14](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-10 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-02](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-12 — Presentar queja por información no recibida](../../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md): `TP-CU12-FB`, `TP-CU12-A02`, `TP-CU12-A04` en [CU-12-presentar-queja-por-informacion-no-recibida.feature](../../04-pruebas/CU-12-presentar-queja-por-informacion-no-recibida.feature)
- [CU-06 — Comunicarse sobre un estudiante](../../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md): `TP-CU06-A06` en [CU-06-comunicarse-sobre-un-estudiante.feature](../../04-pruebas/CU-06-comunicarse-sobre-un-estudiante.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
