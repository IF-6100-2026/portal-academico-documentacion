# RF-29 — Gestionar vínculos y acceso de personas encargadas

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Necesidad | Controlar quién puede consultar información académica de cada estudiante. |
| Requisito | El Portal deberá mantener los vínculos autorizados entre personas encargadas y estudiantes y limitar la consulta de datos al vínculo y rol vigentes. |
| Refinamiento conservado | RF-01 Vincular a una persona encargada con un estudiante. |
| Casos de uso | CU-02 vincula; CU-01 consulta información solo tras validar autorización. |
| Estado | Generalización documental; las políticas institucionales de aprobación siguen pendientes. |

## Criterios generales

- La ficha del estudiante y la identidad de la persona encargada deben existir antes del vínculo.
- El vínculo requiere respaldo y autorización; una solicitud pendiente no concede acceso.
- Suspensión o finalización revoca el acceso sin borrar el historial.
- El Portal vuelve a validar identidad y vínculo al consultar datos protegidos.

## Trazabilidad

RF-01 → RF-29 → CU-02 / CU-01 → features existentes. RF-01 se conserva como detalle trazable, no como capacidad de nivel superior.
