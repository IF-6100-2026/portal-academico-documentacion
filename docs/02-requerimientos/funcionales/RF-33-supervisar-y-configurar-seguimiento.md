# RF-33 — Supervisar y configurar el seguimiento

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Actor | Dirección |
| Necesidad | La Dirección supervisa el seguimiento académico (E-06), pero hoy lo pendiente está repartido entre cuadernos, reportes y comunicaciones (P-05). |
| Requisito | El Portal deberá permitir a la Dirección configurar plazos y recordatorios y supervisar en una sola vista los avisos, quejas y apelaciones pendientes, junto con el registro de consultas. |
| Casos de uso | [CU-08 Supervisar seguimiento académico](../../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md), [CU-10 Configurar plazos y recordatorios](../../03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md) |
| Requisitos detallados | RF-22, RF-24 y RF-25. |
| Estado | Implementado provisionalmente en la rama PC3; los valores de plazos y la retención de la bitácora no son políticas aprobadas. |

## Criterios generales

- Solo la Dirección configura plazos y recordatorios; el Portal no inventa valores predeterminados.
- La vista de supervisión muestra pendientes y plazos sin exponer borradores ni información fuera de su permiso.
- El registro de consultas permite comprobar quién leyó qué y cuándo (RS-02).
- Los cambios de configuración conservan autor y fecha.

## Trazabilidad

RF-33 → CU-08 / CU-10 → features. RF-22, RF-24 y RF-25 son requisitos detallados de este RF, no RF independientes. Se separó de RF-32 en el PC3 para que cada caso de uso dependa de un solo RF general.
