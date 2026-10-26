# Portal Académico — Guion de pruebas de aceptación

## 1. Objetivo

Este guion verifica el flujo básico y **todos los flujos alternos** de los once casos definidos en `06-casos-de-uso-detallados.md`: 11 flujos principales y 66 alternos, para un total de 77 pruebas. Las pruebas expresan resultados de negocio observables; no prescriben interfaz ni diseño interno.

Cada prueba debe comprobar también:

- mínimo privilegio y ausencia de exposición de datos no autorizados;
- trazabilidad de actor, acción, fecha y resultado;
- diferenciación entre aviso enviado, entregado cuando sea comprobable, consultado y fallido;
- conservación inmutable de publicaciones, mensajes y actuaciones previas;
- correspondencia entre la publicación académica y el medio físico oficial;
- independencia entre el resultado académico y el resultado de su notificación.

## 2. Datos base y valores provisionales

Los identificadores siguientes representan datos ficticios de prueba.

| Dato | Valor de referencia |
|---|---|
| Institución | Escuela Primaria de Prueba |
| Periodo | II periodo 2026 |
| Grupo y materia | 4-A, Matemática |
| Docente autorizado | DOC-01, asignado a 4-A Matemática |
| Docente no asignado | DOC-99 |
| Administrador | ADM-01 |
| Dirección / mediación | DIR-01 |
| Encargado autorizado | ENC-01, relación activa con EST-01 |
| Encargado sin relación | ENC-99 |
| Estudiante activo | EST-01 |
| Segundo estudiante activo vinculado | EST-04 |
| Estudiante sin encargado activo | EST-02 |
| Estudiante retirado | EST-03 |
| Evaluación | EVA-01, asignada el 10-09-2026 y devuelta el 15-09-2026 |
| Escala de calificación | 0 a 100 **[PV-T01: por validar con la escuela]** |
| Medio físico | Cuaderno de comunicaciones **[PV-T02: confirmar el medio oficial real]** |

Valores de tiempo adoptados únicamente para las pruebas:

- Primer recordatorio: 48 horas, **[PV-01]**.
- Segundo recordatorio: 72 horas después del primero, **[PV-02]**.
- Escalamiento después de dos recordatorios, **[PV-03]**.
- Plazo de presentación de discrepancia: diez días hábiles, **[PV-04]**.
- Plazo de respuesta docente: tres días hábiles, **[PV-05]**.
- Portal Académico y correo institucional como canales iniciales, **[PV-06]**.

Todos los valores **[PV]** requieren validación antes de ejecutar una aceptación definitiva.

## 2.1 Archivos Gherkin entregables

Los escenarios también se entregan en archivos `.feature`, conforme a la plantilla del curso:

- [uc-01-consultar-progreso-académico.feature](features/uc-01-consultar-progreso-académico.feature)
- [uc-02-vincular-encargado-con-estudiante.feature](features/uc-02-vincular-encargado-con-estudiante.feature)
- [uc-03-publicar-evaluación-asignada.feature](features/uc-03-publicar-evaluación-asignada.feature)
- [uc-04-publicar-resultado-académico.feature](features/uc-04-publicar-resultado-académico.feature)
- [uc-05-gestionar-avisos-no-consultados.feature](features/uc-05-gestionar-avisos-no-consultados.feature)
- [uc-06-comunicarse-sobre-un-estudiante.feature](features/uc-06-comunicarse-sobre-un-estudiante.feature)
- [uc-07-gestionar-discrepancia-académica.feature](features/uc-07-gestionar-discrepancia-académica.feature)
- [uc-08-consultar-reportes-de-seguimiento.feature](features/uc-08-consultar-reportes-de-seguimiento.feature)
- [uc-09-corregir-publicación-académica.feature](features/uc-09-corregir-publicación-académica.feature)
- [uc-10-administrar-configuración-y-accesos.feature](features/uc-10-administrar-configuración-y-accesos.feature)
- [uc-11-consultar-bitácora-de-auditoría.feature](features/uc-11-consultar-bitácora-de-auditoría.feature)

## 3. Pruebas de UC-01 — Consultar progreso académico

**Requisitos verificados:** RF-01, RF-02, RF-04, RF-05, RF-06 y RF-07.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC01-FB | Flujo básico | ENC-01 activo; relaciones vigentes con EST-01 y EST-04; existen publicaciones del II periodo. | Solicitar acceso como ENC-01; cambiar el contexto de EST-04 a EST-01; indicar periodo y materia; consultar una evaluación publicada. | La selección deja toda consulta en el contexto de EST-01; se proporciona solo su perfil e historial autorizado, incluido el detalle vigente; el acceso queda trazado. |
| TP-UC01-A01 | UC-01-A01, paso 2 | Cuenta ENC-01 inactiva o credencial inválida. | Solicitar acceso con la identidad no habilitada. | El acceso se rechaza sin datos académicos; el intento y resultado quedan trazados. |
| TP-UC01-A02 | UC-01-A02, paso 3 | Encargado activo sin relaciones vigentes. | Solicitar acceso y consulta de estudiantes. | Se informa ausencia de estudiantes habilitados; no se revelan perfiles ni borradores; la consulta queda trazada. |
| TP-UC01-A03 | UC-01-A03, paso 6 | ENC-01 relacionado solo con EST-01; existe EST-02. | Solicitar directamente información de EST-02. | La solicitud se deniega sin confirmar información académica de EST-02; el intento queda trazado. |
| TP-UC01-A04 | UC-01-A04, paso 7 | Relación ENC-01–EST-01 vigente; periodo autorizado sin publicaciones. | Consultar EST-01 en el periodo sin publicaciones. | Se entrega un resultado vacío con estudiante y periodo correctos; no aparecen borradores; el acceso queda trazado. |

## 4. Pruebas de UC-02 — Vincular encargado con estudiante

**Requisitos verificados:** RF-01, RF-02 y RF-03.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC02-FB | Flujo básico | ADM-01 autorizado; ENC-01 y EST-01 activos; respaldo institucional completo; no existe relación equivalente. | Registrar tipo de relación, vigencia y referencia del respaldo; solicitar activación. | Se crea una relación activa con alcance y vigencia definidos; ENC-01 recibe constancia; toda la actuación queda trazada. |
| TP-UC02-A01 | UC-02-A01, paso 2 | Usuario activo sin rol administrativo. | Solicitar la vinculación ENC-01–EST-01. | La solicitud se rechaza; no se crea ni modifica una relación; el intento queda trazado. |
| TP-UC02-A02 | UC-02-A02, paso 4 | ADM-01 autorizado; encargado inexistente o EST-03 institucionalmente inactivo. | Proporcionar las identidades y solicitar vinculación. | Se identifica la condición que impide continuar; no se crea relación y la actuación queda trazada. |
| TP-UC02-A03 | UC-02-A03, paso 4 | Ya existe una relación activa ENC-01–EST-01 con el mismo alcance. | Repetir la solicitud equivalente. | Se conserva una única relación, se comunica su vigencia y no se duplica el acceso. |
| TP-UC02-A04 | UC-02-A04, paso 4 | Existe una restricción institucional documentada sobre la autorización. | Solicitar vinculación con la relación restringida. | La relación no se activa; la solicitud queda pendiente de autoridad competente con motivo y responsable trazados. |
| TP-UC02-A05 | UC-02-A05, paso 6 | Identidades activas; falta la referencia del respaldo requerido. | Solicitar activación con verificación incompleta. | No se activa la relación; se identifican los datos pendientes y se conserva la solicitud para continuación posterior. |
| TP-UC02-A06 | UC-02-A06, paso 7 | Relación ENC-01–EST-01 activa; ADM-01 posee respaldo para suspenderla o finalizarla. | Solicitar el cambio de estado con fecha y motivo autorizados. | El nuevo estado rige en el siguiente control de acceso; se conserva el estado anterior, responsable, fecha y motivo; ENC-01 recibe constancia. |

## 5. Pruebas de UC-03 — Publicar evaluación asignada

**Requisitos verificados:** RF-01, RF-02, RF-08, RF-09, RF-10, RF-11, RF-16 y RF-18.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC03-FB | Flujo básico | DOC-01 asignado a 4-A; EST-01 activo; ENC-01 vinculado; evaluación aún no publicada. | Registrar EVA-01, fechas e instrucciones; confirmar entrega al estudiantado y correspondencia con el medio físico; autorizar publicación. | Se publica una definición común y una instancia por estudiante activo, identificada como entregada para realización y todavía sin calificación; se genera únicamente la notificación de primera etapa; quedan estados, incidencias y trazabilidad. |
| TP-UC03-A01 | UC-03-A01, paso 2 | DOC-99 activo pero no asignado a 4-A Matemática. | Solicitar publicación de EVA-01 para 4-A. | La operación se rechaza; no se publica ningún evento ni aviso y el intento queda trazado. |
| TP-UC03-A02 | UC-03-A02, paso 4 | DOC-01 autorizado; fecha de vencimiento anterior a la entrega o falta un dato requerido. | Proporcionar EVA-01 con datos incoherentes; solicitar continuación. | Se identifican los datos por corregir; el evento permanece no publicado y no se generan avisos. |
| TP-UC03-A03 | UC-03-A03, paso 4 | Nómina de 4-A contiene EST-01 activo y EST-03 retirado antes del evento. | Registrar y publicar EVA-01 para el grupo. | EST-03 queda excluido con fundamento comunicado; la publicación continúa para estudiantes activos y la exclusión queda trazada. |
| TP-UC03-A04 | UC-03-A04, paso 6 | Datos académicos válidos; falta confirmación de registro o entrega en el medio físico. | Solicitar publicación sin la confirmación físico–digital. | La publicación se bloquea; el evento permanece no publicado; no se crean avisos y se identifica la confirmación pendiente. |
| TP-UC03-A05 | UC-03-A05, paso 7 | EVA-01 y sus avisos de primera etapa ya fueron publicados. | Repetir la autorización de publicación con el mismo origen de grupo. | Se conserva una instancia por estudiante y una notificación de primera etapa por destinatario; no aparecen duplicados. |
| TP-UC03-A06 | UC-03-A06, paso 9 | EST-01 tiene ENC-01; EST-02 no tiene encargado activo; ambos están en 4-A. | Publicar EVA-01 para el grupo con confirmación físico–digital válida. | Ambos eventos académicos quedan publicados; ENC-01 recibe aviso; EST-02 queda reportado sin destinatario para seguimiento institucional. |
| TP-UC03-A07 | UC-03-A07, paso 10 | Evento válido y publicado; el intento de aviso a ENC-01 produce fallo controlado. | Ejecutar la publicación y registrar el resultado fallido del aviso. | El evento permanece publicado; el aviso queda fallido, sin acuse; queda disponible para UC-05 y no se duplica la evaluación. |

## 6. Pruebas de UC-04 — Publicar resultado académico

**Requisitos verificados:** RF-01, RF-02, RF-12, RF-13, RF-14, RF-17 y RF-18.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC04-FB | Flujo básico | EVA-01 existe; DOC-01 autorizado; resultados inéditos; devolución física confirmable. | Registrar EST-01 = 85 **[PV-T01]**, observación y fecha de devolución; confirmar correspondencia física; autorizar publicación. | Se crea la primera versión inmutable del resultado y la notificación de segunda etapa; la notificación de asignación permanece diferenciada; todo queda trazado. |
| TP-UC04-A01 | UC-04-A01, paso 2 | DOC-99 no está asignado al grupo. | Solicitar publicación de resultados de EVA-01. | La operación se rechaza sin exponer ni modificar resultados; el intento queda trazado. |
| TP-UC04-A02 | UC-04-A02, paso 2 | Evaluación inexistente o EVA-01 marcada como anulada. | Solicitar registro de resultados. | No se admiten resultados ni avisos; se comunica el estado que impide continuar y queda trazabilidad. |
| TP-UC04-A03 | UC-04-A03, paso 4 | DOC-01 autorizado; escala provisional 0–100. | Proporcionar resultado 120 o dejar un resultado requerido incompleto. | No se publica el conjunto seleccionado; los valores de trabajo permanecen en borrador, se identifica cada valor por corregir y no se generan avisos. |
| TP-UC04-A04 | UC-04-A04, paso 6 | Resultados válidos; falta confirmación de devolución o de coincidencia con el medio físico. | Solicitar publicación de resultados. | Los datos permanecen no publicados; no se genera segunda notificación; se identifica la confirmación pendiente. |
| TP-UC04-A05 | UC-04-A05, paso 7 | EST-01 ya posee una versión publicada para EVA-01. | Intentar publicar un valor diferente mediante UC-04. | No se sobrescribe el valor vigente; se conserva la versión existente y el cambio se remite a UC-09. |
| TP-UC04-A06 | UC-04-A06, paso 9 | EST-02 tiene resultado válido y carece de encargado activo. | Publicar el resultado con confirmación físico–digital válida. | El resultado queda publicado; no se inventa destinatario; la ausencia se incorpora al seguimiento institucional. |
| TP-UC04-A07 | UC-04-A07, paso 10 | Resultado publicado; el intento de segunda notificación falla. | Registrar el resultado fallido del aviso. | El resultado permanece publicado; la notificación queda fallida y sin acuse; queda disponible para UC-05. |

## 7. Pruebas de UC-05 — Gestionar avisos no consultados

**Requisitos verificados:** RF-02, RF-18, RF-19, RF-21 y RF-22. Este caso consume la política creada mediante RF-20 en UC-10, pero no la administra.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC05-FB | Flujo básico | Aviso vigente a ENC-01 sin acuse; relación activa; han transcurrido 48 horas **[PV-01]**; cero recordatorios previos. | Activar revisión temporal; emitir primer recordatorio; registrar intento; ENC-01 consulta el aviso. | Se registra el recordatorio y luego un acuse con identidad, fecha y hora; cesan nuevas reiteraciones para ese aviso y destinatario. |
| TP-UC05-A01 | UC-05-A01, paso 2 | Aviso con acuse registrado antes del ciclo temporal. | Activar revisión al cumplirse el intervalo. | No se emite recordatorio ni alerta; el acuse previo permanece inmutable. |
| TP-UC05-A02 | UC-05-A02, paso 3 | Aviso pendiente; la relación ENC-01–EST-01 terminó antes del ciclo. | Activar revisión temporal. | Se cancelan reiteraciones para ENC-01; se conserva el historial; la falta de destinatario válido pasa a seguimiento institucional. |
| TP-UC05-A03 | UC-05-A03, paso 4 | Aviso sin acuse; constan dos recordatorios **[PV-03]** y venció el intervalo aplicable. | Activar un nuevo ciclo de revisión. | No se emite tercer recordatorio; la alerta autorizada identifica estudiante, destinatario, publicación de origen, intentos y antigüedad; queda trazado el escalamiento. |
| TP-UC05-A04 | UC-05-A04, paso 4 | Aviso pendiente; no existe intervalo vigente o los límites se contradicen. | Activar revisión temporal. | No se emite recordatorio; se registra y comunica la incidencia al responsable autorizado; el aviso conserva su estado. |
| TP-UC05-A05 | UC-05-A05, paso 6 | Aviso elegible para recordatorio; el intento produce fallo controlado. | Activar revisión y registrar el fallo. | El intento queda fallido, sin acuse; el aviso continúa pendiente para el siguiente tratamiento permitido. |
| TP-UC05-A06 | UC-05-A06, paso 7 | Primer recordatorio emitido correctamente; ENC-01 no consulta durante las siguientes 72 horas **[PV-02]**. | Cerrar el ciclo sin consulta y activar el ciclo posterior. | No se crea acuse; el primer intento permanece en historial; el segundo ciclo emite el recordatorio correspondiente o escala al alcanzar el límite. |
| TP-UC05-A07 | UC-05-A07, paso 8 | Un aviso ya posee primer acuse de ENC-01 con fecha y hora. | ENC-01 vuelve a consultar el mismo aviso. | Se conserva intacto el primer acuse; no se reinician recordatorios ni se elimina evidencia anterior. |
| TP-UC05-A08 | UC-05-A08, paso 4 | Ya se generó el recordatorio del intervalo vigente; el aviso continúa sin acuse. | Activar otra revisión dentro del mismo intervalo. | No se crea ni envía otro recordatorio; existe un solo intento para el intervalo y el aviso queda pendiente del siguiente. |

## 8. Pruebas de UC-06 — Comunicarse sobre un estudiante

**Requisitos verificados:** RF-01, RF-02, RF-04, RF-23 y RF-24.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC06-FB | Flujo básico | ENC-01 relacionado con EST-01; DOC-01 asignado y ambos activos. | ENC-01 registra un mensaje sobre EST-01; DOC-01 lo consulta y responde. | Mensaje, consulta y respuesta quedan vinculados a EST-01 con autor, destinatario y fecha; el contenido previo permanece inmutable. |
| TP-UC06-A01 | UC-06-A01, paso 2 | ENC-99 carece de relación con EST-01 o DOC-99 carece de asignación. | Solicitar acceso o envío en una conversación sobre EST-01. | Se rechaza el acceso sin confirmar contenido ni participantes; el intento queda trazado. |
| TP-UC06-A02 | UC-06-A02, paso 4 | La relación era válida al iniciar; el destinatario dejó de estar autorizado antes del envío. | Proporcionar el mensaje y solicitar su envío. | El mensaje no se envía; se conserva como no enviado y se comunica la condición de autorización. |
| TP-UC06-A03 | UC-06-A03, paso 4 | Participantes autorizados. | Proporcionar asunto vacío o contenido que incumple las reglas institucionales **[PV: reglas por validar]**. | No se registra un mensaje enviado; se identifica la corrección requerida y no se notifica al destinatario. |
| TP-UC06-A04 | UC-06-A04, paso 9 | Mensaje enviado y consultado por DOC-01; no existe respuesta. | Finalizar el periodo de atención previsto sin registrar respuesta. | La conversación conserva mensaje y evidencia de consulta; no aparece una respuesta ficticia ni se altera el original; queda pendiente según protocolo. |

## 9. Pruebas de UC-07 — Gestionar discrepancia académica

**Requisitos verificados:** RF-01, RF-02, RF-04, RF-15, RF-25, RF-26, RF-27, RF-28 y RF-29.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC07-FB | Flujo básico | ENC-01 autorizado; resultado de EVA-01 publicado hace menos de diez días hábiles **[PV-04]**; no existe caso equivalente. | ENC-01 presenta motivo y respaldo; DOC-01 responde dentro de tres días hábiles **[PV-05]** sin alterar la nota; ENC-01 acepta. | Se crea un identificador y plazo; quedan apertura, respuesta y aceptación; el caso cierra resuelto y el resultado académico no cambia. |
| TP-UC07-A01 | UC-07-A01, paso 2 | ENC-99 sin relación con EST-01. | Intentar abrir discrepancia sobre el resultado de EVA-01 de EST-01. | La apertura se rechaza sin exponer el registro; el intento queda trazado. |
| TP-UC07-A02 | UC-07-A02, paso 2 | Registro no elegible o publicación con antigüedad mayor a diez días hábiles **[PV-04]**. | Solicitar apertura ordinaria. | No se crea caso ordinario; se comunica el fundamento aplicable y se conserva trazabilidad de la solicitud. |
| TP-UC07-A03 | UC-07-A03, paso 4 | ENC-01 autorizado; faltan motivo o descripción requeridos. | Solicitar apertura con información incompleta. | El caso formal todavía no se abre; se identifican datos pendientes; no se altera el registro académico. |
| TP-UC07-A04 | UC-07-A04, paso 4 | Ya existe una discrepancia abierta de ENC-01 sobre el mismo registro y motivo. | Repetir la solicitud equivalente. | Se conserva un único caso; se proporciona su identificador y estado; no se duplican plazos ni notificaciones. |
| TP-UC07-A05 | UC-07-A05, paso 7 | Caso abierto; DOC-01 concluye que el resultado publicado requiere corrección. | Registrar fundamento; ejecutar UC-09; continuar la respuesta de la discrepancia. | UC-09 crea una nueva versión; la anterior permanece; el caso referencia ambas y continúa desde la respuesta emitida. |
| TP-UC07-A06 | UC-07-A06, paso 6 | Caso abierto; transcurren tres días hábiles **[PV-05]** sin respuesta docente. | Activar control de vencimiento; asignar DIR-01; DIR-01 registra resolución que mantiene el resultado. | El caso pasa a escalado, la mediación y su fundamento quedan trazados, se comunica la resolución y se cierra sin cambio automático de nota. |
| TP-UC07-A07 | UC-07-A07, paso 9 | DOC-01 emitió respuesta; ENC-01 registra rechazo fundado dentro del plazo. | Escalar a DIR-01; registrar mediación que ratifica el resultado. | El caso queda resuelto por mediación; la ratificación se comunica; el resultado permanece y todo el historial se conserva. |
| TP-UC07-A08 | UC-07-A08, paso 9 | DOC-01 emitió respuesta; ENC-01 la rechaza; DIR-01 determina una corrección fundada. | Escalar; registrar resolución; DOC-01 ejecuta UC-09; asociar la nueva versión y cerrar. | La versión anterior permanece inmutable, la nueva queda vigente, el caso referencia la corrección y cierra resuelto por mediación. |

## 10. Pruebas de UC-08 — Consultar reportes de seguimiento

**Requisitos verificados:** RF-01, RF-02, RF-30, RF-31 y RF-32.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC08-FB | Flujo básico | DIR-01 autorizado para 4-A; existen resultados publicados, estados de avisos y casos del II periodo. | Solicitar rendimiento, consulta de avisos, comunicación y discrepancias de 4-A; pedir detalle autorizado de un indicador. | Se proporcionan agregados con filtros, definiciones y fecha de corte y solo el detalle permitido; la consulta queda trazada. |
| TP-UC08-A01 | UC-08-A01, paso 2 | Usuario sin rol de Dirección o DIR-01 fuera del ámbito solicitado. | Solicitar reporte de un grupo no autorizado. | Se rechaza la consulta sin revelar agregados ni detalles; el intento queda trazado. |
| TP-UC08-A02 | UC-08-A02, paso 4 | DIR-01 autorizado. | Solicitar periodo inexistente o criterios contradictorios. | No se generan resultados; se identifican los criterios por corregir y no se mezclan ámbitos. |
| TP-UC08-A03 | UC-08-A03, paso 5 | Ámbito y criterios válidos; no existen publicaciones en el periodo. | Solicitar reporte. | Se proporciona resultado vacío con criterios y fecha de corte; no aparecen borradores ni datos de otros ámbitos; la consulta queda trazada. |
| TP-UC08-A04 | UC-08-A04, paso 8 | DIR-01 posee acceso agregado a 4-A pero no al detalle individual solicitado. | Obtener agregado permitido y solicitar detalle fuera del alcance. | El detalle se deniega; el agregado autorizado permanece disponible; el intento queda trazado sin exposición individual. |

## 11. Pruebas de UC-09 — Corregir publicación académica

**Requisitos verificados:** RF-01, RF-02, RF-13, RF-15, RF-17 y RF-18.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC09-FB | Flujo básico | Resultado vigente v1 de EST-01 = 85; DOC-01 autorizado; corrección física documentada. | Proporcionar valor 88, motivo y referencia; confirmar correspondencia con medio físico; autorizar corrección. | Se crea v2 = 88 vinculada a v1; v1 permanece inmutable; v2 queda vigente; se genera un aviso de corrección adicional y queda trazabilidad completa. |
| TP-UC09-A01 | UC-09-A01, paso 2 | DOC-99 no tiene atribución sobre 4-A. | Intentar corregir el resultado de EST-01. | La corrección se rechaza; ninguna versión cambia y el intento queda trazado. |
| TP-UC09-A02 | UC-09-A02, paso 2 | DOC-01 examinó v1; otra actuación autorizada creó v2 antes de su solicitud. | Intentar aplicar una corrección basada en v1. | No se crea una versión sobre el estado desactualizado; v1 y v2 permanecen; se exige examinar la versión vigente. |
| TP-UC09-A03 | UC-09-A03, paso 4 | DOC-01 autorizado; resultado vigente = 85. | Proporcionar 120 **[PV-T01]**, omitir motivo o volver a proporcionar 85. | No se crea una versión; se identifica el dato inválido, motivo ausente o falta de cambio efectivo. |
| TP-UC09-A04 | UC-09-A04, paso 4 | Corrección propuesta válida; falta confirmación de su reflejo en el medio físico oficial. | Solicitar publicación de la corrección. | La versión vigente se conserva; la propuesta permanece no publicada; no se genera aviso de corrección. |
| TP-UC09-A05 | UC-09-A05, paso 7 | Corrección válida para EST-02, que carece de encargado activo. | Publicar la nueva versión con confirmación físico–digital válida. | La nueva versión queda vigente y la previa permanece; no se inventa destinatario; la ausencia pasa a seguimiento institucional. |
| TP-UC09-A06 | UC-09-A06, paso 8 | Corrección publicada; el intento de aviso produce fallo controlado. | Registrar el resultado fallido del aviso. | Ambas versiones permanecen; el aviso queda fallido y sin acuse; queda disponible para UC-05 y no se repite la corrección. |

## 12. Pruebas de UC-10 — Administrar configuración y accesos

**Requisitos verificados:** RF-01, RF-02, RF-20, RF-33 y RF-34.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC10-FB | Flujo básico | ADM-01 activo y autorizado; catálogos y política base conocidos. | En ejecuciones independientes, crear y luego actualizar un elemento académico válido; crear o activar una cuenta y asignar un rol permitido; guardar dos recordatorios a 48 y 72 horas con alerta posterior **[PV-01 a PV-03]**. | Cada cambio queda vigente con actor, fecha, estado anterior y nuevo; el catálogo actualizado puede usarse, la cuenta recibe solo el rol permitido y la política se aplica a avisos nuevos. |
| TP-UC10-A01 | UC-10-A01, paso 2 | Usuario activo sin atribución administrativa. | Solicitar un cambio de catálogo, cuenta o política. | La operación se rechaza sin exponer configuración protegida; nada cambia y el intento queda trazado. |
| TP-UC10-A02 | UC-10-A02, paso 4 | ADM-01 autorizado. | Solicitar un tipo de cambio no admitido o dejar incompleto un dato obligatorio. | No se modifica la configuración; se identifican los datos por corregir y puede iniciarse una nueva solicitud válida. |
| TP-UC10-A03 | UC-10-A03, paso 6 | Una materia activa aparece en publicaciones históricas. | Solicitar su desactivación desde una fecha autorizada. | La materia queda inactiva para registros nuevos y continúa visible en todas las publicaciones históricas; el cambio queda trazado. |
| TP-UC10-A04 | UC-10-A04, paso 6 | Existe una cuenta activa con un rol autorizado. | Suspender la cuenta o sustituir su rol por otro permitido. | El nuevo estado o rol se aplica en el siguiente control de autorización; se conserva el estado anterior y no se altera su historial de actuaciones. |
| TP-UC10-A05 | UC-10-A05, paso 5 | ADM-01 carece de atribución para conceder el rol de auditor o ampliar el ámbito solicitado. | Intentar asignar el privilegio superior. | La asignación se rechaza; cuenta y roles vigentes permanecen; el intento queda trazado. |
| TP-UC10-A06 | UC-10-A06, paso 5 | Existe una política válida vigente. | Proponer intervalo negativo, cero recordatorios con alerta posterior contradictoria o un orden temporal incoherente. | La propuesta se rechaza; la política anterior continúa vigente y se identifican los valores por corregir. |

## 13. Pruebas de UC-11 — Consultar bitácora de auditoría

**Requisitos verificados:** RF-01, RF-02 y RF-35.

| ID | Cobertura | Precondiciones | Datos y pasos resumidos | Resultado esperado |
|---|---|---|---|---|
| TP-UC11-FB | Flujo básico | Auditor autorizado para 4-A; existen actuaciones sobre publicaciones, resultados, avisos, vínculos, mensajes y discrepancias. | Repetir la consulta por cada tipo de entidad con identificador, intervalo, acción y usuario; solicitar una referencia subyacente autorizada. | Para cada tipo se proporciona la secuencia temporal con entidad, acción, usuario, fecha y referencia; el detalle permitido es de solo consulta y cada consulta queda registrada. |
| TP-UC11-A01 | UC-11-A01, paso 2 | Usuario autenticado sin permiso de auditoría. | Solicitar la bitácora de EVA-01. | La consulta se rechaza sin revelar existencia ni contenido de actuaciones; el intento queda trazado. |
| TP-UC11-A02 | UC-11-A02, paso 4 | Auditor autorizado. | Proporcionar un intervalo invertido o filtros incompletos. | No se ejecuta la consulta inválida; se identifican los criterios por corregir y no se mezclan otros ámbitos. |
| TP-UC11-A03 | UC-11-A03, paso 4 | Auditor autorizado únicamente para 4-A. | Solicitar una entidad perteneciente a otro ámbito. | La consulta se deniega sin confirmar actuaciones de la entidad protegida; el intento queda trazado. |
| TP-UC11-A04 | UC-11-A04, paso 5 | Auditor autorizado; filtros válidos sin coincidencias. | Consultar una entidad e intervalo sin actuaciones. | Se proporciona resultado vacío con filtros e intervalo aplicados; no aparecen actuaciones de otras entidades; la consulta queda trazada. |
| TP-UC11-A05 | UC-11-A05, paso 8 | El auditor puede ver una referencia de auditoría, pero carece de permiso sobre el detalle académico subyacente. | Solicitar el detalle protegido desde el resultado permitido. | La información de auditoría autorizada permanece; el detalle se deniega y no se amplía el alcance del actor. |
| TP-UC11-A06 | UC-11-A06, paso 7 | Auditor autorizado para consulta. | Solicitar modificar o eliminar una actuación de la bitácora. | La operación se rechaza; toda la bitácora permanece inalterada y el intento queda trazado. |

## 14. Matriz de cobertura de flujos

| Caso de uso | Flujo básico | Flujos alternos definidos | Pruebas correspondientes | Cobertura |
|---|---:|---:|---:|---|
| UC-01 | 1 | 4 | TP-UC01-FB y TP-UC01-A01…A04 | Completa |
| UC-02 | 1 | 6 | TP-UC02-FB y TP-UC02-A01…A06 | Completa |
| UC-03 | 1 | 7 | TP-UC03-FB y TP-UC03-A01…A07 | Completa |
| UC-04 | 1 | 7 | TP-UC04-FB y TP-UC04-A01…A07 | Completa |
| UC-05 | 1 | 8 | TP-UC05-FB y TP-UC05-A01…A08 | Completa |
| UC-06 | 1 | 4 | TP-UC06-FB y TP-UC06-A01…A04 | Completa |
| UC-07 | 1 | 8 | TP-UC07-FB y TP-UC07-A01…A08 | Completa |
| UC-08 | 1 | 4 | TP-UC08-FB y TP-UC08-A01…A04 | Completa |
| UC-09 | 1 | 6 | TP-UC09-FB y TP-UC09-A01…A06 | Completa |
| UC-10 | 1 | 6 | TP-UC10-FB y TP-UC10-A01…A06 | Completa |
| UC-11 | 1 | 6 | TP-UC11-FB y TP-UC11-A01…A06 | Completa |
| **Total** | **11** | **66** | **77 pruebas** | **Completa** |

## 15. Criterios de cierre de la prueba

Un caso puede considerarse aceptado únicamente cuando:

1. su flujo básico y todos sus alternos obtienen el resultado esperado;
2. no se produce exposición de información fuera del mínimo privilegio;
3. las actuaciones relevantes aparecen en la trazabilidad;
4. no se sobrescribe historia publicada;
5. las publicaciones académicas exigen congruencia físico–digital;
6. los dos hitos ordinarios de una evaluación producen avisos diferenciados;
7. ningún fallo de aviso elimina o duplica el evento académico;
8. ninguna discrepancia modifica una calificación sin UC-09 y una actuación autorizada;
9. los valores **[PV]** utilizados han sido validados o sustituidos por los acordados con la institución.
