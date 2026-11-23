# Análisis de retroalimentación docente y avance independiente

- **Estado:** CU-16 se integró mediante [PR #15](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/15). Los otros 17 casos se integraron mediante el [PR #25](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/25), ya fusionado como merge `e5701a6`; el tag `pc3` identifica la versión final de entrega.
- **Fuente:** enunciado/lineamientos PC3, comentarios del profesor compartidos por el equipo y material público de IF-6100-2026.
- **Autoría:** este avance no atribuye cambios a un integrante que no los haya realizado.
- **Límite académico:** no se encontró confirmación escrita del profesor para la selección del núcleo ni para las decisiones provisionales que se describen aquí.

## Retroalimentación docente y respuesta

| Observación | Qué implica | Respuesta del repositorio | Pendiente |
|---|---|---|---|
| El diagrama de contexto debe usar C4. | Portal como caja negra con entidades externas y datos que cruzan el límite. | La documentación identifica C4 Nivel 1 y el modelo editable está en IcePanel; BPMN se reserva para procesos. | Revisar que actores/relaciones del modelo representen el alcance actual. |
| Hay demasiados CU y RF quedaron más específicos que los CU. | RF debe expresar una capacidad general; cada CU detalla una meta de un actor. | RF-26 a RF-32 son generales; RF-01 a RF-25 se conservan como refinamientos históricos y se trazan a CU/features/issues. | Confirmar la agrupación con el equipo/profesor. |
| Falta un caso de uso de registrar estudiante. | La ficha del estudiante precede a matrícula, publicaciones y vínculo familiar. | CU-16 tiene ficha, handler, endpoint protegido, migración, formulario React y pruebas unitarias/HTTP. Dar de alta no concede acceso familiar. | Confirmar actor, campos e identificación para duplicados. |
| Hay que ordenar la idea, planificar y avanzar incrementalmente. | Definir propósito y reglas, revisar un plan y construir una porción verificable. | Se documentó flujo SDD para CU-16 en `.specify/` y `specs/001-registrar-estudiante/`; luego el usuario pidió completar los otros 17 casos. | Revisar el alcance, decisiones y cambios antes de presentar. |

## Material del curso que se pudo verificar

La organización pública [IF-6100-2026](https://github.com/orgs/IF-6100-2026/repositories) publica el repositorio [`public`](https://github.com/IF-6100-2026/public). Su carpeta `ia/` incluye [sesión A](https://github.com/IF-6100-2026/public/blob/main/ia/sesion-a/index.html) y [sesión B](https://github.com/IF-6100-2026/public/blob/main/ia/sesion-b/index.html), con material sobre Claude Code, skills y Spec-Driven Development (SDD). En lo que se revisó no había un paquete público instalable de skills del profesor con `SKILL.md`; eso no descarta recursos privados compartidos por Mediación/Drive.

Una skill organiza instrucciones y recursos alrededor de `SKILL.md`; `AGENTS.md` o `CLAUDE.md` guardan instrucciones generales del repositorio. La guía pública explica el concepto, pero no acredita que este equipo haya descargado o aplicado una skill privada del profesor.

## SDD documentado para CU-16

| Fase | Archivo o evidencia |
|---|---|
| Constitution | `.specify/memory/constitution.md` |
| Specify | `specs/001-registrar-estudiante/spec.md` |
| Plan | `specs/001-registrar-estudiante/plan.md` |
| Tasks | `specs/001-registrar-estudiante/tasks.md` |
| Implement y verificar | Handler Java, endpoint Dirección, migración Liquibase, formulario React, `mvn test`, compilación frontend y prueba HTTP manual contra PostgreSQL temporal. |

El flujo documentado de SDD se aplicó con detalle a CU-16. Los incrementos restantes se implementaron a partir de las fichas/features existentes; no se afirma que cada caso haya tenido su propio ciclo de Spec Kit.

## Alcance funcional añadido en la rama actual

La rama `codex/pc3-remaining-use-cases` implementa los 17 casos que faltaban: CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21. Con CU-01, CU-02 y CU-16 hay código para los **20 casos vigentes**; CU-11 fue retirado antes de PC3. La matriz, clases, endpoints y evidencia concreta están en [estado de implementación](estado-de-implementacion.md) y [checklist por CU](checklist-casos-de-uso.md).

## Estado de verificación

- `mvn test` con JDK 21: 12 pruebas, 0 fallos/errores y 0 violaciones de Checkstyle.
- `npm run build`: TypeScript y Vite pasan.
- `python scripts/validar_documentacion.py`: 20 CU, 20 `.feature`, 103 escenarios escritos y 0 errores estructurales.
- PostgreSQL 17 temporal: Liquibase aplicó 8 changesets; la API respondió saludable y se comprobó por HTTP el flujo principal de los 17 casos nuevos.
- CU-16: además de pruebas unitarias, respuestas HTTP 401/201/403/400 comprobadas manualmente.
- Los escenarios alternos Gherkin no se ejecutaron como suite Cucumber; la prueba HTTP de los flujos nuevos no está automatizada en CI.

## Decisiones provisionales que deben revisarse

Los plazos y estados de apelación, los perfiles y permisos docentes, la estructura curso/materia/grupo/periodo, la escala 0–100, el procedimiento de avisos y la política de vínculo familiar están codificados para una demo local. Se detallan en [brechas](brechas-de-requerimientos.md). Nada aquí equivale a una política institucional aprobada.

La integración final quedó en `pc3/core-consulta-vinculos` y el tag `pc3` identifica la versión de entrega. El enlace del repositorio y del tag debe registrarse en Mediación Virtual. Los issues #3 y #4 todavía aparecen abiertos aunque CU-01 y CU-02 tienen código y verificaciones documentadas. El historial no se altera para inventar commits de los integrantes.
