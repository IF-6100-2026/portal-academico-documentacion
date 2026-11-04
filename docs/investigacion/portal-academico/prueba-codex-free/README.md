# Segunda ejecución del flujo: Codex con el plan gratuito

**Fecha:** 4 de noviembre de 2026, de 14:56 a 15:38 (hora de Costa Rica).
**Quién:** Josué Delgado conduce Codex en la app de escritorio y hace los commits. El registro, las capturas y la verificación con `gradlew build` se hicieron con apoyo de Claude Code. El código lo generó Codex.

Esta prueba complementa la demo RF-04 (`../demo-rf04/`). Comprueba lo que esa demo dejó pendiente: el **plan gratuito** y la **verificación humana entre pasos** (V1–V3).

## Herramienta y plan

| Dato | Valor |
| --- | --- |
| Plan | **ChatGPT Free, $0 al mes** ([E03-02](evidencias/E03-02-plan-de-la-cuenta.png)) |
| App | Codex para Windows 26.1029.7803.0 |
| Modelo visible | «6 Luna», esfuerzo Medium |
| Permisos | «Ask for approval» |
| Límite | Mensual, compartido con otras funciones de ChatGPT; 100 % restante al empezar |

## Proyecto y tarea

- **Repositorio de partida:** el proyecto base trabajado en clase (`spring-boot-base-app`: Spring Boot 4.1, Java 26, Gradle 9.8, CQRS con ArchUnit y Checkstyle). Se trabajó por encima de él, sin rehacerlo.
- **Issue:** cambiar el nombre del usuario autenticado, con `PUT /api/private/users/current/name`.
  - CA-01: nombre válido, se guarda sin espacios en los extremos.
  - CA-02: nombre vacío → 400 `REQUIRED_FIELDS`.
  - CA-03: más de 100 caracteres → 400 `INVALID_FIELDS` (código nuevo 1004).
  - CA-04: usuario inexistente → 400 `INVALID_USER`.
- **Cambio resultante:** [cambio-cambiar-nombre-usuario.patch](cambio-cambiar-nombre-usuario.patch), 8 archivos y 445 líneas, en 3 commits con el pie `Generated-by: Codex (OpenAI)` ([commits](evidencias/commits.txt)).

## Lo que pasó, paso por paso

| Hora | Fase del flujo | Qué hizo Codex | Control humano | Resultado | Evidencia |
| --- | --- | --- | --- | --- | --- |
| 14:56 | Contexto | Al agregar el proyecto, **importó por su cuenta** `CLAUDE.md` → `AGENTS.md` y `.claude/skills` → `.agents/skills`, reemplazando «Claude» por «Codex». | `diff` contra los originales | **6 referencias rotas** (por ejemplo `.Codex/skills`, que no existe). Error de la IA. | [E04](evidencias/E04-importacion-automatica-agents.txt) |
| 14:57 | Prueba de humo | Explicó el proyecto y cómo correr las pruebas (9 s). | Comparado con el código | Correcto. | [E03-01](evidencias/E03-01-app-codex-estado-inicial.png) |
| 15:07 | Contexto (C0) | Corrigió las 6 referencias y agregó las reglas de trabajo (plan primero, un paso por turno, sin commits). | Búsqueda de rutas rotas: 0 | Correcto, pero **no notó** una frase que cambió de sentido en `speckit-constitution`. | [E05-01](evidencias/E05-01-paso3-agents-md.png) |
| 15:22 | Plan (V1) | Plan de 3 pasos; agregó por su cuenta el caso límite de 100 caracteres exactos y esperó la aprobación. | Revisión contra el issue | Plan aprobado. | [E06-01](evidencias/E06-01-V1-plan.png) |
| 15:25 | Generación (V2) | Escribió las pruebas y después el handler con la constante `MAX_NAME_LENGTH`. **No corrió el build.** | `gradlew build` por la persona | **4 de 7 pruebas fallan**: compara records con campos `String...`, y los arreglos se comparan por referencia. Error de la IA detectado en V3. | [E07-01](evidencias/E07-01-V2-paso1.png), [E07-02](evidencias/E07-02-V3-paso1-pruebas-fallan.txt) |
| 15:29 | Corrección | Con el mensaje de error y sin pista de la causa, la explicó, cambió a `assertInstanceOf` + `assertArrayEquals` y esta vez corrió el build. | `gradlew build` repetido | 19 pruebas en verde. Una vuelta de corrección. | [E07-03](evidencias/E07-03-correccion.png) |
| 15:37 | Generación (V2) | Request, `INVALID_FIELDS(1004)`, controller con `switch` exhaustivo y 7 pruebas del controller. Corrió el build. | `gradlew build` por la persona | **26 pruebas, 0 fallas**; ArchUnit y Checkstyle en verde. | [E08-01](evidencias/E08-01-V2-paso2-controller.png) |

La bitácora completa está en [bitacora.md](bitacora.md).

## Hallazgos

1. **El plan gratuito alcanzó** para una funcionalidad completa con sus pruebas: 6 prompts en unos 40 minutos.
2. **Codex cumplió el patrón del proyecto** (capas, `Result` sealed, `switch` exhaustivo, constantes) a partir del contexto importado: ArchUnit y Checkstyle pasaron a la primera.
3. **Dos errores de la IA, los dos detectados por la persona:** las referencias rotas de la importación automática y las 4 pruebas mal escritas. En el segundo caso Codex no había corrido el build. Sin la verificación humana (V3), el error habría pasado.
4. **Corrige bien con buena información:** con el mensaje de error encontró la causa sola y lo resolvió en una vuelta.
5. **Límite de las pruebas generadas:** las del controller comprueban la ruta leyendo las anotaciones, no con una petición HTTP real, así que el JWT y el JSON no quedan probados. Harían falta pruebas E2E.
6. **El contexto también se revisa:** la app migra sola la configuración de otra herramienta, pero con un reemplazo mecánico que introduce errores.

## Pendiente

- Revisión de Codex del diff completo (V4) y medición de la cuota usada al final.
- Revisión humana por la otra persona (V5) e integración.
