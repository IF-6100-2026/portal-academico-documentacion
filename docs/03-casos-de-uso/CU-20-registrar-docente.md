# CU-20 — Registrar docente

| Campo | Detalle |
|---|---|
| **Descripción** | Dirección registra un perfil docente para que pueda ser asignado a grupos académicos. |
| **Requerimientos generales** | [RF-28 Gestionar profesores](../02-requerimientos/funcionales/RF-28-gestion-de-profesores.md) |
| **Actores** | Dirección (provisional; confirmar quién mantiene el catálogo). |
| **Precondiciones** | Sesión válida y permiso para mantener el catálogo docente. La cuenta institucional se provisiona por el mecanismo de identidad del centro. |
| **Postcondiciones** | El perfil docente queda activo en el catálogo y disponible para asignaciones. |
| **Estado** | Borrador de descomposición; sin implementación. Se propone reutilizar el usuario institucional existente y no crear credenciales desde este caso. |

## Flujo básico

1. Dirección solicita registrar un perfil docente.
2. El Portal solicita el identificador de cuenta y el nombre visible de la persona docente.
3. Dirección completa los datos y confirma.
4. El Portal verifica que la cuenta exista y que no tenga ya un perfil docente activo.
5. El Portal registra o habilita el perfil y confirma el resultado.

## Flujos alternos

- **A01 — Actor sin autorización.** El Portal deniega la operación y no modifica el catálogo.
- **A02 — Cuenta institucional inexistente o datos incompletos.** El Portal señala qué debe corregirse y no registra el perfil.
- **A03 — Perfil docente ya activo.** El Portal conserva el perfil existente y evita duplicarlo.

## Otros campos

- **Restricciones:** RS-03; no guardar contraseñas en el perfil académico.
- **Dependencias:** la provisión de la identidad institucional ocurre fuera de este CU; CU-18 usa el perfil activo.
- **Pruebas:** [CU-20-registrar-docente.feature](../04-pruebas/CU-20-registrar-docente.feature).
- **Issue:** pendiente de crear. <!-- issue:CU-20 -->
- **Decisiones abiertas:** datos institucionales requeridos, mecanismo de provisión de cuentas, rol autorizado y relación entre perfil docente y usuario.

\n