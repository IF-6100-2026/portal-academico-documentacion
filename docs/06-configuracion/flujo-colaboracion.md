# Flujo de colaboración e integración

## Responsabilidad de cada repositorio

| Repositorio | Uso |
|---|---|
| `cbastiancq-lab/portal-academico_documentacion_preliminar` | Preparar y revisar documentación del equipo. |
| `IF-6100-2026/portal-academico-documentacion` | Integración oficial de documentación con el profesor. |
| `IF-6100-2026/portal-academico-frontend` | Código React y contrato cliente de la API. |
| `IF-6100-2026/portal-academico-backend` | Código Spring Boot y migraciones PostgreSQL. |

Unificar significa mantener una especificación y un contrato coherentes entre los repositorios. La elección de un repositorio único o tres para la entrega todavía requiere confirmar el enunciado con el profesor. No se transfieren ni borran repositorios para resolver esa decisión.

## Documentación

1. Revisar las correcciones en el PR de análisis de Josué (`pc2-correcciones-analisis` → `main` del fork).
2. Revisar también las correcciones de integración (`codex/unificar-pc2` → `pc2-correcciones-analisis`). Fusionar primero este PR de ajustes, para que queden incluidos en el PR de análisis.
3. Integrar el análisis revisado en `main` del fork.
4. Abrir el PR desde `cbastiancq-lab:main` hacia `IF-6100-2026:main` en Documentación.
5. Resolver cualquier diferencia nueva conservando los aportes de ambos repositorios.

No copiar archivos manualmente por encima de la rama de otra persona. Trabajar en ramas propias y revisar el diff antes de fusionar. Los cambios del fork no llegan automáticamente al repositorio oficial.

## Código

Cada PR de frontend o backend debe indicar los CU/RF del PC2 que implementa, el contrato afectado y las pruebas ejecutadas. El endpoint de salud es infraestructura y no completa un CU de negocio.

Para una integración completa: revisar y fusionar los PR de código, ejecutar la guía de ambiente y registrar la prueba real frontend → API → PostgreSQL. Crear el tag `pc2` solamente después de resolver los pendientes de entrega.

## Issues y permisos

Al revisar el fork, los issues estaban desactivados. El propietario puede activarlos en Settings → General → Features → Issues. Después se crean los 11 issues de los CU y se sustituyen los marcadores `por crear` en las fichas y la matriz por URLs reales. No se consideran creados hasta contar con sus enlaces.
