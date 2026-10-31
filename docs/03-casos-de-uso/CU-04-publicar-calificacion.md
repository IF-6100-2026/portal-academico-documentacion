# CU-04-PublicarCalificacion («Publicar calificación»)

| Campo | Detalle |
|---|---|
| **Descripción** | Que la docente registre las calificaciones del grupo para una evaluación, confirme que coinciden con el cuaderno y las publique como un hecho distinto de la asignación. Así la persona encargada no depende del reporte físico que lleva el estudiante (P-01, P-02). |
| **Estereotipo y paquete** | «Épica 1» Registro y consulta de notas y evaluaciones |
| **Precondiciones** | 1. La docente tiene una cuenta activa.<br>2. La evaluación existe y pertenece a un grupo, una materia y un periodo de la docente. |
| **Postcondiciones** | Cada calificación queda publicada como primera versión y se genera un aviso de calificación para cada persona encargada con vínculo activo.<br>**Garantía mínima** (si no termina con éxito): Ninguna calificación publicada se sobrescribe; sin la confirmación con el cuaderno no se publica nada. |
| **Actores** | A-02 Docente (principal) |
| **Relaciones del caso de uso** | Asociado con los actores A-02 Docente. Ninguna relación «include» o «extend». Sigue normalmente a [CU-03](CU-03-publicar-evaluacion-asignada.md). Un cambio posterior se hace con [CU-09](CU-09-corregir-calificacion-publicada.md). Los avisos generados se siguen en [CU-05](CU-05-dar-seguimiento-a-avisos-no-consultados.md). |

## Flujo básico

1. La docente elige una evaluación registrada.
2. El Portal valida la asignación de la docente y que la evaluación esté vigente (RS-03) (A01, A02).
3. La docente registra la calificación y la observación de cada estudiante del grupo en una misma pantalla.
4. El Portal valida la escala y que no falten valores obligatorios (A03).
5. La docente indica la fecha de devolución y confirma que las calificaciones coinciden con el cuaderno de la asignatura.
6. El Portal valida que la confirmación esté presente (A04).
7. La docente autoriza la publicación (A05).
8. El Portal publica cada calificación como primera versión, con autor y fecha.
9. El Portal genera un aviso de calificación para cada persona encargada con vínculo activo (A06).
10. El Portal muestra a la docente el resumen de la publicación.
11. Más adelante, la docente abre la evaluación y el Portal le muestra, por estudiante, si cada persona encargada ya leyó la calificación y cuándo (RS-02).

## Flujos alternos

- **A01 — Docente sin asignación (paso 2).** El Portal rechaza la operación sin mostrar ni publicar calificaciones. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — Evaluación inexistente o anulada (paso 2).** El Portal impide registrar calificaciones e informa el estado de la evaluación. El caso de uso termina.
- **A03 — Calificación fuera de escala o incompleta (paso 4).** El Portal señala cada valor por corregir y guarda el conjunto como borrador. La docente puede retomar el paso 3.
- **A04 — Falta la confirmación con el cuaderno (paso 6).** El Portal impide la publicación y conserva las calificaciones como borrador, sin avisos. La docente puede retomar el paso 5.
- **A05 — Calificación ya publicada (paso 7).** El Portal impide sobrescribir la versión publicada. El Portal indica que el cambio se hace con CU-09 y el caso de uso termina.
- **A06 — Estudiante sin persona encargada activa (paso 9).** El Portal publica la calificación sin generar aviso. El Portal señala al estudiante para la supervisión de la Dirección (CU-08). El flujo continúa en el paso 10.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Publicar evaluación y resultado](prototipos/stitch-2/screen.png)
- **Métricas:** complejidad media (11 pasos y 6 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-05](../02-requerimientos/funcionales/RF-05-confirmar-congruencia-con-el-cuaderno.md), [RF-07](../02-requerimientos/funcionales/RF-07-registrar-calificaciones-del-grupo.md), [RF-08](../02-requerimientos/funcionales/RF-08-publicar-calificacion.md), [RF-11](../02-requerimientos/funcionales/RF-11-avisar-publicacion-de-calificacion.md)
- **Pruebas:** [CU-04-publicar-calificacion.feature](../04-pruebas/CU-04-publicar-calificacion.feature)
- **Issue:** [Issue](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/6) <!-- issue:CU-04 -->
- **Equivale en el PC1 a:** UC-04 («Publicar resultado académico»)
