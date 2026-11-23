# CU-17 — Registrar grupo académico

| Campo | Detalle |
|---|---|
| **Descripción** | Dirección registra un grupo académico para organizar la matrícula y las publicaciones del periodo. |
| **Requerimientos generales** | [RF-27 Gestionar cursos y grupos](../02-requerimientos/funcionales/RF-27-gestion-de-cursos.md) |
| **Actores** | Dirección (provisional; confirmar quién tiene permiso). |
| **Precondiciones** | Sesión válida y permiso para mantener la oferta académica. |
| **Postcondiciones** | El grupo queda registrado y disponible para asignar docentes y estudiantes. |
| **Estado** | Implementado provisionalmente y comprobado por HTTP en la rama PC3. La relación entre curso/materia, nivel, grupo y periodo debe revisarse con el equipo. |

## Flujo básico

1. Dirección solicita registrar un grupo académico.
2. El Portal solicita los datos mínimos acordados para identificar curso/materia, nivel, grupo y periodo.
3. Dirección completa los datos y confirma.
4. El Portal verifica que los datos requeridos correspondan a un periodo disponible.
5. El Portal registra el grupo y confirma el resultado.

## Flujos alternos

- **A01 — Actor sin autorización.** El Portal deniega la operación y no cambia la oferta académica.
- **A02 — Datos incompletos o inválidos.** El Portal indica qué dato falta o debe corregirse y no guarda el grupo.
- **A03 — Grupo equivalente ya registrado.** El Portal conserva un solo registro y muestra el existente. La clave exacta de equivalencia queda pendiente de validar con el modelo académico.

## Otros campos

- **Restricciones:** RS-03; no eliminar físicamente grupos que ya tengan actividad académica.
- **Dependencias:** ninguna para crear la ficha; CU-18 y CU-19 requieren un grupo activo.
- **Pruebas:** [CU-17-registrar-grupo-academico.feature](../04-pruebas/CU-17-registrar-grupo-academico.feature).
- **Issue:** [#20 — Registrar grupo académico](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/20). <!-- issue:CU-17 -->
- **Decisiones abiertas:** distinguir curso/materia de grupo; formato de periodo; campos obligatorios; clave para prevenir duplicados; rol autorizado.

\n
