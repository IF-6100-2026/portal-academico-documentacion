# Portal Académico — Casos de uso detallados

## 1. Alcance y convenciones

Este documento especifica once casos de uso del MVP de **Portal Académico** para una institución de educación primaria. Describe comportamiento observable del sistema y evita decisiones de interfaz, infraestructura o implementación.

Convenciones:

- Un registro **publicado** es visible para sus destinatarios; un borrador no lo es.
- El **medio físico oficial** es el cuaderno de comunicaciones, hoja impresa u otro medio que la institución confirme durante la validación del proyecto.
- El **acuse de consulta** acredita que una persona autorizada accedió al aviso o a su detalle; no equivale al envío ni a la entrega técnica del aviso.
- Las autorizaciones se aplican con criterio de mínimo privilegio.
- Las fechas y cantidades marcadas **[PV]** son valores provisionales por validar con la institución.
- Cada flujo alterno identifica el paso exacto del flujo básico que sustituye o interrumpe.

## 2. Actores

| Actor | Responsabilidad relevante |
|---|---|
| Encargado legal | Consulta el progreso de estudiantes autorizados, recibe avisos, se comunica con el docente y presenta discrepancias. |
| Docente | Registra y publica eventos académicos, confirma su correspondencia con el medio físico y atiende comunicaciones y discrepancias. |
| Administrador institucional | Valida relaciones entre encargados y estudiantes y administra su vigencia. |
| Dirección o figura mediadora | Consulta información institucional y resuelve discrepancias escaladas. |
| Tiempo / servicio de notificaciones | Activa recordatorios y controles de vencimiento conforme a los parámetros institucionales. |
| Estudiante | Participante indirecto y sujeto del registro académico; no posee acceso directo en el MVP. |

---

## UC-01 — Consultar progreso académico

### Propósito

Permitir que un encargado legal consulte, de manera autónoma, el perfil y el historial académico publicado de un estudiante con quien mantiene una relación de acceso vigente.

### Requisitos relacionados

RF-01, RF-02, RF-04, RF-05, RF-06 y RF-07.

### Actores

- **Principal:** Encargado legal.
- **Secundarios:** Ninguno.

### Precondiciones

1. El encargado posee una identidad institucional registrada.
2. El estudiante pertenece a un periodo académico conocido por Portal Académico.
3. Para el flujo básico existe una relación encargado–estudiante activa.

### Postcondiciones

- **Éxito:** el encargado obtiene únicamente el perfil y los registros publicados que puede consultar; el acceso queda trazado.
- **Garantía mínima:** ningún dato de un estudiante no autorizado queda expuesto; los intentos denegados quedan trazados.

### Relaciones

- Puede dar origen a UC-06, **Comunicarse sobre un estudiante**.
- Puede dar origen a UC-07, **Gestionar discrepancia académica**, desde un registro elegible.

### Flujo básico

1. El encargado solicita acceso a Portal Académico con su identidad institucional.
2. Portal Académico valida la identidad, el estado de la cuenta y el rol del encargado.
3. Portal Académico determina las relaciones encargado–estudiante vigentes.
4. Portal Académico pone a disposición únicamente los estudiantes comprendidos por esas relaciones.
5. El encargado especifica un estudiante y un periodo académico autorizados.
6. Portal Académico vuelve a validar la relación vigente para la solicitud concreta.
7. Portal Académico proporciona el perfil académico y los registros publicados del periodo, organizados por materia y tipo de evento.
8. El encargado solicita el detalle de un registro publicado.
9. Portal Académico proporciona el detalle, su estado vigente y la información de trazabilidad visible para el encargado.
10. Portal Académico registra el acceso efectuado.

### Flujos alternos

**UC-01-A01 — Identidad o cuenta no habilitada (paso 2)**

1. Portal Académico rechaza el acceso sin revelar información académica.
2. Portal Académico registra el intento y su resultado.
3. El caso de uso finaliza.

**UC-01-A02 — Ausencia de relaciones activas (paso 3)**

1. Portal Académico informa que no existen estudiantes habilitados para consulta.
2. Portal Académico no expone perfiles ni registros académicos.
3. Portal Académico registra la consulta sin resultados y el caso de uso finaliza.

**UC-01-A03 — Solicitud de un estudiante no autorizado (paso 6)**

1. Portal Académico deniega la solicitud concreta.
2. Portal Académico no confirma la existencia ni el contenido académico del estudiante solicitado.
3. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-01-A04 — Periodo sin registros publicados (paso 7)**

1. Portal Académico informa que el periodo autorizado no contiene registros publicados.
2. Portal Académico conserva el contexto del estudiante y del periodo sin presentar datos de borradores.
3. El flujo continúa en el paso 10.

---
### Prototipo

[Prototipo Stitch — Perfil y progreso académico](prototipos/stitch-1/screen.png)

### Requerimientos especiales

- Mínimo privilegio, vínculo vigente y ausencia de borradores.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-02 — Vincular encargado con estudiante

### Propósito

Establecer o actualizar una relación de acceso verificada y con vigencia definida entre un encargado legal y un estudiante.

### Requisitos relacionados

RF-01, RF-02 y RF-03.

### Actores

- **Principal:** Administrador institucional.
- **Secundario:** Encargado legal.

### Precondiciones

1. El administrador posee una identidad institucional registrada.
2. Existen identidades verificables para el encargado y el estudiante.
3. La institución dispone del respaldo requerido para validar la relación.

### Postcondiciones

- **Éxito:** la relación queda en el estado autorizado, con alcance y vigencia determinados; el encargado recibe constancia del cambio.
- **Garantía mínima:** ninguna relación queda activa sin validación institucional; las decisiones y sus responsables quedan trazados.

### Relaciones

- Habilita el acceso descrito en UC-01, UC-06 y UC-07.

### Flujo básico

1. El administrador solicita registrar una relación encargado–estudiante o actualizar su estado.
2. Portal Académico valida la identidad, el rol y el alcance administrativo del actor.
3. El administrador identifica al encargado, al estudiante, el tipo de relación, el estado autorizado y su periodo de vigencia.
4. Portal Académico verifica ambas identidades y la coherencia de la solicitud con las relaciones existentes.
5. El administrador registra la referencia del respaldo utilizado para verificar la relación.
6. Portal Académico valida que la información de verificación exigida esté completa.
7. Portal Académico establece el estado de la relación con el alcance y vigencia autorizados.
8. Portal Académico comunica al encargado el cambio y su efecto sobre el acceso.
9. Portal Académico registra la actuación administrativa completa.

### Flujos alternos

**UC-02-A01 — Actor sin atribución administrativa (paso 2)**

1. Portal Académico rechaza la solicitud.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-02-A02 — Identidad inexistente o inactiva (paso 4)**

1. Portal Académico identifica la persona o condición institucional que impide la vinculación, sin crear la relación.
2. Portal Académico registra el resultado y el caso de uso finaliza.

**UC-02-A03 — Relación activa equivalente (paso 4)**

1. Portal Académico conserva la relación vigente sin crear un duplicado.
2. Portal Académico comunica al administrador la existencia y vigencia de la relación.
3. Portal Académico registra la actuación y el caso de uso finaliza.

**UC-02-A04 — Restricción o conflicto de autorización (paso 4)**

1. Portal Académico mantiene la solicitud sin acceso activo.
2. El administrador registra el motivo y remite la validación a la autoridad institucional competente.
3. Portal Académico registra el estado pendiente y el caso de uso finaliza.

**UC-02-A05 — Verificación incompleta (paso 6)**

1. Portal Académico no activa la relación.
2. Portal Académico identifica los datos de verificación pendientes.
3. El administrador puede completar la solicitud desde el paso 5 en una actuación posterior.

**UC-02-A06 — Suspensión o finalización de una relación vigente (paso 7)**

1. Portal Académico cambia la relación al estado suspendido o finalizado desde la fecha autorizada.
2. El cambio surte efecto en el siguiente control de acceso.
3. Portal Académico conserva el estado anterior, la fecha, el responsable y el motivo.
4. El flujo continúa en el paso 8.

---
### Prototipo

[Prototipo Stitch — Perfil y progreso académico](prototipos/stitch-1/screen.png)

### Requerimientos especiales

- Validación institucional, vigencia y bitácora de cada cambio.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-03 — Publicar evaluación asignada

### Propósito

Registrar una evaluación asignada a un grupo, confirmar su correspondencia con el medio físico oficial y comunicar su entrega al estudiantado a los encargados autorizados.

### Requisitos relacionados

RF-01, RF-02, RF-08, RF-09, RF-10, RF-11, RF-16 y RF-18.

### Actores

- **Principal:** Docente.
- **Secundarios:** Encargado legal y estudiante como participante indirecto.

### Precondiciones

1. El docente posee una identidad institucional registrada.
2. El grupo, la materia, el periodo y la nómina estudiantil existen.
3. Para el flujo básico el docente está asignado a la materia y al grupo.

### Postcondiciones

- **Éxito:** existe un evento publicado por cada estudiante activo comprendido; se crea la notificación de primera etapa para cada encargado autorizado y se conserva la trazabilidad.
- **Garantía mínima:** ningún evento se publica sin confirmación físico–digital; un fallo de notificación no elimina ni duplica el evento académico.

### Relaciones

- Precede normalmente a UC-04, **Publicar resultado académico**.
- Los avisos pendientes quedan sujetos a UC-05, **Gestionar avisos no consultados**.

### Flujo básico

1. El docente solicita registrar una evaluación asignada para un grupo.
2. Portal Académico valida la identidad, el rol y la asignación del docente al grupo, materia y periodo.
3. El docente proporciona el nombre y tipo de evaluación, las fechas de entrega y vencimiento, las instrucciones y el grupo destinatario.
4. Portal Académico valida los datos, la coherencia de fechas y la nómina activa comprendida.
5. El docente declara la fecha de entrega al estudiantado y confirma la correspondencia con el medio físico oficial.
6. Portal Académico valida la presencia y coherencia de la confirmación físico–digital.
7. El docente autoriza la publicación del evento.
8. Portal Académico publica una instancia del evento para cada estudiante activo comprendido y registra su origen común de grupo.
9. Portal Académico crea la notificación de primera etapa para cada encargado con relación activa y realiza el envío configurado.
10. Portal Académico registra por destinatario el momento, el tipo de notificación y el resultado del intento.
11. Portal Académico proporciona al docente el resultado agregado de la publicación y las incidencias identificadas.

### Flujos alternos

**UC-03-A01 — Docente sin asignación autorizada (paso 2)**

1. Portal Académico rechaza la operación sin publicar información.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-03-A02 — Datos incompletos o fechas incoherentes (paso 4)**

1. Portal Académico identifica los datos que requieren corrección.
2. Portal Académico conserva la información como no publicada.
3. El docente puede reanudar el flujo en el paso 3.

**UC-03-A03 — Nómina con estudiantes inactivos (paso 4)**

1. Portal Académico excluye de la publicación a los estudiantes que no están activos en la fecha del evento.
2. Portal Académico informa al docente las exclusiones y su fundamento.
3. El flujo continúa en el paso 5 con la nómina activa.

**UC-03-A04 — Confirmación físico–digital ausente o incoherente (paso 6)**

1. Portal Académico impide la publicación.
2. Portal Académico mantiene el evento como no publicado e identifica la confirmación pendiente.
3. El docente puede reanudar el flujo en el paso 5.

**UC-03-A05 — Solicitud repetida de publicación (paso 7)**

1. Portal Académico reconoce el evento de grupo previamente publicado.
2. Portal Académico conserva una sola publicación por estudiante y no genera avisos duplicados.
3. Portal Académico proporciona al docente el resultado de la publicación existente y el caso de uso finaliza.

**UC-03-A06 — Estudiante sin encargado activo (paso 9)**

1. Portal Académico conserva el evento académico publicado para el estudiante.
2. Portal Académico registra la ausencia de destinatario y la incorpora al seguimiento institucional.
3. El flujo continúa en el paso 10 para los demás destinatarios.

**UC-03-A07 — Fallo del intento de notificación (paso 10)**

1. Portal Académico conserva la publicación académica.
2. Portal Académico registra el intento como fallido, sin acuse de consulta.
3. Portal Académico deja el aviso disponible para el tratamiento establecido en UC-05.
4. El flujo continúa en el paso 11.

---
### Prototipo

[Prototipo Stitch — Publicar evaluación](prototipos/stitch-2/screen.png)

### Requerimientos especiales

- Confirmación de congruencia con el medio físico y publicación sin duplicados.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-04 — Publicar resultado académico

### Propósito

Publicar los resultados de una evaluación devuelta al estudiantado, confirmar su correspondencia con el medio físico oficial y emitir la notificación de segunda etapa.

### Requisitos relacionados

RF-01, RF-02, RF-12, RF-13, RF-14, RF-17 y RF-18.

### Actores

- **Principal:** Docente.
- **Secundarios:** Encargado legal y estudiante como participante indirecto.

### Precondiciones

1. El docente posee una identidad institucional registrada.
2. La evaluación asignada existe y pertenece a un grupo, materia y periodo conocidos.
3. Para el flujo básico el docente está autorizado y los resultados no han sido publicados previamente.

### Postcondiciones

- **Éxito:** los resultados seleccionados quedan publicados como primera versión; se crea una notificación de segunda etapa para cada encargado autorizado y se conserva la trazabilidad.
- **Garantía mínima:** ningún valor publicado se sobrescribe; un fallo de notificación no elimina ni duplica el resultado.

### Relaciones

- Sigue normalmente a UC-03, **Publicar evaluación asignada**.
- Una modificación posterior se tramita mediante UC-09, **Corregir publicación académica**.
- Los avisos pendientes quedan sujetos a UC-05, **Gestionar avisos no consultados**.

### Flujo básico

1. El docente identifica una evaluación previamente registrada.
2. Portal Académico valida la existencia y estado de la evaluación, la identidad del docente y su asignación al grupo, materia y periodo.
3. El docente proporciona los resultados y observaciones de los estudiantes comprendidos en la publicación.
4. Portal Académico valida la escala, el formato y la integridad de los resultados proporcionados.
5. El docente registra la fecha de devolución al estudiantado y confirma que cada resultado coincide con el medio físico oficial entregado.
6. Portal Académico valida la presencia y coherencia de la confirmación físico–digital.
7. El docente autoriza la publicación de los resultados seleccionados.
8. Portal Académico publica la primera versión inmutable de cada resultado y registra su trazabilidad.
9. Portal Académico crea la notificación de segunda etapa para cada encargado con relación activa y realiza el envío configurado.
10. Portal Académico registra por destinatario el momento, el tipo de notificación y el resultado del intento.
11. Portal Académico proporciona al docente el resultado agregado de la publicación y las incidencias identificadas.

### Flujos alternos

**UC-04-A01 — Docente sin asignación autorizada (paso 2)**

1. Portal Académico rechaza la operación sin exponer ni publicar resultados.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-04-A02 — Evaluación inexistente, anulada o no publicable (paso 2)**

1. Portal Académico impide el registro de resultados para la evaluación indicada.
2. Portal Académico comunica el estado que impide continuar.
3. Portal Académico registra la actuación y el caso de uso finaliza.

**UC-04-A03 — Resultado incompleto o fuera de la escala válida (paso 4)**

1. Portal Académico identifica los resultados que requieren corrección.
2. Portal Académico no publica el conjunto seleccionado.
3. El docente puede reanudar el flujo en el paso 3.

**UC-04-A04 — Confirmación físico–digital ausente o incoherente (paso 6)**

1. Portal Académico impide la publicación de los resultados seleccionados.
2. Portal Académico conserva los datos como no publicados e identifica la confirmación pendiente.
3. El docente puede reanudar el flujo en el paso 5.

**UC-04-A05 — Resultado publicado previamente (paso 7)**

1. Portal Académico impide sobrescribir la versión publicada.
2. Portal Académico conserva íntegra la versión vigente.
3. Portal Académico remite el cambio requerido a UC-09 y el caso de uso finaliza.

**UC-04-A06 — Estudiante sin encargado activo (paso 9)**

1. Portal Académico conserva el resultado académico publicado para el estudiante.
2. Portal Académico registra la ausencia de destinatario y la incorpora al seguimiento institucional.
3. El flujo continúa en el paso 10 para los demás destinatarios.

**UC-04-A07 — Fallo del intento de notificación (paso 10)**

1. Portal Académico conserva el resultado publicado.
2. Portal Académico registra el intento como fallido, sin acuse de consulta.
3. Portal Académico deja el aviso disponible para el tratamiento establecido en UC-05.
4. El flujo continúa en el paso 11.

---
### Prototipo

[Prototipo Stitch — Publicar evaluación](prototipos/stitch-2/screen.png)

### Requerimientos especiales

- Separación entre evaluación asignada y resultado calificado; historial inmutable.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-05 — Gestionar avisos no consultados

### Propósito

Dar seguimiento a las notificaciones académicas sin acuse de consulta, emitir recordatorios controlados y elevar los casos que requieren intervención institucional.

### Requisitos relacionados

RF-02, RF-18, RF-19, RF-21 y RF-22.

### Actores

- **Principal:** Tiempo / servicio de notificaciones.
- **Secundarios:** Encargado legal, docente y Dirección.

### Precondiciones

1. Existe una notificación académica creada por UC-03, UC-04 o UC-09.
2. Portal Académico conserva su estado y sus intentos anteriores.
3. Para el flujo básico existen parámetros institucionales vigentes de recordatorio y escalamiento.

### Postcondiciones

- **Éxito:** el aviso obtiene acuse de consulta después del recordatorio y no requiere nuevas reiteraciones.
- **Garantía mínima:** cada decisión, intento y resultado queda trazado; el sistema no atribuye un acuse inexistente.

### Relaciones

- Da seguimiento a las notificaciones originadas por UC-03, UC-04 y UC-09.
- Consume la política de recordatorios administrada mediante UC-10.

### Flujo básico

1. El actor Tiempo activa la revisión al cumplirse el intervalo de seguimiento configurado.
2. Portal Académico identifica una notificación vigente que carece de acuse de consulta.
3. Portal Académico valida la vigencia de la relación entre el destinatario y el estudiante.
4. Portal Académico determina el intervalo transcurrido, el número de intentos y el límite institucional aplicable.
5. Portal Académico emite el recordatorio que corresponde al ciclo vigente.
6. Portal Académico registra el momento, número de intento y resultado del recordatorio.
7. El encargado accede al aviso o a su detalle mediante una identidad autorizada.
8. Portal Académico registra el acuse de consulta con identidad, fecha y hora.
9. Portal Académico detiene nuevas reiteraciones para ese destinatario y aviso.

### Flujos alternos

**UC-05-A01 — Aviso consultado antes de la revisión (paso 2)**

1. Portal Académico reconoce el acuse existente.
2. Portal Académico no emite recordatorios ni alertas adicionales.
3. El caso de uso finaliza.

**UC-05-A02 — Relación de acceso inactiva (paso 3)**

1. Portal Académico cancela las reiteraciones dirigidas a esa persona.
2. Portal Académico conserva el historial previo y registra el motivo de la cancelación.
3. Portal Académico incorpora la ausencia de destinatario válido al seguimiento institucional y el caso de uso finaliza.

**UC-05-A03 — Límite de recordatorios alcanzado (paso 4)**

1. Portal Académico no emite un nuevo recordatorio al destinatario.
2. Portal Académico genera una alerta de seguimiento para los roles institucionales autorizados.
3. Portal Académico registra el escalamiento y el caso de uso finaliza.

**UC-05-A04 — Parámetros ausentes o incoherentes (paso 4)**

1. Portal Académico no emite un recordatorio con reglas indeterminadas.
2. Portal Académico registra la incidencia de configuración y la comunica al responsable autorizado.
3. La notificación conserva su estado anterior y el caso de uso finaliza.

**UC-05-A05 — Fallo del recordatorio (paso 6)**

1. Portal Académico registra el intento como fallido y no crea un acuse de consulta.
2. Portal Académico conserva el aviso pendiente para el siguiente tratamiento permitido.
3. El caso de uso finaliza.

**UC-05-A06 — Aviso aún no consultado después del recordatorio (paso 7)**

1. Portal Académico conserva el estado pendiente sin crear un acuse.
2. Portal Académico mantiene el intento en el historial.
3. Un ciclo posterior continúa desde el paso 1 hasta alcanzar consulta o escalamiento.

**UC-05-A07 — Apertura posterior al primer acuse (paso 8)**

1. Portal Académico conserva la identidad, fecha y hora del primer acuse de consulta.
2. Portal Académico no reinicia recordatorios ni elimina la evidencia anterior.
3. El flujo continúa en el paso 9.

**UC-05-A08 — Revisión repetida dentro del mismo intervalo (paso 4)**

1. Portal Académico reconoce el recordatorio ya generado para el intervalo vigente.
2. Portal Académico no crea ni envía un recordatorio duplicado.
3. Portal Académico conserva el aviso pendiente para el siguiente intervalo aplicable y el caso de uso finaliza.

---
### Prototipo

[Prototipo Stitch — Panel institucional de supervisión](prototipos/stitch-4/screen.png)

### Requerimientos especiales

- Recordatorios configurables, límite de reintentos y escalamiento auditable.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-06 — Comunicarse sobre un estudiante

### Propósito

Mantener una comunicación institucional trazable entre un encargado legal y el docente autorizado, vinculada al estudiante y conservada como evidencia.

### Requisitos relacionados

RF-01, RF-02, RF-04, RF-23 y RF-24.

### Actores

- **Principales:** Encargado legal o docente.
- **Secundario:** Docente o encargado legal que recibe la comunicación.

### Precondiciones

1. El iniciador posee una identidad institucional activa.
2. Para el flujo básico existe una relación vigente entre encargado y estudiante y una asignación docente vigente.

### Postcondiciones

- **Éxito:** el mensaje y la respuesta quedan asociados al estudiante, con autoría, fecha, destinatarios e historial inmutable.
- **Garantía mínima:** ninguna persona ajena a la relación o asignación autorizada accede a la conversación; el historial previo no se sobrescribe.

### Relaciones

- Puede originarse después de UC-01.
- Una inconformidad formal sobre un registro académico se tramita mediante UC-07, no mediante la modificación de la conversación.

### Flujo básico

1. El iniciador solicita establecer una comunicación institucional asociada a un estudiante.
2. Portal Académico valida la identidad, la relación encargado–estudiante, la asignación docente y el alcance de ambos participantes.
3. El iniciador proporciona el asunto y el contenido de la comunicación.
4. Portal Académico valida que el contenido sea admisible y que el destinatario continúe autorizado.
5. Portal Académico crea el hilo con el estudiante, asunto, creador, participantes autorizados, fecha y hora.
6. Portal Académico agrega el mensaje inicial al hilo sin sustituir contenido previo.
7. El destinatario solicita consultar el hilo mediante su identidad institucional.
8. Portal Académico vuelve a validar la autorización y proporciona el historial íntegro del hilo.
9. El destinatario proporciona una respuesta asociada a la conversación.
10. Portal Académico agrega la respuesta con autor, fecha y hora sin alterar los mensajes previos.

### Flujos alternos

**UC-06-A01 — Relación o asignación no autorizada (paso 2)**

1. Portal Académico rechaza el acceso a la comunicación.
2. Portal Académico no confirma contenido ni participantes de conversaciones existentes.
3. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-06-A02 — Destinatario dejó de estar autorizado (paso 4)**

1. Portal Académico impide el envío al destinatario inactivo.
2. Portal Académico conserva la comunicación como no enviada e identifica la condición que impide continuar.
3. El caso de uso finaliza.

**UC-06-A03 — Contenido incompleto o no admisible (paso 4)**

1. Portal Académico no registra el contenido como mensaje enviado.
2. Portal Académico identifica el aspecto que requiere corrección.
3. El iniciador puede reanudar el flujo en el paso 3.

**UC-06-A04 — Ausencia de respuesta (paso 9)**

1. Portal Académico conserva la conversación y sus mensajes disponibles para los participantes autorizados.
2. Portal Académico no registra una respuesta inexistente ni altera el mensaje original.
3. La conversación permanece pendiente conforme al protocolo institucional y el caso de uso finaliza.

---
### Prototipo

[Prototipo Stitch — Mensajería institucional](prototipos/stitch-5/screen.png)

### Requerimientos especiales

- Mensajes asociados al estudiante y participantes autorizados.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-07 — Gestionar discrepancia académica

### Propósito

Registrar, atender y resolver formalmente una discrepancia sobre una publicación académica, con estados, plazos, responsables, trazabilidad y mediación institucional.

### Requisitos relacionados

RF-01, RF-02, RF-04, RF-15, RF-25, RF-26, RF-27, RF-28 y RF-29.

### Actores

- **Principal inicial:** Encargado legal.
- **Principales de atención:** Docente y Dirección o figura mediadora.

### Precondiciones

1. El encargado posee una identidad activa y una relación vigente con el estudiante.
2. Existe un registro académico publicado y elegible para discrepancia.
3. Existe un protocolo institucional con responsables y plazos vigentes.

### Postcondiciones

- **Éxito:** la discrepancia queda resuelta y cerrada con fundamento, participantes, actuaciones y fechas conservados.
- **Garantía mínima:** ninguna discrepancia modifica automáticamente el registro académico; los estados y actuaciones ya emitidos permanecen inmutables.

### Relaciones

- Puede originarse desde UC-01.
- Puede extenderse con UC-09 cuando una resolución autorizada requiere corregir una publicación.

### Flujo básico

1. El encargado identifica el registro académico objeto de discrepancia.
2. Portal Académico valida la relación de acceso, la elegibilidad del registro y el plazo de presentación.
3. El encargado proporciona el motivo, la descripción y las referencias de respaldo disponibles.
4. Portal Académico valida la información requerida y la ausencia de una discrepancia abierta equivalente.
5. Portal Académico crea el caso con identificador, estado abierto, fecha, responsable docente y plazo aplicable; luego comunica su apertura a los participantes.
6. El docente examina el registro, el planteamiento y sus antecedentes dentro del plazo establecido.
7. El docente registra una respuesta fundada y una propuesta de resolución que no altera por sí misma el registro académico.
8. Portal Académico comunica la respuesta al encargado y registra el estado como respuesta emitida.
9. El encargado registra su aceptación de la respuesta dentro del plazo aplicable.
10. Portal Académico cierra el caso como resuelto y conserva íntegro el historial.

### Flujos alternos

**UC-07-A01 — Encargado sin relación autorizada (paso 2)**

1. Portal Académico rechaza la apertura del caso sin exponer el registro académico.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-07-A02 — Registro no elegible o plazo de presentación vencido (paso 2)**

1. Portal Académico no crea una discrepancia ordinaria.
2. Portal Académico comunica el fundamento institucional aplicable y conserva trazabilidad de la solicitud.
3. El caso de uso finaliza.

**UC-07-A03 — Información requerida incompleta (paso 4)**

1. Portal Académico no abre todavía el caso formal.
2. Portal Académico identifica la información pendiente.
3. El encargado puede reanudar el flujo en el paso 3 dentro del plazo aplicable.

**UC-07-A04 — Discrepancia abierta equivalente (paso 4)**

1. Portal Académico conserva un único caso abierto.
2. Portal Académico comunica al encargado el identificador y estado del caso existente.
3. Portal Académico registra la actuación y el caso de uso finaliza.

**UC-07-A05 — Docente propone una corrección (paso 7)**

1. El docente registra el fundamento de la corrección propuesta en la discrepancia.
2. Portal Académico invoca UC-09 para crear una nueva versión de la publicación; la versión anterior permanece inmutable.
3. Portal Académico incorpora la referencia de la corrección y su resultado al caso.
4. El flujo continúa en el paso 8.

**UC-07-A06 — Vencimiento del plazo de respuesta docente (paso 6)**

1. El actor Tiempo identifica el vencimiento **[PV: tres días hábiles]**.
2. Portal Académico cambia el estado a escalado y asigna el caso a la figura mediadora autorizada.
3. La figura mediadora examina el historial completo y registra una resolución fundada que mantiene el registro vigente.
4. Portal Académico comunica la resolución, cierra el caso como resuelto por mediación y conserva el historial.

**UC-07-A07 — Rechazo de la respuesta y ratificación en mediación (paso 9)**

1. El encargado registra su disconformidad y fundamento dentro del plazo aplicable.
2. Portal Académico cambia el estado a escalado y asigna el caso a la figura mediadora autorizada.
3. La figura mediadora examina el historial y registra una resolución fundada que ratifica el registro vigente.
4. Portal Académico comunica la resolución, cierra el caso como resuelto por mediación y conserva el historial.

**UC-07-A08 — Rechazo de la respuesta y corrección ordenada en mediación (paso 9)**

1. El encargado registra su disconformidad y fundamento dentro del plazo aplicable.
2. Portal Académico cambia el estado a escalado y asigna el caso a la figura mediadora autorizada.
3. La figura mediadora examina el historial y registra una resolución fundada que requiere una corrección.
4. El docente autorizado ejecuta UC-09; la versión anterior permanece inmutable.
5. Portal Académico incorpora el resultado de la corrección al caso, comunica la resolución y cierra el caso como resuelto por mediación.

---
### Prototipo

[Prototipo Stitch — Presentar discrepancia académica](prototipos/stitch-3/screen.png)

### Requerimientos especiales

- Plazo configurable; participan docente, Comité de Evaluación y Dirección; no cambia notas automáticamente.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-08 — Consultar reportes de seguimiento

### Propósito

Permitir que Dirección consulte información consolidada de rendimiento y seguimiento escuela–hogar dentro de su ámbito autorizado.

### Requisitos relacionados

RF-01, RF-02, RF-30, RF-31 y RF-32.

### Actores

- **Principal:** Dirección.
- **Secundarios:** Ninguno.

### Precondiciones

1. El actor posee una identidad institucional activa.
2. Existen periodos, grupos y materias conocidos por Portal Académico.
3. Para el flujo básico el actor cuenta con atribución para el ámbito solicitado.

### Postcondiciones

- **Éxito:** Dirección obtiene datos consolidados y, únicamente dentro de su alcance, el detalle necesario para el seguimiento; la consulta queda trazada.
- **Garantía mínima:** no se revelan datos fuera del ámbito autorizado y los datos no publicados quedan excluidos.

### Relaciones

- Utiliza los eventos y estados generados por UC-03, UC-04, UC-05, UC-06 y UC-07.

### Flujo básico

1. Dirección solicita un reporte institucional de seguimiento.
2. Portal Académico valida la identidad, el rol y el ámbito institucional autorizado.
3. Dirección especifica periodo, grupo, materia y dimensiones requeridas entre rendimiento, consulta de avisos, comunicación y discrepancias.
4. Portal Académico valida la existencia, coherencia y alcance de los criterios.
5. Portal Académico consolida únicamente registros publicados y estados vigentes comprendidos por la autorización.
6. Portal Académico proporciona los resultados con sus definiciones, filtros y fecha de corte.
7. Dirección solicita el detalle autorizado de un resultado agregado que requiere seguimiento.
8. Portal Académico vuelve a validar el alcance para las personas y registros comprendidos por el detalle.
9. Portal Académico proporciona el detalle permitido y registra la consulta.

### Flujos alternos

**UC-08-A01 — Actor o ámbito no autorizado (paso 2)**

1. Portal Académico rechaza la consulta sin exponer resultados agregados ni individuales.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-08-A02 — Criterios inexistentes o incoherentes (paso 4)**

1. Portal Académico no genera resultados con criterios inválidos.
2. Portal Académico identifica los criterios que requieren corrección.
3. Dirección puede reanudar el flujo en el paso 3.

**UC-08-A03 — Ámbito válido sin datos publicados (paso 5)**

1. Portal Académico proporciona un resultado sin datos e identifica los criterios y la fecha de corte aplicados.
2. Portal Académico no incorpora borradores ni datos de otros ámbitos.
3. El flujo continúa en el paso 9 para registrar la consulta.

**UC-08-A04 — Solicitud de detalle fuera del alcance (paso 8)**

1. Portal Académico deniega únicamente el detalle no autorizado.
2. Portal Académico conserva disponible el resultado agregado autorizado.
3. Portal Académico registra el intento y el caso de uso finaliza.

---
### Prototipo

[Prototipo Stitch — Panel institucional de supervisión](prototipos/stitch-4/screen.png)

### Requerimientos especiales

- Reportes agregados y detalle limitado por ámbito autorizado.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-09 — Corregir publicación académica

### Propósito

Corregir una publicación académica mediante una nueva versión, conservar inmutable la versión anterior, restablecer la congruencia físico–digital y comunicar el cambio a los encargados autorizados.

### Requisitos relacionados

RF-01, RF-02, RF-13, RF-15, RF-17 y RF-18.

### Actores

- **Principal:** Docente autorizado.
- **Secundarios:** Encargado legal y Dirección o figura mediadora cuando la corrección procede de UC-07.

### Precondiciones

1. Existe una publicación académica vigente.
2. El docente posee una identidad institucional activa.
3. Para el flujo básico el docente conserva atribución sobre el grupo, materia y periodo o posee una autorización formal equivalente.

### Postcondiciones

- **Éxito:** existe una nueva versión vigente vinculada a la anterior; ambas permanecen trazables y los encargados autorizados reciben un aviso de corrección adicional a las dos etapas ordinarias.
- **Garantía mínima:** la versión previa nunca se elimina ni se sobrescribe; ninguna corrección se publica sin motivo y confirmación físico–digital.

### Relaciones

- Extiende UC-04 para cambios posteriores a la primera publicación.
- Puede ser invocado por UC-07 como consecuencia de una resolución.
- Los avisos pendientes quedan sujetos a UC-05.

### Flujo básico

1. El docente identifica la publicación académica que requiere corrección.
2. Portal Académico valida la identidad, la atribución del docente y la versión vigente del registro.
3. El docente proporciona el valor corregido, el motivo, la referencia de autorización aplicable y la fecha efectiva.
4. Portal Académico valida el formato, la existencia de un cambio efectivo, el motivo y la confirmación de que la corrección coincide con el medio físico oficial.
5. Portal Académico crea una nueva versión vinculada a la anterior y conserva la versión previa inmutable.
6. Portal Académico establece la nueva versión como vigente y registra autoría, fecha, motivo y relación entre versiones.
7. Portal Académico crea un aviso de corrección para cada encargado con relación activa y realiza el envío configurado.
8. Portal Académico registra por destinatario el momento, el tipo de aviso y el resultado del intento.
9. Portal Académico incorpora la referencia de la corrección a cualquier discrepancia relacionada y proporciona al docente el resultado agregado.

### Flujos alternos

**UC-09-A01 — Docente sin atribución autorizada (paso 2)**

1. Portal Académico rechaza la corrección sin exponer información adicional ni alterar versiones.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-09-A02 — Versión vigente diferente de la examinada por el docente (paso 2)**

1. Portal Académico no aplica la corrección sobre una versión desactualizada.
2. Portal Académico conserva todas las versiones existentes sin cambio.
3. Portal Académico comunica la necesidad de examinar la versión vigente y el caso de uso finaliza.

**UC-09-A03 — Dato inválido, motivo ausente o valor sin cambio efectivo (paso 4)**

1. Portal Académico no crea una nueva versión.
2. Portal Académico identifica el dato que requiere corrección o la ausencia de cambio efectivo.
3. El docente puede reanudar el flujo en el paso 3.

**UC-09-A04 — Confirmación físico–digital ausente o incoherente (paso 4)**

1. Portal Académico impide publicar la corrección.
2. Portal Académico conserva la versión vigente y mantiene la propuesta como no publicada.
3. El docente puede reanudar el flujo en el paso 3 después de regularizar el medio físico oficial.

**UC-09-A05 — Estudiante sin encargado activo (paso 7)**

1. Portal Académico conserva la nueva versión vigente y su historial.
2. Portal Académico registra la ausencia de destinatario y la incorpora al seguimiento institucional.
3. El flujo continúa en el paso 8 para los demás destinatarios.

**UC-09-A06 — Fallo del intento de aviso de corrección (paso 8)**

1. Portal Académico conserva la nueva versión y la versión previa.
2. Portal Académico registra el intento como fallido, sin acuse de consulta.
3. Portal Académico deja el aviso disponible para el tratamiento establecido en UC-05.
4. El flujo continúa en el paso 9.

---
### Prototipo

[Prototipo Stitch — Publicar evaluación y resultado](prototipos/stitch-2/screen.png)

### Requerimientos especiales

- Corrección versionada sin sobrescribir ni eliminar el resultado anterior.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-10 — Administrar configuración y accesos

### Propósito

Mantener los catálogos académicos, las cuentas y roles institucionales y la política de recordatorios, conservando el historial y aplicando mínimo privilegio.

### Requisitos relacionados

RF-01, RF-02, RF-20, RF-33 y RF-34.

### Actores

- **Principal:** Administrador institucional.
- **Secundario:** Dirección como autoridad de la política institucional.

### Precondiciones

1. El administrador posee una identidad institucional registrada.
2. Para el flujo básico la cuenta tiene atribución administrativa vigente sobre el tipo de configuración solicitado.
3. La institución ha definido los catálogos, roles y límites de política que puede aprobar.

### Postcondiciones

- **Éxito:** el cambio autorizado queda vigente desde la fecha aplicable, con estado anterior, estado nuevo, actor y momento conservados.
- **Garantía mínima:** ningún catálogo con historia se elimina, ninguna cuenta obtiene privilegios no autorizados y una política inválida no afecta los avisos.

### Relaciones

- Proporciona los catálogos, cuentas y roles utilizados por UC-01 a UC-09.
- La política de recordatorios configurada es consumida por UC-05.
- Las actuaciones administrativas pueden consultarse mediante UC-11.

### Flujo básico

1. El administrador solicita efectuar un cambio de configuración institucional.
2. Portal Académico valida la identidad, el rol y el alcance administrativo del actor.
3. El administrador identifica el tipo de cambio, el elemento afectado, los datos propuestos y su fecha de vigencia.
4. Portal Académico valida que el tipo de cambio sea admitido y que los datos requeridos estén completos.
5. Portal Académico valida las reglas institucionales aplicables al catálogo, cuenta, rol o política de recordatorios indicados.
6. Portal Académico aplica el cambio autorizado sin eliminar referencias históricas.
7. Portal Académico conserva el estado anterior, el estado nuevo, el actor, la fecha y el fundamento aplicable.
8. Portal Académico proporciona el resultado y la vigencia efectiva del cambio.

### Flujos alternos

**UC-10-A01 — Actor sin atribución administrativa (paso 2)**

1. Portal Académico rechaza la operación sin exponer configuración protegida.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-10-A02 — Tipo de cambio no admitido o datos incompletos (paso 4)**

1. Portal Académico no aplica modificación alguna.
2. Portal Académico identifica el tipo o los datos que requieren corrección.
3. El administrador puede reanudar el flujo en el paso 3.

**UC-10-A03 — Desactivación de un elemento de catálogo con historia (paso 6)**

1. Portal Académico cambia el elemento académico a estado inactivo desde la fecha autorizada.
2. Portal Académico impide utilizarlo en registros nuevos.
3. Portal Académico conserva los registros históricos que lo referencian.
4. El flujo continúa en el paso 7.

**UC-10-A04 — Suspensión de cuenta o cambio de roles (paso 6)**

1. Portal Académico aplica el nuevo estado o conjunto de roles autorizado.
2. El nuevo alcance surte efecto en el siguiente control de autorización.
3. Portal Académico conserva el estado y los roles anteriores para trazabilidad.
4. El flujo continúa en el paso 7.

**UC-10-A05 — Asignación de privilegio no autorizada (paso 5)**

1. Portal Académico rechaza la asignación de rol o alcance solicitado.
2. Portal Académico conserva sin cambios la cuenta y sus roles vigentes.
3. Portal Académico registra el intento y el caso de uso finaliza.

**UC-10-A06 — Política de recordatorios incoherente (paso 5)**

1. Portal Académico rechaza la política propuesta.
2. Portal Académico conserva vigente la política anterior.
3. Portal Académico identifica los valores que incumplen las reglas institucionales y el caso de uso finaliza.

---
### Prototipo

[Prototipo Stitch — Panel institucional de supervisión](prototipos/stitch-4/screen.png)

### Requerimientos especiales

- Solo administración autorizada; configuración con historial de cambios.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## UC-11 — Consultar bitácora de auditoría

### Propósito

Permitir que una persona con atribución de auditoría reconstruya las actuaciones sobre una entidad sin modificar la bitácora ni ampliar su acceso a información protegida.

### Requisitos relacionados

RF-01, RF-02 y RF-35.

### Actores

- **Principal:** Auditor institucional autorizado.
- **Secundario:** Dirección, únicamente cuando posee el permiso de auditoría correspondiente.

### Precondiciones

1. El actor posee una identidad institucional activa.
2. Existen actuaciones registradas por Portal Académico.
3. Para el flujo básico el actor posee permiso de auditoría sobre el tipo de entidad y ámbito solicitados.

### Postcondiciones

- **Éxito:** el actor obtiene una secuencia de actuaciones autorizadas con entidad, acción, usuario, fecha y referencia al cambio; la consulta queda registrada.
- **Garantía mínima:** la bitácora permanece inmutable y no se revela el detalle de entidades fuera del alcance del actor.

### Relaciones

- Consulta actuaciones originadas en UC-01 a UC-10.

### Flujo básico

1. El actor solicita consultar la bitácora de auditoría.
2. Portal Académico valida la identidad, el permiso de auditoría y el ámbito institucional del actor.
3. El actor especifica el tipo e identificador de entidad, intervalo de fechas y filtros de acción o usuario requeridos.
4. Portal Académico valida la coherencia de los criterios y su pertenencia al ámbito autorizado.
5. Portal Académico obtiene las actuaciones registradas que cumplen los criterios autorizados.
6. Portal Académico proporciona las actuaciones en orden temporal, con entidad, acción, usuario, fecha y referencia al cambio, además de los filtros aplicados.
7. El actor solicita el detalle de una referencia incluida en el resultado.
8. Portal Académico valida el permiso sobre la entidad subyacente y proporciona el detalle permitido en modo de consulta.
9. Portal Académico registra la consulta de auditoría efectuada.

### Flujos alternos

**UC-11-A01 — Actor sin permiso de auditoría (paso 2)**

1. Portal Académico rechaza la consulta sin revelar la existencia o contenido de actuaciones protegidas.
2. Portal Académico registra el intento denegado y el caso de uso finaliza.

**UC-11-A02 — Filtros incompletos o incoherentes (paso 4)**

1. Portal Académico no ejecuta una consulta con criterios inválidos.
2. Portal Académico identifica los criterios que requieren corrección.
3. El actor puede reanudar el flujo en el paso 3.

**UC-11-A03 — Entidad fuera del ámbito autorizado (paso 4)**

1. Portal Académico deniega la consulta para la entidad indicada.
2. Portal Académico no confirma contenido ni actuaciones de la entidad protegida.
3. Portal Académico registra el intento y el caso de uso finaliza.

**UC-11-A04 — Consulta válida sin actuaciones (paso 5)**

1. Portal Académico proporciona un resultado vacío con los filtros y el intervalo aplicados.
2. Portal Académico no incorpora actuaciones de otras entidades o ámbitos.
3. El flujo continúa en el paso 9.

**UC-11-A05 — Detalle subyacente fuera del permiso (paso 8)**

1. Portal Académico conserva visible únicamente la información de auditoría permitida.
2. Portal Académico deniega el detalle protegido sin ampliar el alcance del actor.
3. Portal Académico registra la solicitud denegada y el caso de uso finaliza.

**UC-11-A06 — Solicitud de modificar o eliminar la bitácora (paso 7)**

1. Portal Académico rechaza la operación de cambio.
2. Portal Académico conserva inalteradas todas las actuaciones registradas.
3. Portal Académico registra el intento denegado y el caso de uso finaliza.

---
### Prototipo

[Prototipo Stitch — Panel institucional de supervisión](prototipos/stitch-4/screen.png)

### Requerimientos especiales

- Bitácora de solo consulta; no permite modificar ni eliminar actuaciones.
- Los mensajes de error deben indicar la corrección posible sin revelar información no autorizada.

### Autor

Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco

## 3. Valores provisionales por validar

| Identificador | Valor provisional |
|---|---|
| PV-01 | Primer recordatorio de aviso no consultado: 48 horas después del aviso inicial. |
| PV-02 | Segundo recordatorio: 72 horas después del primer recordatorio. |
| PV-03 | Límite ordinario: dos recordatorios antes de elevar una alerta institucional. |
| PV-04 | Plazo para presentar una discrepancia: diez días hábiles desde la publicación cuestionada. |
| PV-05 | Plazo de respuesta docente a una discrepancia: tres días hábiles. |
| PV-06 | Canales iniciales de aviso: aviso dentro de Portal Académico y correo institucional; deben validarse disponibilidad, consentimiento y costo. |

Estos valores no constituyen reglas definitivas hasta su validación con Dirección y personal docente.
