# CU-21 — Actualizar perfil docente

| Campo | Detalle |
|---|---|
| **Descripción** | Dirección actualiza los datos visibles o la disponibilidad de un perfil docente, conservando el historial académico relacionado. |
| **Requerimientos generales** | [RF-28 Gestionar profesores](../02-requerimientos/funcionales/RF-28-gestion-de-profesores.md) |
| **Actores** | Dirección (provisional; confirmar quién mantiene el catálogo). |
| **Precondiciones** | Sesión válida y perfil docente existente. |
| **Postcondiciones** | Los cambios quedan registrados; si el perfil se inactiva, no se elimina el historial ni sus publicaciones. |
| **Estado** | Actualización e inactivación implementadas y comprobadas por HTTP en la rama PC3; conserva el historial y no elimina físicamente la cuenta ni su contenido académico. |

## Flujo básico

1. Dirección busca y selecciona un perfil docente.
2. El Portal muestra los datos que pueden modificarse.
3. Dirección cambia el nombre visible u otro dato permitido y confirma.
4. El Portal valida los cambios y conserva responsable y fecha.
5. El Portal confirma la actualización.

## Flujos alternos

- **A01 — Actor sin autorización.** El Portal deniega la operación sin revelar ni cambiar el perfil.
- **A02 — Perfil inexistente.** El Portal informa que no encontró el perfil y no guarda cambios.
- **A03 — Inactivar perfil.** Dirección confirma la inactivación; el Portal impide nuevas asignaciones y conserva las publicaciones e historial previos.

## Otros campos

- **Restricciones:** RS-03; usar inactivación lógica, sin borrar el historial académico.
- **Dependencias:** CU-20 Registrar docente; los grupos pueden requerir reasignación mediante CU-18.
- **Pruebas:** [CU-21-actualizar-perfil-docente.feature](../04-pruebas/CU-21-actualizar-perfil-docente.feature).
- **Issue:** [#24 — Actualizar perfil docente](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/24). <!-- issue:CU-21 -->
- **Decisiones abiertas:** campos editables, quién puede inactivar, confirmación de reasignaciones y auditoría requerida.

\n
