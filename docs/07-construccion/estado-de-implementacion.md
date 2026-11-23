# Estado de implementación

## Resumen

La rama PC3 `pc3/core-consulta-vinculos` integra CU-01, CU-02 y CU-16; el PR [#15](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/15) fue fusionado el 23/11/2026 y cerró el [issue #16](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/16). CU-01 y CU-02 son el núcleo propuesto y verificado en su alcance documentado; no hay confirmación de aprobación docente. CU-16 tiene endpoint/formulario, tres pruebas unitarias, build frontend y verificación HTTP manual contra PostgreSQL limpio. Los otros 17 CU son especificaciones o borradores, no módulos funcionales. Las propuestas CU-17 a CU-21 siguen pendientes de revisión del grupo y no tienen implementación. La prueba HTTP de CU-16 no está automatizada en CI.

**Avance por casos de uso:** 3 de 20 tienen implementación en esta rama (**15 %**): CU-01, CU-02 y CU-16. CU-01 y CU-02 son el núcleo propuesto; ambos pasan 31 comprobaciones HTTP repetibles. CU-16 pasa pruebas unitarias, compilación y una verificación HTTP manual contra una base limpia. Este porcentaje cuenta casos con código, no equivale al porcentaje de la rúbrica ni certifica aprobación docente. Los otros 17 no están implementados.

| CU | Estado | Código disponible | Issue | Faltantes principales |
|---|---|---|---|---|
| CU-01 Consultar progreso académico | Implementado en el núcleo propuesto | Lista de vínculos activos; publicaciones y avisos sin consulta; perfil y publicaciones por periodo; filtro de borradores; registro de consultas; historial de calificación corregida. | [#3](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/3), abierto; falta PR y cierre. | Los avisos se muestran en la lista, sin correo ni calendario; historial es de demostración. Autenticación y revisión institucional pendientes. |
| CU-02 Vincular encargado con estudiante | Implementado en el núcleo propuesto | Catálogo de Dirección; alta con referencia de respaldo; bloqueo de duplicados y de alta activa ante solicitud pendiente; estado pendiente sin acceso; suspensión/finalización con historial. | [#4](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/4), abierto; falta PR y cierre. | No existe flujo para aprobar solicitudes pendientes ni política institucional validada; solo Dirección crea/retira vínculos en el prototipo. |
| CU-03 a CU-10 | Especificados | Fichas y escenarios Gherkin. | [#5](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/5), [#6](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/6), [#7](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/7), [#8](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/8), [#9](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/9), [#10](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/10), [#11](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/11), [#12](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/12) (abiertos). | Publicación docente, avisos, mensajes, seguimiento, apelación, corrección y plazos. |
| CU-12 | Especificado | Ficha y escenarios Gherkin. | [#13](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/13), abierto. | Registro y atención de quejas. |
| CU-13 a CU-15 | Propuestas especificadas | Desglose de RF-19/RF-20, fichas y escenarios Gherkin. | [#17](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/17), [#18](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/18), [#19](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/19), abiertos con etiqueta `brecha`. | Revisión del grupo/profesor y toda la implementación. |
| CU-16 Registrar estudiante | Implementado y fusionado en la rama PC3 | Ficha, handler con 3 pruebas unitarias, endpoint, migración y formulario React; verificación HTTP manual. | [Issue #16](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/16), cerrado mediante el [PR #15](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/15). | Automatizar integración en CI y confirmar política institucional de identidad/duplicados antes de producción. |
| CU-17 a CU-19 Cursos y grupos | Borradores; no implementados | Fichas y cuatro escenarios por caso. | [#20](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/20), [#21](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/21), [#22](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/22), abiertos con etiqueta `brecha`. | Revisar modelo curso/materia/grupo/periodo, cardinalidad, matrículas y permisos. |
| CU-20 a CU-21 Docentes | Borradores; no implementados | Fichas y cuatro escenarios por caso. | [#23](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/23), [#24](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/24), abiertos con etiqueta `brecha`. | Confirmar identidad, provisión de cuentas, campos y permisos. |

Hay 20 CU documentados (CU-01 a CU-10 y CU-12 a CU-21); CU-11 se retiró previamente. El conteo no significa que estén implementados o aprobados. El modelo documental agrupa las capacidades en RF-26 a RF-32 y conserva RF-01 a RF-25 como refinamientos detallados; véase [trazabilidad](../02-requerimientos/trazabilidad-rf-cu.md).

## Trazabilidad de issues

Todos los 20 casos vigentes tienen issue enlazado al documento, con las etiquetas `caso-de-uso` y `brecha` cuando siguen abiertos, y responsable asignado a `cbastiancq-lab`. CU-16 está cerrado tras el merge del PR #15; los issues #17–#24 registran los casos aún no implementados. La trazabilidad de issues no significa que esos casos estén implementados.

## Componentes integrados

- `backend/`: API Java 21/Spring Boot, handlers, seguridad, persistencia JPA, migración Liquibase y datos ficticios.
- `frontend/`: interfaz React/TypeScript para CU-01, CU-02 y el alta local de CU-16.
- `docker-compose.yml`: servicio PostgreSQL local con volumen persistente.
- `scripts/verificar-nucleo-pc3.ps1`: comprobaciones API reales del alcance CU-01/CU-02.

## Cobertura y límites

- Las versiones de Java, frontend y base de datos arrancan con la guía de [ambiente de desarrollo](../06-configuracion/ambiente-de-desarrollo.md).
- La matriz de escenarios especificados frente a verificaciones ejecutadas está en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md).
- Los datos son ficticios; no se deben cargar datos escolares reales.
- La aceptación docente del núcleo y la revisión final de los integrantes son decisiones académicas que este repositorio no puede certificar.
