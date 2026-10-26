# Portal Académico — Centro Educativo María Montessori

## Definición del problema y visión

**Curso:** IF-6100 Análisis y Diseño de Sistemas  
**Etapa:** Inicio del Proceso Unificado  
**Alcance del MVP:** educación primaria  
**Nombre canónico:** Portal Académico — Centro Educativo María Montessori  
**Antecedente de nombre:** *Conecta Montessori* fue un nombre provisional anterior y no debe utilizarse como nombre principal en los demás artefactos.

## 1. Criterio de evidencia

Este documento separa lo que la organización ya confirmó de lo que todavía debe validarse. Una afirmación marcada como hipótesis no se presenta como hecho ni se usa para justificar por sí sola una decisión de alcance.

### 1.1 Evidencia confirmada por la Dirección

- Las calificaciones se comunican mediante hojas impresas.
- El estudiante transporta la hoja desde la escuela hasta el hogar y, por tanto, actúa como intermediario.
- Se han presentado retrasos y ocultamiento de algunas hojas por parte de estudiantes.
- Personas encargadas han formulado quejas por no recibir oportunamente esa información.

### 1.2 Hipótesis que requieren validación

- La frecuencia y magnitud de los retrasos u omisiones.
- El recorrido exacto de la hoja, incluidas firmas, copias, devoluciones y responsables.
- Los medios actuales para comunicar tareas, observaciones y evaluaciones antes de calificarlas.
- La carga de trabajo que generan las comunicaciones repetidas, llamadas, reuniones o reclamos.
- La existencia de plazos, responsables, estados y una figura mediadora para atender discrepancias.
- Los canales digitales disponibles para las familias y sus condiciones reales de conectividad.

El registro completo de supuestos y su plan de validación se encuentra en `09-supuestos-validaciones.md`.

## 2. Situación actual

En el Centro Educativo María Montessori, las calificaciones se comunican mediante hojas impresas que el estudiante lleva al hogar. Este mecanismo coloca al estudiante entre la institución y la persona encargada en una comunicación académica relevante.

La Dirección ha reportado que algunas hojas llegan tarde o son ocultadas y que personas encargadas han presentado quejas. Con la evidencia disponible todavía no se puede cuantificar cuántos casos ocurren, cuánto tardan ni qué pasos institucionales se siguen después de una queja; esos datos deben levantarse antes de cerrar el análisis.

## 3. Problema por resolver

La institución no dispone de un canal institucional directo y verificable que permita a las personas encargadas conocer oportunamente la información académica que les corresponde. La recepción de las calificaciones depende de que el estudiante entregue la hoja impresa, por lo que un retraso u ocultamiento interrumpe la comunicación aunque el personal docente ya haya generado la información.

Esta interrupción puede retrasar el acompañamiento desde el hogar y origina quejas hacia la institución. También impide comprobar, con la evidencia actualmente disponible, cuándo la información quedó disponible para la familia y cuándo fue consultada. El proyecto no asume todavía que toda comunicación, tarea o reclamo siga el mismo proceso: esos aspectos se modelarán únicamente después de validarlos con la organización.

## 4. Población y contexto del MVP

El MVP se limita al nivel de **educación primaria** del Centro Educativo María Montessori. Sus usuarios humanos previstos son el personal docente, las personas encargadas legalmente vinculadas con cada estudiante, la Dirección o figura institucional que atienda escalaciones, y el personal que administre cuentas y relaciones de acceso.

No se promete una solución común para colegios o secundaria. En esos niveles podrían cambiar la cantidad de docentes por estudiante, la autonomía del estudiante, los roles de orientación y coordinación, y los protocolos de comunicación; una adaptación requeriría un levantamiento independiente.

## 5. Objetivo del MVP

Establecer un medio institucional directo, oportuno y trazable para comunicar el seguimiento académico del estudiantado de primaria a sus personas encargadas, reduciendo la dependencia del estudiante como intermediario y permitiendo detectar comunicaciones que aún no se han completado.

El MVP deberá:

- facilitar acceso independiente a la información de estudiantes previamente vinculados por la institución;
- distinguir la entrega de una evaluación para realizarla de su devolución calificada;
- conservar evidencia de publicación, envío y consulta sin tratar esos estados como equivalentes;
- permitir recordatorios configurables cuando una publicación no ha sido consultada;
- conservar mensajes y discrepancias asociados con el estudiante y el registro académico correspondiente;
- aportar reportes acotados de rendimiento y comunicación a la Dirección;
- reducir trabajo repetitivo mediante registro por grupo, carga tabular y avisos automáticos;
- mantener congruencia entre lo publicado y el medio físico oficial mientras ambos coexistan.

## 6. Visión del sistema

Para el personal docente, la Dirección y las personas encargadas del estudiantado de primaria del Centro Educativo María Montessori que necesitan compartir y consultar el seguimiento académico de forma directa, oportuna y verificable, **Portal Académico** es un sistema de información que centraliza los eventos académicos y la comunicación asociada, facilitando un acompañamiento temprano sin depender del estudiante como intermediario. Mantiene el criterio evaluativo del personal docente y da trazabilidad institucional a publicaciones, consultas y discrepancias.

## 7. Alcance funcional inicial

### Incluido en el MVP

- Vinculación institucional entre estudiantes y personas encargadas autorizadas.
- Consulta del perfil académico y del historial por periodo y materia.
- Registro eficiente de evaluaciones y resultados por grupo.
- Publicación de dos hitos: evaluación entregada para realizarse y evaluación devuelta con calificación.
- Confirmación docente de congruencia con el medio físico oficial antes de publicar cada hito.
- Avisos automáticos, registro de consulta, recordatorios configurables y alertas de seguimiento.
- Mensajería institucional contextualizada por estudiante.
- Registro, atención, escalamiento y cierre trazable de discrepancias.
- Corrección controlada de registros sin borrar la versión anterior.
- Reportes acotados de rendimiento, consulta de avisos y estado de discrepancias.

### Fuera del MVP

- Matrícula, cobros y administración financiera.
- Impartición de clases o evaluación en línea.
- Calificación automática.
- Acceso directo del estudiante con cuenta propia.
- Implementación completa para secundaria o colegios.
- Integración con sistemas nacionales del MEP.
- Aplicaciones móviles nativas, SMS o mensajería comercial, salvo que se aprueben posteriormente.
- Decisiones automáticas mediante inteligencia artificial.

## 8. Principios de solución

1. **Acceso con vínculo verificado.** Una persona encargada solo consulta estudiantes cuya relación haya sido validada por la institución.
2. **Eficiencia docente.** Una evaluación se registra una vez para el grupo; las calificaciones se cargan de forma conjunta y los avisos se generan sin redactar un mensaje individual por estudiante.
3. **Dos hitos distintos.** La asignación o entrega de la evaluación y su devolución calificada son eventos diferentes y producen avisos diferentes.
4. **Congruencia físico-digital.** Mientras el medio físico continúe vigente, la publicación requiere confirmación docente de que ambos registros corresponden al mismo evento. El sistema conserva quién confirmó y cuándo; no pretende verificar físicamente un cuaderno.
5. **Trazabilidad, no vigilancia absoluta.** Publicar, enviar, entregar por un proveedor y consultar son estados distintos. El acuse institucional corresponde a la consulta registrada en el Portal.
6. **Intervención humana.** Una discrepancia no cambia automáticamente una calificación y una mediación no sustituye el criterio profesional del docente.
7. **IA futura y supervisada.** Una eventual integración de IA solo podría apoyar resúmenes o detección de pendientes, siempre con revisión humana, privacidad y aprobación institucional; no forma parte del MVP.

## 9. Resultados esperados y medición propuesta

Los siguientes indicadores son **propuestas por validar**, no compromisos confirmados:

- porcentaje de publicaciones consultadas por al menos una persona encargada autorizada;
- tiempo transcurrido entre publicación y primera consulta;
- cantidad de publicaciones pendientes después de los recordatorios configurados;
- tiempo docente requerido para registrar una evaluación y sus resultados por grupo;
- cantidad y antigüedad de discrepancias abiertas, escaladas y cerradas;
- cantidad de correcciones con historial completo y nueva notificación.

Las metas numéricas se definirán con la Dirección después de obtener una línea base del proceso actual.

