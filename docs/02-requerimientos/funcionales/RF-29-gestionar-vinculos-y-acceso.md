# RF-29 — Gestionar vínculos y acceso de personas encargadas

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Necesidad | Controlar quién puede consultar información académica de cada estudiante. |
| Requisito | El Portal deberá mantener los vínculos autorizados entre personas encargadas y estudiantes y limitar la consulta de datos al vínculo y rol vigentes. |
| Casos de uso | [CU-02 Vincular encargado con estudiante](../../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) |
| Requisito detallado | RF-01 Vincular a una persona encargada con un estudiante. |
| Estado | Implementado en la rama PC3; las políticas institucionales de aprobación siguen pendientes. |

## Criterios generales

- La ficha del estudiante y la identidad de la persona encargada deben existir antes del vínculo.
- El vínculo requiere respaldo y autorización; una solicitud pendiente no concede acceso.
- Suspensión o finalización revoca el acceso sin borrar el historial.
- El Portal vuelve a validar identidad y vínculo al consultar datos protegidos.

## Trazabilidad

RF-29 → CU-02 → feature. RF-01 es un requisito detallado de este RF, no un RF independiente. Los casos que muestran datos protegidos, como [CU-01](../../03-casos-de-uso/CU-01-consultar-progreso-academico.md) (RF-30), dependen del vínculo activo que crea este RF.
