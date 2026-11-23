# CU-15-ConsultarResolucionDeApelacion («Consultar resolución de apelación»)

| Campo | Detalle |
|---|---|
| **Descripción** | La persona encargada consulta la resolución de su apelación; el Portal registra el acuse y cierra el caso (RF-20). |
| **Estereotipo y paquete** | «Épica 3» Seguimiento de apelaciones y pendientes |
| **Precondiciones** | La resolución está registrada y la persona encargada es quien presentó la apelación. |
| **Postcondiciones** | La persona encargada ve el resultado y el fundamento del Comité; queda registrado quién y cuándo lo consultó y la apelación pasa de «resuelta» a «cerrada». |
| **Actores** | A-01 Persona encargada legal (principal). |
| **Relaciones del caso de uso** | El Comité registra la resolución en [CU-14](CU-14-registrar-resolucion-de-apelacion.md). El aviso pendiente sigue el tratamiento de [CU-05](CU-05-dar-seguimiento-a-avisos-no-consultados.md). |

## Flujo básico

1. La persona encargada abre el aviso de resolución.
2. El Portal valida sesión, identidad del solicitante y vínculo con el estudiante (RS-03; A01).
3. El Portal muestra el resultado, fundamento, fecha e integrantes que emitieron la decisión.
4. La persona encargada confirma que consultó la resolución.
5. El Portal registra el acuse y cambia el estado de «resuelta» a «cerrada».

## Flujos alternos

- **A01 — Persona ajena al expediente (paso 2).** El Portal deniega la consulta sin revelar la resolución o confirmar que la apelación existe.
- **A02 — Resolución aún no disponible (paso 3).** El Portal informa que la decisión todavía no se ha registrado; no cambia el estado de la apelación.

## Otros campos

- **Restricciones:** [RS-01](../02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md), [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md). El estado «cerrada» se mantiene como propuesta S-03 hasta su validación.
- **Métricas:** complejidad baja (5 pasos y 2 flujos alternos).
- **Prioridad:** alta (RF-20 tiene prioridad «Debe»).
- **Estado:** nuevo desglose de RF-20 para separar la acción de la persona encargada; estructura propuesta pendiente de revisión del grupo/docente; no implementado.
- **Requerimientos generales:** [RF-32](../02-requerimientos/funcionales/RF-32-gestionar-apelaciones-y-pendientes.md)
- **Refinamientos detallados conservados:** RF-20
- **Pruebas:** [CU-15-consultar-resolucion-de-apelacion.feature](../04-pruebas/CU-15-consultar-resolucion-de-apelacion.feature).
- **Issue:** [#19 — Consultar resolución de apelación](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/19).
