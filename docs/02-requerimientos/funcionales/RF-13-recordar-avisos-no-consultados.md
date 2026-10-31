# RF-13 — Recordar los avisos no consultados

| Campo | Detalle |
|---|---|
| Épica | 2. Comunicación entre la escuela y la persona encargada |
| Actor | Persona encargada legal (el recordatorio lo dispara el servicio programado) |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-21 |

## Necesidad

Cuando el cuaderno o el reporte no llega al hogar, hoy no hay nada que lo reitere. La persona encargada necesita que se le recuerde lo que no ha leído.

## Requisito

El Portal deberá generar un recordatorio cuando un aviso siga pendiente al cumplirse el intervalo configurado por la Dirección.

## Criterios de aceptación

- Se genera un solo recordatorio por intervalo y hace referencia al aviso original.
- No se genera recordatorio si el aviso ya fue consultado o si el vínculo dejó de estar activo.
- Sin una configuración válida no se emite el recordatorio y se informa a la Dirección.

## Origen verificable

- **Evidencia:** [E-04, E-13](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-03, AC-08 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-03](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-05 — Dar seguimiento a avisos no consultados](../../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md): `TP-CU05-FB`, `TP-CU05-A04`, `TP-CU05-A05`, `TP-CU05-A07` en [CU-05-dar-seguimiento-a-avisos-no-consultados.feature](../../04-pruebas/CU-05-dar-seguimiento-a-avisos-no-consultados.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
