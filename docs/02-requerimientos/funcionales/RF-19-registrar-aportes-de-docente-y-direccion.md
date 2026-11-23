# RF-19 — Registrar los aportes de la docente y la Dirección

> **Clasificación:** requisito detallado de [RF-32 — Gestionar apelaciones](RF-32-gestionar-apelaciones-y-pendientes.md). No se cuenta como requerimiento funcional independiente; conserva su identificador para no romper enlaces, escenarios ni issues.

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

- [CU-13 — Aportar información a una apelación](../../03-casos-de-uso/CU-13-aportar-informacion-a-apelacion.md): `TP-CU13-FB`, `TP-CU13-A01`, `TP-CU13-A02`, `TP-CU13-A03` en [CU-13-aportar-informacion-a-apelacion.feature](../../04-pruebas/CU-13-aportar-informacion-a-apelacion.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
