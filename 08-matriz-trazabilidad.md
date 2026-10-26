# 08. Matriz integral de trazabilidad

**Cadena exigida:** `BPMN actual → problema → requisito funcional → caso de uso → prueba`  
**Catálogo fuente:** `03-requerimientos-funcionales.md`  
**Convención de pruebas:** `TP-UCxx-FB` para flujo básico y `TP-UCxx-Ayy` para alternos  
**Versión:** 2026-09-15  
**Estado:** propuesta para revisión; no equivale a aprobación de la organización

## Convenciones

- `AC-xx`: actividad del proceso AS-IS.
- `P-xx`: problema o decisión pendiente asociado con las observaciones del profesor.
- `PV-xx`: punto de levantamiento pendiente.
- `RF-xx`: requisito funcional atómico del catálogo vigente.
- `UC-xx`: caso de uso propuesto.

Un RF puede originarse en una carencia sin que exista una actividad AS-IS equivalente. La matriz lo indica expresamente; no introduce el sistema futuro en el BPMN.

## Catálogo vigente de RF-01 a RF-35

| ID | Nombre exacto resumido | Prioridad |
|---|---|---|
| RF-01 | Autenticar a un usuario | Debe |
| RF-02 | Aplicar permisos por rol | Debe |
| RF-03 | Registrar una relación encargado–estudiante | Debe |
| RF-04 | Restringir la consulta al vínculo activo | Debe |
| RF-05 | Seleccionar un estudiante vinculado | Debe |
| RF-06 | Consultar el perfil académico | Debe |
| RF-07 | Consultar el historial académico | Debe |
| RF-08 | Crear una evaluación para un grupo | Debe |
| RF-09 | Registrar el hito de entrega para realización | Debe |
| RF-10 | Confirmar congruencia del primer hito | Debe |
| RF-11 | Publicar el primer hito | Debe |
| RF-12 | Cargar resultados por grupo | Debe |
| RF-13 | Confirmar congruencia del segundo hito | Debe |
| RF-14 | Publicar el resultado calificado | Debe |
| RF-15 | Corregir un resultado con historial | Debería |
| RF-16 | Generar un aviso por el primer hito | Debe |
| RF-17 | Generar un aviso por el segundo hito | Debe |
| RF-18 | Registrar el estado de un aviso | Debe |
| RF-19 | Registrar el acuse de consulta | Debe |
| RF-20 | Configurar la política de recordatorios | Debe |
| RF-21 | Generar un recordatorio por falta de consulta | Debe |
| RF-22 | Alertar un caso sin consulta | Debe |
| RF-23 | Crear un hilo de comunicación | Debe |
| RF-24 | Registrar un mensaje en un hilo | Debe |
| RF-25 | Abrir una discrepancia académica | Debe |
| RF-26 | Asignar una discrepancia | Debe |
| RF-27 | Actualizar el estado de una discrepancia | Debe |
| RF-28 | Escalar una discrepancia | Debe |
| RF-29 | Registrar la resolución de una discrepancia | Debe |
| RF-30 | Consultar rendimiento agregado | Debería |
| RF-31 | Consultar seguimiento de comunicaciones | Debe |
| RF-32 | Consultar estado de discrepancias | Debe |
| RF-33 | Administrar catálogos académicos | Debe |
| RF-34 | Administrar cuentas y roles | Debe |
| RF-35 | Consultar la bitácora de una entidad | Debe |

## Matriz BPMN → problema → RF → UC → prueba

| Origen BPMN AS-IS | Problema | RF | UC trazado | Prueba(s) de referencia | Fundamento y cautela |
|---|---|---|---|---|---|
| `AC-09`, excepción `AC-05` | P-01 | RF-01 | UC-01, UC-02, UC-03, UC-04, UC-06, UC-07, UC-08, UC-09, UC-10, UC-11 | `TP-UC01-A01` y pruebas de acceso de cada UC | El mecanismo exacto de autenticación está por validar. |
| `AC-04`, `AC-09`; sin control digital AS-IS | P-02 | RF-02 | UC-01 a UC-11 | `TP-UC01-A01`, `TP-UC02-A01` y pruebas de autorización por UC | Los permisos son una regla propuesta, no comportamiento actual. |
| Relación implícita en `AC-04`/`AC-09` | P-02 | RF-03 | UC-02 | `TP-UC02-FB`, `TP-UC02-A05` | Documentos, cardinalidades y autoridad de validación siguen pendientes. |
| `AC-09`, `AC-10` | P-02 | RF-04 | UC-01, UC-06, UC-07 | `TP-UC01-FB`, `TP-UC01-A03`, `TP-UC06-A01`, `TP-UC07-A01` | Una cuenta sin vínculo activo no debe exponer datos. |
| `AC-09`; hipótesis de varios vínculos | P-02 | RF-05 | UC-01 | `TP-UC01-FB`, `TP-UC01-A02` | La existencia de varios estudiantes por cuenta debe validarse. |
| `AC-10`; contexto de la hoja | P-02 | RF-06 | UC-01 | `TP-UC01-FB`, `TP-UC01-A04` | El perfil debe proceder de datos académicos autorizados. |
| `AC-10`; hojas separadas y fallo `AC-05` | P-01, P-05 | RF-07 | UC-01 | `TP-UC01-FB`, `TP-UC01-A04` | Los periodos disponibles deben acordarse. |
| `AC-01`; PV-08 | P-05, P-07 | RF-08 | UC-03 | `TP-UC03-FB`, `TP-UC03-A03` | Crear una vez por grupo busca evitar repetición; debe probarse con docentes. |
| PV-03; actividad actual insuficientemente levantada | P-07 | RF-09 | UC-03 | `TP-UC03-FB`, `TP-UC03-A02` | Debe levantarse cómo se asigna hoy una evaluación para realizar. |
| `AC-01`, `AC-02`; PV-01/PV-03 | P-08 | RF-10 | UC-03 | `TP-UC03-A04` | “Medio físico oficial” y responsable de confirmar están por definir. |
| PV-03; publicación inexistente AS-IS | P-01, P-07, P-08 | RF-11 | UC-03 | `TP-UC03-FB`, `TP-UC03-A04`, `TP-UC03-A05` | Solo un hito confirmado puede publicarse y no debe duplicarse. |
| `AC-01`; PV-08 | P-05, P-07 | RF-12 | UC-04 | `TP-UC04-FB`, `TP-UC04-A03` | La carga grupal conserva un resultado por estudiante y permite borradores. |
| `AC-01`, `AC-02`; PV-01/PV-03 | P-08 | RF-13 | UC-04, UC-09 | `TP-UC04-A04`, `TP-UC09-A01` | La congruencia se valida antes de publicar o corregir. |
| `AC-01`, `AC-02` | P-01, P-07 | RF-14 | UC-04 | `TP-UC04-FB`, `TP-UC04-A05` | La devolución calificada es distinta de la asignación. |
| Corrección actual no levantada; posible resultado de `AC-06`–`AC-08` | P-03, P-08 | RF-15 | UC-07, UC-09 | `TP-UC07-FB`, `TP-UC09-FB`, `TP-UC09-A01`, `TP-UC09-A02` | Debe conservar versión previa, autor, fecha y motivo. |
| `AC-02`→`AC-05`; sin aviso automático AS-IS | P-01, P-05, P-06, P-07 | RF-16 | UC-03 | `TP-UC03-FB`, `TP-UC03-A06`, `TP-UC03-A07` | El primer hito genera avisos sin mensajes individuales del docente. |
| `AC-02`→`AC-05`; corrección no levantada | P-01, P-05, P-06, P-07 | RF-17 | UC-04, UC-09 | `TP-UC04-FB`, `TP-UC04-A06`, `TP-UC04-A07`, `TP-UC09-FB` | Resultado y corrección generan aviso de segunda etapa. |
| `AC-02`, `AC-05`, `AC-09`; PV-06 | P-06 | RF-18 | UC-03, UC-04, UC-05, UC-09 | `TP-UC03-A07`, `TP-UC04-A07`, `TP-UC05-FB`, `TP-UC05-A05` | Enviado, entrega técnica, visto y fallido no son sinónimos. |
| `AC-09`, `AC-10`; sin acuse verificable | P-06 | RF-19 | UC-05 | `TP-UC05-FB`, `TP-UC05-A01` | Consultar prueba apertura, no comprensión o aceptación. |
| PV-06; sin política AS-IS | P-06 | RF-20 | UC-10 | `TP-UC10-FB`, `TP-UC10-A01` | Número, intervalo y umbral requieren decisión de Dirección. |
| Excepción `AC-05`, contacto `AC-11` | P-06 | RF-21 | UC-05 | `TP-UC05-FB`, `TP-UC05-A01`, `TP-UC05-A06` | El recordatorio referencia el aviso original y cesa con el acuse. |
| `AC-05`, `AC-11`; PV-06/PV-08 | P-05, P-06 | RF-22 | UC-05 | `TP-UC05-A02`, `TP-UC05-A03` | Responsable final y contacto posterior están por validar. |
| `AC-11`, `AC-12`; PV-04 | P-03 | RF-23 | UC-06 | `TP-UC06-FB`, `TP-UC06-A01` | Solo participantes autorizados pueden integrar el hilo. |
| `AC-06`, `AC-07`, `AC-13`; PV-04 | P-03 | RF-24 | UC-06 | `TP-UC06-FB`, `TP-UC06-A02`, `TP-UC06-A04` | El protocolo institucional sigue pendiente. |
| `AC-11`, `AC-12`; PV-04/PV-05 | P-03 | RF-25 | UC-07 | `TP-UC07-FB`, `TP-UC07-A02`, `TP-UC07-A03` | La discrepancia señala una publicación y un motivo. |
| `AC-06`, `AC-08`; PV-05 | P-03 | RF-26 | UC-07 | `TP-UC07-FB`, `TP-UC07-A01` | Quién asigna y atiende debe decidirlo la institución. |
| `AC-06`–`AC-08`; PV-05 | P-03 | RF-27 | UC-07 | `TP-UC07-FB`, `TP-UC07-A05`, `TP-UC07-A06`, `TP-UC07-A07`, `TP-UC07-A08` | Catálogo y transiciones son hipótesis de solución. |
| `AC-08`; PV-05 | P-03 | RF-28 | UC-07 | `TP-UC07-A06`, `TP-UC07-A07`, `TP-UC07-A08` | No se afirma mediación formal o plazo actual. |
| `AC-07`, `AC-13`; PV-05 | P-03 | RF-29 | UC-07 | `TP-UC07-FB`, `TP-UC07-A06`, `TP-UC07-A07`, `TP-UC07-A08` | Resolver no modifica automáticamente la calificación. |
| Información de `AC-01`; consolidación no confirmada | P-05 | RF-30 | UC-08 | `TP-UC08-FB`, `TP-UC08-A01` | Indicadores, granularidad y privacidad requieren aprobación. |
| `AC-05`, `AC-11`; PV-06/PV-08 | P-05, P-06 | RF-31 | UC-08 | `TP-UC08-FB`, `TP-UC08-A01` | Los totales deben rastrearse a avisos existentes. |
| `AC-06`–`AC-08`; PV-05 | P-03 | RF-32 | UC-08 | `TP-UC08-FB`, `TP-UC08-A01` | Vencimiento depende de plazos no fijados. |
| `AC-01` y PV-01/PV-03 implican contexto académico | P-02, P-07 | RF-33 | UC-10 | `TP-UC10-FB`, `TP-UC10-A02` | Desactivar catálogos no debe borrar historia. |
| Sin actividad AS-IS equivalente; soporte a P-01/P-02 | P-01, P-02 | RF-34 | UC-10 | `TP-UC10-FB`, `TP-UC10-A01` | La gestión de cuentas y roles queda separada de la vinculación. |
| En AS-IS no hay bitácora consolidada confirmada | P-03, P-06, P-08 | RF-35 | UC-11 | `TP-UC11-FB`, `TP-UC11-A01` | Retención y alcance del auditor están por validar. |

## Cobertura directa por caso de uso

| UC | Nombre | Actores asociados | RF cubiertos directamente | Pruebas |
|---|---|---|---|---|
| UC-01 | Consultar progreso académico | Encargado legal | RF-01, RF-02, RF-04, RF-05, RF-06, RF-07 | `TP-UC01-FB`, `TP-UC01-A01`–`TP-UC01-A04` |
| UC-02 | Vincular encargado con estudiante | Administrador institucional | RF-01, RF-02, RF-03 | `TP-UC02-FB`, `TP-UC02-A01`–`TP-UC02-A06` |
| UC-03 | Publicar evaluación asignada | Docente; Estudiante indirecto | RF-01, RF-02, RF-08, RF-09, RF-10, RF-11, RF-16, RF-18 | `TP-UC03-FB`, `TP-UC03-A01`–`TP-UC03-A07` |
| UC-04 | Publicar resultado de evaluación | Docente; Estudiante indirecto | RF-01, RF-02, RF-12, RF-13, RF-14, RF-17, RF-18 | `TP-UC04-FB`, `TP-UC04-A01`–`TP-UC04-A07` |
| UC-05 | Gestionar avisos no consultados | Tiempo/servicio de notificaciones; Encargado legal; Docente; Dirección/mediador | RF-02, RF-18, RF-19, RF-21, RF-22 | `TP-UC05-FB`, `TP-UC05-A01`–`TP-UC05-A08` |
| UC-06 | Comunicarse sobre el seguimiento académico | Encargado legal; Docente | RF-01, RF-02, RF-04, RF-23, RF-24 | `TP-UC06-FB`, `TP-UC06-A01`–`TP-UC06-A04` |
| UC-07 | Gestionar discrepancia académica | Encargado legal; Docente; Dirección/mediador | RF-01, RF-02, RF-04, RF-15, RF-25, RF-26, RF-27, RF-28, RF-29 | `TP-UC07-FB`, `TP-UC07-A01`–`TP-UC07-A08` |
| UC-08 | Consultar reportes institucionales | Dirección/mediador | RF-01, RF-02, RF-30, RF-31, RF-32 | `TP-UC08-FB`, `TP-UC08-A01`–`TP-UC08-A04` |
| UC-09 | Corregir publicación académica | Docente | RF-01, RF-02, RF-13, RF-15, RF-17, RF-18 | `TP-UC09-FB`, `TP-UC09-A01`–`TP-UC09-A06` |
| UC-10 | Administrar configuración y accesos | Administrador institucional; Dirección | RF-01, RF-02, RF-20, RF-33, RF-34 | `TP-UC10-FB`, `TP-UC10-A01`–`TP-UC10-A06` |
| UC-11 | Consultar bitácora de auditoría | Auditor institucional autorizado; Dirección según permiso | RF-01, RF-02, RF-35 | `TP-UC11-FB`, `TP-UC11-A01`–`TP-UC11-A06` |

Los RF-33, RF-34 y RF-35 son capacidades de soporte con objetivos propios; por eso se representan en UC-10 y UC-11, no como precondiciones ocultas de los nueve casos de negocio.

## Trazabilidad de las ocho observaciones del profesor

| Observación | Problema / BPMN | RF exactos | UC | Pruebas de referencia | Acción pendiente |
|---|---|---|---|---|---|
| OBS-PROF-01 — acceso inmediato e independiente | P-01; `AC-09`, fallo `AC-05` | RF-01, RF-04, RF-07 | UC-01 | `TP-UC01-FB`, `TP-UC01-A01` | Confirmar disponibilidad y restricciones institucionales. |
| OBS-PROF-02 — perfil y acceso del encargado | P-02; relación implícita en `AC-04`/`AC-09` | RF-03, RF-04, RF-05, RF-06 | UC-01, UC-02 | `TP-UC02-FB`, `TP-UC02-A05`, `TP-UC01-A02`, `TP-UC01-A03` | Definir autoridad, documentos y cardinalidades. |
| OBS-PROF-03 — comunicación encargado–docente | P-03; `AC-06`–`AC-08`, `AC-11`–`AC-13` **[PV]** | RF-23 a RF-29 | UC-06, UC-07 | `TP-UC06-FB`, `TP-UC06-A01`, `TP-UC07-FB` y alternos | Levantar PV-04/PV-05 y no afirmar mediación actual. |
| OBS-PROF-04 — diferenciar colegios y escuelas | P-04; decisión de alcance | Sin RF del MVP; RF-F03 queda futuro | Transversal | Validación de alcance, no prueba funcional | Mantener ejemplos en primaria y escuela de referencia. |
| OBS-PROF-05 — carga administrativa | P-05; `AC-06`–`AC-08`; PV-08 | RF-08, RF-12, RF-16, RF-17, RF-31 | UC-03, UC-04, UC-08 | `TP-UC03-FB`, `TP-UC04-FB`, `TP-UC08-FB` | Medir tareas, tiempo y volumen antes de afirmar reducción. |
| OBS-PROF-06 — notificación, acuse y reiteración | P-06; `AC-02`→`AC-05`, `AC-09`; PV-06 | RF-18 a RF-22 | UC-03, UC-04, UC-05, UC-09, UC-10 | `TP-UC05-FB`, `TP-UC05-A01`, `TP-UC05-A03`, `TP-UC10-FB` | Definir canal, política, estados y responsable. |
| OBS-PROF-07 — avisar asignación y devolución | P-07; PV-03 y `AC-01`/`AC-02` parciales | RF-09, RF-11, RF-14, RF-16, RF-17 | UC-03, UC-04 | `TP-UC03-FB`, `TP-UC04-FB` | Levantar ambos hitos actuales. |
| OBS-PROF-08 — congruencia con clase/cuaderno | P-08; `AC-01`, `AC-02`; PV-01/PV-03 | RF-10, RF-13, RF-15, RF-35 | UC-03, UC-04, UC-07, UC-09, UC-11 | `TP-UC03-A04`, `TP-UC04-A04`, `TP-UC09-FB`, `TP-UC11-FB` | Definir fuente autorizada, corrección y permiso de auditoría. |

## Controles de consistencia

- Los RF-01 a RF-35 aparecen en la matriz y cada uno se relaciona con al menos un UC.
- Los once UC tienen actor, RF y prueba de referencia.
- Los IDs de prueba usan exclusivamente `-FB` y `-Ayy`, conforme a `07-guion-pruebas.md`.
- Las ocho observaciones tienen origen, RF, UC/prueba cuando corresponde y acción pendiente.
- No se inventan tiempos, cantidad de recordatorios, canales, métricas ni figura mediadora.
