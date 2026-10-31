# RF-23 — Alertar a la Dirección los avisos sin consulta

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-22 |

## Necesidad

La Dirección supervisa el seguimiento y necesita saber a quién contactar cuando los recordatorios no bastan.

## Requisito

El Portal deberá generar una alerta para la Dirección cuando un aviso alcance la cantidad configurada de recordatorios sin consulta, o cuando un estudiante no tenga persona encargada activa.

## Criterios de aceptación

- La alerta muestra estudiante, destinatario, publicación de origen, recordatorios emitidos y antigüedad.
- Después de la alerta no se emiten más recordatorios para ese aviso.
- La alerta no modifica ninguna información académica.

## Origen verificable

- **Evidencia:** [E-06, E-13](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-08, AC-11 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-03, P-05](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-05 — Dar seguimiento a avisos no consultados](../../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md): `TP-CU05-A02`, `TP-CU05-A03` en [CU-05-dar-seguimiento-a-avisos-no-consultados.feature](../../04-pruebas/CU-05-dar-seguimiento-a-avisos-no-consultados.feature)
- [CU-08 — Supervisar el seguimiento académico](../../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md): `TP-CU08-FB` en [CU-08-supervisar-seguimiento-academico.feature](../../04-pruebas/CU-08-supervisar-seguimiento-academico.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
