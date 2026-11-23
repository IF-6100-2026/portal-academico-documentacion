# Cambios del PC2 al PC3

## Evidencia y respuesta

El PC2 recibió **15/20**. El comentario del profesor indicó que el diagrama de contexto se había entregado como BPMN, que la representación de 34 flujos no era legible, y que varios requerimientos estaban reunidos en pocos casos de uso. En particular, CU-07 abarcaba seis RF. La guía del PC3 requiere un producto integrado y un núcleo cuya selección debía consultarse con el profesor.

No se encontró una selección aprobada del núcleo y el equipo no la recuerda con certeza. Por eso CU-01 y CU-02 se eligieron aquí como **propuesta razonada**: consulta académica depende de vínculos autorizados, y juntos permiten demostrar seguridad por rol/vínculo, persistencia, auditoría, backend y UI. No se presentan como una selección docente aprobada. Se debe confirmar el alcance con el profesor y el grupo.

## Observaciones atendidas en esta preparación

| Observación o necesidad | Respuesta PC3 | Límite visible |
|---|---|---|
| Contexto modelado como BPMN y con exceso de detalle | Se agregó un diagrama de contexto de sistema en SVG; el catálogo agrupa F-01 a F-34 por actor y flujo. | La revisión del docente sigue pendiente. Los BPMN de procesos actuales se conservan como modelos de proceso, no como diagrama de contexto. |
| Casos de uso demasiado amplios y supuestos no explicitados | CU-07 se acotó a presentar y asignar inicialmente una apelación. RF-19 y RF-20 se desglosaron en CU-13, CU-14 y CU-15. Se documentaron estados supuestos, preguntas abiertas y propuestas. | Los nuevos CU y estados requieren revisión del grupo/profesor. |
| Producto integrado | Se conectaron interfaz React, API Spring Boot, PostgreSQL y migraciones Liquibase. | El núcleo funcional propuesto se concentra en CU-01 y CU-02; los otros casos están especificados y se documentan sin presentarlos como implementados. |
| Patrones de implementación del curso | La API usa handlers CQRS, resultados `sealed`, UUID, Liquibase y Checkstyle. | El cumplimiento se limita al código presente; no implica una certificación del profesor. |
| Necesidad de casos de prueba | Hay un archivo Gherkin por CU, un guion repetible de humo API y resultados de pruebas separados por tipo. | Gherkin permanece como especificación, no ejecutado por Cucumber. |

## Estado y decisiones pendientes

La ruta de contexto ahora es [SVG y catálogo](01-inicio/diagrama-de-contexto.md). El inventario de código, brechas y verificaciones está en [estado de implementación](07-construccion/estado-de-implementacion.md), [brechas](07-construccion/brechas-de-requerimientos.md) y [resultados](08-resultados-de-pruebas/resultados-pruebas.md).

PC3 requiere commits de los integrantes. El historial del repositorio conserva las contribuciones previas de sus autores; esta entrega no crea ni atribuye commits ficticios. El equipo debe revisar su propia contribución y enviar el enlace de repositorio/tag en Mediación Virtual.
