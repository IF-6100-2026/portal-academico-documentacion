# CU-01-ConsultarProgresoAcademico («Consultar progreso académico»)

| Campo | Detalle |
|---|---|
| **Descripción** | Que la persona encargada consulte el perfil, las evaluaciones y las calificaciones publicadas de un estudiante con quien tiene vínculo activo, sin depender de que el estudiante lleve el cuaderno o el reporte al hogar (P-01, P-02). |
| **Estereotipo y paquete** | «Épica 1» Registro y consulta de notas y evaluaciones |
| **Precondiciones** | 1. La persona encargada tiene una cuenta activa en el Portal.<br>2. El estudiante pertenece a un periodo y grupo registrados. |
| **Postcondiciones** | La persona encargada ve solo la información publicada de los estudiantes que tiene vinculados, y la consulta queda registrada (RS-02).<br>**Garantía mínima** (si no termina con éxito): Ningún dato de un estudiante no vinculado queda expuesto, y los intentos denegados quedan registrados. |
| **Actores** | A-01 Persona encargada legal (principal) |
| **Relaciones del caso de uso** | Asociado con los actores A-01 Persona encargada legal. Ninguna relación «include» o «extend». Desde el detalle de una calificación la persona encargada puede iniciar, como casos independientes, [CU-06](CU-06-comunicarse-sobre-un-estudiante.md), [CU-07](CU-07-gestionar-apelacion-de-calificacion.md) o [CU-12](CU-12-presentar-queja-por-informacion-no-recibida.md). |

## Flujo básico

1. La persona encargada ingresa al Portal.
2. El Portal valida la identidad y obtiene los vínculos activos de la persona encargada (RS-03) (A01, A05).
3. El Portal muestra los estudiantes vinculados y, para el primero, las últimas publicaciones y los avisos pendientes (RS-01).
4. La persona encargada elige un estudiante y un periodo.
5. El Portal vuelve a validar el vínculo para esa solicitud concreta (RS-03) (A02).
6. El Portal muestra el perfil del estudiante (grado, grupo, materias y docentes) y sus evaluaciones y calificaciones publicadas del periodo, por materia, con fecha (A03).
7. La persona encargada abre una evaluación o una calificación.
8. El Portal muestra el detalle: instrucciones y fechas de la evaluación, o la calificación con su observación y su versión vigente (A04).
9. El Portal registra la consulta (RS-02).

## Flujos alternos

- **A01 — Sin vínculos activos (paso 2).** El Portal informa que no hay estudiantes habilitados para consulta. No se muestra ningún perfil ni registro académico. El Portal registra la consulta y el caso de uso termina.
- **A02 — Estudiante no autorizado (paso 5).** El Portal deniega la solicitud sin confirmar si el estudiante existe. El Portal registra el intento denegado y el caso de uso termina.
- **A03 — Periodo sin publicaciones (paso 6).** El Portal informa que el periodo no tiene publicaciones, sin mostrar borradores. El flujo continúa en el paso 9.
- **A04 — Calificación corregida (paso 8).** El Portal muestra la versión vigente e indica que fue corregida, con la fecha de la corrección. La persona encargada puede ver la versión anterior. El flujo continúa en el paso 9.

- **A05 — Sesión ausente, vencida o cuenta inactiva (paso 2).** El Portal deniega la consulta y solicita autenticación válida sin mostrar estudiantes, publicaciones ni vínculos. No registra una lectura académica y el caso de uso termina.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-01](../02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md), [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Perfil y progreso académico](prototipos/stitch-1/screen.png)
- **Métricas:** complejidad media (9 pasos y 5 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-02](../02-requerimientos/funcionales/RF-02-consultar-perfil-del-estudiante.md), [RF-03](../02-requerimientos/funcionales/RF-03-consultar-evaluaciones-y-calificaciones.md)
- **Pruebas:** [CU-01-consultar-progreso-academico.feature](../04-pruebas/CU-01-consultar-progreso-academico.feature)
- **Issue:** _por crear_ <!-- issue:CU-01 -->
- **Equivale en el PC1 a:** UC-01
