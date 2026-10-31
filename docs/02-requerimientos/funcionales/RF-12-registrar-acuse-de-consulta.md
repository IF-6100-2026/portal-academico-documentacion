# RF-12 — Registrar el acuse de consulta de un aviso

| Campo | Detalle |
|---|---|
| Épica | 2. Comunicación entre la escuela y la persona encargada |
| Actor | Persona encargada legal |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-18 y RF-19 |

## Necesidad

Hoy la docente sabe que la persona encargada recibió la información solo cuando ve la firma en el cuaderno. Necesita darse cuenta de que la persona encargada leyó lo que publicó, y cuándo.

## Requisito

El Portal deberá registrar la persona, la fecha y la hora en que una persona encargada abre por primera vez un aviso, mantener el aviso como pendiente mientras no exista esa apertura y mostrar esa lectura a la docente que publicó.

## Criterios de aceptación

- La primera apertura cambia el aviso de pendiente a consultado.
- Las aperturas posteriores no reemplazan el primer acuse.
- Ningún aviso aparece como consultado sin una apertura registrada.
- La docente que publicó ve, por estudiante, si cada persona encargada leyó la publicación y cuándo (RS-02).

## Origen verificable

- **Evidencia:** [E-04](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** AC-03 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-03](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-05 — Dar seguimiento a avisos no consultados](../../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md): `TP-CU05-FB`, `TP-CU05-A01`, `TP-CU05-A06` en [CU-05-dar-seguimiento-a-avisos-no-consultados.feature](../../04-pruebas/CU-05-dar-seguimiento-a-avisos-no-consultados.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
