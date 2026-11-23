# RF-27 — Gestionar cursos y grupos

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Épica | Registro y consulta de notas y evaluaciones |
| Actor | Personal autorizado del centro, provisional |
| Estado | Capacidad general citada en la retroalimentación; sin CU ni implementación. |

## Necesidad
El seguimiento por estudiante requiere organizar cursos, grupos y periodos para asignar las evaluaciones correctas.

## Requisito general
El Portal deberá permitir al personal autorizado mantener cursos y grupos académicos y relacionarlos con su periodo y participantes.

## Guía de descomposición
- Definir curso/materia, grupo y periodo.
- Restringir cambios al personal autorizado.
- Validar asociaciones con docentes y estudiantes.
- Inactivar registros con historial en vez de borrarlos.
- Especificar cada objetivo concreto en un CU propio.

No se crea un CU genérico «gestionar cursos» porque mezcla objetivos. Los CUs de registrar/modificar curso y asociar estudiantes/docentes quedan pendientes de descomposición. Definir si curso, materia, grupo y periodo son conceptos distintos.
