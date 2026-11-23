# RF-28 — Gestionar profesores

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Épica | Registro y consulta de notas y evaluaciones |
| Actor | Personal autorizado del centro, provisional |
| Estado | Capacidad general citada en la retroalimentación; sin CU ni implementación. |

## Necesidad
El Portal debe identificar docentes responsables para relacionarlos con cursos/grupos y organizar quién registra información académica.

## Requisito general
El Portal deberá permitir al personal autorizado mantener el catálogo de profesores y sus asignaciones académicas.

## Guía de descomposición
- Registrar y consultar un perfil docente identificable.
- Consultar y cambiar asignaciones con autorización.
- Conservar el historial de publicaciones al retirar una asignación.
- Inactivar perfiles con historial en vez de borrarlos.
- Especificar cada operación concreta en un CU con actor y validaciones.

No se crea un CU genérico «gestionar profesores». Los CU concretos de registro, actualización y asignación quedan pendientes de descomposición, después de definir el modelo de curso/grupo de RF-27.
