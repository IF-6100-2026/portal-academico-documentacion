# RF-28 — Gestionar profesores

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Épica | Registro y consulta de notas y evaluaciones |
| Actor | Personal autorizado del centro, provisional |
| Estado | Capacidad general con borrador de descomposición en CU-20 y CU-21; sin implementación y pendiente de revisión. |

## Necesidad
El Portal debe identificar docentes responsables para relacionarlos con cursos/grupos y organizar quién registra información académica.

## Requisito general
El Portal deberá permitir al personal autorizado mantener el catálogo de profesores y sus asignaciones académicas.

## Descomposición provisional en casos de uso

- [CU-20 Registrar docente](../../03-casos-de-uso/CU-20-registrar-docente.md): agregar al catálogo una identidad institucional ya provisionada.
- [CU-21 Actualizar perfil docente](../../03-casos-de-uso/CU-21-actualizar-perfil-docente.md): mantener los datos o inactivar el perfil conservando el historial.
- La asignación de docente a grupo se especializa como [CU-18](../../03-casos-de-uso/CU-18-asignar-docente-a-grupo.md), asociada también con RF-27.

La separación evita un CU genérico «gestionar profesores». Son propuestas locales para revisión: identidad, campos, permisos, baja lógica y provisión de cuentas no están aprobados ni implementados. No se elimina físicamente a una persona que tenga actividad académica asociada.
