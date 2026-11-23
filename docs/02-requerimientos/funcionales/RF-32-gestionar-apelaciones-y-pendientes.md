# RF-32 — Gestionar apelaciones y pendientes

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Necesidad | Tramitar una apelación con evidencia, responsables, plazos, resolución y seguimiento. |
| Requisito | El Portal deberá apoyar la presentación, asignación, atención, resolución y consulta de apelaciones, además de permitir supervisar pendientes y plazos relacionados. |
| Refinamientos conservados | RF-17 a RF-22 y RF-24 a RF-25; RF-23 también se enlaza con RF-31. |
| Casos de uso | CU-06, CU-07, CU-08, CU-10, CU-12, CU-13, CU-14 y CU-15. |
| Estado | Generalización documental; no implica que estos flujos estén implementados ni que sus reglas institucionales estén aprobadas. |

## Criterios generales

- Cada apelación conserva su estado, responsables, aportes, evidencias, plazos y resolución.
- La persona encargada consulta únicamente resoluciones relacionadas con sus vínculos vigentes.
- Dirección supervisa pendientes y plazos sin exponer borradores o información fuera de su permiso.
- Los cambios de estado conservan historial y no borran evidencia.
- Los plazos y recordatorios dependen de configuración autorizada; no se inventan valores institucionales.

## Trazabilidad

RF-17 a RF-22 y RF-24 a RF-25 → RF-32 → CU-06 / CU-07 / CU-08 / CU-10 / CU-12 / CU-13 / CU-14 / CU-15 → features existentes.
