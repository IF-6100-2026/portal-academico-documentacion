# Checklist PC3 de implementación y pruebas por caso de uso

Actualizado el 23 de noviembre de 2026 desde `codex/pc3-sdd-registro-estudiantes`. La casilla indica si hay código funcional en esta rama; la columna de evidencia distingue verificaciones ejecutadas de escenarios Gherkin documentados. Gherkin no se cuenta como prueba ejecutada.

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
| CU-13 Aportar información a una apelación | [ ] | Propuesta especificada; sin implementación. Gherkin pendiente de ejecutar y descomposición pendiente de revisión. |
| CU-14 Registrar resolución de apelación | [ ] | Propuesta especificada; sin implementación. Gherkin pendiente de ejecutar y descomposición pendiente de revisión. |
| CU-15 Consultar resolución de apelación | [ ] | Propuesta especificada; sin implementación. Gherkin pendiente de ejecutar y descomposición pendiente de revisión. |
| CU-16 Registrar estudiante | [x] | Tres pruebas unitarias del handler, build React y verificación HTTP manual: 401 sin sesión, 201 Dirección, 403 Docente, 400 grupo vacío. La integración no está automatizada; revisar los cinco criterios del [issue #16](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/16). Código en el [PR #15](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/15), pendiente de revisión/merge. |
| CU-17 Registrar grupo académico | [ ] | Borrador de caso de uso; sin implementación. Actores y modelo académico requieren revisión. |
| CU-18 Asignar docente a grupo | [ ] | Borrador de caso de uso; sin implementación. Actores y modelo académico requieren revisión. |
| CU-19 Inscribir estudiante en grupo | [ ] | Borrador de caso de uso; sin implementación. Actores y modelo académico requieren revisión. |
| CU-20 Registrar docente | [ ] | Borrador de caso de uso; sin implementación. Identidad, aprovisionamiento y permisos requieren revisión. |
| CU-21 Actualizar perfil docente | [ ] | Borrador de caso de uso; sin implementación. Identidad, aprovisionamiento y permisos requieren revisión. |

## Resumen

- Código presente: **3 de 20 casos (15 %)**. Esto no significa que el resto esté terminado ni aprobado.
- Casos no implementados: CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21. Las justificaciones y supuestos están en [brechas](brechas-de-requerimientos.md); los borradores requieren revisión del grupo.
- Casos implementados: CU-01/CU-02 tienen smoke API; CU-16 tiene pruebas unitarias y verificación HTTP manual separada. No se deben presentar los escenarios Gherkin como pruebas ejecutadas.
- Para cerrar CU-16 falta revisión humana del PR y comprobar/cerrar sus criterios de aceptación con evidencia.

Ver el detalle de limitaciones y comandos reproducibles en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md).
