# RF-10 — Avisar la publicación de una evaluación

| Campo | Detalle |
|---|---|
| Épica | 2. Comunicación entre la escuela y la persona encargada |
| Actor | Persona encargada legal |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-16 |

## Necesidad

Hoy se entera de una evaluación solo si el estudiante le muestra el cuaderno. Necesita un aviso directo.

## Requisito

El Portal deberá generar un aviso dentro del Portal para cada persona encargada con vínculo activo cuando se publique una evaluación asignada.

## Criterios de aceptación

- Se genera un aviso por destinatario sin acción adicional de la docente.
- El aviso es distinguible del aviso de calificación.
- Un estudiante sin persona encargada activa queda señalado para la supervisión de la Dirección.

## Origen verificable

- **Evidencia:** [E-02, E-12, E-13](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-02 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-02](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-03 — Publicar evaluación asignada](../../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md): `TP-CU03-FB`, `TP-CU03-A06` en [CU-03-publicar-evaluacion-asignada.feature](../../04-pruebas/CU-03-publicar-evaluacion-asignada.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
