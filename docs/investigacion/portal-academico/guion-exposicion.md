# Guion de exposición

Duración propuesta: 18 minutos, con dos minutos de margen. Cada integrante prepara las tres partes para la rifa. Los resultados técnicos están documentados; antes de presentar deben completar la revisión humana y la sesión gratuita requerida.

| Diapositiva | Parte | Tema | Tiempo |
| --- | --- | --- | --- |
| 1 | Portada | Agentes de generación
de código | 0:30 |
| 2 | Parte 1 | Conceptos y punto de partida | 1:30 |
| 3 | Parte 1 | Tipos de asistencia | 1:15 |
| 4 | Parte 1 | Evidencia sobre productividad | 1:45 |
| 5 | Parte 2 | Prueba en Java con Codex | 1:15 |
| 6 | Parte 2 | División de labores ingeniero / IA | 1:30 |
| 7 | Parte 2 | Flujo ejecutado y revisión pendiente | 2:00 |
| 8 | Parte 2 | Demostración y evidencia | 2:00 |
| 9 | Parte 3 | Resultados de la prueba | 2:00 |
| 10 | Parte 3 | Riesgos y controles | 1:30 |
| 11 | Parte 3 | Recomendaciones para el proyecto | 1:30 |
| 12 | Parte 3 | Declaración de uso de IA | 0:45 |
| 13 | Parte 3 | Fuentes para defender el análisis | 0:30 |

## Demostración de dos minutos

Abrir `demo-rf04/ISSUE.md`, ejecutar `demo.ps1` o `bash demo.sh`, y mostrar el caso de fecha anterior y el mensaje de corrección. Abrir el método `ca03RechazaEntregaAnteriorEIdentificaElCampo`. Si no se puede ejecutar, usar el registro E08. Explicar que este validador es una parte de RF-04 y todavía no tiene interfaz ni persistencia.

## Notas por diapositiva

### 1. Agentes de generación
de código

Tema 2, proyecto Portal Académico. Se identificaron dos integrantes. El enunciado pide tres; falta confirmar una excepción o incorporar el tercer nombre. La ejecución técnica de RF-04 ya tiene registros. La revisión humana, la sesión gratuita y el PR están pendientes. Fuentes: instrucciones entregadas por el usuario y demo-rf04/evidencias/README.md.

### 2. Conceptos y punto de partida

Explicar la diferencia entre un modelo y un agente: el agente añade herramientas, acciones y observaciones. Conectar la definición con una tarea Java concreta. No confundir capacidad con corrección garantizada. Fuentes: Wang et al. (2024), https://doi.org/10.1007/s11704-024-40231-1 ; Zhao et al., https://arxiv.org/abs/2303.18223

### 3. Tipos de asistencia

Distinguir el chat que propone de un agente que puede actuar. La ubicación local o remota no decide por sí sola cuánta autonomía existe: los permisos y la revisión importan. Fuentes: Wang et al. (2024), https://doi.org/10.1007/s11704-024-40231-1 ; OpenAI, https://learn.chatgpt.com/docs/permission-modes

### 4. Evidencia sobre productividad

Cui et al. combinaron tres experimentos de campo con 4.867 desarrolladores y autocompletado. Becker et al. asignaron 246 tareas a 16 desarrolladores experimentados en repositorios conocidos, usando sobre todo Cursor Pro y Claude de principios de 2025. Son métricas y herramientas distintas. No presentar estos porcentajes como resultados de Codex. Fuentes: https://www.microsoft.com/en-us/research/publication/the-effects-of-generative-ai-on-high-skilled-work-evidence-from-three-field-experiments-with-software-developers/ ; https://arxiv.org/abs/2507.09089

### 5. Prueba en Java con Codex

La regla corresponde al segundo criterio de RF-04. Se permite entrega igual o posterior, y fecha de entrega ausente cuando no aplica. Los campos obligatorios son nombre, tipo, fecha de asignación e instrucciones. No se implementaron persistencia, autorización, grupos ni un endpoint. Fuente del requisito: https://github.com/IF-6100-2026/portal-academico-documentacion/blob/9515bd6370c090e09bea0dcafc170e632ced7e43/docs/02-requerimientos/funcionales/RF-04-registrar-evaluacion-asignada.md . Ver demo-rf04/ISSUE.md y evidencias/resumen.json.

### 6. División de labores ingeniero / IA

Esta tabla describe la participación real. El usuario autorizó la preparación. La IA eligió la regla, redactó los criterios, escribió código y pruebas y ejecutó los comandos. No hubo aprobaciones humanas entre pasos. Los controles V1–V5 del protocolo siguen pendientes. Fuente: investigacion.md, sección 4 y demo-rf04/PLAN.md.

### 7. Flujo ejecutado y revisión pendiente

Seguir el flujo: la IA lee el requisito, prepara el entorno y las pruebas, implementa y comprueba el resultado. Si falla, diagnostica, corrige y repite. Se conservan las salidas y el parche. El tramo de revisión humana todavía no ocurrió: el equipo debe leer el cambio y reproducir los resultados antes del commit y PR. Otra persona debe revisar ese PR. La ejecución se apartó del protocolo de aprobación paso a paso; esta desviación se declara. Fuente: sección 4 y bitácora.

### 8. Demostración y evidencia

Mostrar durante dos minutos la consola: un caso aceptado, una fecha anterior y campos ausentes. Abrir el test de CA-03 para vincular criterio y aserción. Si no se puede ejecutar, mostrar demo-rf04/evidencias/E08-demo-consola.log y E06-verde-backend.log. Son salidas reales. No afirmar que existe un PR publicado. La fase roja es un fallo esperado, no un error del agente. Fuente: archivos de evidencia entregados.

### 9. Resultados de la prueba

Las 17 pruebas nuevas cubren cuatro criterios; las 22 incluyen las cinco anteriores. No hubo fallos, errores ni pruebas omitidas en las suites finales. El intervalo 15:17:16–15:28:05 incluye preparación y esperas. Excluye lectura previa, redacción posterior y revisión humana. Los ocho comandos sumaron 56,173 s. No calcular ahorro. Dos errores de preparación del agente: intentó leer un manifiesto ausente y reutilizó un proxy temporal vencido. El primero es un registro reconstruido de la sesión, sin log adjunto; el segundo cuenta con E03. E05 fue una fase roja prevista. Fuente: demo-rf04/evidencias/README.md y ejecuciones.jsonl.

### 10. Riesgos y controles

Las cifras de los estudios describen sus muestras, no la tasa de errores de nuestra demo. Pearce et al. investigaron escenarios diseñados alrededor de debilidades de seguridad. Spracklen et al. estudiaron Python y JavaScript. Presentar solo como observados los riesgos documentados en la bitácora. Fuentes: https://arxiv.org/abs/2108.09293 ; https://arxiv.org/abs/2211.03622 ; https://www.usenix.org/conference/usenixsecurity25/presentation/spracklen ; https://www.apache.org/legal/generative-tooling.html En esta ejecución se observó que el mismo agente generó código, pruebas y revisión. La confianza en la cobertura necesita revisión humana. No se detectaron dependencias inventadas ni defectos funcionales en los casos ejecutados. Fuente propia: demo-rf04/REVISION-AGENTE.md.

### 11. Recomendaciones para el proyecto

Relacionar las recomendaciones con lo observado: la preparación falló antes de ejecutar la regla; los criterios permitieron organizar 17 pruebas y revisar 148 líneas nuevas; el mismo agente escribió pruebas e implementación. Completar la revisión humana y la sesión gratuita antes de entregar. La muestra no demuestra mejoras universales de productividad. Fuentes: bitácora y sección 8 de investigacion.md.

### 12. Declaración de uso de IA

El borrador inicial declara Claude Code para investigación y redacción. ChatGPT/Codex preparó esta revisión, la presentación y la ejecución técnica de RF-04. El usuario autorizó la preparación, pero no realizó revisiones detalladas entre pasos. No se atribuyen cambios de código a los integrantes. La modalidad gratuita no está comprobada. Fuente: declaración de uso de IA en investigacion.md.

### 13. Fuentes para defender el análisis

Las referencias completas están en investigacion.md. Cada integrante debe conocer la pregunta, el método, el resultado y el límite de las fuentes que cite. Preparar todas las partes, ya que la rifa define quién expone cada una. Referencias:
https://www.microsoft.com/en-us/research/publication/the-effects-of-generative-ai-on-high-skilled-work-evidence-from-three-field-experiments-with-software-developers/
https://arxiv.org/abs/2507.09089
https://www.usenix.org/conference/usenixsecurity25/presentation/spracklen
https://learn.chatgpt.com/docs/pricing
https://www.apache.org/legal/generative-tooling.html
