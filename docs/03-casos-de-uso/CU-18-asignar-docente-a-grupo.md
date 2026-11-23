# CU-18 — Asignar docente a grupo

| Campo | Detalle |
|---|---|
| **Descripción** | Dirección relaciona un perfil docente existente con un grupo activo para definir quién puede registrar actividad académica en ese grupo. |
| **Requerimientos generales** | [RF-27 Gestionar cursos y grupos](../02-requerimientos/funcionales/RF-27-gestion-de-cursos.md), [RF-28 Gestionar profesores](../02-requerimientos/funcionales/RF-28-gestion-de-profesores.md) |
| **Actores** | Dirección (provisional; confirmar quién realiza asignaciones). |
| **Precondiciones** | El grupo y el perfil docente existen y están activos. |
| **Postcondiciones** | La asignación queda registrada con grupo, docente, responsable y fecha. |
| **Estado** | Borrador de descomposición; sin implementación. No se define aún si un grupo admite varios docentes ni los periodos de vigencia. |

## Flujo básico

1. Dirección abre las asignaciones de un grupo activo.
2. El Portal muestra perfiles docentes disponibles.
3. Dirección selecciona el docente y confirma.
4. El Portal verifica que ambos registros estén activos.
5. El Portal guarda la asignación y confirma el resultado.

## Flujos alternos

- **A01 — Actor sin autorización.** El Portal deniega la operación sin revelar ni alterar asignaciones.
- **A02 — Grupo o docente inexistente/inactivo.** El Portal explica qué registro impide continuar y no asigna.
- **A03 — Asignación ya existente.** El Portal conserva la asignación existente y evita duplicarla.

## Otros campos

- **Restricciones:** RS-03; conservar historial de asignaciones para explicar publicaciones anteriores.
- **Dependencias:** CU-17 Registrar grupo académico y CU-20 Registrar docente.
- **Pruebas:** [CU-18-asignar-docente-a-grupo.feature](../04-pruebas/CU-18-asignar-docente-a-grupo.feature).
- **Issue:** pendiente de crear. <!-- issue:CU-18 -->
- **Decisiones abiertas:** cardinalidad docente/grupo, vigencia de una asignación, regla de sustitución y rol autorizado.

\n