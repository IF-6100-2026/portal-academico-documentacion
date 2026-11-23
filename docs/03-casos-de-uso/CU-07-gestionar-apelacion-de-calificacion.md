# CU-07-PresentarApelacion («Presentar apelación de calificación»)

| Campo | Detalle |
|---|---|
| **Descripción** | La persona encargada presenta una apelación sobre una calificación publicada. El Portal registra la solicitud y la entrega al Comité con el expediente inicial (E-07, E-11; P-04). |
| **Estereotipo y paquete** | «Épica 3» Seguimiento de apelaciones y pendientes |
| **Precondiciones** | 1. La persona encargada tiene vínculo activo con el estudiante.<br>2. La calificación está publicada.<br>3. La Dirección configuró el plazo aplicable; no se inventa un plazo predeterminado (E-08, CU-10). |
| **Postcondiciones** | La apelación queda registrada con su número, solicitante, fecha, estado inicial y plazo configurado; queda asignada al Comité y notificada según RF-18.<br>**Garantía mínima:** la solicitud incompleta o no autorizada no crea una apelación ni revela información ajena. |
| **Actores** | A-01 Persona encargada legal (principal); A-03 Comité de Evaluación, A-02 Docente y A-04 Dirección (receptores del expediente inicial). |
| **Relaciones del caso de uso** | Se inicia desde una calificación publicada consultada en [CU-01](CU-01-consultar-progreso-academico.md) o desde la conversación de [CU-06](CU-06-comunicarse-sobre-un-estudiante.md). La documentación y resolución se gestionan en [CU-13](CU-13-aportar-informacion-a-apelacion.md) y [CU-14](CU-14-registrar-resolucion-de-apelacion.md); la persona encargada la consulta en [CU-15](CU-15-consultar-resolucion-de-apelacion.md). |

## Flujo básico

1. La persona encargada selecciona apelar una calificación publicada.
2. El Portal comprueba la sesión, el rol, el vínculo activo, el estado publicado y el plazo configurado (RS-03; A01, A02).
3. La persona encargada indica los puntos apelados, el motivo y adjunta el respaldo.
4. El Portal valida que la información esté completa y que no exista una apelación abierta equivalente (A03, A04).
5. El Portal registra la apelación con número, fecha, persona solicitante, calificación apelada y plazo de resolución.
6. El Portal asigna la apelación al Comité de Evaluación y comunica la apertura a la docente de la evaluación y a la Dirección, según RF-18.

## Flujos alternos

- **A01 — Sin vínculo activo (paso 2).** El Portal deniega la solicitud sin revelar si el estudiante o la calificación existen; registra el intento y termina.
- **A02 — Calificación no publicada o plazo vencido (paso 2).** El Portal no registra la apelación e informa la condición aplicable y el plazo configurado.
- **A03 — Información incompleta (paso 4).** El Portal no crea la apelación e indica qué falta; la persona encargada puede completarla mientras el plazo siga vigente.
- **A04 — Apelación abierta equivalente (paso 4).** El Portal conserva la existente, informa su número y estado a la persona autorizada y no crea un duplicado.

## Otros campos

- **Restricciones:** [RS-01](../02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).
- **Métricas:** complejidad media (6 pasos y 4 flujos alternos).
- **Prioridad:** alta (RF-17 y RF-18 tienen prioridad «Debe»).
- **Estado:** separado del flujo de aportes, resolución y consulta para atender la observación del PC2; propuesta de estructura pendiente de revisión del grupo/docente.
- **Requerimientos generales:** [RF-32](../02-requerimientos/funcionales/RF-32-gestionar-apelaciones-y-pendientes.md)
- **Refinamientos detallados conservados:** RF-17, RF-18
- **Pruebas:** [CU-07-gestionar-apelacion-de-calificacion.feature](../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature).
- **Issue:** [Issue](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/9) <!-- issue:CU-07 -->
- **Equivale en el PC1 a:** UC-07 («Gestionar discrepancia académica»), ahora delimitado a la presentación y asignación inicial.
