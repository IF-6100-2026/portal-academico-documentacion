# Guion para presentar el avance al profesor

Duración sugerida: **7 a 9 minutos**, más preguntas.

## 1. Apertura — 40 segundos

> Nuestro proyecto aborda una brecha concreta del Centro Educativo María Montessori: el progreso académico se comunica principalmente mediante hojas impresas que el estudiante lleva al hogar. Ese intermediario puede retrasar u ocultar la información, por lo que el encargado legal pierde la oportunidad de acompañar a tiempo. Para esta versión unificamos el nombre como Portal Académico y limitamos el alcance a primaria.

Evitar afirmar que el problema es simplemente “no existe una plataforma”. El problema de negocio es la **falta de un canal directo, oportuno y trazable**.

## 2. Evidencia y límites — 50 segundos

Presentar primero lo confirmado:

- La escuela utiliza hojas impresas para comunicar calificaciones.
- El estudiante transporta la información.
- La Dirección reportó retrasos u ocultamiento y quejas de encargados.

Luego separar las hipótesis que aún requieren entrevista:

- Protocolo actual para consultas y reclamos.
- Tiempo docente dedicado a repetir comunicaciones o coordinar reuniones.
- Persona que interviene cuando docente y encargado no llegan a un acuerdo.
- Frecuencia de hojas tardías o no entregadas.

Frase útil:

> No convertimos una hipótesis en requisito definitivo. La dejamos identificada y formulamos la pregunta de validación correspondiente.

## 3. Proceso actual BPMN — 60 segundos

Mostrar el BPMN **AS-IS** y explicar tres rupturas:

1. La información sale de la escuela en papel y depende del estudiante.
2. La firma física no permite conocer con certeza cuándo se informó al encargado.
3. Una aclaración o discrepancia no tiene todavía un expediente único verificable.

Aclarar que el diagrama del PC1 representa el proceso actual, no el proceso futuro.

## 4. Visión y alcance — 45 segundos

> Para el personal docente, la Dirección y los encargados legales del Centro Educativo María Montessori, que necesitan compartir y consultar el progreso académico de forma directa y oportuna, Portal Académico es un sistema de información institucional que centraliza ese seguimiento y deja evidencia de las comunicaciones para facilitar un acompañamiento temprano del estudiantado.

Dentro del MVP:

- Perfil académico por estudiante y acceso validado del encargado.
- Dos hitos de una evaluación: asignación y devolución calificada.
- Notificación, acuse de consulta y seguimiento de avisos no vistos.
- Comunicación y discrepancias con historial.
- Reportes básicos para Dirección.

Fuera del MVP: matrícula, cobros, clases o evaluaciones en línea, integración con el MEP y decisiones automáticas mediante IA.

## 5. Respuesta a las observaciones — 90 segundos

| Observación | Decisión incorporada |
|---|---|
| Acceso independiente | Credenciales propias y consulta en cualquier momento. |
| Perfil y acceso del encargado | Vínculo institucional validado; una cuenta puede tener varios estudiantes autorizados. |
| Comunicación docente–encargado | Hilo asociado al estudiante con fecha, participantes e historial. |
| Escuela frente a colegio | El MVP se limita a primaria; secundaria queda como ampliación de alcance. |
| Carga administrativa | La evaluación se crea una vez por grupo y los resultados se cargan en conjunto. |
| Notificación y acuse | Estados distintos para creado, enviado, entregado cuando sea verificable, visto y fallido; recordatorios configurables. |
| Dos momentos de evaluación | Aviso al asignar y otro al devolver el resultado calificado. |
| Congruencia físico–digital | La publicación exige confirmación docente de correspondencia con el medio físico oficial. |

IA: posible integración futura de apoyo, siempre con revisión humana y sin decidir notas o reclamos.

## 6. Casos de uso y pruebas — 60 segundos

Explicar un ejemplo completo:

> En “Publicar evaluación asignada”, el docente registra una evaluación para todo el grupo, confirma que fue entregada o consignada por el medio físico oficial y publica. Esto activa un aviso para cada encargado autorizado. El flujo alterno bloquea la publicación cuando falta esa confirmación. El guion de prueba verifica ambos caminos.

Usar ese ejemplo para recorrer la cadena:

> actividad del BPMN → problema de dependencia del papel → RF-04/RF-06/RF-15/RF-17 → UC-03 → TP-UC03-FP y alternos

## 7. Reglas críticas — 45 segundos

- “Enviado”, “entregado” y “visto” representan estados diferentes.
- Un aviso no visto se reitera según parámetros institucionales y luego pasa a seguimiento humano.
- Una corrección conserva el valor anterior, autor, fecha y motivo.
- Una discrepancia no modifica automáticamente la nota.
- El docente conserva el criterio evaluativo; la mediación formaliza la comunicación.

## 8. Cierre y preguntas al profesor — 60 segundos

> El paquete ya cubre los artefactos evaluados y la trazabilidad entre ellos. Antes del cierre queremos validar con la Dirección los parámetros operativos y confirmar con usted que el nivel de detalle de los casos de uso y las pruebas corresponde al PC1.

Preguntas concretas:

1. ¿Prefiere que el acuse institucional sea únicamente la apertura dentro del portal, aunque el proveedor de correo confirme la entrega?
2. ¿Es correcto dejar los plazos de recordatorio y mediación como parámetros por validar en esta fase?
3. ¿Conviene modelar al estudiante como actor indirecto en el diagrama aunque no tenga cuenta en el MVP?
4. ¿La confirmación docente de congruencia con el medio físico responde adecuadamente a la observación 8, o espera una regla distinta?
5. ¿El detalle completo de los once casos de uso cubre el nivel esperado para el PC1 o recomienda dejar los dos casos administrativos como soporte fuera del conjunto priorizado?

## Respuestas breves a objeciones probables

**“¿No aumenta la carga al duplicar papel y sistema?”**  
Durante la transición puede existir duplicidad. La propuesta reduce trabajo repetitivo al crear una evaluación una vez por grupo, cargar resultados en tabla y generar avisos automáticamente. La carga real debe medirse con la Dirección.

**“¿Cómo saben que el encargado recibió la información?”**  
El portal distingue entrega técnica de consulta. El acuse institucional registra usuario, fecha y hora al abrir el contenido. Los avisos no vistos generan recordatorios y luego seguimiento humano.

**“¿El sistema sustituye el cuaderno?”**  
No en el MVP. La regla inicial exige congruencia con el medio físico definido por la escuela. El cambio de canal definitivo requiere una decisión institucional posterior.

**“¿Qué haría la IA?”**  
Nada obligatorio en el MVP. En una fase futura podría resumir tendencias o sugerir borradores, siempre con revisión humana, controles de privacidad y sin decidir calificaciones o reclamos.
