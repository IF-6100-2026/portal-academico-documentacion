# Checklist PC3 de implementación y pruebas por caso de uso

Actualizado el 23 de noviembre de 2026 sobre la rama `pc3/core-consulta-vinculos`, tras fusionar el PR #15. La casilla indica si hay código funcional integrado; la columna de evidencia distingue verificaciones ejecutadas de escenarios Gherkin documentados. Gherkin no se cuenta como prueba ejecutada.

| CU | Implementado | Resultado y alcance de la verificación |
|---|---:|---|
| CU-01 Consultar progreso académico | [x] | 31 comprobaciones HTTP de CU-01/CU-02 aprobadas con PostgreSQL 17 limpio; revisión visual manual del flujo. Véase [resultados](../08-resultados-de-pruebas/resultados-pruebas.md). |
| CU-02 Vincular encargado con estudiante | [x] | 31 comprobaciones HTTP compartidas con CU-01; altas, permisos por rol/vínculo, pendientes e historial. |
| CU-03 Publicar evaluación asignada | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-04 Publicar calificación | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-05 Dar seguimiento a avisos no consultados | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-06 Comunicarse sobre un estudiante | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-07 Presentar apelación de una calificación | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-08 Supervisar el seguimiento académico | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-09 Corregir una calificación publicada | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-10 Configurar plazos y recordatorios | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-11 Bitácora de auditoría | N/A | Retirado antes de PC3; no forma parte de los 20 casos vigentes. |
| CU-12 Presentar queja por información no recibida | [ ] | Sin implementación. Escenarios Gherkin documentados; no ejecutados con Cucumber. |
| CU-13 Aportar información a una apelación | [ ] | Propuesta especificada, sin implementación; issue [#17](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/17). Gherkin no ejecutado; descomposición pendiente de revisión. |
| CU-14 Registrar resolución de apelación | [ ] | Propuesta especificada, sin implementación; issue [#18](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/18). Gherkin no ejecutado; descomposición pendiente de revisión. |
| CU-15 Consultar resolución de apelación | [ ] | Propuesta especificada, sin implementación; issue [#19](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/19). Gherkin no ejecutado; descomposición pendiente de revisión. |
| CU-16 Registrar estudiante | [x] | Tres pruebas unitarias del handler, build React y verificación HTTP manual: 401 sin sesión, 201 Dirección, 403 Docente, 400 grupo vacío. Integración no automatizada; el [issue #16](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/16) quedó cerrado al fusionar el [PR #15](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/15). |
| CU-17 Registrar grupo académico | [ ] | Borrador sin implementación; issue [#20](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/20). Actores y modelo académico requieren revisión. |
| CU-18 Asignar docente a grupo | [ ] | Borrador sin implementación; issue [#21](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/21). Actores y modelo académico requieren revisión. |
| CU-19 Inscribir estudiante en grupo | [ ] | Borrador sin implementación; issue [#22](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/22). Actores y modelo académico requieren revisión. |
| CU-20 Registrar docente | [ ] | Borrador sin implementación; issue [#23](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/23). Identidad, aprovisionamiento y permisos requieren revisión. |
| CU-21 Actualizar perfil docente | [ ] | Borrador sin implementación; issue [#24](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/24). Identidad, aprovisionamiento y permisos requieren revisión. |

## Resumen

- Código presente: **3 de 20 casos (15 %)**. Esto no significa que el resto esté terminado ni aprobado.
- Casos no implementados: CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21. Las justificaciones y supuestos están en [brechas](brechas-de-requerimientos.md); los borradores requieren revisión del grupo.
- Casos implementados: CU-01/CU-02 tienen smoke API; CU-16 tiene pruebas unitarias y verificación HTTP manual separada. No se deben presentar los escenarios Gherkin como pruebas ejecutadas.
- Los issues #17–#24 mantienen visible el trabajo pendiente de los casos no implementados; CU-16 fue fusionado y su issue quedó cerrado.

Ver el detalle de limitaciones y comandos reproducibles en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md).
