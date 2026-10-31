# RF-11 — Avisar la publicación de una calificación o su corrección

| Campo | Detalle |
|---|---|
| Épica | 2. Comunicación entre la escuela y la persona encargada |
| Actor | Persona encargada legal |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-17 |

## Necesidad

Hoy la calificación del periodo llega en un reporte que el estudiante puede retrasar u ocultar. Necesita un aviso directo, también cuando la nota se corrige.

## Requisito

El Portal deberá generar un aviso para cada persona encargada con vínculo activo cuando se publique una calificación o una corrección.

## Criterios de aceptación

- La corrección produce un aviso nuevo que identifica la versión vigente.
- El aviso de calificación es distinto del aviso de la evaluación asignada.
- Un estudiante sin persona encargada activa queda señalado para la supervisión de la Dirección.

## Origen verificable

- **Evidencia:** [E-12, E-13](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-08 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-02](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-04 — Publicar calificación](../../03-casos-de-uso/CU-04-publicar-calificacion.md): `TP-CU04-FB`, `TP-CU04-A06` en [CU-04-publicar-calificacion.feature](../../04-pruebas/CU-04-publicar-calificacion.feature)
- [CU-09 — Corregir una calificación publicada](../../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md): `TP-CU09-FB`, `TP-CU09-A05` en [CU-09-corregir-calificacion-publicada.feature](../../04-pruebas/CU-09-corregir-calificacion-publicada.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
