# Cambios del PC2 al PC3

## Evidencia y respuesta

El PC2 recibió **15/20**. El comentario del profesor indicó que el diagrama de contexto se había entregado como BPMN, que la representación de 34 flujos no era legible, y que varios requerimientos estaban reunidos en pocos casos de uso. En particular, CU-07 abarcaba seis RF. La guía del PC3 requiere un producto integrado y un núcleo cuya selección debía consultarse con el profesor.

No se encontró una selección aprobada del núcleo y el equipo no la recuerda con certeza. Por eso CU-01 y CU-02 se eligieron aquí como **propuesta razonada**: consulta académica depende de vínculos autorizados, y juntos permiten demostrar seguridad por rol/vínculo, persistencia, auditoría, backend y UI. No se presentan como una selección docente aprobada. Se debe confirmar el alcance con el profesor y el grupo.

## Observaciones atendidas en esta preparación

| Observación o necesidad | Respuesta PC3 | Límite visible |
|---|---|---|
| Contexto modelado como BPMN y con exceso de detalle | Se creó un diagrama C4 Nivel 1 editable en IcePanel con cuatro entidades externas y relaciones legibles; el catálogo conserva el detalle de F-01 a F-39. F-35 a F-39 corresponden a propuestas CU-17 a CU-21. | La revisión del docente sigue pendiente. Los BPMN de procesos actuales se conservan como modelos de proceso, no como diagrama de contexto. |
| Casos de uso demasiado amplios y supuestos no explicitados | CU-07 se acotó; RF-19/RF-20 se desglosaron en CU-13 a CU-15. La jerarquía local define RF-26 a RF-32 como capacidades generales y conserva RF-01 a RF-25 como refinamientos. CU-16 se especificó e implementó; RF-27/RF-28 se descompusieron en borradores CU-17 a CU-21. | El mapeo y los CUs nuevos requieren revisión del grupo. CU-16 tiene pruebas unitarias, build y verificación manual con PostgreSQL; falta automatizar la integración. CU-17 a CU-21 no tienen código. |
| Producto integrado | Se conectaron interfaz React, API Spring Boot, PostgreSQL y migraciones Liquibase. | El núcleo funcional propuesto se concentra en CU-01 y CU-02; los otros casos están especificados y se documentan sin presentarlos como implementados. |
| Patrones de implementación del curso | La API usa handlers CQRS, resultados `sealed`, UUID, Liquibase y Checkstyle. | El cumplimiento se limita al código presente; no implica una certificación del profesor. |
| Necesidad de casos de prueba | Hay un archivo Gherkin por CU, un guion repetible de humo API y resultados de pruebas separados por tipo. | Gherkin permanece como especificación, no ejecutado por Cucumber. |
| Issue por caso de uso y trazabilidad bidireccional | Todos los 20 casos vigentes tienen issue, enlace desde la matriz y etiqueta `caso-de-uso`; los issues abiertos tienen además `brecha` y responsable asignado. CU-16 (#16) se cerró al fusionar el PR #15. | Los issues pendientes de implementación siguen abiertos como brechas; el profesor/grupo aún debe revisar las propuestas CU-13–CU-15 y CU-17–CU-21. Ver [estado de implementación](07-construccion/estado-de-implementacion.md). |

## Estado y decisiones pendientes

La fuente editable del contexto está en [IcePanel y su catálogo de flujos](01-inicio/diagrama-de-contexto.md). El inventario de código, brechas y verificaciones está en [estado de implementación](07-construccion/estado-de-implementacion.md), [brechas](07-construccion/brechas-de-requerimientos.md) y [resultados](08-resultados-de-pruebas/resultados-pruebas.md).

PC3 requiere commits de todos los integrantes. El PR #15 ya se fusionó y los issues vigentes están vinculados desde la matriz. El historial conserva las contribuciones previas de sus autores; esta entrega no crea ni atribuye commits ficticios. Antes de presentar, comprobar que cada integrante tenga commits atribuibles a su autoría, actualizar el tag `pc3` al commit final de esta rama y enviar los enlaces del repositorio y tag en Mediación Virtual.
