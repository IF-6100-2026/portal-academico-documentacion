# RF-14 — Intercambiar mensajes sobre un estudiante

| Campo | Detalle |
|---|---|
| Épica | 2. Comunicación entre la escuela y la persona encargada |
| Actor | Persona encargada legal y docente |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-23 y RF-24 |

## Necesidad

Hoy se comunican mediante el cuaderno de recados. La directora pidió mensajería, y necesitan conversar sobre el estudiante con el historial guardado.

## Requisito

El Portal deberá permitir que una persona encargada con vínculo activo y una docente asignada al estudiante intercambien mensajes asociados a ese estudiante.

## Criterios de aceptación

- Cada mensaje conserva autor, destinatario, fecha y contenido, y no reemplaza a los anteriores.
- Un mensaje con asunto o contenido vacío no se envía.
- Si el destinatario dejó de estar autorizado, el mensaje no se envía y se informa el motivo.
- Quien envía ve si el mensaje fue leído, con fecha y hora (RS-02).

## Origen verificable

- **Evidencia:** [E-07, E-10](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-04 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-01](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-06 — Comunicarse sobre un estudiante](../../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md): `TP-CU06-FB`, `TP-CU06-A02`, `TP-CU06-A03`, `TP-CU06-A04` en [CU-06-comunicarse-sobre-un-estudiante.feature](../../04-pruebas/CU-06-comunicarse-sobre-un-estudiante.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
