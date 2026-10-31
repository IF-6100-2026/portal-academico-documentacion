# RF-19 — Registrar los aportes de la docente y la Dirección

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Docente y Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | Parte de RF-27; resto nuevo |

## Necesidad

En el caso real la docente y la Dirección participaron con el Comité. Sus aportes deben quedar en el expediente.

## Requisito

El Portal deberá permitir a la docente adjuntar la prueba y su criterio de calificación, y a la Dirección registrar sus observaciones dentro de la apelación; también deberá permitir al Comité solicitar información adicional.

## Criterios de aceptación

- Cada aporte conserva autor, fecha y contenido, y no reemplaza a los anteriores.
- Una solicitud de información deja la apelación en «pendiente de información» hasta que se responda.
- Ningún aporte cambia por sí solo la calificación.

## Origen verificable

- **Evidencia:** [E-11](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** SP-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-04](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-07 — Gestionar apelación de una calificación](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md): `TP-CU07-FB`, `TP-CU07-A05` en [CU-07-gestionar-apelacion-de-calificacion.feature](../../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
