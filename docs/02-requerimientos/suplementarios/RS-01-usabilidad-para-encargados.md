# RS-01 — Usabilidad para personas encargadas con poca experiencia digital

| Campo | Detalle |
|---|---|
| Categoría | Usabilidad (FURPS+) · Facilidad de aprendizaje y adecuación reconocible (ISO/IEC 25010) |
| Actor afectado | Persona encargada legal |
| Equivale en el PC1 a | RS-USA-01 (reformulado); incorpora parte de RS-USA-03 y RS-POR-01 |

## Necesidad

Hoy la persona encargada recibe la información en papel: el cuaderno de la asignatura y el reporte físico (E-02, E-03). Al pasar a un medio digital, el Portal no puede suponer que domina la tecnología. Si consultar una nota o apelar se vuelve difícil, la familia sigue dependiendo del cuaderno y del estudiante, que es justo el problema P-02.

## Requerimiento

Una persona encargada con poca experiencia digital deberá poder completar, sin ayuda y desde un teléfono, las tareas principales del Portal.

## Criterio medible

Se considera cumplido cuando, en una prueba moderada con **5 personas encargadas** que declaren usar el teléfono solo para llamadas, mensajería o redes sociales:

| Tarea | Meta propuesta |
|---|---|
| Ver la última calificación publicada de su estudiante | 4 de 5 sin ayuda, en 2 minutos o menos |
| Reconocer qué avisos tiene pendientes | 4 de 5 sin ayuda |
| Presentar una queja por información no recibida | 4 de 5 sin ayuda, en 4 minutos o menos |
| Presentar una apelación con una foto como respaldo | 4 de 5 sin ayuda, en 5 minutos o menos |

Además:

- Desde el inicio de sesión, la última calificación está a **3 toques o menos**.
- Todas las tareas se completan en una pantalla de **360 px** de ancho sin desplazamiento horizontal.
- Los textos de la interfaz no usan términos técnicos («hito», «estado», «registro»). Cada mensaje de error dice qué hacer para continuar.

> **Valores propuestos por el equipo.** Las cantidades, tiempos y anchos son una propuesta para volver verificable el requerimiento; no son una política confirmada por la institución. Se validarán en la prueba piloto.

## Método de verificación

Prueba moderada con guion y cronómetro, aplicada sobre el prototipo y luego sobre el sistema. Se registra éxito o fracaso por tarea, tiempo y puntos de ayuda solicitados.

## Efecto en los casos de uso

| CU | Efecto concreto |
|---|---|
| [CU-01](../../03-casos-de-uso/CU-01-consultar-progreso-academico.md) | El paso 3 muestra al entrar las últimas publicaciones y los avisos pendientes del estudiante, sin buscar por periodo. |
| [CU-05](../../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | Los avisos pendientes se distinguen visualmente de los consultados y el recordatorio lleva directo al detalle. |
| [CU-07](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | La apelación se inicia desde la calificación (paso 1) y acepta una foto como respaldo (paso 3); el estado se muestra con palabras simples. |
| [CU-12](../../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | Antes de registrar la queja se muestra lo ya publicado (paso 3), para que la persona encuentre la información sin quejarse si ya existe. |
