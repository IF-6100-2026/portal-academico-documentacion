# 02. Proceso actual y base de trazabilidad

**Proyecto:** Portal Académico  
**Organización de referencia:** Centro Educativo María Montessori (Escuela Bilingüe María Montessori)  
**Fase:** PC1 — Inicio (Inception)  
**Versión de trabajo:** 2026-09-15  
**Estado:** listo para revisión con el profesor; contiene puntos explícitos por validar con la Dirección

## Propósito y regla de modelado

Este documento describe el proceso **AS-IS**, es decir, cómo se comunica actualmente una calificación mediante una hoja física. El BPMN asociado no contiene el Portal Académico, notificaciones automáticas, perfiles, reportes ni otras capacidades futuras.

La separación es deliberada:

- El BPMN representa el problema actual.
- Los problemas y observaciones justifican los requisitos funcionales.
- Los casos de uso representan una propuesta TO-BE conceptual y se muestran en un diagrama separado.
- Ninguna hipótesis aparece presentada como un hecho confirmado.

Archivos relacionados:

- [BPMN 2.0 del proceso actual](diagramas/02-proceso-actual.bpmn)
- [Vista SVG del proceso actual](diagramas/02-proceso-actual.svg)
- [Vista PNG del proceso actual](diagramas/02-proceso-actual.png)
- [Fuente PlantUML del diagrama de casos de uso](diagramas/05-casos-de-uso.puml)
- [Vista SVG del diagrama de casos de uso](diagramas/05-casos-de-uso.svg)
- [Matriz integral de trazabilidad](08-matriz-trazabilidad.md)

![BPMN AS-IS del proceso actual de comunicación de calificaciones mediante hoja física](diagramas/02-proceso-actual.svg)

## Base de evidencia

| ID | Dato disponible | Estado | Uso permitido |
|---|---|---|---|
| E-01 | La organización de referencia es el Centro Educativo María Montessori y Daisy Quirós Quesada, su directora, aportó la validación del proceso. | Confirmado mediante entrevista del 19 de septiembre de 2026. | Contextualizar el proyecto en una organización concreta. |
| E-02 | Se utilizan hojas tradicionales impresas para comunicar calificaciones. | Confirmado por la fuente aportada por el equipo. | Modelar la hoja física como objeto que viaja de la escuela al hogar. |
| E-03 | El estudiante transporta la hoja; se han presentado casos en los que la retrasa, oculta o no la entrega oportunamente. | Confirmado por la fuente aportada por el equipo. | Modelar la dependencia del estudiante y el camino de no entrega. |
| E-04 | Algunos padres o encargados se han quejado por no recibir la información oportunamente. | Confirmado por la fuente aportada por el equipo. | Modelar el contacto o la queja como respuesta al fallo de entrega. |
| E-05 | El profesor dejó ocho observaciones concretas sobre acceso, perfiles, comunicación, contexto, carga, avisos, doble hito y congruencia. | Confirmado en el foro del curso. | Tratarlas como criterios de revisión y preguntas de levantamiento, no como hechos del proceso actual. |

## Aspectos todavía por validar

| ID | Hipótesis o dato faltante | Pregunta de levantamiento |
|---|---|---|
| PV-01 | Instrumento exacto en el que cada docente registra inicialmente las calificaciones. | ¿Dónde y cómo registra el docente la nota antes de preparar la hoja que recibe el estudiante? |
| PV-02 | Momento, frecuencia, plazo esperado y volumen de hojas tardías o no entregadas. | ¿Cuándo se entregan las hojas, cuánto tiempo tiene el estudiante y cuántos casos tardíos se observan por periodo? |
| PV-03 | Canales actuales para tareas, observaciones y evaluaciones asignadas. | ¿Qué información se anota en cuaderno, agenda, hoja, llamada, reunión u otro canal, y quién la genera? |
| PV-04 | Protocolo real de comunicación docente–encargado. | Cuando un encargado consulta o reclama, ¿por qué canal entra la solicitud, quién responde y qué pasos siguen? |
| PV-05 | Intervención de Dirección, plazos, escalamiento, mediación y registro de resoluciones. | ¿Cuándo pasa un caso a Dirección, existe una figura mediadora y queda alguna evidencia del acuerdo? |
| PV-06 | Acuse, repetición y manejo actual de comunicaciones no recibidas. | ¿Cómo sabe hoy la escuela que el encargado recibió la información y qué hace si no la recibió? |
| PV-07 | Diferencias aplicables entre escuela primaria y colegio. | ¿Qué parte del problema y del proceso cambia por nivel educativo? El alcance inicial será solo la escuela de referencia. |
| PV-08 | Carga administrativa real. | ¿Cuánto tiempo y cuántos contactos/reuniones adicionales genera repetir o aclarar información? |

## Participantes y responsabilidades actuales

| Participante BPMN | Responsabilidad representada | Límites del modelo |
|---|---|---|
| Escuela | Preparar y entregar la hoja física; atender contactos o quejas. | Las responsabilidades de Docente y Dirección posteriores a una queja están marcadas **[PV]** hasta levantar el protocolo real. |
| Estudiante | Transportar la hoja desde la escuela y entregarla —o no entregarla oportunamente— al encargado. | No se presupone que el estudiante sea usuario de un sistema futuro. |
| Encargado legal | Esperar/recibir y revisar la hoja; contactar a la escuela si no llega o si requiere aclaración. | El canal, los plazos y la forma de recibir respuesta están marcados **[PV]**. |

Los participantes se modelan como **pools independientes**. Los flujos internos son `sequenceFlow`; toda interacción entre pools es `messageFlow`. No existe ningún `sequenceFlow` que cruce pools.

## Catálogo de actividades AS-IS

| ID | Pool / responsabilidad | Actividad | Fuente | Estado |
|---|---|---|---|---|
| AC-01 | Escuela / Docente | Preparar la hoja física de calificaciones. | E-02; el instrumento previo es PV-01. | Confirmada al nivel de la hoja; detalle por validar. |
| AC-02 | Escuela / Docente | Entregar la hoja al estudiante. | E-02, E-03. | Confirmada. |
| AC-03 | Estudiante | Transportar la hoja hacia el hogar. | E-03. | Confirmada. |
| AC-04 | Estudiante | Entregar la hoja al encargado. | E-02, E-03. | Camino esperado del proceso. |
| AC-05 | Estudiante | Retrasar, ocultar o no entregar oportunamente la hoja. | E-03. | Excepción confirmada; frecuencia PV-02. |
| AC-06 | Escuela / Docente | Atender la consulta o queja por el canal actual. | E-04; procedimiento PV-04. | Existencia del contacto confirmada; pasos **[PV]**. |
| AC-07 | Escuela / Docente | Comunicar una aclaración o respuesta. | Consecuencia lógica de E-04; PV-04 y PV-05. | **[PV]**. |
| AC-08 | Escuela / Dirección | Intervenir cuando el caso se escala. | PV-05. | **[PV]**; no se afirma que exista mediación formal. |
| AC-09 | Encargado legal | Esperar la entrega que depende del estudiante. | E-02, E-03. | Confirmada como dependencia del canal. |
| AC-10 | Encargado legal | Revisar la hoja recibida. | Propósito de E-02. | Camino esperado; detalle por validar. |
| AC-11 | Encargado legal | Contactar a la escuela o presentar una queja por no recibirla. | E-04. | Confirmada; canal y plazo PV-04/PV-06. |
| AC-12 | Encargado legal | Solicitar una aclaración sobre la información recibida. | PV-04. | **[PV]**. |
| AC-13 | Encargado legal | Recibir la respuesta de la escuela. | PV-04/PV-05. | **[PV]**. |

## Flujos del proceso actual

### Camino esperado confirmado

1. `AC-01`: el docente prepara la hoja física de calificaciones.
2. `AC-02`: la escuela entrega la hoja al estudiante.
3. `AC-03`: el estudiante la transporta hacia el hogar.
4. Cuando la entrega ocurre oportunamente, `AC-04` transfiere la hoja al encargado.
5. `AC-09` y `AC-10`: el encargado deja de esperar y revisa la información recibida.

### Excepción confirmada

1. Después de `AC-03`, algunos estudiantes siguen `AC-05`: retrasan, ocultan o no entregan la hoja oportunamente.
2. El encargado no dispone de acceso independiente a esa información por este canal.
3. En algunos casos el encargado realiza `AC-11` y contacta a la escuela o presenta una queja.
4. A partir de ese punto, `AC-06`–`AC-08` y `AC-13` muestran únicamente el espacio del protocolo que debe levantarse; las actividades están rotuladas **[PV]** en los diagramas.

### Interacciones entre pools

| ID | Origen | Destino | Mensaje u objeto |
|---|---|---|---|
| MF-01 | `AC-02` Escuela | Inicio del pool Estudiante | Hoja física de calificaciones. |
| MF-02 | `AC-04` Estudiante | `AC-09` Encargado | Hoja entregada al hogar. |
| MF-03 | `AC-11` o `AC-12` Encargado | Inicio de atención en Escuela | Queja o solicitud de aclaración; canal por validar. |
| MF-04 | `AC-07` Escuela | `AC-13` Encargado | Respuesta o aclaración; canal y evidencia por validar. |

## Problemas y observaciones que debe atender la propuesta

| ID | Problema / decisión pendiente | Evidencia u origen | Relación con el proceso |
|---|---|---|---|
| P-01 | El encargado no tiene acceso inmediato e independiente; depende de `AC-03`/`AC-04`. | E-03 y observación 1 del profesor. | La no entrega conduce a `AC-05`. |
| P-02 | Debe definirse cómo se valida la relación entre encargado y estudiante y qué perfil puede consultar. | Observación 2 del profesor. | El proceso físico presupone la relación, pero no muestra validación institucional. |
| P-03 | El proceso y protocolo de comunicación padre/encargado–docente no están levantados. | E-04, PV-04/PV-05 y observación 3. | `AC-06`–`AC-08`, `AC-12` y `AC-13` están por validar. |
| P-04 | El contexto debe limitarse a una escuela primaria y no generalizarse a colegios o a toda Costa Rica. | PV-07 y observación 4. | Es una decisión de alcance, no una actividad AS-IS. |
| P-05 | La carga administrativa existe como hipótesis, pero falta medirla. | PV-08 y observación 5. | Se relaciona con `AC-06`–`AC-08` y contactos repetidos. |
| P-06 | No está definido cómo confirmar recepción ni qué hacer ante una comunicación no recibida. | PV-06 y observación 6. | El canal físico falla en `AC-05`; no hay acuse verificable documentado. |
| P-07 | La propuesta debe distinguir dos hitos: evaluación asignada para realizar y devolución calificada. | Observación 7. | PV-03 debe determinar cómo ocurren hoy ambos hitos. |
| P-08 | Debe preservarse congruencia entre lo comunicado en clase/medio físico y lo publicado digitalmente. | Observación 8. | PV-03 y PV-01 deben establecer la fuente autorizada. |

## Frontera con el proceso futuro

El diagrama de casos de uso contiene once capacidades propuestas: consulta, vinculación, publicación en dos etapas, seguimiento de avisos, comunicación, discrepancias, reportes, correcciones, administración y auditoría. Ese diagrama **no corrige ni reemplaza** el BPMN AS-IS. Cada capacidad se mantiene como propuesta sujeta a validación, y su origen se documenta en `08-matriz-trazabilidad.md`.

## Criterio de cierre para presentar al profesor

Antes de convertir este modelo en una línea base aprobada se debe:

1. Recorrer el BPMN con la Dirección y corregir cada actividad **[PV]**.
2. Registrar fuente, fecha y persona que valida el recorrido.
3. Responder PV-01 a PV-08 sin convertir estimaciones en estadísticas institucionales.
4. Confirmar que el alcance es educación primaria en la escuela de referencia.
5. Obtener acuerdo sobre los dos hitos de evaluación y sobre qué medio es la fuente autorizada.
6. Actualizar los RF, UC y pruebas afectados mediante la matriz de trazabilidad.
