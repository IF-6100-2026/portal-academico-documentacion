# CU-19 — Inscribir estudiante en grupo

| Campo | Detalle |
|---|---|
| **Descripción** | Dirección incorpora a un estudiante registrado a un grupo académico para que el seguimiento y las evaluaciones correspondan al periodo correcto. |
| **Requerimientos generales** | [RF-26 Gestionar estudiantes](../02-requerimientos/funcionales/RF-26-gestion-de-estudiantes.md), [RF-27 Gestionar cursos y grupos](../02-requerimientos/funcionales/RF-27-gestion-de-cursos.md) |
| **Actores** | Dirección (provisional; confirmar quién matricula). |
| **Precondiciones** | El estudiante existe y el grupo está activo. |
| **Postcondiciones** | La inscripción queda registrada para el grupo, con responsable y fecha. |
| **Estado** | Borrador de descomposición; sin implementación. La relación estudiante/grupo y su vigencia requieren revisión. |

## Flujo básico

1. Dirección abre la matrícula de un grupo activo.
2. El Portal muestra estudiantes elegibles.
3. Dirección selecciona un estudiante registrado y confirma la inscripción.
4. El Portal verifica que estudiante y grupo estén activos.
5. El Portal registra la inscripción y confirma el resultado.

## Flujos alternos

- **A01 — Actor sin autorización.** El Portal deniega la operación sin modificar matrículas.
- **A02 — Estudiante o grupo inexistente/inactivo.** El Portal explica qué registro impide continuar y no inscribe.
- **A03 — Inscripción ya existente.** El Portal conserva una sola inscripción vigente y muestra el registro existente.

## Otros campos

- **Restricciones:** RS-03; una persona encargada no obtiene acceso por la matrícula; el vínculo autorizado sigue siendo CU-02.
- **Dependencias:** CU-16 Registrar estudiante y CU-17 Registrar grupo académico.
- **Pruebas:** [CU-19-inscribir-estudiante-en-grupo.feature](../04-pruebas/CU-19-inscribir-estudiante-en-grupo.feature).
- **Issue:** [#22 — Inscribir estudiante en grupo](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/22). <!-- issue:CU-19 -->
- **Decisiones abiertas:** una o varias matrículas activas por periodo, retiros/cambios de grupo, fecha de vigencia y rol autorizado.

\n
