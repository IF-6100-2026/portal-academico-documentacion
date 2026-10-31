# Trazabilidad RF → CU → prueba → issue

Cadena completa del proyecto: **proceso actual (BPMN) → problema → RF → CU → escenario de prueba → issue → código**. El tramo BPMN → problema → RF está en [trazabilidad-bpmn-rf.md](../01-inicio/bpmn/trazabilidad-bpmn-rf.md). Las equivalencias con los identificadores del PC1 están en [cambios-pc1-pc2.md](../cambios-pc1-pc2.md).

## Requerimientos funcionales

| RF | Nombre | Épica | Actor | Proceso actual | CU | Escenarios |
|---|---|---|---|---|---|---|
| [RF-01](funcionales/RF-01-vincular-encargado-con-estudiante.md) | Vincular a una persona encargada con un estudiante | Registro y consulta de notas y evaluaciones | Dirección | AC-02, AC-08 | [CU-02](../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) | `TP-CU02-FB`, `TP-CU02-A02`, `TP-CU02-A03`, `TP-CU02-A04`, `TP-CU02-A05`, `TP-CU02-A06` |
| [RF-02](funcionales/RF-02-consultar-perfil-del-estudiante.md) | Consultar el perfil del estudiante vinculado | Registro y consulta de notas y evaluaciones | Persona encargada legal | AC-02, AC-08 | [CU-01](../03-casos-de-uso/CU-01-consultar-progreso-academico.md) | `TP-CU01-FB`, `TP-CU01-A01`, `TP-CU01-A02` |
| [RF-03](funcionales/RF-03-consultar-evaluaciones-y-calificaciones.md) | Consultar evaluaciones y calificaciones publicadas | Registro y consulta de notas y evaluaciones | Persona encargada legal | AC-01, AC-06, AC-09 | [CU-01](../03-casos-de-uso/CU-01-consultar-progreso-academico.md) | `TP-CU01-FB`, `TP-CU01-A03`, `TP-CU01-A04` |
| [RF-04](funcionales/RF-04-registrar-evaluacion-asignada.md) | Registrar una evaluación asignada a un grupo | Registro y consulta de notas y evaluaciones | Docente | AC-01 | [CU-03](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) | `TP-CU03-FB`, `TP-CU03-A02`, `TP-CU03-A03` |
| [RF-05](funcionales/RF-05-confirmar-congruencia-con-el-cuaderno.md) | Confirmar la congruencia con el cuaderno antes de publicar | Registro y consulta de notas y evaluaciones | Docente | AC-01 | [CU-03](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md), [CU-04](../03-casos-de-uso/CU-04-publicar-calificacion.md), [CU-09](../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) | `TP-CU03-A04`, `TP-CU04-A04`, `TP-CU09-A04` |
| [RF-06](funcionales/RF-06-publicar-evaluacion-asignada.md) | Publicar la evaluación asignada | Registro y consulta de notas y evaluaciones | Docente | AC-01, AC-02 | [CU-03](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) | `TP-CU03-FB`, `TP-CU03-A05` |
| [RF-07](funcionales/RF-07-registrar-calificaciones-del-grupo.md) | Registrar las calificaciones del grupo | Registro y consulta de notas y evaluaciones | Docente | AC-01 | [CU-04](../03-casos-de-uso/CU-04-publicar-calificacion.md) | `TP-CU04-FB`, `TP-CU04-A03` |
| [RF-08](funcionales/RF-08-publicar-calificacion.md) | Publicar la calificación | Registro y consulta de notas y evaluaciones | Docente | AC-06, AC-07, AC-08 | [CU-04](../03-casos-de-uso/CU-04-publicar-calificacion.md) | `TP-CU04-FB`, `TP-CU04-A02`, `TP-CU04-A05` |
| [RF-09](funcionales/RF-09-corregir-calificacion-con-historial.md) | Corregir una calificación publicada con historial | Registro y consulta de notas y evaluaciones | Docente | SP-01 | [CU-09](../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md), [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | `TP-CU09-FB`, `TP-CU09-A02`, `TP-CU09-A03`, `TP-CU07-A06` |
| [RF-10](funcionales/RF-10-avisar-publicacion-de-evaluacion.md) | Avisar la publicación de una evaluación | Comunicación entre la escuela y la persona encargada | Persona encargada legal | AC-02 | [CU-03](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) | `TP-CU03-FB`, `TP-CU03-A06` |
| [RF-11](funcionales/RF-11-avisar-publicacion-de-calificacion.md) | Avisar la publicación de una calificación o su corrección | Comunicación entre la escuela y la persona encargada | Persona encargada legal | AC-08 | [CU-04](../03-casos-de-uso/CU-04-publicar-calificacion.md), [CU-09](../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) | `TP-CU04-FB`, `TP-CU04-A06`, `TP-CU09-FB`, `TP-CU09-A05` |
| [RF-12](funcionales/RF-12-registrar-acuse-de-consulta.md) | Registrar el acuse de consulta de un aviso | Comunicación entre la escuela y la persona encargada | Persona encargada legal | AC-03 | [CU-05](../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | `TP-CU05-FB`, `TP-CU05-A01`, `TP-CU05-A06` |
| [RF-13](funcionales/RF-13-recordar-avisos-no-consultados.md) | Recordar los avisos no consultados | Comunicación entre la escuela y la persona encargada | Persona encargada legal (el recordatorio lo dispara el servicio programado) | AC-03, AC-08 | [CU-05](../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | `TP-CU05-FB`, `TP-CU05-A04`, `TP-CU05-A05`, `TP-CU05-A07` |
| [RF-14](funcionales/RF-14-intercambiar-mensajes.md) | Intercambiar mensajes sobre un estudiante | Comunicación entre la escuela y la persona encargada | Persona encargada legal y docente | AC-04 | [CU-06](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | `TP-CU06-FB`, `TP-CU06-A02`, `TP-CU06-A03`, `TP-CU06-A04` |
| [RF-15](funcionales/RF-15-presentar-queja-por-informacion-no-recibida.md) | Presentar una queja por información no recibida | Comunicación entre la escuela y la persona encargada | Persona encargada legal | AC-10 | [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md), [CU-06](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | `TP-CU12-FB`, `TP-CU12-A02`, `TP-CU12-A04`, `TP-CU06-A06` |
| [RF-16](funcionales/RF-16-atender-queja-por-informacion-no-recibida.md) | Atender una queja por información no recibida | Comunicación entre la escuela y la persona encargada | Dirección | AC-11 | [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | `TP-CU12-FB`, `TP-CU12-A03` |
| [RF-17](funcionales/RF-17-presentar-apelacion.md) | Presentar una apelación sobre una calificación | Seguimiento de apelaciones y pendientes | Persona encargada legal | AC-04 | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md), [CU-06](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | `TP-CU07-FB`, `TP-CU07-A02`, `TP-CU07-A03`, `TP-CU07-A04`, `TP-CU06-A05` |
| [RF-18](funcionales/RF-18-asignar-apelacion-al-comite.md) | Asignar la apelación al Comité de Evaluación | Seguimiento de apelaciones y pendientes | Comité de Evaluación | SP-01 | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | `TP-CU07-FB` |
| [RF-19](funcionales/RF-19-registrar-aportes-de-docente-y-direccion.md) | Registrar los aportes de la docente y la Dirección | Seguimiento de apelaciones y pendientes | Docente y Dirección | SP-01 | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | `TP-CU07-FB`, `TP-CU07-A05` |
| [RF-20](funcionales/RF-20-registrar-resolucion-de-apelacion.md) | Registrar la resolución de la apelación | Seguimiento de apelaciones y pendientes | Comité de Evaluación | SP-01, AC-05 | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | `TP-CU07-FB`, `TP-CU07-A06`, `TP-CU07-A08` |
| [RF-21](funcionales/RF-21-controlar-estado-y-plazo-de-apelacion.md) | Controlar el estado y el plazo de la apelación | Seguimiento de apelaciones y pendientes | Dirección | SP-01 | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md), [CU-08](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) | `TP-CU07-A07`, `TP-CU08-FB` |
| [RF-22](funcionales/RF-22-configurar-plazos-y-recordatorios.md) | Configurar plazos y recordatorios | Seguimiento de apelaciones y pendientes | Dirección | SP-01 | [CU-10](../03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md) | `TP-CU10-FB`, `TP-CU10-A02`, `TP-CU10-A03` |
| [RF-23](funcionales/RF-23-alertar-avisos-sin-consulta.md) | Alertar a la Dirección los avisos sin consulta | Seguimiento de apelaciones y pendientes | Dirección | AC-08, AC-11 | [CU-05](../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md), [CU-08](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) | `TP-CU05-A02`, `TP-CU05-A03`, `TP-CU08-FB` |
| [RF-24](funcionales/RF-24-consultar-estado-del-seguimiento.md) | Consultar el estado del seguimiento | Seguimiento de apelaciones y pendientes | Dirección | AC-11, SP-01 | [CU-08](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) | `TP-CU08-FB`, `TP-CU08-A02`, `TP-CU08-A03` |
| [RF-25](funcionales/RF-25-consultar-registro-de-consultas.md) | Consultar el registro de consultas | Seguimiento de apelaciones y pendientes | Dirección | AC-11 | [CU-08](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md), [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | `TP-CU08-FB`, `TP-CU12-FB` |

## Requerimientos suplementarios

| RS | Nombre | CU afectados |
|---|---|---|
| [RS-01](suplementarios/RS-01-usabilidad-para-encargados.md) | Usabilidad para personas encargadas con poca experiencia digital | CU-01, CU-05, CU-07, CU-12 |
| [RS-02](suplementarios/RS-02-trazabilidad-de-consultas.md) | Trazabilidad: saber quién leyó qué y cuándo | CU-01, CU-03, CU-04, CU-05, CU-06, CU-08, CU-12 |
| [RS-03](suplementarios/RS-03-seguridad-por-actor-y-vinculo.md) | Seguridad por actor y vínculo | Todos |

## Cobertura por caso de uso

| CU | RF | RS | Prueba | Issue |
|---|---|---|---|---|
| [CU-01 — Consultar progreso académico](../03-casos-de-uso/CU-01-consultar-progreso-academico.md) | RF-02, RF-03 | RS-01, RS-02, RS-03 | [CU-01-consultar-progreso-academico.feature](../04-pruebas/CU-01-consultar-progreso-academico.feature) | _por crear_ <!-- issue:CU-01 --> |
| [CU-02 — Vincular encargado con estudiante](../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) | RF-01 | RS-03 | [CU-02-vincular-encargado-con-estudiante.feature](../04-pruebas/CU-02-vincular-encargado-con-estudiante.feature) | _por crear_ <!-- issue:CU-02 --> |
| [CU-03 — Publicar evaluación asignada](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) | RF-04, RF-05, RF-06, RF-10 | RS-02, RS-03 | [CU-03-publicar-evaluacion-asignada.feature](../04-pruebas/CU-03-publicar-evaluacion-asignada.feature) | _por crear_ <!-- issue:CU-03 --> |
| [CU-04 — Publicar calificación](../03-casos-de-uso/CU-04-publicar-calificacion.md) | RF-05, RF-07, RF-08, RF-11 | RS-02, RS-03 | [CU-04-publicar-calificacion.feature](../04-pruebas/CU-04-publicar-calificacion.feature) | _por crear_ <!-- issue:CU-04 --> |
| [CU-05 — Dar seguimiento a avisos no consultados](../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | RF-12, RF-13, RF-23 | RS-01, RS-02, RS-03 | [CU-05-dar-seguimiento-a-avisos-no-consultados.feature](../04-pruebas/CU-05-dar-seguimiento-a-avisos-no-consultados.feature) | _por crear_ <!-- issue:CU-05 --> |
| [CU-06 — Comunicarse sobre un estudiante](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | RF-14, RF-15, RF-17 | RS-02, RS-03 | [CU-06-comunicarse-sobre-un-estudiante.feature](../04-pruebas/CU-06-comunicarse-sobre-un-estudiante.feature) | _por crear_ <!-- issue:CU-06 --> |
| [CU-07 — Gestionar apelación de una calificación](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | RF-09, RF-17, RF-18, RF-19, RF-20, RF-21 | RS-01, RS-03 | [CU-07-gestionar-apelacion-de-calificacion.feature](../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature) | _por crear_ <!-- issue:CU-07 --> |
| [CU-08 — Supervisar el seguimiento académico](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) | RF-21, RF-23, RF-24, RF-25 | RS-02, RS-03 | [CU-08-supervisar-seguimiento-academico.feature](../04-pruebas/CU-08-supervisar-seguimiento-academico.feature) | _por crear_ <!-- issue:CU-08 --> |
| [CU-09 — Corregir una calificación publicada](../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) | RF-05, RF-09, RF-11 | RS-03 | [CU-09-corregir-calificacion-publicada.feature](../04-pruebas/CU-09-corregir-calificacion-publicada.feature) | _por crear_ <!-- issue:CU-09 --> |
| [CU-10 — Configurar plazos y recordatorios](../03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md) | RF-22 | RS-03 | [CU-10-configurar-plazos-y-recordatorios.feature](../04-pruebas/CU-10-configurar-plazos-y-recordatorios.feature) | _por crear_ <!-- issue:CU-10 --> |
| [CU-12 — Presentar queja por información no recibida](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | RF-15, RF-16, RF-25 | RS-01, RS-02, RS-03 | [CU-12-presentar-queja-por-informacion-no-recibida.feature](../04-pruebas/CU-12-presentar-queja-por-informacion-no-recibida.feature) | _por crear_ <!-- issue:CU-12 --> |

## Controles

- Los 25 RF tienen actor, evidencia, actividad del BPMN, al menos un CU y al menos un escenario.
- Los 11 CU tienen al menos un RF y un archivo `.feature`. Los enlaces de issues se completan al publicarlos; «por crear» es un pendiente real.
- Los escenarios `A01` de cada CU que prueban accesos no autorizados se trazan a RS-03.

## Cobertura transversal de acceso (RS-03)

| CU | Escenario | Condición |
|---|---|---|
| CU-01 | `TP-CU01-A05` | Rechazar una sesión inválida antes de revelar vínculos o publicaciones. |
| CU-05 | `TP-CU05-A08` | Revalidar el vínculo al abrir, aunque el recordatorio se emitió con autorización. |

Estos escenarios completan RS-03 sin añadir nuevos RF ni cambiar las épicas. Son especificaciones pendientes de automatizar. El catálogo integrado contiene 71 escenarios.
