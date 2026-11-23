# Registro de uso de IA

## Asistencia utilizada

Se utilizó Codex (modelo GPT-6) como asistente durante la preparación de PC3, además de Git, PowerShell, Maven, npm y Docker Compose para revisar, editar, compilar y ejecutar la solución. La automatización del navegador se usó para consultar Mediación Virtual y verificar la aplicación local. GitHub se consultó para ubicar el repositorio del proyecto. La fuente de alcance académico fue la descripción del PC3 y la retroalimentación disponible en Mediación Virtual.

## Trabajo asistido

- Revisión de los artefactos PC2, los comentarios del profesor y los lineamientos PC3.
- Propuesta de CU-01 y CU-02 como núcleo sin atribuir aprobación inexistente.
- Implementación de una API de demostración, migración de base de datos y UI para consulta de progreso y administración de vínculos.
- Revisión/actualización de trazabilidad, casos de uso, escenarios Gherkin, diagramas, decisiones, brechas y guías.
- Implementación de publicaciones recientes, avisos pendientes, historial de corrección y bloqueo de acceso para vínculos pendientes.
- Preparación y ejecución de comprobaciones de compilación, API y documentación (los resultados concretos se registran en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md)).
- Búsqueda del material público del curso sobre Claude Code, skills y SDD; elaboración asistida de constitución, spec, plan y tasks para CU-16. La especificación y el plan requieren revisión humana.
- Implementación local de CU-16: handler Java, endpoint protegido, migración de auditoría de alta y formulario React. Se ejecutaron `mvn test` (8 pruebas, 0 fallos), `npm run build`, el validador documental y una comprobación manual de endpoint/migración contra PostgreSQL temporal. Falta automatizar la integración y completar la revisión humana.
- Descomposición provisional de RF-27/RF-28 en CU-17 a CU-21 y sus escenarios Gherkin. Los actores, el modelo curso/grupo, la identidad docente y las reglas de inscripción están marcados como supuestos para revisión; no se generó código para esos casos.
- Se cargó el modelo C4 Nivel 1 en IcePanel como objetos y relaciones explícitos a partir de los flujos documentados. No se invocó la generación automática de diagramas; F-35 a F-39 quedan con estado futuro y requieren revisión del grupo.

## Revisión humana pendiente

La IA puede haber interpretado de forma incompleta la rúbrica, los patrones de referencia o el contexto institucional. El grupo debe revisar el código y los documentos, confirmar el alcance con el profesor cuando sea posible y comprobar el resultado de la entrega. La selección CU-01 + CU-02 se declara como propuesta del equipo; no se le atribuye aprobación docente. Este registro no atribuye trabajo a un integrante que no lo haya confirmado.
