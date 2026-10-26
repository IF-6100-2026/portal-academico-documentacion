# Portal Académico — Centro Educativo María Montessori

## Requerimientos suplementarios, reglas y restricciones

**Alcance:** MVP para educación primaria.  
**Marco de calidad:** FURPS+ y características de ISO/IEC 25010.  
**Nota de validación:** todo umbral numérico marcado como `propuesto` es un supuesto inicial para volver verificable el requisito; debe aprobarse con la institución y el equipo antes de convertirse en compromiso.

## 1. Requerimientos de calidad medibles

| ID | FURPS+ / ISO 25010 | Requerimiento verificable | Método de verificación | Estado del umbral |
|---|---|---|---|---|
| RS-SEC-01 | Funcionalidad / Seguridad | El 100 % de las solicitudes de información académica deberá comprobar sesión, rol y vínculo activo cuando corresponda. | Pruebas positivas y negativas para cada rol y relación. | Criterio obligatorio; matriz de permisos por validar. |
| RS-SEC-02 | Seguridad / Confidencialidad | Una cuenta de encargado no deberá recuperar datos identificables de estudiantes sin vínculo activo, incluso mediante una dirección o identificador manipulado. | Pruebas de autorización horizontal con al menos un estudiante autorizado y otro no autorizado. | Obligatorio. |
| RS-SEC-03 | Seguridad / Trazabilidad | El 100 % de altas, cambios, publicaciones, consultas, correcciones, asignaciones, escalaciones y cierres deberá registrar usuario o proceso, fecha, acción y entidad afectada. | Comparación de una muestra de operaciones contra la bitácora. | Obligatorio; plazo de retención por validar. |
| RS-SEC-04 | Seguridad / Protección de datos | Los datos deberán viajar por un canal cifrado y las credenciales no deberán almacenarse como texto legible. | Revisión de configuración y análisis técnico antes de producción. | Obligatorio; mecanismo técnico se define en diseño. |
| RS-USA-01 | Usabilidad / Adecuación reconocible | Al menos 4 de 5 participantes representativos deberán completar sin ayuda las tareas de consultar una nota, reconocer si un aviso fue visto y abrir una discrepancia. | Prueba moderada con guion y registro de éxito por tarea. | `4 de 5`, propuesto por validar. |
| RS-USA-02 | Usabilidad / Eficiencia de uso | Un docente capacitado deberá crear una evaluación de grupo y dejar listos sus resultados para publicación en un máximo de 5 minutos, excluyendo el tiempo de calificación pedagógica. | Prueba cronometrada con un grupo de datos de ejemplo. | `5 minutos`, propuesto por validar con línea base. |
| RS-USA-03 | Accesibilidad | Las funciones del MVP deberán cumplir WCAG 2.2 nivel AA en navegación por teclado, contraste, foco visible, nombres accesibles y mensajes de error. | Evaluación automática y revisión manual de los flujos críticos. | WCAG 2.2 AA, propuesto como estándar de aceptación. |
| RS-REN-01 | Rendimiento / Comportamiento temporal | El 95 % de las consultas habituales deberá responder en 3 segundos o menos bajo una carga de 200 sesiones concurrentes. | Prueba de carga con datos representativos y medición del percentil 95. | `3 s / 200 sesiones`, propuesto por validar. |
| RS-REN-02 | Rendimiento / Capacidad | La carga de resultados de un grupo de 40 estudiantes deberá guardarse en 5 segundos o menos, sin duplicar resultados. | Prueba de rendimiento con 40 filas válidas y repetición controlada. | `40 / 5 s`, propuesto por validar. |
| RS-CON-01 | Confiabilidad / Disponibilidad | El Portal deberá alcanzar 99,5 % de disponibilidad mensual durante el curso lectivo, excluyendo mantenimiento anunciado. | Cálculo mensual a partir del monitoreo del servicio. | `99,5 %`, propuesto por validar. |
| RS-CON-02 | Confiabilidad / Tolerancia a fallos | Repetir un intento de notificación no deberá duplicar la publicación académica ni crear más de un recordatorio para el mismo intervalo. | Pruebas de reintento e idempotencia con fallos simulados. | Obligatorio. |
| RS-CON-03 | Confiabilidad / Recuperabilidad | La pérdida máxima de datos deberá ser de 24 horas y la restauración del servicio deberá completarse en 4 horas desde que se autorice la recuperación. | Simulacro de respaldo y restauración documentado. | RPO `24 h` y RTO `4 h`, propuestos por validar. |
| RS-INT-01 | Integridad de datos | Una publicación no podrá quedar visible sin periodo, grupo o estudiante, materia, docente responsable, tipo de hito, fecha y confirmación de congruencia. | Pruebas de validación omitiendo cada dato obligatorio. | Campos definitivos por validar. |
| RS-INT-02 | Integridad / No repudio interno | Una corrección no deberá sobrescribir la versión publicada anterior ni su autoría. | Publicar, corregir y reconstruir ambas versiones desde la bitácora. | Obligatorio. |
| RS-MAN-01 | Mantenibilidad / Modificabilidad | Los periodos, grupos, materias, estados habilitados y parámetros de recordatorio deberán modificarse mediante configuración autorizada, sin cambiar código. | Prueba de cambio de parámetro y comprobación de su efecto. | Obligatorio; responsable por validar. |
| RS-COM-01 | Compatibilidad / Interoperabilidad | La integración con un canal externo deberá registrar el identificador del intento y su resultado sin convertir `enviado` en `visto`. | Prueba con proveedor simulado que responde éxito, fallo y estado desconocido. | Canal externo del MVP por validar. |
| RS-PRI-01 | Privacidad / Minimización | Los reportes agregados no deberán exponer información identificable cuando el usuario solo tenga permiso para datos consolidados. | Pruebas por rol y revisión de exportaciones, si se autorizan. | Obligatorio. |
| RS-POR-01 | Portabilidad / Adaptabilidad | Los flujos críticos deberán poder completarse en las dos familias de navegador aprobadas por la institución y en anchos desde 360 hasta 1366 píxeles. | Matriz de pruebas de compatibilidad y revisión responsive. | Navegadores y anchos, propuestos por validar. |

## 2. Reglas de negocio

### RN-01 — Validación del vínculo

Una relación encargado–estudiante solo puede pasar a activa después de que la institución compruebe la autorización. El documento o procedimiento de comprobación está pendiente de validación.

### RN-02 — Alcance de la persona encargada

Una persona encargada solo puede consultar información de estudiantes con vínculo activo. La posibilidad de varios encargados por estudiante y varios estudiantes por encargado es un supuesto de modelado que debe confirmarse, incluidas restricciones de custodia.

### RN-03 — Estudiante sin cuenta en el MVP

El estudiante es la persona sobre la que se registra información y un participante indirecto del proceso actual, pero no tendrá cuenta propia en el MVP. Cualquier cambio requiere ampliación formal del alcance.

### RN-04 — Borrador no visible

Un registro en borrador no es visible para las personas encargadas y no genera avisos.

### RN-05 — Congruencia físico-digital

Mientras la institución mantenga un medio físico oficial, el docente debe confirmar que el registro digital corresponde con lo entregado o consignado físicamente antes de publicarlo. Esta confirmación es una declaración trazable; el Portal no puede comprobar por sí mismo el contenido de un cuaderno o documento físico.

### RN-06 — Dos hitos de evaluación

Cada evaluación puede producir dos hitos independientes:

1. entrega o asignación para que el estudiantado la realice;
2. devolución de la evaluación con su resultado.

Publicar un hito no implica que el otro exista ni permite tratarlos como un solo aviso.

### RN-07 — Estados de notificación no equivalentes

`Creado`, `enviado`, `entregado por el proveedor` cuando pueda comprobarse, `visto` y `fallido` son estados diferentes. El acuse institucional de lectura corresponde al estado `visto`, originado por la consulta autenticada del detalle.

### RN-08 — Recordatorios configurables

Los avisos no vistos generan recordatorios según una política institucional configurable. Como punto de conversación se propone un primer recordatorio 48 horas después de la publicación, un segundo 72 horas después del primero y una alerta humana al quinto día hábil; **todos estos plazos son supuestos por validar**.

### RN-09 — Corrección trazable

Una corrección conserva el valor anterior, nuevo valor, motivo, autor y fecha, y genera un aviso nuevo. El procedimiento para autorizar correcciones está pendiente de validación.

### RN-10 — Discrepancia sin cambio automático

Abrir, responder o escalar una discrepancia no cambia automáticamente una calificación. Toda modificación académica se ejecuta mediante el proceso de corrección autorizado.

### RN-11 — Estados de discrepancia

Se propone el catálogo `abierta`, `en revisión docente`, `respondida`, `escalada`, `en mediación`, `resuelta` y `cerrada`. **El catálogo, sus transiciones, responsables y causales son supuestos por validar**.

### RN-12 — Plazos de discrepancia

Se propone acuse institucional dentro de 1 día hábil, respuesta inicial docente dentro de 3 días hábiles y escalamiento al completar 5 días hábiles sin resolución. **Estos plazos son supuestos por validar** y no describen todavía una política vigente.

### RN-13 — Criterio profesional y mediación

El personal docente conserva el criterio evaluativo. La Dirección o figura mediadora formaliza la comunicación y verifica el procedimiento, pero no sustituye automáticamente ese criterio.

### RN-14 — Acceso a reportes

Los reportes individualizados están restringidos a roles con responsabilidad directa. Dirección puede consultar agregados institucionales; indicadores y nivel de desglose deben aprobarse antes del diseño final.

### RN-15 — Primaria como límite del MVP

El MVP se modela para educación primaria. Secundaria o colegio requiere validar nuevos actores, cantidad de docentes, participación del estudiante y protocolos de comunicación.

### RN-16 — Inteligencia artificial futura

La IA no forma parte del MVP. Una integración futura solo podrá proponer contenido o señalar patrones; requerirá revisión humana antes de cualquier uso, no podrá calificar ni resolver discrepancias y deberá superar una evaluación de privacidad, sesgo, exactitud y responsabilidad.

## 3. Restricciones y decisiones de alcance

- No se construye un LMS ni se imparten lecciones o evaluaciones en línea.
- No se incluyen matrícula, cobros ni integración nacional con el MEP.
- El Portal no garantiza por sí solo que una familia lea la información; registra y hace visible dónde la comunicación sigue pendiente.
- El medio físico continuará durante la transición hasta que la institución decida lo contrario.
- El canal inicial de aviso todavía no está confirmado. Portal y correo son una propuesta; SMS, WhatsApp y aplicación nativa permanecen fuera del MVP.
- La importación de datos históricos y la migración desde otros sistemas no están incluidas hasta conocer las fuentes reales.
- Las obligaciones legales específicas sobre datos de menores, conservación y consentimiento deben ser revisadas por la institución antes de implementación.

## 4. Verificación antes de aprobar la línea base

1. Confirmar con Dirección cada umbral marcado como propuesto.
2. Probar la matriz completa de permisos y vínculos.
3. Ejecutar los escenarios críticos de dos hitos, congruencia y corrección.
4. Simular avisos no vistos, reintentos, recordatorios y alerta final.
5. Recorrer una discrepancia desde apertura hasta cierre y escalamiento.
6. Realizar prueba breve de usabilidad con personas encargadas y docentes.
7. Revisar privacidad, accesibilidad, respaldo y bitácora antes de una prueba piloto.

