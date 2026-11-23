# Estado de implementación

## Resumen

La rama contiene un núcleo local integrado, propuesto para PC3: **CU-01 y CU-02 están implementados para la demostración y verificados en su alcance documentado aquí**. La propuesta se ejecutó porque el equipo no pudo confirmar qué selección se aprobó con el profesor; no se presenta como aprobación docente. Los demás 12 CU son especificaciones, no módulos funcionales. Las limitaciones de política, seguridad institucional y escenarios pendientes siguen registradas en [brechas](brechas-de-requerimientos.md).

| CU | Estado | Código disponible | Faltantes principales |
|---|---|---|---|
| CU-01 Consultar progreso académico | Implementado en el núcleo propuesto | Lista de vínculos activos; últimas publicaciones y avisos sin consulta; perfil y publicaciones por periodo; filtro de borradores; validación por solicitud; registro de consultas; historial visible de una calificación corregida. | Los avisos se muestran en la lista, sin correo ni calendario; historial es de demostración. Autenticación y revisión institucional pendientes. |
| CU-02 Vincular encargado con estudiante | Implementado en el núcleo propuesto | Catálogo de Dirección; alta con referencia de respaldo; bloqueo de duplicados activos y de alta activa ante solicitud pendiente; estado pendiente sin acceso; suspensión/finalización con motivo, autor, fecha e historial. | No existe un flujo para aprobar una solicitud pendiente ni una política institucional validada; solo Dirección puede crear/retirar vínculos en el prototipo. |
| CU-03 a CU-10 | Especificado | Fichas de CU y escenarios Gherkin. | Publicación docente, avisos, mensajes, seguimiento, apelación, corrección y plazos. |
| CU-12 | Especificado | Ficha y escenarios Gherkin. | Registro y atención de quejas. |
| CU-13 a CU-15 | Propuesta especificada | Desglose de RF-19/RF-20, fichas y escenarios Gherkin. | Validación del grupo/profesor, issue de seguimiento y toda la implementación. |

Hay 14 CU documentados (CU-01 a CU-10, CU-12 a CU-15); CU-11 se retiró previamente. El conteo no significa que los 14 estén implementados. Véase [trazabilidad](../02-requerimientos/trazabilidad-rf-cu.md).

## Componentes integrados

- `backend/`: API Java 21/Spring Boot, handlers, seguridad, persistencia JPA, migración Liquibase y datos ficticios.
- `frontend/`: interfaz React/TypeScript para CU-01 y CU-02.
- `docker-compose.yml`: servicio PostgreSQL local con volumen persistente.
- `scripts/verificar-nucleo-pc3.ps1`: comprobaciones API reales del alcance CU-01/CU-02.

## Cobertura y límites

- Las versiones de Java, frontend y base de datos arrancan con la guía de [ambiente de desarrollo](../06-configuracion/ambiente-de-desarrollo.md).
- La matriz de escenarios especificados frente a verificaciones ejecutadas está en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md).
- Los datos son ficticios; no se deben cargar datos escolares reales.
- La aceptación docente del núcleo y la revisión final de los integrantes son decisiones académicas que este repositorio no puede certificar.
