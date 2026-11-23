# Portal Académico Montessori — PC3

Proyecto de Análisis y Diseño de Sistemas (IF-6100) para el Centro Educativo María Montessori. El repositorio integra documentación, API Spring Boot, interfaz React y PostgreSQL.

**Alcance funcional de PC3:** hay código integrado para los 20 casos vigentes; CU-11 se retiró antes de PC3. El PR #25 fue fusionado en `pc3/core-consulta-vinculos` (merge `e5701a6`). El tag `pc3` identifica la versión final de entrega. La rama tuvo pruebas unitarias, compilación del frontend, validación documental y smoke HTTP de los flujos principales contra PostgreSQL 17 temporal. Los escenarios Gherkin no se ejecutaron como suite; los supuestos institucionales y los límites se detallan en [estado de implementación](docs/07-construccion/estado-de-implementacion.md) y [brechas](docs/07-construccion/brechas-de-requerimientos.md).

El [PR #25](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/25) está fusionado. Los issues de CU-01 y CU-02 (#3 y #4) siguen abiertos en GitHub aunque esos casos sí tienen código y evidencia HTTP; su estado de seguimiento no significa que falte su implementación. Los perfiles, grupos, escala de calificación, plazos, notificaciones y procedimiento de apelación incluyen decisiones provisionales que el grupo debe revisar; no se atribuye aprobación docente.

## Integrantes registrados

Josué Delgado Corrales y Sebastián Cordero Quesada. El historial conserva commits de ambos. Los cambios de esta entrega se atribuyen al autor real; no se crean commits en nombre de quien no los hizo.

## Inicio local

Requiere Java 21, Maven 3.9+, Node.js 22.12+ o 24+, npm y Docker Compose v2.

Desde la raíz del repositorio:

```powershell
Copy-Item .env.example .env
docker compose up -d --wait
```

En una terminal, desde `backend/`:

```powershell
mvn test
mvn spring-boot:run
```

En otra terminal, desde `frontend/`:

```powershell
Copy-Item .env.example .env
npm ci
npm run dev
```

Abre <http://localhost:5173>. La guía de [ambiente de desarrollo](docs/06-configuracion/ambiente-de-desarrollo.md) incluye puertos, perfiles demo, verificación y diagnóstico.

## Revisión y demostración

- [Documentación e índice de artefactos](docs/README.md)
- [Respuesta a la evaluación PC2 y alcance PC3](docs/cambios-pc2-pc3.md)
- [Análisis de retroalimentación docente y avance independiente](docs/07-construccion/ANALISIS-Y-AVANCE-DEFINICION-REGISTRO-ESTUDIANTE.md)
- [Jerarquía general RF → CU → refinamiento y prueba](docs/02-requerimientos/trazabilidad-rf-cu.md)
- [Guía del curso: skills y SDD](docs/06-configuracion/guia-sdd-y-skills-del-curso.md)
- [Spec, plan y tareas de registrar estudiante](specs/001-registrar-estudiante/spec.md)
- [Estado de implementación](docs/07-construccion/estado-de-implementacion.md)
- [Brechas y decisiones pendientes](docs/07-construccion/brechas-de-requerimientos.md)
- [Decisiones de diseño](docs/07-construccion/decisiones-de-diseno.md)
- [Resultados de las verificaciones ejecutadas](docs/08-resultados-de-pruebas/resultados-pruebas.md)
- [Registro de uso de IA](docs/09-uso-de-ia/uso-de-ia.md)
- [Guion de demostración PC3](docs/07-construccion/guion-demostracion-pc3.md)

Para repetir la verificación integrada del núcleo, iniciar base y API y ejecutar `./scripts/verificar-nucleo-pc3.ps1 -BaseUrl http://localhost:8080` desde PowerShell. Usa identidades ficticias, deja un vínculo pendiente y crea vínculos de prueba que terminan suspendidos/finalizados; las trazas permanecen en la base local. La evidencia de los otros CU fue un smoke HTTP manual contra una instancia temporal; véase el [reporte](docs/08-resultados-de-pruebas/resultados-pruebas.md).

## Entrega PC3

El tag `pc3` identifica la versión integrada que contiene los 20 casos vigentes. Copia los enlaces del repositorio y del tag en la actividad PC3 de Mediación Virtual; subir el tag no registra por sí solo la entrega.

La selección docente del núcleo no se pudo confirmar antes de la fecha límite; por eso el alcance ejecutado se registra como propuesta del equipo. La revisión final del grupo y la entrega del enlace/tag en Mediación Virtual se completan aparte.
