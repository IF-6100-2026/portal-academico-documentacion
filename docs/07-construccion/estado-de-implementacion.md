# Estado de implementación

## Resumen

La rama contiene un núcleo local integrado, propuesto para PC3: **CU-01 y CU-02 tienen implementación parcial**. Se construyeron sus caminos principales y controles de acceso; faltan alternos identificados en sus fichas. Ningún caso se marca como completo hasta cubrir también sus requisitos y escenarios acordados. Los otros 12 CU solo están documentados y no tienen código funcional.

| CU | Estado | Código disponible | Faltantes principales |
|---|---|---|---|
| CU-01 Consultar progreso académico | Parcial | Lista de vínculos activos; perfil y publicaciones por periodo; filtro de borradores; validación por solicitud; registro de consulta autorizada/denegada; interfaz de consulta. | Historial de correcciones A04; aún no hay calendario de avisos ni pruebas unitarias de handler. |
| CU-02 Vincular encargado con estudiante | Parcial | Catálogo de Dirección; alta con referencia de respaldo; protección contra duplicado activo; suspender/finalizar con motivo, autor, fecha e historial. | A04 de restricción institucional/custodia y flujo de solicitud pendiente; política de respaldo y aprobación no confirmada. |
| CU-03 a CU-10 | Especificado | Fichas de CU y escenarios Gherkin. | Publicación docente, avisos, mensajes, seguimiento, apelación, corrección y plazos. |
| CU-12 | Especificado | Ficha y escenarios Gherkin. | Registro y atención de quejas. |
| CU-13 a CU-15 | Propuesta especificada | Desglose de RF-19/RF-20, fichas y escenarios Gherkin. | Validación del grupo/profesor, issue de seguimiento y toda la implementación. |

Hay 14 CU documentados (CU-01 a CU-10, CU-12 a CU-15); CU-11 se retiró previamente. El conteo no significa que los 14 estén implementados. Véase [trazabilidad](../02-requerimientos/trazabilidad-rf-cu.md).

## Componentes integrados

- `backend/`: API Java 21/Spring Boot, handlers, seguridad, persistencia JPA, migración Liquibase y datos ficticios.
- `frontend/`: interfaz React/TypeScript para CU-01 y CU-02.
- `docker-compose.yml`: servicio PostgreSQL local con volumen persistente.
- `scripts/verificar-nucleo-pc3.ps1`: comprobaciones API reales del alcance CU-01/CU-02.

## Criterio de cierre para el núcleo

Antes de considerar cerrada la entrega de PC3, el grupo debe confirmar el núcleo con el profesor, revisar estas fichas/cambios, resolver las brechas de alcance, verificar que las pruebas requeridas por la rúbrica estén satisfechas y que todos los integrantes tengan sus commits propios de código. Los resultados locales de esta rama no sustituyen la consulta ni la entrega en Mediación Virtual.
