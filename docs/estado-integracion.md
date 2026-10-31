# Estado integrado del Portal Académico

Revisión del 30 de octubre de 2026 (Costa Rica). Se revisaron los cuatro repositorios del proyecto y sus ramas disponibles; no toda la cuenta GitHub ni los documentos externos de Drive.

## Línea base observada

| Repositorio / rama | Commit | Estado observado |
|---|---|---|
| Documentación oficial / `main` | `07c739a` | PC1 e infraestructura documental de diseño. |
| Fork / `main` | `07c739a` | Idéntico al oficial al comenzar la revisión. |
| Fork / `pc2-correcciones-analisis` | `1b1ecf1` | 14 commits por delante de main; PR #1 de Josué abierto. 25 RF, 3 RS, 11 CU y 69 escenarios Gherkin. |
| Frontend / `main` | `29bc1c4` | React, TypeScript, Vite y pantalla de salud; no hay pantallas académicas. |
| Backend / `main` | `a6fe04e` | Spring Boot, PostgreSQL/Liquibase, CORS y salud; no hay entidades ni endpoints académicos. |

El tag histórico `pc1` existe en la documentación oficial y apunta a `763fd64280f83d5c5c84fc4762de44938c30d94f`. La raíz conserva los artefactos anteriores. PC1 y PC2 usan identificadores RF distintos: no intercambiarlos.

## Correcciones de esta revisión

- README principal orientado al índice PC2, con el PC1 claramente identificado como archivo histórico.
- Guía única del flujo fork → oficial y coordinación con frontend/backend. No se borraron ni transfirieron repositorios.
- Dos BPMN importados con bpmn-js sin advertencias y exportados a SVG/PNG; diagrama de CU generado desde PlantUML. Se ajustó una etiqueta del BPMN para evitar solaparla con el carril.
- Validador reproducible de enlaces locales, correspondencia CU/feature, identificadores de escenarios y referencias XML de BPMN: `python scripts/validar_documentacion.py`.
- Backend: importación opcional de `.env`, URL JDBC derivada de `DB_PORT` y `DB_NAME`, origen CORS configurable y PostgreSQL publicado solo en localhost.
- Frontend: puerto Vite estricto, versión Node declarada, normalización de URL, validación de respuesta, tiempo máximo de 10 segundos, cancelación al desmontar y botón de reintento.
- Pruebas del contrato HTTP, CORS y resolución de configuración incorporadas al backend.

## Validación realizada

| Comprobación | Resultado / límite |
|---|---|
| Frontend `npm ci` y `npm run build` | Compila. Verificado con Node 24.22.1. |
| Prueba de navegador del frontend | 6 comprobaciones: UP, HTTP 503, reintento, JSON incompatible, DOWN y timeout. API simulada, no integración real con PostgreSQL. |
| Backend Maven con JDK 21 | 5 pruebas: contrato HTTP, origen permitido, origen rechazado, URL JDBC por variables y URL externa. Sin base de datos real. |
| Restricción del entorno de pruebas Java | Se necesitó cargar Mockito como agente explícito porque el entorno no permite adjuntarlo dinámicamente. Esto no modifica el código de producción. |
| Documentación | Validación estructural y de rutas locales con el script; no verifica enlaces externos ni todos los anchors Markdown. |
| Diagramas | Importación BPMN sin advertencias y revisión visual de PNG; esto no equivale a validar el proceso con la escuela. |
| Escenarios académicos | 69 especificaciones, **no 69 pruebas pasadas**. Falta ejecutor y código de negocio. |

No se ejecutó Docker/PostgreSQL ni una prueba real React → Spring → PostgreSQL en esta revisión. El endpoint `/api/v1/health` es diagnóstico HTTP; no certifica conectividad con la base de datos.

## Pendientes priorizados

| Prioridad | Pendiente | Criterio para cerrarlo |
|---|---|---|
| Alta | Revisar e integrar el PR #1 de análisis con estos ajustes | PC2 en main del fork y PR hacia la organización, sin perder aportes. |
| Alta | Confirmar formato de entrega: uno o tres repositorios | Decisión del profesor registrada; el README anterior señala diferencia con el enunciado. No se consultó el enunciado externo en esta revisión. |
| Alta | Activar issues del fork y crear los 11 CU | URLs reales en cada ficha y matriz. Actualmente dicen `por crear`; no se inventaron números. |
| Alta | Completar diseño académico | Modelo de dominio/relacional, contratos API, realización de CU y reglas alineadas con los 25 RF. La pantalla de salud no satisface un CU. |
| Alta | Implementar RS-03 | Autenticación, autorización por rol/vínculo/asignación y pruebas negativas. Spring Boot y CORS por sí solos no lo resuelven. |
| Implementación | Ejecutar los casos negativos de acceso | Se añadieron CU-01 A05 (sesión inválida) y CU-05 A08 (vínculo revocado al abrir). Falta implementarlos y automatizarlos. |
| Alta | Verificar arranque integrado | PostgreSQL 17 saludable, migración aplicada, API disponible y frontend conectado usando los mismos valores de entorno. |
| Media | Construir funcionalidades académicas | Frontend, servicios, persistencia y pruebas de cada CU; actualmente están pendientes los 11 CU. Priorizar vínculos, publicación y consulta tras acordar el diseño. |
| Media | Prototipos CU-02 y CU-12 | Vistas de vinculación y queja; los HTML heredados son referencias, no funcionalidades. |
| Media | Automatizar verificaciones | CI para build frontend, tests backend y validador documental; ejecutor Gherkin cuando exista código académico. |
| Entrega | Revisar supuestos y artefactos del curso | Validación con profesor/escuela, declaración de IA actualizada por el equipo y tag `pc2` después de revisión final. |

Los cambios documentales quedan en un PR hacia la rama de análisis. La integración rechazó crear un árbol Git en Frontend con HTTP 403; los cambios de código quedan como [parches aplicables](../integracion/README.md) en el fork, no como ramas publicadas de la organización. No implican aprobación académica ni que `main` ya esté actualizado.

## Integración solicitada tras recibir la evaluación del profesor

La evaluación del PC1 fue aportada por Sebastián en una captura. La respuesta por observación está en [revision-profesor-pc1.md](revision-profesor-pc1.md). Los 69 escenarios de la revisión inicial se conservan y se agregan dos negativos de seguridad: **71 escenarios especificados en total**. No se consideran ejecutados.
