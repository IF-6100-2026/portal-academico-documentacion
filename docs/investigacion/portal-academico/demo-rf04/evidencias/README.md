# Evidencia real de la ejecución

Fecha: **4 de noviembre de 2026**, horas de Costa Rica (UTC−6). Ejecutor: **agente Codex**, sin revisión humana realizada. No se ha comprobado una cuenta gratuita.

## Resultados

| Evidencia | Resultado y uso |
| --- | --- |
| [Línea base](E04-linea-base-proxy-vigente.log) | 5 pruebas aprobadas antes de agregar los archivos. |
| [Fase roja](E05-rojo-sin-implementacion.log) | Fallo de compilación esperado: las clases nuevas todavía no existían. No se cuenta como defecto del agente. |
| [Backend con el cambio](E06-verde-backend.log) | 22 pruebas aprobadas: 5 existentes + 17 nuevas. |
| [Demo independiente](E07-verde-demo-independiente.log) | 17 pruebas aprobadas. |
| [Consola](E08-demo-consola.log) | 6 escenarios comprobados. |
| [Resumen estructurado](resumen.json) | Versiones, conteos y estado de revisión. |
| [Tamaño del cambio](diff-stat.txt) | 4 archivos nuevos, 148 líneas en el backend. |
| Carpetas `junit-linea-base`, `junit-backend` y `junit-demo` | Resultados XML de Surefire. Se quitó únicamente el bloque de propiedades del entorno; se conservaron casos y resultados. |

## Registro de comandos

Las duraciones incluyen el arranque de Maven y, cuando correspondió, descargas. No representan solamente tiempo de CPU de las pruebas.

| ID | Inicio | Fin | Segundos | Salida | Evidencia |
| --- | --- | --- | --- | --- | --- |
| E01-linea-base | 15:22:23 | 15:22:25 | 1.869 | 1 | [Registro](E01-linea-base.log) |
| E02-linea-base-configurada | 15:23:11 | 15:23:13 | 1.626 | 1 | [Registro](E02-linea-base-configurada.log) |
| E03-linea-base-diagnostico | 15:25:42 | 15:25:44 | 1.652 | 1 | [Registro](E03-linea-base-diagnostico.log) |
| E04-linea-base-proxy-vigente | 15:26:09 | 15:26:45 | 35.497 | 0 | [Registro](E04-linea-base-proxy-vigente.log) |
| E05-rojo-sin-implementacion | 15:26:57 | 15:27:01 | 3.382 | 1 | [Registro](E05-rojo-sin-implementacion.log) |
| E06-verde-backend | 15:27:37 | 15:27:45 | 7.594 | 0 | [Registro](E06-verde-backend.log) |
| E07-verde-demo-independiente | 15:27:51 | 15:27:55 | 3.641 | 0 | [Registro](E07-verde-demo-independiente.log) |
| E08-demo-consola | 15:28:04 | 15:28:05 | 0.912 | 0 | [Registro](E08-demo-consola.log) |

El registro comenzó a las **15:17:16**; la consola terminó a las **15:28:05**: un intervalo de **10 min 49 s**. Incluye preparación y esperas, pero no la lectura previa del repositorio, la redacción posterior ni una revisión humana. No es una medición de ahorro ni el tiempo total de todo el trabajo. Los ocho comandos registrados sumaron **56.173 s**.

## Incidencias y errores

1. **Entorno:** había Java 17 y faltaba Maven. El proyecto requiere Java 21. Se usaron distribuciones portables con sus comprobaciones de integridad, registradas en `herramientas.json`.
2. **Preparación del agente:** tras fallar una consulta de descarga, un paso intentó leer un manifiesto que no se había generado. Se corrigió obteniendo la distribución desde una fuente disponible. Esta incidencia se reconstruye de la sesión; no tiene duración individual ni log de proceso adjunto.
3. **Red:** E01 no resolvió el repositorio Maven. E02 terminó con código 1 y su archivo conserva solo la salida inicial; no permite asignar por sí solo una causa. E03 confirmó una conexión rechazada al proxy temporal.
4. **Preparación del agente:** se reutilizó una configuración de proxy que ya no estaba vigente entre ejecuciones. Se corrigió generando la configuración a partir del entorno vigente en cada comando. E04 pasó. La configuración privada no forma parte del paquete.
5. **Fase roja prevista:** E05 falló porque todavía no se había implementado el código. Es evidencia del orden pruebas→implementación, no un error funcional.

Se identificaron **dos errores de preparación del agente**; uno tiene respaldo en el diagnóstico y el otro se declara como reconstrucción de la sesión. No se detectaron errores funcionales en las 17 pruebas nuevas. No se asignan a V1–V5 porque esos controles humanos no se realizaron.

## Lo que falta registrar

- Aprobación del alcance y lectura de código/pruebas por el equipo.
- Ejecución humana en las computadoras de los integrantes y revisión por otra persona.
- Sesión y evidencia real de la modalidad gratuita requerida.
- Issue y PR publicados, comentarios y estado final. No hay nuevos commits ni PR de esta ejecución.

Los archivos contienen salidas reales. No se generaron capturas o comentarios para simular participación humana.
