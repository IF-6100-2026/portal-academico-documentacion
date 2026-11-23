# Estado de implementación

## Resumen

La rama `codex/pc3-remaining-use-cases` parte de la rama PC3 `pc3/core-consulta-vinculos` y añade los flujos de los 17 casos que estaban pendientes: CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21. Junto con CU-01, CU-02 y CU-16, hay código para los **20 casos vigentes**. CU-11 se retiró antes de PC3.

Esta rama está publicada mediante el [PR #25](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/25), abierto contra `pc3/core-consulta-vinculos` y pendiente de revisión e integración. Los issues de los 17 casos siguen abiertos; el PR se creó sin palabras clave de cierre, por lo que deben cerrarse explícitamente al integrar. Los flujos principales de los 17 casos nuevos se probaron manualmente por HTTP contra una instancia temporal de PostgreSQL 17. Los escenarios Gherkin siguen sin ejecutarse como suite Cucumber. La implementación usa supuestos de demostración allí donde falta una decisión institucional; no se atribuye aprobación docente.

## Matriz de implementación y trazabilidad

| CU | Estado en esta rama | Código principal | Issue / brecha y límite |
|---|---|---|---|
| CU-01 Consultar progreso académico | Implementado en la base PC3 | `GuardianAcademicController`, consulta de progreso y UI React. | [#3](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/3). 31 comprobaciones HTTP en la rama base; avisos son internos, no correo/calendario. |
| CU-02 Vincular encargado con estudiante | Implementado en la base PC3 | `DirectorLinkController`, handler de vínculos, catálogo, bitácora e UI. | [#4](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/4). 31 comprobaciones HTTP en la rama base; el proceso institucional de aprobación de pendientes sigue abierto. |
| CU-03 Publicar evaluación asignada | Implementado | `TeacherAcademicWorkflowController`, `AcademicPublicationWorkflow`; el docente publica al grupo y se generan avisos. | [#5](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/5); escala de avisos y reglas escolares son provisionales. |
| CU-04 Publicar calificación | Implementado | `AcademicPublicationWorkflow`; publicación por estudiante y conservación de borrador para corrección. | [#6](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/6); rango 0–100 provisional, requiere confirmación. |
| CU-05 Dar seguimiento a avisos no consultados | Implementado | `AcademicPublicationWorkflow`; lecturas, recordatorios limitados y alertas internas. | [#7](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/7); proceso invocable por Dirección, sin scheduler/correo configurado. |
| CU-06 Comunicarse sobre un estudiante | Implementado | `StudentCommunicationWorkflow` y controlador de conversaciones. | [#8](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/8); mensajes internos, sin adjuntos ni correo. |
| CU-07 Presentar apelación de una calificación | Implementado | `AppealWorkflow`, endpoint de persona encargada y cálculo de fecha límite configurada. | [#9](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/9); identidad, plazos y ciclo son propuestas locales. |
| CU-08 Supervisar el seguimiento académico | Implementado | `AcademicPublicationWorkflow` y panel de Dirección. | [#10](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/10); conjunto de datos visible requiere validación institucional. |
| CU-09 Corregir una calificación publicada | Implementado | `AcademicPublicationWorkflow`; historial de versiones, motivo y control optimista de concurrencia. | [#11](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/11); política académica de corrección sigue pendiente. |
| CU-10 Configurar plazos y recordatorios | Implementado | `DirectorWorkflowController`; valores actuales e historial de cambios. | [#12](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/12); rangos configurables no equivalen a política aprobada. |
| CU-11 Bitácora de auditoría | Retirado | No aplica. | Excluido del listado vigente antes de PC3. |
| CU-12 Presentar queja por información no recibida | Implementado | `StudentCommunicationWorkflow`, controladores de persona encargada, docente y Dirección. | [#13](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/13); la coincidencia se resuelve con materia, periodo y publicación. |
| CU-13 Aportar información a una apelación | Implementado | `AppealWorkflow`; aportes y solicitudes de información en el expediente. | [#17](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/17); descomposición propuesta, falta validación del grupo. |
| CU-14 Registrar resolución de apelación | Implementado | `AppealWorkflow`; acción del rol Comité, resolución separada de la nota. | [#18](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/18); composición y reglas del Comité provisionales. |
| CU-15 Consultar resolución de apelación | Implementado | `AppealWorkflow`, endpoint familiar y acuse/cierre. | [#19](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/19); descomposición propuesta, falta validación del grupo. |
| CU-16 Registrar estudiante | Implementado e integrado en la base PC3 | Handler, endpoint protegido, migración y formulario React. | [#16](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/16), cerrado mediante [PR #15](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/15). No hay prueba de integración automatizada. |
| CU-17 Registrar grupo académico | Implementado | `DirectorAcademicCatalogController` y `ManageAcademicCatalogHandlerImpl`. | [#20](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/20); modelo simplificado de materia/nivel/grupo/periodo. |
| CU-18 Asignar docente a grupo | Implementado | Catálogo académico y relación de asignación docente. | [#21](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/21); se permite una asignación activa por grupo bajo reglas provisionales. |
| CU-19 Inscribir estudiante en grupo | Implementado | Catálogo académico y matrícula activa por estudiante/grupo. | [#22](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/22); vigencia y cambios de grupo requieren validación. |
| CU-20 Registrar docente | Implementado | Catálogo de perfiles enlazado a una cuenta docente que ya existe. | [#23](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/23); no crea credenciales ni integra identidad institucional. |
| CU-21 Actualizar perfil docente | Implementado | Historial de perfil, actualización e inactivación sin borrar el historial. | [#24](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/24); campos y autorización institucional pendientes. |

## Componentes integrados

- `backend/`: Java 21/Spring Boot, controladores delgados, flujos/handlers, seguridad por rol, persistencia JDBC/JPA y Liquibase.
- `frontend/`: React/TypeScript con vistas para encargado, Dirección, docente y Comité.
- `docker-compose.yml`: PostgreSQL local y persistencia para desarrollo.
- `docs/07-construccion/checklist-casos-de-uso.md`: resultado verificable por caso.
- `docs/08-resultados-de-pruebas/resultados-pruebas.md`: comandos, smoke HTTP y limitaciones.

## Cobertura y límites

- En la rama se ejecutaron `mvn test` (12 pruebas, 0 fallos/errores, Checkstyle limpio), `npm run build` y `python scripts/validar_documentacion.py` (20 CUs, 20 archivos `.feature`, 103 escenarios escritos, 0 errores estructurales).
- Se probó por HTTP al menos un flujo principal para cada CU de los 17 añadidos, contra una instancia desechable local de PostgreSQL 17. No es una prueba automatizada repetible aún.
- Los 103 escenarios `.feature` son especificaciones y no un reporte de ejecución Cucumber.
- No se ejecutaron pruebas automatizadas de navegador, accesibilidad, rendimiento ni revisión con usuarios.
- Las cuentas y datos son de demostración. No cargar datos académicos reales.
- Falta revisar el PR #25 y las decisiones abiertas, confirmar commits de todos los integrantes y, tras integrar la rama, actualizar el tag final `pc3`, cerrar sus issues y entregar los enlaces en Mediación Virtual.
