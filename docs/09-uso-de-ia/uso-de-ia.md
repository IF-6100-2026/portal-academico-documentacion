# Registro de uso de IA

## Asistencia utilizada

Se utilizó Codex (modelo GPT-6) como asistente durante la preparación de PC3, y Josué Delgado Corrales usó Claude (sección [Uso de Claude](#uso-de-claude-josué-delgado-corrales)), además de Git, PowerShell, Maven, npm y Docker Compose para revisar, editar, compilar y ejecutar la solución. La automatización del navegador se usó para consultar Mediación Virtual y verificar la aplicación local. GitHub se consultó para ubicar el repositorio del proyecto. La fuente de alcance académico fue la descripción del PC3 y la retroalimentación disponible en Mediación Virtual.

## Herramientas, skills y método

| Herramienta o skill | Uso en el proyecto |
|---|---|
| Codex (GPT-6) | Análisis de retroalimentación, elaboración de artefactos, cambios de documentación, implementación de backend/frontend y apoyo en verificación; el grupo debe revisar el resultado. |
| Flujo SDD de la sesión B (Constitution → Specify → Plan → Tasks → Implement) | Organización de CU-16 en `.specify/memory/constitution.md` y `specs/001-registrar-estudiante/`; se aplicó el método documentado en clase, sin afirmar que se instaló una skill del profesor. |
| Skill `computer-use` de Codex | Navegación visual de Mediación Virtual, GitHub y la aplicación local; no generó código del producto. |
| Skills del profesor | No se encontró un paquete público instalable con `SKILL.md` en los repositorios revisados. Se consultó el material educativo público de la sesión B y no se declara haber importado una skill que no se encontró. |

## Trabajo asistido

- Revisión de los artefactos PC2, los comentarios del profesor y los lineamientos PC3.
- Propuesta de CU-01 y CU-02 como núcleo sin atribuir aprobación inexistente.
- Implementación de una API de demostración, migraciones de base de datos y UI para consulta de progreso y administración de vínculos.
- Revisión/actualización de trazabilidad, casos de uso, escenarios Gherkin, diagramas, decisiones, brechas y guías.
- Implementación de publicaciones recientes, avisos pendientes, historial de corrección y bloqueo de acceso para vínculos pendientes.
- Preparación y ejecución de comprobaciones de compilación, API y documentación (los resultados concretos se registran en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md)).
- Búsqueda del material público del curso sobre Claude Code, skills y SDD; elaboración asistida de constitución, spec, plan y tasks para CU-16. La especificación y el plan requieren revisión humana.
- Implementación de CU-16: handler Java, endpoint protegido, migración de auditoría de alta y formulario React. Se ejecutaron pruebas unitarias y una comprobación manual de endpoint/migración contra PostgreSQL temporal.
- Implementación provisional de CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21: flujos Java, migraciones Liquibase, controladores HTTP y vistas React por rol. Cada caso nuevo tuvo al menos una comprobación de flujo principal HTTP en base aislada. Los actores, la escala, los plazos, el modelo curso/grupo, la identidad docente y otros criterios no confirmados se describen como supuestos.
- Verificación final integrada mediante los PR #26 y #27: `mvn test` (23 pruebas, 0 fallos, 0 errores, 0 omitidas, Checkstyle limpio), `npm run build`, validador documental (20 CU, 20 features y 103 escenarios escritos) y smoke HTTP contra PostgreSQL 17 temporal. Los Gherkin no se ejecutaron en bloque como suite Cucumber; los resultados y límites están en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md).
- Se cargó el modelo C4 Nivel 1 en IcePanel como objetos y relaciones explícitos a partir de los flujos documentados. No se invocó la generación automática de diagramas; F-35 a F-39 quedan con estado futuro y requieren revisión del grupo.

## Uso de Claude (Josué Delgado Corrales)

### Herramienta, modelo y configuración

| Elemento | Detalle |
|---|---|
| Herramienta | Claude Code en la aplicación de escritorio de Claude (pestaña Code), con navegador integrado. |
| Modelo | Claude Opus 5.5 (`claude-opus-5-5`). |
| Quién la usó | Josué Delgado Corrales, en PC2 y PC3. |
| Archivo de instrucciones | Un `CLAUDE.md` personal (fuera del repositorio) que fija el idioma, pide confirmar antes de publicar, subir o borrar, y prohíbe guardar secretos. No se adjunta porque contiene información de otra organización ajena al curso. |
| Memoria del proyecto | Una regla local: no agregar líneas de atribución automática en commits ni PR. El uso de IA se declara aquí, no en cada commit. |
| Skills, agentes y MCP | No se usaron skills ni agentes para generar artefactos del producto. El navegador integrado sirvió para abrir los `.bpmn` en bpmn.io y revisar GitHub. |

### Usos concretos

| # | Artefacto o parte | Qué se pidió (resumen) | Qué se aceptó, cambió o descartó | Quién lo verificó |
|---|---|---|---|---|
| 1 | PC2: problema y visión, RF por épicas, RS-01 a RS-03, actores, CU y `.feature` | Corregir las observaciones del PC1 y registrar cada cambio en `cambios-pc1-pc2.md`. | Se aceptó tras revisión; los CU se rehicieron con la plantilla del curso que Josué indicó. | Josué |
| 2 | PC2: BPMN del proceso actual y su trazabilidad | Rehacer el BPMN con un solo fin, compuertas cerradas y subproceso de apelación, en `.bpmn` editable en bpmn.io. | Josué revisó los diagramas en bpmn.io antes de subirlos. Se descartó un ajuste del carril del Comité porque Sebastián corrigió la etiqueta de otra forma. | Josué |
| 3 | PC2: diagramas de contexto y de casos de uso en `.bpmn` y exportación a PNG | Generar ambos diagramas como `.bpmn` y exportar las imágenes. | Se aceptó. El profesor luego pidió C4 para el contexto, que se rehízo en PC3. | Josué |
| 4 | PC2: `docs/cambios-pc1-pc2.md` | Tabla de observaciones atendidas y equivalencias PC1 → PC2. | Se aceptó. | Josué |
| 5 | PC3: pruebas de integración de CU-16 y CU-01 (`backend/src/test/.../integration/`) | Pruebas automatizadas contra la API con seguridad real y PostgreSQL desechable (Testcontainers), una por escenario seleccionado del `.feature`. | Ejecutadas en la revisión final: 11 pruebas Testcontainers; el total `mvn test` fue 23 sin fallos. Evidencia: [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md). | Josué |
| 6 | PC3: actualización de este documento | Declarar el uso de Claude. | Se aceptó la declaración de Claude. El estado final de los PR #26 y #27 se documenta en la tabla de integración; no se atribuyen a Josué los commits escritos por Sebastián. | Josué |
| 7 | PC3: diagramas C4 nivel 1 (`diagrama-de-contexto.svg`) y nivel 2 (`c4-contenedores.svg`) | Redibujar el contexto en notación C4 (personas, verbos en las relaciones y flujos F-xx) y agregar la vista de contenedores a partir del código real (React, Spring Boot y PostgreSQL). | Los diagramas C4 de ambos niveles se integraron mediante el PR #26. El modelo de IcePanel se sincronizó visualmente con el SVG el 23 de noviembre de 2026. La revisión del profesor y la validación académica del grupo siguen pendientes. | Josué |
| 8 | PC3: jerarquía RF → CU (`02-requerimientos/`, fichas CU y encabezados `.feature`) | Atender la observación «RF más generales y CU más específicos»: 8 RF generales con RF-33 nuevo, un solo RF por CU y RF-01 a RF-25 como requisitos detallados. | Josué comunicó la observación del profesor y eligió aplicar la propuesta; se verificó que los 20 CU y sus `.feature` apunten a un solo RF y que no haya enlaces rotos. La estructura se integró mediante el PR #26; su validación final por el profesor sigue pendiente. | Josué |

### Verificación humana y correcciones

- Cada diagrama `.bpmn` se abrió en bpmn.io y se revisó antes de subirlo.
- El validador `scripts/validar_documentacion.py` se ejecutó después de los cambios del PC2.
- Ejemplos de correcciones: Claude afirmó en un reporte que Josué no tenía permiso de escritura en ningún repositorio, cuando solo aplicaba al de documentación; Josué lo detectó y se corrigió. Claude propuso crear una rama nueva y Josué lo rechazó para no duplicar trabajo.
- Claude no subió nada, no publicó y no abrió PR sin la aprobación explícita de Josué en cada caso.

## Revisión humana pendiente

La IA puede haber interpretado de forma incompleta la rúbrica, los patrones de referencia o el contexto institucional. El grupo debe revisar el código y los documentos, confirmar el alcance con el profesor cuando sea posible y comprobar el resultado de la entrega. La selección CU-01 + CU-02 y los procesos de apelación, notificación y matrícula se declaran como decisiones provisionales; no se les atribuye aprobación docente. Este registro no atribuye trabajo a un integrante que no lo haya confirmado.
