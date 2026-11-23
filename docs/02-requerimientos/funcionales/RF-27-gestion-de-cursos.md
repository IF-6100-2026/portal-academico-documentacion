# RF-27 — Gestionar cursos y grupos

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Épica | Registro y consulta de notas y evaluaciones |
| Actor | Personal autorizado del centro, provisional |
| Estado | Capacidad general con borrador de descomposición en CU-17 a CU-19; sin implementación y pendiente de revisión. |

## Necesidad
El seguimiento por estudiante requiere organizar cursos, grupos y periodos para asignar las evaluaciones correctas.

## Requisito general
El Portal deberá permitir al personal autorizado mantener cursos y grupos académicos y relacionarlos con su periodo y participantes.

## Descomposición provisional en casos de uso

- [CU-17 Registrar grupo académico](../../03-casos-de-uso/CU-17-registrar-grupo-academico.md): crear el contenedor de curso/materia, nivel, grupo y periodo.
- [CU-18 Asignar docente a grupo](../../03-casos-de-uso/CU-18-asignar-docente-a-grupo.md): establecer quién puede registrar actividad académica para el grupo.
- [CU-19 Inscribir estudiante en grupo](../../03-casos-de-uso/CU-19-inscribir-estudiante-en-grupo.md): incorporar un estudiante ya registrado a un grupo.

Estos CUs expresan metas distintas en vez de concentrarlas en un caso genérico de «gestionar cursos». Son propuestas locales para revisión; el modelo de curso/materia/grupo/periodo, la cardinalidad, las reglas de inscripción y los permisos siguen abiertos. No hay implementación asociada.
