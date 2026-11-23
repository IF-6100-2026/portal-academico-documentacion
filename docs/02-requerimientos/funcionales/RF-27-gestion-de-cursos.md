# RF-27 — Gestionar cursos y grupos

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Épica | Registro y consulta de notas y evaluaciones |
| Actor | Personal autorizado del centro, provisional |
| Estado | Implementado provisionalmente en la rama PC3 mediante CU-17 y CU-18; modelo simplificado pendiente de revisión. |

## Necesidad
El seguimiento por estudiante requiere organizar cursos, grupos y periodos para asignar las evaluaciones correctas.

## Requisito general
El Portal deberá permitir al personal autorizado mantener cursos y grupos académicos y relacionarlos con su periodo y participantes.

## Descomposición provisional en casos de uso

- [CU-17 Registrar grupo académico](../../03-casos-de-uso/CU-17-registrar-grupo-academico.md): crear el contenedor de curso/materia, nivel, grupo y periodo.
- [CU-18 Asignar docente a grupo](../../03-casos-de-uso/CU-18-asignar-docente-a-grupo.md): establecer quién puede registrar actividad académica para el grupo.

La inscripción de estudiantes en un grupo usa los grupos de este RF, pero se especifica en [CU-19](../../03-casos-de-uso/CU-19-inscribir-estudiante-en-grupo.md) bajo [RF-26](RF-26-gestion-de-estudiantes.md), para que cada caso de uso dependa de un solo RF general.

Estos CUs expresan metas distintas en vez de concentrarlas en un caso genérico de «gestionar cursos». La implementación local cubre materia, nivel, grupo, periodo, asignación docente e inscripción activa. El modelo de curso/materia/grupo/periodo, la cardinalidad, las reglas de inscripción y los permisos siguen abiertos a revisión.
