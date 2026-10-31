# RS-02 — Trazabilidad: saber quién leyó qué y cuándo

| Campo | Detalle |
|---|---|
| Categoría | Seguridad · Responsabilidad y no repudio (ISO/IEC 25010) |
| Actores afectados | Docente (ve si la persona encargada leyó sus publicaciones y mensajes); Dirección (lo consulta para supervisar y atender quejas); persona encargada (ve si la docente leyó su mensaje) |
| Equivale en el PC1 a | RS-SEC-03 (reorientado a la lectura); absorbe el propósito de RF-35 y UC-11 |

## Necesidad

Hoy la docente sabe que la persona encargada recibió una evaluación solo cuando ve la firma en el cuaderno (E-04). Si el cuaderno o el reporte no llega al hogar (E-13), la docente no se entera, y la Dirección no tiene con qué responder una queja (E-14). En el Portal, la docente debe poder darse cuenta de que la persona encargada leyó lo que publicó o le escribió, y la Dirección debe poder comprobarlo (P-03, P-05).

## Requerimiento

El Portal deberá registrar la primera lectura de cada publicación, aviso y mensaje: quién lo leyó y la fecha y hora. Además, deberá mostrar esa lectura:

- a la **docente**, en cada evaluación, calificación y mensaje que publicó o envió;
- a la **persona encargada**, en cada mensaje que envió;
- a la **Dirección**, por estudiante o por publicación.

El registro no podrá modificarse.

## Criterio medible

- El **100 %** de las primeras lecturas de evaluaciones, calificaciones, avisos y mensajes queda registrado con persona, fecha y hora.
- La docente ve el estado «leído» o «no leído» de cada persona encargada destinataria, con la fecha y hora de la lectura, **1 minuto o menos** después de que ocurre.
- La docente identifica qué personas encargadas no han leído una publicación en **2 toques o menos** desde la evaluación.
- El **0 %** de los registros se puede editar o borrar desde el Portal. Un intento queda registrado como denegado.
- Los registros se conservan durante **5 años**.

> **Valores propuestos por el equipo.** El minuto, los toques y el plazo de retención son una propuesta; el plazo real de conservación lo definen la institución y la normativa aplicable.

## Método de verificación

- Publicar una evaluación, abrirla con una persona encargada y comprobar que la docente ve la lectura con su fecha y hora ([TP-CU03-FB](../../04-pruebas/CU-03-publicar-evaluacion-asignada.feature)).
- Enviar un mensaje y comprobar que quien lo envió ve cuándo fue leído ([TP-CU06-FB](../../04-pruebas/CU-06-comunicarse-sobre-un-estudiante.feature)).
- Intentar modificar un registro con cada rol.

## Efecto en los casos de uso

| CU | Efecto concreto |
|---|---|
| [CU-03](../../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) y [CU-04](../../03-casos-de-uso/CU-04-publicar-calificacion.md) | Paso 11: la docente ve, por estudiante, si cada persona encargada leyó la publicación y cuándo. Reemplaza la revisión de la firma en el cuaderno. |
| [CU-06](../../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | Paso 7: al abrir el mensaje, el Portal registra la lectura y quien lo envió la ve con fecha y hora. |
| [CU-05](../../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | El acuse (paso 7) es la primera lectura y no se reemplaza con aperturas posteriores (A06). |
| [CU-01](../../03-casos-de-uso/CU-01-consultar-progreso-academico.md) | El paso 9 registra la consulta; también se registran los intentos denegados. |
| [CU-08](../../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) y [CU-12](../../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | La Dirección consulta el registro de lecturas para supervisar y para responder una queja con evidencia. |
