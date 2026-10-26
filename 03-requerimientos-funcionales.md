# Portal Académico — Centro Educativo María Montessori

## Requerimientos funcionales del MVP

**Alcance:** educación primaria  
**Convención:** cada requisito expresa una capacidad atómica, un actor o disparador identificable y un criterio observable.  
**Prioridades:** `Debe` = indispensable para el MVP; `Debería` = importante si el tiempo lo permite; `Futuro` = fuera del MVP.

## 1. Acceso y relación encargado–estudiante

### RF-01 — Autenticar a un usuario

- **Actor y problema:** Usuario registrado; necesita ingresar sin depender de que otra persona habilite cada consulta.
- **Requisito:** El Portal deberá autenticar al usuario mediante credenciales institucionalmente aprobadas.
- **Criterio de aceptación:** con credenciales válidas inicia una sesión asociada a una cuenta; con credenciales inválidas no permite el ingreso.
- **Prioridad:** Debe.
- **Origen:** observaciones 1 y 2 del profesor; mecanismo exacto de autenticación por validar.

### RF-02 — Aplicar permisos por rol

- **Actor y problema:** Institución; debe impedir que una persona consulte o modifique información fuera de su responsabilidad.
- **Requisito:** El Portal deberá autorizar cada operación según el rol activo del usuario.
- **Criterio de aceptación:** una prueba por rol confirma que únicamente aparecen y se ejecutan las operaciones autorizadas.
- **Prioridad:** Debe.
- **Origen:** protección de datos de menores; regla institucional propuesta.

### RF-03 — Registrar una relación encargado–estudiante

- **Actor y problema:** Administrador institucional; necesita establecer quién está autorizado para consultar a cada estudiante.
- **Requisito:** El Portal deberá permitir registrar una relación entre una persona encargada y un estudiante, con estado pendiente, activa, suspendida o finalizada.
- **Criterio de aceptación:** el registro conserva ambos participantes, estado, responsable institucional y fecha de cada cambio.
- **Prioridad:** Debe.
- **Origen:** observación 2 del profesor; cardinalidades y documentación exigida por validar.

### RF-04 — Restringir la consulta al vínculo activo

- **Actor y problema:** Persona encargada; debe consultar únicamente a estudiantes para los que posee autorización vigente.
- **Requisito:** El Portal deberá mostrar información académica solamente cuando exista una relación encargado–estudiante activa.
- **Criterio de aceptación:** una cuenta con vínculo activo consulta al estudiante y una cuenta sin vínculo activo recibe denegación sin exponer datos.
- **Prioridad:** Debe.
- **Origen:** observación 2 del profesor y privacidad.

### RF-05 — Seleccionar un estudiante vinculado

- **Actor y problema:** Persona encargada con más de un estudiante; necesita cambiar de contexto sin utilizar otra cuenta.
- **Requisito:** El Portal deberá permitir seleccionar uno de los estudiantes asociados activamente con la cuenta.
- **Criterio de aceptación:** al cambiar la selección, toda consulta posterior corresponde únicamente al estudiante elegido.
- **Prioridad:** Debe.
- **Origen:** hipótesis de múltiples vínculos por validar con la Dirección.

### RF-06 — Consultar el perfil académico

- **Actor y problema:** Persona encargada; necesita reconocer el contexto del estudiante antes de interpretar su progreso.
- **Requisito:** El Portal deberá permitir consultar grado, grupo, periodo, materias y docentes del estudiante seleccionado.
- **Criterio de aceptación:** el perfil recupera esos datos del periodo activo sin incluir información de estudiantes no vinculados.
- **Prioridad:** Debe.
- **Origen:** observación 2 del profesor.

### RF-07 — Consultar el historial académico

- **Actor y problema:** Persona encargada; necesita revisar el progreso sin solicitar al docente que repita la información.
- **Requisito:** El Portal deberá permitir consultar evaluaciones, resultados y observaciones del estudiante por periodo y materia.
- **Criterio de aceptación:** al indicar periodo y materia devuelve los registros publicados correspondientes y permite reconocer su fecha y estado.
- **Prioridad:** Debe.
- **Origen:** observaciones 1 y 5 del profesor.

## 2. Registro eficiente y dos hitos de evaluación

### RF-08 — Crear una evaluación para un grupo

- **Actor y problema:** Docente; necesita evitar registrar la misma evaluación de forma individual para cada estudiante.
- **Requisito:** El Portal deberá permitir crear una evaluación una sola vez y asociarla con un grupo y una materia.
- **Criterio de aceptación:** un registro creado queda relacionado con todos los estudiantes activos del grupo sin duplicar la definición de la evaluación.
- **Prioridad:** Debe.
- **Origen:** observación 5 del profesor.

### RF-09 — Registrar el hito de entrega para realización

- **Actor y problema:** Docente; necesita comunicar que una evaluación fue entregada o asignada para que el estudiantado la realice.
- **Requisito:** El Portal deberá permitir registrar, para una evaluación, la fecha de entrega o asignación, la fecha límite cuando corresponda y una descripción académica breve.
- **Criterio de aceptación:** el hito queda identificado como `entregada para realización` y no contiene todavía una calificación.
- **Prioridad:** Debe.
- **Origen:** observación 7 del profesor; campos definitivos por validar.

### RF-10 — Confirmar congruencia del primer hito

- **Actor y problema:** Docente; debe evitar publicar una evaluación que no corresponda con lo comunicado mediante el medio físico oficial.
- **Requisito:** El Portal deberá exigir al docente confirmar la correspondencia físico-digital antes de publicar el hito de entrega para realización.
- **Criterio de aceptación:** sin confirmación el hito permanece en borrador; con confirmación conserva usuario y fecha y habilita su publicación.
- **Prioridad:** Debe.
- **Origen:** observación 8 del profesor; significado de “medio físico oficial” por validar.

### RF-11 — Publicar el primer hito

- **Actor y problema:** Docente; necesita poner la asignación a disposición de las personas encargadas autorizadas.
- **Requisito:** El Portal deberá permitir publicar el hito confirmado de entrega para realización.
- **Criterio de aceptación:** al publicar cambia de borrador a publicado, registra autor y fecha y queda visible para los vínculos activos del grupo.
- **Prioridad:** Debe.
- **Origen:** observaciones 1, 7 y 8 del profesor.

### RF-12 — Cargar resultados por grupo

- **Actor y problema:** Docente; necesita registrar resultados sin abrir un formulario separado por estudiante.
- **Requisito:** El Portal deberá permitir registrar en una misma operación de trabajo las calificaciones y observaciones individuales de los estudiantes del grupo.
- **Criterio de aceptación:** cada valor queda asociado con un solo estudiante y la evaluación común, y los valores incompletos pueden conservarse como borrador.
- **Prioridad:** Debe.
- **Origen:** observación 5 del profesor.

### RF-13 — Confirmar congruencia del segundo hito

- **Actor y problema:** Docente; debe evitar publicar una calificación que no corresponda con la evaluación devuelta por el medio físico oficial.
- **Requisito:** El Portal deberá exigir al docente confirmar la correspondencia físico-digital antes de publicar cada resultado.
- **Criterio de aceptación:** un resultado no confirmado permanece en borrador; la confirmación conserva usuario y fecha.
- **Prioridad:** Debe.
- **Origen:** observación 8 del profesor.

### RF-14 — Publicar el resultado calificado

- **Actor y problema:** Docente; necesita comunicar la devolución calificada como un evento distinto de la asignación.
- **Requisito:** El Portal deberá permitir publicar la calificación y observación confirmadas para cada estudiante.
- **Criterio de aceptación:** el resultado cambia a publicado, queda visible únicamente para usuarios autorizados y mantiene referencia a la evaluación de origen.
- **Prioridad:** Debe.
- **Origen:** observación 7 del profesor.

### RF-15 — Corregir un resultado con historial

- **Actor y problema:** Docente autorizado; necesita corregir un dato sin borrar la evidencia de lo publicado previamente.
- **Requisito:** El Portal deberá permitir sustituir un resultado publicado únicamente si se registra un motivo de corrección.
- **Criterio de aceptación:** la versión anterior permanece consultable para auditoría y la nueva versión conserva autor, fecha y motivo.
- **Prioridad:** Debería.
- **Origen:** trazabilidad e integridad; procedimiento de autorización por validar.

## 3. Notificaciones, acuse y seguimiento

### RF-16 — Generar un aviso por el primer hito

- **Actor y problema:** Sistema, a partir de la publicación docente; debe avisar sin exigir mensajes individuales.
- **Requisito:** El Portal deberá generar un aviso para cada persona encargada con vínculo activo cuando se publique una evaluación entregada para realización.
- **Criterio de aceptación:** una publicación genera un aviso distinguible del aviso de resultado para cada destinatario activo, sin acción adicional del docente.
- **Prioridad:** Debe.
- **Origen:** observaciones 5, 6 y 7 del profesor.

### RF-17 — Generar un aviso por el segundo hito

- **Actor y problema:** Sistema, a partir de la publicación docente; debe comunicar la devolución calificada por separado.
- **Requisito:** El Portal deberá generar un aviso para cada persona encargada con vínculo activo cuando se publique un resultado calificado o una corrección.
- **Criterio de aceptación:** cada publicación o corrección produce un aviso asociado con el resultado y distinto del aviso del primer hito.
- **Prioridad:** Debe.
- **Origen:** observaciones 6 y 7 del profesor.

### RF-18 — Registrar el estado de un aviso

- **Actor y problema:** Institución; necesita diferenciar creación, envío, entrega técnica, consulta y fallo.
- **Requisito:** El Portal deberá conservar para cada aviso su estado y la fecha y hora de cada transición disponible.
- **Criterio de aceptación:** el historial no usa `enviado`, `entregado`, `visto` y `fallido` como sinónimos y muestra solo estados respaldados por un evento registrado.
- **Prioridad:** Debe.
- **Origen:** observación 6 del profesor.

### RF-19 — Registrar el acuse de consulta

- **Actor y problema:** Persona encargada e institución; necesitan evidencia de que el contenido fue consultado, no solamente enviado.
- **Requisito:** El Portal deberá registrar usuario, fecha y hora cuando una persona encargada abra el detalle de un aviso.
- **Criterio de aceptación:** la primera apertura cambia el aviso a visto y aperturas posteriores no eliminan el primer acuse.
- **Prioridad:** Debe.
- **Origen:** observación 6 del profesor.

### RF-20 — Configurar la política de recordatorios

- **Actor y problema:** Dirección o administrador autorizado; necesita ajustar plazos sin modificar el sistema.
- **Requisito:** El Portal deberá permitir configurar el número de recordatorios, el intervalo entre ellos y el momento de alerta institucional.
- **Criterio de aceptación:** una política guardada se aplica a avisos nuevos y conserva quién la modificó y cuándo.
- **Prioridad:** Debe.
- **Origen:** observación 6 del profesor; valores iniciales son supuestos por validar.

### RF-21 — Generar un recordatorio por falta de consulta

- **Actor y problema:** Sistema programado; debe reiterar un aviso que continúa sin acuse después del plazo configurado.
- **Requisito:** El Portal deberá generar un recordatorio cuando un aviso permanezca no visto al cumplirse el intervalo configurado.
- **Criterio de aceptación:** el recordatorio referencia el aviso original, se genera una sola vez por intervalo y no se genera si ya existe acuse.
- **Prioridad:** Debe.
- **Origen:** observación 6 del profesor.

### RF-22 — Alertar un caso sin consulta

- **Actor y problema:** Dirección o responsable de seguimiento; necesita identificar a quién contactar después de agotar los recordatorios.
- **Requisito:** El Portal deberá colocar un aviso no visto en seguimiento institucional cuando se alcance el límite configurado.
- **Criterio de aceptación:** el caso aparece con estudiante, destinatario, publicación de origen, intentos y antigüedad, sin modificar la información académica.
- **Prioridad:** Debe.
- **Origen:** observación 6 del profesor; responsable final por validar.

## 4. Comunicación y discrepancias

### RF-23 — Crear un hilo de comunicación

- **Actor y problema:** Docente o persona encargada; necesita formular una consulta en un contexto institucional y trazable.
- **Requisito:** El Portal deberá permitir crear un hilo asociado con un estudiante, un asunto y participantes autorizados.
- **Criterio de aceptación:** el hilo conserva creador y fecha y no puede incorporar participantes sin vínculo o responsabilidad vigente.
- **Prioridad:** Debe.
- **Origen:** observación 3 del profesor; protocolo actual por validar.

### RF-24 — Registrar un mensaje en un hilo

- **Actor y problema:** Participante autorizado; necesita responder sin perder el historial de la conversación.
- **Requisito:** El Portal deberá permitir agregar mensajes fechados a un hilo vigente.
- **Criterio de aceptación:** cada mensaje conserva autor, fecha y contenido y no sustituye mensajes anteriores.
- **Prioridad:** Debe.
- **Origen:** observación 3 del profesor.

### RF-25 — Abrir una discrepancia académica

- **Actor y problema:** Persona encargada; necesita cuestionar formalmente un resultado u observación y conocer su seguimiento.
- **Requisito:** El Portal deberá permitir abrir una discrepancia asociada con un registro académico publicado e indicar su motivo.
- **Criterio de aceptación:** el caso recibe identificador, fecha, solicitante, registro cuestionado y estado inicial `abierta`.
- **Prioridad:** Debe.
- **Origen:** observación 3 del profesor; existencia y forma del protocolo actual por validar.

### RF-26 — Asignar una discrepancia

- **Actor y problema:** Dirección o regla institucional; necesita establecer quién debe atender cada caso.
- **Requisito:** El Portal deberá permitir asignar una discrepancia a un responsable autorizado.
- **Criterio de aceptación:** el caso conserva responsable actual, fecha de asignación e historial de reasignaciones.
- **Prioridad:** Debe.
- **Origen:** hipótesis de proceso futuro por validar.

### RF-27 — Actualizar el estado de una discrepancia

- **Actor y problema:** Responsable del caso; necesita reflejar su avance mediante estados institucionalmente definidos.
- **Requisito:** El Portal deberá permitir cambiar el estado de una discrepancia únicamente mediante transiciones autorizadas.
- **Criterio de aceptación:** cada transición conserva estado anterior, estado nuevo, usuario, fecha y comentario cuando la regla lo exija.
- **Prioridad:** Debe.
- **Origen:** hipótesis de proceso futuro; catálogo de estados por validar.

### RF-28 — Escalar una discrepancia

- **Actor y problema:** Responsable autorizado o regla de plazo; necesita remitir un caso no resuelto a la instancia institucional correspondiente.
- **Requisito:** El Portal deberá permitir escalar una discrepancia y registrar el motivo del escalamiento.
- **Criterio de aceptación:** el caso conserva responsable anterior, nueva instancia, motivo y fecha sin alterar automáticamente la nota cuestionada.
- **Prioridad:** Debe.
- **Origen:** observación 3 del profesor; figura mediadora y plazos por validar.

### RF-29 — Registrar la resolución de una discrepancia

- **Actor y problema:** Responsable autorizado; necesita dejar evidencia del resultado y cerrar el caso.
- **Requisito:** El Portal deberá permitir registrar una resolución antes de cerrar una discrepancia.
- **Criterio de aceptación:** el cierre conserva texto de resolución, responsable, fecha y referencia a cualquier corrección académica separada.
- **Prioridad:** Debe.
- **Origen:** observación 3 del profesor.

## 5. Reportes, administración y auditoría

### RF-30 — Consultar rendimiento agregado

- **Actor y problema:** Dirección; necesita identificar tendencias sin revisar estudiante por estudiante.
- **Requisito:** El Portal deberá permitir consultar resultados agregados por periodo, grupo y materia.
- **Criterio de aceptación:** la consulta devuelve conteos y medidas aprobadas para los filtros indicados y restringe el detalle según permisos.
- **Prioridad:** Debería.
- **Origen:** necesidad institucional propuesta; indicadores por validar.

### RF-31 — Consultar seguimiento de comunicaciones

- **Actor y problema:** Dirección; necesita reconocer avisos pendientes y tiempos de consulta.
- **Requisito:** El Portal deberá permitir consultar publicaciones vistas, no vistas, fallidas y en seguimiento por periodo y grupo.
- **Criterio de aceptación:** los totales pueden rastrearse hasta avisos existentes y respetan los permisos del usuario.
- **Prioridad:** Debe.
- **Origen:** observaciones 5 y 6 del profesor.

### RF-32 — Consultar estado de discrepancias

- **Actor y problema:** Dirección o figura mediadora; necesita detectar casos abiertos, vencidos y escalados.
- **Requisito:** El Portal deberá permitir consultar discrepancias por estado, responsable y antigüedad.
- **Criterio de aceptación:** cada resultado coincide con los filtros y enlaza con el historial del caso para usuarios autorizados.
- **Prioridad:** Debe.
- **Origen:** observación 3 del profesor.

### RF-33 — Administrar catálogos académicos

- **Actor y problema:** Administrador institucional; necesita mantener periodos, grados, grupos y materias usados por los registros.
- **Requisito:** El Portal deberá permitir crear, actualizar y desactivar periodos, grados, grupos y materias.
- **Criterio de aceptación:** desactivar un elemento impide usarlo en registros nuevos sin borrar su historial.
- **Prioridad:** Debe.
- **Origen:** soporte mínimo del dominio.

### RF-34 — Administrar cuentas y roles

- **Actor y problema:** Administrador institucional; necesita controlar quién puede operar el Portal.
- **Requisito:** El Portal deberá permitir crear, activar, suspender y asignar roles a cuentas institucionales.
- **Criterio de aceptación:** cada cambio conserva actor, fecha y estado anterior y surte efecto en el siguiente control de autorización.
- **Prioridad:** Debe.
- **Origen:** seguridad y operación institucional.

### RF-35 — Consultar la bitácora de una entidad

- **Actor y problema:** Auditor institucional autorizado; necesita reconstruir cambios relevantes sin alterar los registros.
- **Requisito:** El Portal deberá permitir consultar la bitácora de publicaciones, resultados, avisos, vínculos, mensajes y discrepancias.
- **Criterio de aceptación:** la consulta identifica entidad, acción, usuario, fecha y referencia al cambio, y no permite editar la bitácora.
- **Prioridad:** Debe.
- **Origen:** trazabilidad del problema y observaciones 6 y 8 del profesor.

## 6. Funciones futuras fuera del MVP

- **RF-F01 — Apoyo de IA con revisión humana:** una fase futura podrá proponer resúmenes o señalar pendientes, pero ninguna salida se publicará ni afectará calificaciones o discrepancias sin revisión y confirmación humana.
- **RF-F02 — Canales adicionales:** SMS, aplicaciones móviles nativas o mensajería comercial requerirán evaluación de consentimiento, costo, privacidad y disponibilidad.
- **RF-F03 — Adaptación a secundaria:** requerirá un nuevo levantamiento de actores, relaciones, protocolos y autonomía estudiantil.

## 7. Trazabilidad de las observaciones del profesor

| Observación | Respuesta en los requisitos |
|---|---|
| 1. Acceso inmediato e independiente | RF-01, RF-04, RF-07 |
| 2. Perfil y acceso del encargado | RF-03 a RF-06 |
| 3. Comunicación padre/encargado–docente | RF-23 a RF-29 |
| 4. Diferenciar colegios y escuelas | Alcance exclusivo de primaria; RF-F03 para una evolución |
| 5. Carga administrativa | RF-08, RF-12, RF-16, RF-17 y RF-31 |
| 6. Notificación, acuse y reiteración | RF-18 a RF-22 |
| 7. Avisar entrega y devolución calificada | RF-09, RF-11, RF-14, RF-16 y RF-17 |
| 8. Congruencia con lo realizado en clase | RF-10, RF-13, RF-15 y RF-35 |
| Extra. IA | RF-F01, fuera del MVP y siempre con revisión humana |

