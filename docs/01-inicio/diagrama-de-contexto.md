# Diagrama de contexto

El Portal Académico aparece como **una sola caja negra**. A su alrededor están las entidades externas con las que intercambia información, y cada flujo lleva el nombre del **dato** que viaja. No se muestran módulos, pantallas ni tablas.

![Diagrama de contexto del Portal Académico](diagrama-de-contexto.png)

## Fuente (Mermaid)

GitHub dibuja este bloque directamente; la imagen anterior se exportó de esta misma fuente.

```mermaid
flowchart LR
    ENC["Persona encargada legal"]
    DOC["Docente"]
    COM["Comité de Evaluación"]
    DIR["Dirección"]
    P(("Portal Académico"))

    ENC -- "F-01 criterios de consulta<br/>F-02 mensaje sobre el estudiante<br/>F-03 apelación con respaldo<br/>F-04 queja por información no recibida<br/>F-05 acuse de consulta" --> P
    P -- "F-06 perfil, evaluaciones y calificaciones<br/>F-07 aviso de evaluación asignada<br/>F-08 aviso de calificación o corrección<br/>F-09 recordatorio de aviso pendiente<br/>F-10 respuesta de la docente<br/>F-11 resolución de la apelación<br/>F-12 respuesta a la queja" --> ENC

    DOC -- "F-13 evaluación asignada confirmada<br/>F-14 calificaciones del grupo confirmadas<br/>F-15 corrección con motivo<br/>F-16 respuesta al mensaje<br/>F-17 prueba y criterio de calificación" --> P
    P -- "F-18 mensaje de la persona encargada<br/>F-19 aviso de apelación<br/>F-20 solicitud de publicación faltante<br/>F-21 resumen de publicación" --> DOC

    COM -- "F-22 resolución de la apelación<br/>F-23 solicitud de información adicional" --> P
    P -- "F-24 expediente de la apelación" --> COM

    DIR -- "F-25 vínculo con respaldo<br/>F-26 plazos y política de recordatorios<br/>F-27 observaciones de la apelación<br/>F-28 hallazgo y respuesta de la queja<br/>F-29 criterios de supervisión" --> P
    P -- "F-30 alerta de aviso sin consulta<br/>F-31 estado del seguimiento<br/>F-32 registro de consultas<br/>F-33 aviso de queja<br/>F-34 aviso de apelación o vencimiento" --> DIR

    classDef ext fill:#F8FBFF,stroke:#275D85,color:#17324D
    classDef sys fill:#275D85,stroke:#17324D,color:#FFFFFF,font-weight:bold
    class ENC,DOC,COM,DIR ext
    class P sys
```

## Correspondencia de las entidades externas

| Entidad externa | Actor de casos de uso | Participante del BPMN |
|---|---|---|
| Persona encargada legal | [A-01](../03-casos-de-uso/actores.md#a-01--persona-encargada-legal) | Carril «Persona encargada legal» |
| Docente | [A-02](../03-casos-de-uso/actores.md#a-02--docente) | Carril «Docente» |
| Comité de Evaluación | [A-03](../03-casos-de-uso/actores.md#a-03--comité-de-evaluación) | Carril «Comité de Evaluación» y subproceso SP-01 |
| Dirección | [A-04](../03-casos-de-uso/actores.md#a-04--dirección) | Carril «Dirección» y subproceso SP-01 |

**Quiénes no aparecen y por qué:**

- **Estudiante:** participa en el BPMN porque hoy lleva el cuaderno y el reporte, pero no intercambia datos con el Portal (no tiene cuenta en el MVP).
- **Tiempo (servicio programado):** es un disparador interno del sistema, no una entidad externa.
- **Plataforma digital actual de la escuela:** existe (E-01), pero la integración está fuera del MVP, así que no intercambia datos con el Portal.

No aparece ninguna entidad nueva respecto de los actores y del BPMN.

## Flujos de datos

| ID | Origen | Destino | Dato | CU que lo produce o consume |
|---|---|---|---|---|
| F-01 | Persona encargada | Portal | Criterios de consulta (estudiante y periodo) | [CU-01](../03-casos-de-uso/CU-01-consultar-progreso-academico.md) |
| F-02 | Persona encargada | Portal | Mensaje sobre el estudiante | [CU-06](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) |
| F-03 | Persona encargada | Portal | Apelación con puntos, motivo y respaldo | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-04 | Persona encargada | Portal | Queja por información no recibida | [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| F-05 | Persona encargada | Portal | Acuse de consulta de un aviso | [CU-05](../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) |
| F-06 | Portal | Persona encargada | Perfil, evaluaciones y calificaciones publicadas | [CU-01](../03-casos-de-uso/CU-01-consultar-progreso-academico.md) |
| F-07 | Portal | Persona encargada | Aviso de evaluación asignada | [CU-03](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) |
| F-08 | Portal | Persona encargada | Aviso de calificación o de corrección | [CU-04](../03-casos-de-uso/CU-04-publicar-calificacion.md), [CU-09](../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) |
| F-09 | Portal | Persona encargada | Recordatorio de aviso pendiente | [CU-05](../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) |
| F-10 | Portal | Persona encargada | Respuesta de la docente | [CU-06](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) |
| F-11 | Portal | Persona encargada | Resolución de la apelación | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-12 | Portal | Persona encargada | Respuesta a la queja | [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| F-13 | Docente | Portal | Evaluación asignada con confirmación del cuaderno | [CU-03](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) |
| F-14 | Docente | Portal | Calificaciones del grupo con confirmación del cuaderno | [CU-04](../03-casos-de-uso/CU-04-publicar-calificacion.md) |
| F-15 | Docente | Portal | Corrección de calificación con motivo | [CU-09](../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) |
| F-16 | Docente | Portal | Respuesta al mensaje | [CU-06](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) |
| F-17 | Docente | Portal | Prueba y criterio de calificación | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-18 | Portal | Docente | Mensaje de la persona encargada | [CU-06](../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) |
| F-19 | Portal | Docente | Aviso de apelación | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-20 | Portal | Docente | Solicitud de publicar información faltante | [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| F-21 | Portal | Docente | Resumen de la publicación (incluidos y sin destinatario) | [CU-03](../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md), [CU-04](../03-casos-de-uso/CU-04-publicar-calificacion.md) |
| F-22 | Comité de Evaluación | Portal | Resolución de la apelación | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-23 | Comité de Evaluación | Portal | Solicitud de información adicional | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-24 | Portal | Comité de Evaluación | Expediente de la apelación | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-25 | Dirección | Portal | Vínculo persona encargada–estudiante con respaldo | [CU-02](../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) |
| F-26 | Dirección | Portal | Plazos y política de recordatorios | [CU-10](../03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md) |
| F-27 | Dirección | Portal | Observaciones de la apelación | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| F-28 | Dirección | Portal | Hallazgo y respuesta de la queja | [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| F-29 | Dirección | Portal | Criterios de supervisión (periodo y grupo) | [CU-08](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) |
| F-30 | Portal | Dirección | Alerta de aviso sin consulta o estudiante sin destinatario | [CU-05](../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) |
| F-31 | Portal | Dirección | Estado del seguimiento (avisos, quejas y apelaciones) | [CU-08](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) |
| F-32 | Portal | Dirección | Registro de consultas | [CU-08](../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md), [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| F-33 | Portal | Dirección | Aviso de queja registrada | [CU-12](../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| F-34 | Portal | Dirección | Aviso de apelación o de plazo vencido | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
