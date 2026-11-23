# RF-30 — Gestionar seguimiento académico

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Necesidad | Registrar y consultar el avance académico del estudiante durante un periodo. |
| Requisito | El Portal deberá permitir gestionar evaluaciones y calificaciones, publicarlas y avisarlas a las personas autorizadas, consultar el progreso y conservar correcciones e historial. |
| Casos de uso | [CU-01 Consultar progreso académico](../../03-casos-de-uso/CU-01-consultar-progreso-academico.md), [CU-03 Publicar evaluación asignada](../../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md), [CU-04 Publicar calificación](../../03-casos-de-uso/CU-04-publicar-calificacion.md), [CU-09 Corregir calificación publicada](../../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) |
| Requisitos detallados | RF-02 a RF-11. |
| Estado | Implementado provisionalmente en la rama PC3; escala y política de corrección requieren confirmación. |

## Criterios generales

- La consulta muestra información publicada pertinente al estudiante y periodo seleccionados.
- El docente registra y publica evaluaciones/calificaciones según autorización.
- La publicación respeta la confirmación de congruencia acordada con el cuaderno.
- Cada publicación o corrección genera el aviso correspondiente para las personas autorizadas (RF-10, RF-11).
- Las correcciones conservan el valor anterior y su historial.
- Los borradores no se revelan a personas encargadas.

## Trazabilidad

RF-30 → CU-01 / CU-03 / CU-04 / CU-09 → features. RF-02 a RF-11 son requisitos detallados de este RF, no RF independientes.
