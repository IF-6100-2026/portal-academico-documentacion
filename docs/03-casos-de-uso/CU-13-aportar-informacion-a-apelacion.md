# CU-13-AportarInformacionAApelacion («Aportar información a una apelación»)

| Campo | Detalle |
|---|---|
| **Descripción** | La docente agrega la prueba y el criterio de calificación al expediente; la Dirección incorpora sus observaciones; el Comité puede solicitar información adicional (RF-19, E-11). |
| **Estereotipo y paquete** | «Épica 3» Seguimiento de apelaciones y pendientes |
| **Precondiciones** | La apelación está asignada al Comité y abierta para recibir aportes. La identidad y el rol del participante corresponden al caso (RS-03). |
| **Postcondiciones** | Cada aporte conserva autor, fecha y contenido; se agrega al expediente sin sustituir aportes anteriores ni cambiar por sí solo la calificación. |
| **Actores** | A-02 Docente, A-04 Dirección y A-03 Comité de Evaluación. |
| **Relaciones del caso de uso** | Continúa una apelación presentada en [CU-07](CU-07-gestionar-apelacion-de-calificacion.md). El Comité puede solicitar información y el participante indicado responde en este caso. La resolución se registra en [CU-14](CU-14-registrar-resolucion-de-apelacion.md). |

## Flujo básico

1. Un participante autorizado abre una apelación asignada.
2. El Portal valida rol y acceso al expediente (RS-03; A01).
3. La docente adjunta la prueba y registra el criterio utilizado, o la Dirección agrega sus observaciones.
4. El Portal valida que el contenido requerido esté presente (A02).
5. El Portal agrega el aporte con autor y fecha, manteniendo los anteriores.
6. El Comité revisa el expediente actualizado.

## Flujos alternos

- **A01 — Participante sin acceso al expediente (paso 2).** El Portal deniega la operación sin devolver datos y registra el intento.
- **A02 — Aporte vacío (paso 4).** El Portal no agrega una entrada vacía e indica qué información falta.
- **A03 — Solicitud de información adicional (paso 6).** El Comité identifica qué información y a quién solicita. El Portal registra la solicitud y deja el caso pendiente hasta que el destinatario responda; al agregarse la respuesta, el caso vuelve al estado de revisión propuesto en S-03.

## Otros campos

- **Restricciones:** [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md). El estado «pendiente de información» es una propuesta S-03 y requiere validación institucional.
- **Métricas:** complejidad media (6 pasos y 3 flujos alternos).
- **Prioridad:** alta (RF-19 tiene prioridad «Debe»).
- **Estado:** nuevo desglose de RF-19 para atender la observación del PC2; aporte básico implementado y comprobado por HTTP en la rama PC3. El desglose requiere revisión del grupo/docente y los alternos Gherkin no se ejecutaron.
- **Requerimiento funcional:** [RF-32 — Gestionar apelaciones](../02-requerimientos/funcionales/RF-32-gestionar-apelaciones-y-pendientes.md)
- **Requisitos detallados que cumple:** RF-19, RF-21
- **Pruebas:** [CU-13-aportar-informacion-a-apelacion.feature](../04-pruebas/CU-13-aportar-informacion-a-apelacion.feature).
- **Issue:** [#17 — Aportar información a una apelación](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/17).
