# RF-26 — Gestionar el registro de estudiantes

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Épica | Registro y consulta de notas y evaluaciones |
| Actor | Dirección, provisionalmente por continuidad con CU-02 |
| Estado | Capacidad general implementada en la rama PC3: CU-16 registra fichas y CU-19 las inscribe en grupos. La identificación institucional y duplicados siguen pendientes. |

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

## Descomposición en casos de uso

- [CU-16 Registrar estudiante](../../03-casos-de-uso/CU-16-registrar-estudiante.md): crear la ficha básica del estudiante.
- [CU-19 Inscribir estudiante en grupo](../../03-casos-de-uso/CU-19-inscribir-estudiante-en-grupo.md): incorporar un estudiante ya registrado a un grupo académico existente (RF-27).

## Trazabilidad
RF-26 → [CU-16](../../03-casos-de-uso/CU-16-registrar-estudiante.md) ([feature](../../04-pruebas/CU-16-registrar-estudiante.feature), [spec SDD](../../../specs/001-registrar-estudiante/spec.md)) y [CU-19](../../03-casos-de-uso/CU-19-inscribir-estudiante-en-grupo.md) ([feature](../../04-pruebas/CU-19-inscribir-estudiante-en-grupo.feature)). Es un alcance nuevo del PC3, sin requisitos detallados heredados del PC2.
