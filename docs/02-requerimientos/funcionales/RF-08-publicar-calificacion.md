# RF-08 — Publicar la calificación

| Campo | Detalle |
|---|---|
| Épica | 1. Registro y consulta de notas y evaluaciones |
| Actor | Docente |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-14 |

## Necesidad

Necesita comunicar la calificación como un hecho distinto de la asignación, sin esperar al reporte físico que lleva el estudiante.

## Requisito

El Portal deberá permitir publicar las calificaciones confirmadas de una evaluación como primera versión de cada resultado.

## Criterios de aceptación

- La calificación publicada mantiene la referencia a su evaluación de origen.
- Una calificación ya publicada no se puede sobrescribir por esta vía; el cambio se remite a la corrección (RF-09).
- No se pueden publicar calificaciones de una evaluación inexistente o anulada.

## Origen verificable

- **Evidencia:** [E-03, E-12, E-13](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-06, AC-07, AC-08 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-01, P-02](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-04 — Publicar calificación](../../03-casos-de-uso/CU-04-publicar-calificacion.md): `TP-CU04-FB`, `TP-CU04-A02`, `TP-CU04-A05` en [CU-04-publicar-calificacion.feature](../../04-pruebas/CU-04-publicar-calificacion.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
