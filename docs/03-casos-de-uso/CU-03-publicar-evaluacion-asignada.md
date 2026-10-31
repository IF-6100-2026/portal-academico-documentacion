# CU-03-PublicarEvaluacionAsignada («Publicar evaluación asignada»)

| Campo | Detalle |
|---|---|
| **Descripción** | Que la docente registre una sola vez para el grupo una evaluación ya anotada en el cuaderno de la asignatura, confirme que coincide con él y la publique, de modo que cada persona encargada reciba un aviso sin depender del cuaderno que lleva el estudiante (P-01, P-02). |
| **Estereotipo y paquete** | «Épica 1» Registro y consulta de notas y evaluaciones |
| **Precondiciones** | 1. La docente tiene una cuenta activa.<br>2. El grupo, la materia, el periodo y la nómina existen. |
| **Postcondiciones** | La evaluación queda publicada para cada estudiante activo del grupo y se genera un aviso para cada persona encargada con vínculo activo.<br>**Garantía mínima** (si no termina con éxito): Nada se publica sin la confirmación con el cuaderno; repetir la publicación no duplica la evaluación ni los avisos. |
| **Actores** | A-02 Docente (principal) |
| **Relaciones del caso de uso** | Asociado con los actores A-02 Docente. Ninguna relación «include» o «extend». Normalmente precede a [CU-04](CU-04-publicar-calificacion.md). Los avisos generados se siguen en [CU-05](CU-05-dar-seguimiento-a-avisos-no-consultados.md). |

## Flujo básico

1. La docente solicita registrar una evaluación para un grupo.
2. El Portal valida que la docente esté asignada al grupo, la materia y el periodo (RS-03) (A01).
3. La docente indica nombre, tipo, fecha de asignación, fecha de entrega e instrucciones.
4. El Portal valida los datos, la coherencia de las fechas y la nómina activa (A02, A03).
5. La docente confirma que la evaluación coincide con lo anotado en el cuaderno de la asignatura.
6. El Portal valida que la confirmación esté presente (A04).
7. La docente autoriza la publicación (A05).
8. El Portal publica la evaluación para cada estudiante activo del grupo, con autor y fecha.
9. El Portal genera un aviso de evaluación asignada para cada persona encargada con vínculo activo (A06).
10. El Portal muestra a la docente el resumen: estudiantes incluidos, avisos generados y estudiantes sin persona encargada.
11. Más adelante, la docente abre la evaluación y el Portal le muestra, por estudiante, si cada persona encargada ya la leyó y cuándo (RS-02).

## Flujos alternos

- **A01 — Docente sin asignación (paso 2).** El Portal rechaza la operación sin publicar nada. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — Datos incompletos o fechas incoherentes (paso 4).** El Portal indica qué datos corregir y guarda la evaluación como borrador. La docente puede retomar el paso 3.
- **A03 — Estudiantes inactivos en la nómina (paso 4).** El Portal excluye a los estudiantes inactivos en la fecha de la evaluación y lo informa a la docente. El flujo continúa en el paso 5 con la nómina activa.
- **A04 — Falta la confirmación con el cuaderno (paso 6).** El Portal impide la publicación y mantiene la evaluación como borrador, sin avisos. La docente puede retomar el paso 5.
- **A05 — Publicación repetida (paso 7).** El Portal reconoce que la evaluación ya fue publicada para el grupo. Conserva una sola publicación por estudiante y no genera avisos duplicados. El Portal muestra el resumen de la publicación existente y el caso de uso termina.
- **A06 — Estudiante sin persona encargada activa (paso 9).** El Portal publica la evaluación para ese estudiante sin generar aviso. El Portal señala al estudiante para la supervisión de la Dirección (CU-08). El flujo continúa en el paso 10.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Publicar evaluación](prototipos/stitch-2/screen.png)
- **Métricas:** complejidad media (11 pasos y 6 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-04](../02-requerimientos/funcionales/RF-04-registrar-evaluacion-asignada.md), [RF-05](../02-requerimientos/funcionales/RF-05-confirmar-congruencia-con-el-cuaderno.md), [RF-06](../02-requerimientos/funcionales/RF-06-publicar-evaluacion-asignada.md), [RF-10](../02-requerimientos/funcionales/RF-10-avisar-publicacion-de-evaluacion.md)
- **Pruebas:** [CU-03-publicar-evaluacion-asignada.feature](../04-pruebas/CU-03-publicar-evaluacion-asignada.feature)
- **Issue:** _por crear_ <!-- issue:CU-03 -->
- **Equivale en el PC1 a:** UC-03
