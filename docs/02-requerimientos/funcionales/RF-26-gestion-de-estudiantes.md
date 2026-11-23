# RF-26 — Gestionar el registro de estudiantes

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Épica | Registro y consulta de notas y evaluaciones |
| Actor | Dirección, provisionalmente por continuidad con CU-02 |
| Estado | Propuesta; CU-16 especifica el alta. No implementado. |

## Necesidad
El seguimiento académico requiere una ficha del estudiante antes de asociar vínculos y publicaciones.

## Requisito general
El Portal deberá permitir al personal autorizado mantener el registro básico de estudiantes, comenzando por crear una ficha activa con identificador interno único e información académica mínima.

## Criterios generales
- Solo actores autorizados pueden crear o modificar fichas.
- El alta genera UUID y ficha activa.
- Los campos se definen en [CU-16 Registrar estudiante](../../03-casos-de-uso/CU-16-registrar-estudiante.md).
- El alta no da acceso a una persona encargada; se requiere [CU-02 y su respaldo](../../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md).
- No borrar físicamente fichas con historial.

## Trazabilidad
[CU-16](../../03-casos-de-uso/CU-16-registrar-estudiante.md) · [feature](../../04-pruebas/CU-16-registrar-estudiante.feature) · [spec SDD](../../../specs/001-registrar-estudiante/spec.md)
