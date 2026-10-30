# Problema y visión

**Sistema:** Portal Académico — Centro Educativo María Montessori
**Alcance:** educación primaria del Centro Educativo María Montessori
**Fase:** Elaboración (PC2) · versión del 26 de octubre de 2026

Este documento define el problema a partir de la evidencia del levantamiento. Cada afirmación sobre el proceso actual cita un identificador `E-xx` de la tabla de [evidencia](#evidencia). Lo que todavía no está confirmado aparece en [supuestos abiertos](#supuestos-abiertos) y no se usa para justificar requerimientos.

## Evidencia

Fuentes:

- **Entrevista semiestructurada** con Daisy Quirós Quesada, directora del Centro Educativo María Montessori, realizada por mensajería el 19 de septiembre de 2026 (diez preguntas y un caso real).
- **Levantamiento inicial con la Dirección** registrado en el PC1 (septiembre de 2026).

| ID | Hecho confirmado | Fuente |
|---|---|---|
| E-01 | El progreso académico se registra en una plataforma digital y también en un registro físico. | Entrevista, pregunta 1 |
| E-02 | Las evaluaciones diarias se anotan en el cuaderno de cada asignatura. | Entrevista, pregunta 2 |
| E-03 | Los reportes semestrales se entregan en físico a las personas encargadas. | Entrevista, pregunta 2 |
| E-04 | Para comprobar que la persona encargada recibió la información se pide su firma de recibido en el cuaderno de la asignatura. | Entrevista, pregunta 3 |
| E-05 | La directora considera que sí se informa oportunamente. | Entrevista, pregunta 4 |
| E-06 | La Dirección supervisa el seguimiento académico. | Entrevista, pregunta 5 |
| E-07 | La persona encargada presenta una apelación mediante el cuaderno de recados, y en ella participa el Comité de Evaluación. | Entrevista, pregunta 6 |
| E-08 | Existe un plazo para resolver una apelación, y depende de la situación. | Entrevista, pregunta 7 |
| E-09 | El proceso genera poca carga administrativa. | Entrevista, pregunta 8 |
| E-10 | El sistema debería mostrar el progreso académico, las evaluaciones y el registro de notas, y ofrecer formularios, calificaciones y mensajería. | Entrevista, preguntas 9 y 10 |
| E-11 | Caso real: una madre apeló varios puntos de una prueba. El Comité de Evaluación, junto con la docente y la Dirección, gestionó el caso, emitió la resolución y la comunicó. | Entrevista, caso compartido |
| E-12 | Las calificaciones se comunican en hojas impresas que el estudiante lleva al hogar; el estudiante actúa como intermediario. | Levantamiento inicial |
| E-13 | Algunos estudiantes han retrasado u ocultado esas hojas. | Levantamiento inicial |
| E-14 | Algunas personas encargadas se han quejado de no recibir la información a tiempo. | Levantamiento inicial |

**Cómo se concilian E-05 y E-14.** La directora considera que la institución sí informa a tiempo, pero el levantamiento inicial registra quejas concretas. No se contradicen: la escuela entrega la información, y algunas veces no llega al hogar porque depende del estudiante (E-12, E-13). Por eso la queja se trata como un caso que ocurre, no como una falla general, y no se cuantifica.

## Situación actual

La escuela combina medios digitales y físicos (E-01). Las evaluaciones diarias se anotan en el cuaderno de cada asignatura (E-02), y la persona encargada firma de recibido en ese mismo cuaderno (E-04). Las calificaciones del periodo se entregan en un reporte físico (E-03) que llega al hogar por medio del estudiante (E-12). La Dirección supervisa (E-06). Cuando una persona encargada no está de acuerdo con una calificación, escribe en el cuaderno de recados, y el Comité de Evaluación atiende el caso con la docente y la Dirección (E-07, E-11).

El proceso actual está modelado en [BPMN](bpmn/trazabilidad-bpmn-rf.md).

## Problemas

| ID | Problema | Evidencia | Dónde se ve en el BPMN |
|---|---|---|---|
| P-01 | La información académica de un estudiante está repartida entre la plataforma, los cuadernos de asignatura y los reportes físicos. No existe un lugar único para consultarla. | E-01, E-02, E-03, E-10 | AC-01, AC-06 |
| P-02 | La llegada de la información al hogar depende de que el estudiante lleve el cuaderno o el reporte. Si lo retrasa u oculta, la persona encargada se entera tarde y presenta quejas. | E-12, E-13, E-14 | AC-02, AC-08, AC-10, AC-11 |
| P-03 | La única constancia de recibido es la firma en el cuaderno. No existe registro de cuándo se consultó la información ni forma de recordarla cuando no llega. | E-04, E-13 | AC-03 |
| P-04 | Las apelaciones se tramitan en papel (cuaderno de recados) y en ellas intervienen el Comité, la docente y la Dirección. La evidencia, el estado y el plazo de cada caso quedan dispersos. | E-07, E-08, E-11 | AC-04, AC-05, SP-01 |
| P-05 | La Dirección supervisa el seguimiento, pero no tiene una vista consolidada de lo pendiente: información no recibida, quejas y apelaciones en curso. | E-06, E-14 | AC-11, SP-01 |

**Lo que no es el problema central:**

- La carga administrativa. La directora la considera poca (E-09), así que solo se medirá como indicador.
- La autenticación y los permisos. Son condiciones de seguridad del diseño ([RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)), no la necesidad de los actores.
- La falta de tecnología. La escuela ya usa una plataforma (E-01); lo que falta es integrar los medios y dejar evidencia.

## Épicas

Los requerimientos funcionales se agrupan en tres épicas orientadas al problema. Cada una corresponde a un objetivo de los actores:

1. **Registro y consulta de notas y evaluaciones** (RF-01 a RF-09). La docente quiere registrar y publicar una sola vez las evaluaciones y calificaciones, de forma coherente con el cuaderno de la asignatura. La persona encargada quiere consultarlas sin depender de que el estudiante lleve el cuaderno o el reporte (P-01, P-02).
2. **Comunicación entre la escuela y la persona encargada** (RF-10 a RF-16). La persona encargada quiere recibir avisos, conversar con la docente y reclamar cuando algo no le llega. La docente quiere darse cuenta de que la persona encargada leyó lo que publicó o le escribió (P-02, P-03).
3. **Seguimiento de apelaciones y pendientes** (RF-17 a RF-25). La persona encargada quiere apelar una calificación y conocer el estado de su caso. El Comité de Evaluación quiere resolverlo con la evidencia completa. La Dirección quiere supervisar los pendientes y los plazos (P-04, P-05).

## Visión

Para las personas encargadas, el personal docente, el Comité de Evaluación y la Dirección del nivel de primaria del Centro Educativo María Montessori, que hoy reparten el seguimiento académico entre una plataforma, cuadernos y reportes físicos, **Portal Académico** es un sistema web institucional que reúne en un solo lugar las evaluaciones, las calificaciones, los avisos, los mensajes, las quejas y las apelaciones de cada estudiante. Deja registro de cuándo se publicó y cuándo se consultó cada dato.

A diferencia del cuaderno y del reporte físico, el Portal no depende de que el estudiante lleve la información al hogar, y conserva el historial de cada apelación con sus responsables y su resolución. Respeta el proceso vigente: la docente califica, el Comité de Evaluación resuelve las apelaciones y la Dirección supervisa. El Portal no decide ni cambia calificaciones por su cuenta.

## Alcance del MVP

**Incluido:**

- Vinculación de cada estudiante con sus personas encargadas autorizadas.
- Consulta del perfil, las evaluaciones y las calificaciones publicadas por periodo y materia.
- Publicación de dos hitos por evaluación: la evaluación asignada y la calificación devuelta, con confirmación de que coinciden con el cuaderno de la asignatura.
- Corrección de calificaciones con historial.
- Avisos dentro del Portal, acuse de consulta, recordatorios y alertas a la Dirección.
- Mensajería entre la persona encargada y la docente, asociada al estudiante.
- Quejas por información no recibida, atendidas por la Dirección.
- Apelaciones de calificación atendidas por el Comité de Evaluación con la docente y la Dirección.
- Supervisión del seguimiento por la Dirección y configuración de plazos.

**Fuera del MVP:**

- Matrícula, cobros, expedientes administrativos y lecciones o pruebas en línea.
- Cuenta propia para el estudiante.
- Secundaria y otros centros educativos.
- Integración con la plataforma digital que ya usa la escuela o con sistemas del MEP.
- Avisos por correo, SMS o mensajería comercial. El MVP avisa dentro del Portal.
- Formularios institucionales configurables (E-10). Quedan para una siguiente versión.
- Cualquier decisión tomada por inteligencia artificial.

## Indicadores propuestos

Son propuestas del equipo para medir el efecto del MVP; no son metas aprobadas. La línea base se tomaría en una prueba piloto.

- Porcentaje de publicaciones consultadas por al menos una persona encargada.
- Tiempo entre la publicación y la primera consulta.
- Cantidad de quejas por información no recibida por periodo.
- Apelaciones abiertas, resueltas y fuera de plazo.
- Tiempo docente para publicar una evaluación (solo como indicador, por E-09).

## Supuestos abiertos

No se usan como hechos; si se confirman o se descartan, se actualiza la cadena de trazabilidad.

| ID | Supuesto | Dónde impacta |
|---|---|---|
| S-01 | La «hoja impresa» del levantamiento inicial (E-12) es el reporte físico de calificaciones (E-03). | BPMN, ruta del reporte |
| S-02 | Quien registra los vínculos entre personas encargadas y estudiantes es la Dirección. | [CU-02](../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) |
| S-03 | Los estados de la apelación son presentada, en revisión, pendiente de información, resuelta y cerrada. | [CU-07](../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| S-04 | Un estudiante puede tener varias personas encargadas, y una persona encargada, varios estudiantes. | [CU-01](../03-casos-de-uso/CU-01-consultar-progreso-academico.md), [CU-02](../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) |
