# Portal Académico — Centro Educativo María Montessori

## Supuestos, evidencia y plan de validación

**Alcance del MVP:** educación primaria.  
**Nombre canónico:** Portal Académico — Centro Educativo María Montessori.  
**Nota histórica:** *Conecta Montessori* fue el nombre provisional anterior.

## 1. Registro de evidencia confirmada

| ID | Evidencia confirmada | Fuente disponible | Uso permitido en el análisis | Evidencia adicional necesaria |
|---|---|---|---|---|
| EC-01 | Las calificaciones se comunican mediante hojas impresas. | Dirección del Centro Educativo María Montessori. | Describir el medio actual y modelar al menos ese flujo. | Precisar formato, responsable, periodicidad y conservación. |
| EC-02 | El estudiante transporta la hoja al hogar y actúa como intermediario. | Dirección. | Identificar el punto de dependencia del proceso actual. | Levantar el proceso paso a paso y sus variantes. |
| EC-03 | Algunas hojas se retrasan o son ocultadas. | Dirección. | Sustentar el riesgo de comunicación tardía o incompleta. | Cuantificar frecuencia, demora y grupos afectados. |
| EC-04 | Personas encargadas han presentado quejas. | Dirección. | Sustentar la afectación a familias y a la institución. | Documentar un ejemplo anonimizado y el tratamiento recibido. |

Antes de la entrega formal debe registrarse la fecha de la entrevista, el cargo de la persona informante y la forma autorizada de citar la fuente. No se deben incorporar datos personales de menores.

## 2. Supuestos e hipótesis por validar

| ID | Supuesto o hipótesis | Motivo de inclusión | Cómo validarlo | Prioridad |
|---|---|---|---|---|
| SV-01 | Las tareas, evaluaciones antes de calificarse y observaciones se comunican por medios separados. | Aparece en formulaciones anteriores, pero no está dentro de los cuatro hechos confirmados. | Entrevista y muestra anonimizada de los medios usados. | Alta. |
| SV-02 | No existe un protocolo institucional uniforme para mensajes y discrepancias. | Se requiere para justificar el flujo propuesto. | Solicitar reglamento, formatos, estados, responsables y ejemplos. | Alta. |
| SV-03 | No existe una figura mediadora formal. | La versión anterior lo afirmaba sin evidencia suficiente. | Preguntar quién atiende, escala y cierra hoy una queja. | Alta. |
| SV-04 | Repetir avisos, llamadas o reuniones genera una carga administrativa relevante. | Responde a la observación 5 del profesor. | Medir durante una semana cantidad, duración y responsable de contactos. | Alta. |
| SV-05 | El acceso de la persona encargada debe estar disponible fuera del horario escolar. | Responde al acceso independiente; el horario real no fue confirmado. | Entrevista o encuesta breve a familias y Dirección. | Media. |
| SV-06 | Un encargado puede tener varios estudiantes y un estudiante varios encargados. | Modelo usual, pero puede verse afectado por custodia y autorizaciones. | Revisar expedientes y política institucional sin copiar datos sensibles. | Alta. |
| SV-07 | El estudiante no necesita una cuenta propia en el MVP. | Mantiene el alcance en primaria y enfocado en escuela–hogar. | Confirmar con Dirección y docentes. | Media. |
| SV-08 | El “medio físico oficial” es el cuaderno de comunicaciones o la hoja entregada en clase. | La observación 8 exige congruencia, pero “cuaderno” es ambiguo. | Identificar cada soporte físico, responsable y momento de uso. | Alta. |
| SV-09 | Portal y correo son canales suficientes para el MVP. | Permiten un alcance controlado; conectividad y correos válidos no están confirmados. | Encuesta de acceso y prueba piloto de contacto. | Alta. |
| SV-10 | Dirección o una coordinación puede asumir la mediación. | Se necesita un receptor para escalaciones. | Validar autoridad, suplencias y límites de decisión. | Alta. |
| SV-11 | La creación por grupo y carga tabular reducen la carga docente. | Responde a la observación 5 sin prometer una reducción no medida. | Prototipo y prueba cronometrada contra el proceso actual. | Alta. |
| SV-12 | Los reportes útiles incluyen rendimiento por grupo/materia, avisos no vistos, tiempo de consulta y casos vencidos. | Conjunto mínimo propuesto para Dirección. | Taller de priorización con Dirección. | Media. |
| SV-13 | Secundaria requiere actores y protocolos diferentes. | Justifica limitar el MVP a primaria. | No requiere validación para este alcance; sí antes de una expansión. | Baja para MVP. |
| SV-14 | La IA podría apoyar resúmenes o detección de pendientes. | Observación extra del profesor. | Evaluación futura de utilidad, privacidad, sesgo, exactitud y responsabilidad. | Fuera del MVP. |

## 3. Parámetros temporales propuestos

Ninguno de estos valores describe una política vigente. Son **supuestos explícitos por validar** para poder discutir y probar el flujo:

| Parámetro | Valor propuesto | Pregunta de validación |
|---|---:|---|
| Primer recordatorio de aviso no visto | 48 horas después de publicar | ¿Se cuentan horas naturales o hábiles y existen tipos de aviso urgentes? |
| Segundo recordatorio | 72 horas después del primer recordatorio | ¿Cuántos intentos son aceptables y por qué canal? |
| Alerta para seguimiento humano | Al quinto día hábil sin consulta | ¿Quién recibe la alerta y qué acción realiza? |
| Acuse institucional de una discrepancia | Dentro de 1 día hábil | ¿El acuse es automático o de una persona responsable? |
| Respuesta inicial docente | Dentro de 3 días hábiles | ¿Qué ocurre con ausencias, vacaciones o fines de periodo? |
| Escalamiento por falta de resolución | Al completar 5 días hábiles | ¿Se escala por tiempo, por solicitud o por tipo de caso? |
| Retención de bitácora | 5 años | ¿Qué obligación institucional o legal define el plazo real? |
| Disponibilidad mensual | 99,5 % | ¿Qué ventanas de mantenimiento y calendario lectivo aplican? |
| Respuesta de consultas habituales | Percentil 95 de 3 segundos | ¿Qué conectividad y cantidad de usuarios concurrentes son realistas? |

Los plazos deberán quedar configurables si la institución aprueba que varíen por periodo o tipo de comunicación.

## 4. Guion de validación con la organización

### 4.1 Proceso actual y evidencia

1. ¿Quién prepara, revisa, imprime y entrega la hoja de calificaciones?
2. ¿Qué recibe el estudiante y qué debe devolver firmado, si algo?
3. ¿Cómo sabe hoy la escuela si la persona encargada recibió la información?
4. ¿Con qué frecuencia una hoja llega tarde, no llega o debe entregarse de nuevo?
5. ¿Puede describirse un reclamo real anonimizado desde su inicio hasta su cierre?
6. ¿Qué otros medios se usan para tareas, evaluaciones, observaciones y cambios?

### 4.2 Perfil y acceso del encargado

7. ¿Qué documento o aprobación demuestra que una persona puede consultar a un estudiante?
8. ¿Puede un estudiante tener varios encargados y un encargado varios estudiantes?
9. ¿Cómo se tratan separaciones, custodia, restricciones o cambios de contacto?
10. ¿Qué porcentaje de familias dispone de correo, teléfono inteligente y conexión habitual?
11. ¿Necesitan consultar fuera del horario escolar?

### 4.3 Comunicación y discrepancias

12. ¿Qué temas puede resolver el docente por mensaje y cuáles requieren reunión?
13. ¿Qué canal se usa actualmente y qué evidencia queda?
14. ¿Quién recibe una discrepancia, qué estados recorre y qué plazo existe?
15. ¿Cuándo interviene Dirección u otra figura y qué autoridad tiene?
16. ¿Qué información debe conservarse y durante cuánto tiempo?

### 4.4 Carga administrativa y dos hitos

17. ¿Cuántos grupos, estudiantes, materias y evaluaciones gestiona cada docente?
18. ¿Cuánto tarda hoy en preparar, entregar y volver a comunicar una evaluación?
19. ¿La escuela desea avisar tanto la entrega para realización como la devolución calificada?
20. ¿Qué datos mínimos necesita cada uno de esos hitos?
21. ¿Una carga por grupo y una tabla de resultados representan correctamente el trabajo docente?

### 4.5 Congruencia físico-digital

22. ¿Cuál es el medio físico oficial: hoja, cuaderno de comunicaciones, cuaderno de materia u otro?
23. ¿Qué evento ocurre primero: registro digital, entrega física o ambos?
24. ¿Quién confirma que ambos coinciden y cómo se corrige una diferencia?
25. ¿Durante cuánto tiempo deben coexistir papel y Portal?

### 4.6 Avisos, acuse y reportes

26. ¿Qué significa para la institución `enviado`, `entregado` y `visto`?
27. ¿Cuándo y cuántas veces debe recordarse un aviso no visto?
28. ¿Quién actúa después del último recordatorio?
29. ¿Qué reportes tomaría una decisión real de Dirección y con qué frecuencia?
30. ¿Qué indicadores deben evitarse porque podrían interpretarse incorrectamente?

### 4.7 Inteligencia artificial futura

31. ¿Existe un problema concreto que justifique IA después del MVP?
32. ¿Qué datos podría procesar, quién revisaría la salida y quién respondería por un error?
33. ¿Qué usos se prohíben expresamente? Se propone prohibir calificación y resolución autónomas.

## 5. Evidencias que deben solicitarse

- Una hoja de calificaciones anonimizada.
- Un esquema o fotografía anonimizada del medio físico oficial, si la institución lo autoriza.
- El procedimiento escrito de comunicación o reclamos, si existe.
- Un ejemplo anonimizado de queja y su seguimiento.
- Datos agregados sobre grupos, estudiantes, evaluaciones y contactos, sin nombres.
- Política de custodia, autorización de encargados y conservación de registros, si existe.
- Lista de canales disponibles y limitaciones conocidas de conectividad.

## 6. Criterio de cierre de validación

Un supuesto puede cambiar a `confirmado`, `rechazado` o `modificado` únicamente cuando se registra:

1. fuente y cargo de quien responde;
2. fecha y técnica de levantamiento;
3. respuesta o evidencia obtenida;
4. artefactos afectados;
5. decisión y responsable de aprobarla.

Las afirmaciones rechazadas deben retirarse del problema, requisitos, BPMN y casos de uso; las modificadas deben actualizarse en todos esos artefactos para conservar trazabilidad.

## 7. Registro inicial de decisiones

| Decisión | Estado | Justificación |
|---|---|---|
| Usar **Portal Académico — Centro Educativo María Montessori** como nombre canónico. | Acordada para estos artefactos. | Evita alternar con el nombre provisional *Conecta Montessori*. |
| Limitar el MVP a educación primaria. | Acordada para el análisis. | Responde a la diferencia entre escuela y colegio y controla el alcance. |
| Mantener IA fuera del MVP. | Acordada para el análisis. | No existe todavía evidencia, caso de uso ni controles aprobados. |
| Mantener el medio físico durante la transición. | Supuesto por validar. | Permite responder a la congruencia solicitada sin afirmar que la escuela eliminará el papel. |
| Proponer Portal y correo como canales iniciales. | Supuesto por validar. | Reduce integraciones, pero depende de conectividad, consentimiento y datos de contacto. |

