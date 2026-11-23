# RF-32 — Gestionar apelaciones

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Necesidad | Tramitar una apelación con evidencia, responsables, plazos, resolución y seguimiento. |
| Requisito | El Portal deberá apoyar la presentación, asignación, atención, resolución y consulta de apelaciones de calificación. |
| Casos de uso | [CU-07 Presentar apelación](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md), [CU-13 Aportar información a apelación](../../03-casos-de-uso/CU-13-aportar-informacion-a-apelacion.md), [CU-14 Registrar resolución de apelación](../../03-casos-de-uso/CU-14-registrar-resolucion-de-apelacion.md), [CU-15 Consultar resolución de apelación](../../03-casos-de-uso/CU-15-consultar-resolucion-de-apelacion.md) |
| Requisitos detallados | RF-17 a RF-21. |
| Estado | Implementado provisionalmente en la rama PC3; plazos, estados y procedimiento requieren aprobación institucional. |

## Criterios generales

- Cada apelación conserva su estado, responsables, aportes, evidencias, plazos y resolución.
- La persona encargada consulta únicamente resoluciones relacionadas con sus vínculos vigentes.
- Los cambios de estado conservan historial y no borran evidencia.
- La apelación nunca cambia la calificación por sí sola; si la resolución ordena corregirla, la docente lo hace en CU-09.

## Trazabilidad

RF-32 → CU-07 / CU-13 / CU-14 / CU-15 → features. RF-17 a RF-21 son requisitos detallados de este RF, no RF independientes. La supervisión de pendientes y la configuración de plazos pasaron a [RF-33](RF-33-supervisar-y-configurar-seguimiento.md).
