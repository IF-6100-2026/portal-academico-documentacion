# CU-14-RegistrarResolucionDeApelacion («Registrar resolución de apelación»)

| Campo | Detalle |
|---|---|
| **Descripción** | El Comité registra el resultado y fundamento de la resolución de una apelación y el Portal notifica a la persona encargada (RF-20, E-11). |
| **Estereotipo y paquete** | «Épica 3» Seguimiento de apelaciones y pendientes |
| **Precondiciones** | La apelación está asignada al Comité, se encuentra en revisión y cuenta con la información necesaria. |
| **Postcondiciones** | La apelación queda resuelta con resultado, fundamento, fecha e integrantes emisores. El Portal genera la notificación a la persona encargada. La consulta que la cierra se realiza en [CU-15](CU-15-consultar-resolucion-de-apelacion.md). |
| **Actores** | A-03 Comité de Evaluación (principal); A-01 Persona encargada legal (receptora de la notificación). |
| **Relaciones del caso de uso** | Sigue el análisis y aportes de [CU-13](CU-13-aportar-informacion-a-apelacion.md). Si se ordena cambiar la calificación, el Comité no la cambia: la docente usa [CU-09](CU-09-corregir-calificacion-publicada.md) y registra la referencia de la apelación. |

## Flujo básico

1. El Comité abre el expediente en revisión.
2. El Portal verifica que la sesión y el rol permitan resolver ese expediente (RS-03; A01).
3. El Comité registra el resultado, el fundamento y los integrantes que emiten la decisión (A02).
4. El Portal registra la fecha y cambia el estado a «resuelta».
5. El Portal genera un aviso para la persona encargada que presentó la apelación.

## Flujos alternos

- **A01 — Actor sin permisos o expediente ajeno (paso 2).** El Portal deniega la operación y no revela información del caso.
- **A02 — Resolución incompleta (paso 3).** El Portal no la registra e indica qué campos requeridos faltan.
- **A03 — Resolución ordena corregir la calificación (paso 3).** El Portal conserva la calificación vigente y agrega la referencia de la apelación al expediente. La docente ejecuta CU-09; la apelación queda resuelta según RF-20, sin cambiar una nota automáticamente.

## Otros campos

- **Restricciones:** [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md). Los nombres de estado siguen siendo propuesta S-03 hasta su validación.
- **Métricas:** complejidad media (5 pasos y 3 flujos alternos).
- **Prioridad:** alta (RF-20 tiene prioridad «Debe»).
- **Estado:** nuevo desglose de RF-20 para atender la observación del PC2; estructura propuesta pendiente de revisión del grupo/docente; no implementado.
- **Requerimientos:** [RF-20](../02-requerimientos/funcionales/RF-20-registrar-resolucion-de-apelacion.md).
- **Pruebas:** [CU-14-registrar-resolucion-de-apelacion.feature](../04-pruebas/CU-14-registrar-resolucion-de-apelacion.feature).
- **Issue:** pendiente de crear en GitHub.
