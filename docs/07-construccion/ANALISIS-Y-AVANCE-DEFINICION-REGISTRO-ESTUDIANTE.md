# Análisis de retroalimentación docente y avance independiente

**Estado del documento:** avance local preparado para revisión del equipo.  
**Base:** material público de IF-6100-2026 y notas de retroalimentación compartidas en este chat.  
**Alcance:** trabajo documental y de especificación en una rama propia; no integra ni reemplaza trabajo de otros integrantes.  
**Importante:** esta rama no se ha publicado en GitHub. La integración de GitHub rechazó la creación de rama con HTTP 403. No se afirma aprobación del profesor. CU-16 pasó pruebas unitarias del handler, build frontend, validación documental y una prueba HTTP manual contra PostgreSQL temporal; falta automatizar la verificación de integración.

## Resumen ejecutivo

La retroalimentación docente aporta cuatro ajustes concretos al proyecto:

1. Presentar el diagrama de contexto usando C4 Nivel 1: el Portal como caja negra, con actores/entidades externas y datos que cruzan el límite.
2. Ordenar los requisitos desde capacidades generales hacia objetivos más concretos de los actores.
3. Incorporar el caso de uso que falta: registrar estudiante, y distinguirlo de vincular una persona encargada con un estudiante ya registrado.
4. Trabajar incrementalmente con un flujo de definición, planificación y revisión antes de implementar.

En esta rama quedaron preparados el rótulo y la explicación C4, siete requisitos generales (RF-26 a RF-32), el mapeo de los requisitos detallados anteriores, CU-16, cinco escenarios Gherkin y un paquete documental SDD. CU-16 también tiene handler, endpoint protegido, migración de auditoría básica y formulario React. Las tres pruebas unitarias del handler, el build de React y el validador documental pasan; además, el endpoint y la migración corrieron contra una base PostgreSQL temporal. **La integración no tiene prueba automatizada ni issue asociado.**

La jerarquía RF/CU quedó reorganizada localmente: RF-26 a RF-32 expresan capacidades amplias, RF-01 a RF-25 se identifican como refinamientos detallados preservados, y cada CU enlaza ahora los RF generales junto con los IDs detallados que lo originaron. Se conservaron 15 CU con metas o disparadores identificables; no se fusionaron metas distintas solo para bajar el conteo. Esta propuesta requiere revisión del grupo y no se presenta como aprobada por el profesor.

## 1. Qué material del curso encontramos

La organización pública [IF-6100-2026](https://github.com/orgs/IF-6100-2026/repositories) muestra cuatro repositorios públicos: documentación del Portal, frontend, backend y `public`. El más relacionado con lo que describió el profesor es [`IF-6100-2026/public`](https://github.com/IF-6100-2026/public), repositorio de material público del curso.

En su carpeta `ia/` están [sesión A](https://github.com/IF-6100-2026/public/blob/main/ia/sesion-a/index.html) y [sesión B](https://github.com/IF-6100-2026/public/blob/main/ia/sesion-b/index.html). La sesión B explica **Claude Code**, las skills y Spec-Driven Development (SDD). Así que probablemente lo recordado como “Cloud” era **Claude**.

### ¿Dónde están las skills?

El repositorio público enseña qué es una skill y cómo se usa; no encontramos ahí una colección instalable con muchos `SKILL.md` ni otro repositorio público separado de skills del profesor. La guía describe una skill como instrucciones y recursos organizados alrededor de `SKILL.md`, que se cargan cuando hacen falta. `AGENTS.md` o `CLAUDE.md`, en cambio, guardan instrucciones generales del proyecto.

Eso no descarta que el profesor tenga un repositorio privado o que haya compartido archivos por Mediación/Drive. Lo comprobable en los repositorios públicos es el material educativo; no debe afirmarse que ya descargamos o aplicamos skills del curso.

### SDD que presenta la sesión

| Paso | Qué deja definido | Archivo que preparamos para CU-16 |
|---|---|---|
| Constitution | Reglas de producto, arquitectura y límites | `.specify/memory/constitution.md` |
| Specify | Actor, necesidad, alcance, flujos y aceptación | `specs/001-registrar-estudiante/spec.md` |
| Plan | Enfoque técnico para backend, frontend y persistencia | `specs/001-registrar-estudiante/plan.md` |
| Tasks | Pasos implementables en orden | `specs/001-registrar-estudiante/tasks.md` |
| Implement | Código y revisión posterior del cambio | Handler, endpoint, migración y formulario implementados localmente; handler probado, frontend compilado. Falta integración DB. |

La idea central del material es pensar y revisar cada paso antes de pasar al siguiente; Spec Kit puede ordenar los archivos, pero no sustituye las decisiones del equipo.

## 2. Retroalimentación docente y respuesta del repositorio

| Observación compartida | Qué significa en el proyecto | Avance local | Estado real |
|---|---|---|---|
| Diagrama de contexto en C4 | Mostrar el Portal como sistema único y nombrar relaciones/datos externos; no dibujar componentes internos en el Nivel 1. | El SVG y su página ahora dicen C4 Nivel 1 y explican la caja negra. Se conserva la tabla de flujos F-01 a F-34. | Etiquetado y explicación ajustados; el equipo todavía debe revisar que el dibujo represente exactamente los datos acordados. |
| RF más generales y CU más concretos | Un RF expresa una capacidad de negocio amplia; los CUs describen metas concretas del actor. | Se definieron RF-26 a RF-32; RF-01 a RF-25 se mapearon como refinamientos y se actualizaron las relaciones de CUs/features. | Reorganizado en la rama local; falta revisión del grupo y actualización de issues en GitHub. |
| Falta registrar estudiante | El estudiante debe existir antes de asociar vínculos y dar seguimiento. | CU-16, feature, endpoint protegido, formulario y auditoría básica; alta separada de CU-02. | Handler y frontend verificados; cinco respuestas HTTP comprobadas en PostgreSQL temporal. Falta automatizar la integración, issue y revisión de grupo. Actor/campos provisionales. |
| Empezar desde la idea, planear y entregar incrementos | Aclarar propósito, generar/revisar plan y construir una porción pequeña antes de ampliar el sistema. | Constitución, spec, plan, tasks SDD y porción vertical local de CU-16. | Verificaciones locales completadas, incluida la integración manual con PostgreSQL; falta revisión en grupo y automatizarla. |

## 3. Avance concreto realizado en esta rama

### Requerimientos y caso nuevo

- [RF-26 Gestionar el registro de estudiantes](../02-requerimientos/funcionales/RF-26-gestion-de-estudiantes.md): capacidad general; define que el registro y el vínculo familiar son distintos.
- [RF-27 Gestionar cursos y grupos](../02-requerimientos/funcionales/RF-27-gestion-de-cursos.md): capacidad general; faltan CUs que la descompongan.
- [RF-28 Gestionar profesores](../02-requerimientos/funcionales/RF-28-gestion-de-profesores.md): capacidad general; faltan CUs que la descompongan.
- [RF-29 Gestionar vínculos y acceso](../02-requerimientos/funcionales/RF-29-gestionar-vinculos-y-acceso.md): agrupa el detalle RF-01.
- [RF-30 Gestionar seguimiento académico](../02-requerimientos/funcionales/RF-30-gestionar-seguimiento-academico.md): agrupa RF-02 a RF-09.
- [RF-31 Gestionar comunicación y avisos](../02-requerimientos/funcionales/RF-31-gestionar-comunicacion-y-avisos.md): agrupa RF-10 a RF-16 y RF-23.
- [RF-32 Gestionar apelaciones y pendientes](../02-requerimientos/funcionales/RF-32-gestionar-apelaciones-y-pendientes.md): agrupa RF-17 a RF-22 y RF-24 a RF-25.
- [CU-16 Registrar estudiante](../03-casos-de-uso/CU-16-registrar-estudiante.md): actor Dirección provisional, campos existentes, escenarios alternos e implementación local.
- [Feature de CU-16](../04-pruebas/CU-16-registrar-estudiante.feature): cinco escenarios de especificación; no se reportan como ejecutados por Cucumber.
- [Matriz de trazabilidad RF → CU → prueba → issue](../02-requerimientos/trazabilidad-rf-cu.md): enlaza el nuevo requisito/caso y registra que el issue sigue pendiente.

### Documentación y navegación

- Se identificó explícitamente el diagrama de contexto como C4 Nivel 1 y se mantuvo el detalle de flujos que ya existía.
- Se agregaron enlaces desde los índices principales hacia RF-26 a RF-32, CU-16 y sus artefactos SDD.
- Se actualizaron el estado de implementación, los cambios PC2→PC3, la colaboración y el registro de uso de IA para distinguir propuesta, código y evidencia.
- La guía SDD local enlaza el material público del curso y aclara que no se encontró un paquete instalable de skills.

## 4. Decisiones provisionales de CU-16

El caso se redactó con el modelo que ya existe en el repositorio: nombre completo, nivel, grupo, docente, UUID interno y estado activo. Dirección aparece como actor provisional por continuidad con el mantenimiento de vínculos en CU-02. **No son reglas confirmadas por la institución ni por el profesor.**

Registrar estudiante crea la ficha; CU-02 establece después quién puede consultar sus datos. El alta no crea automáticamente una relación con la persona encargada. Esta separación reduce el riesgo de que una ficha nueva otorgue acceso a datos académicos.

Se dejó pendiente la política de duplicidad porque no encontramos un identificador escolar confirmado. El borrador no inventa un número de documento ni bloquea altas únicamente por coincidencia de nombre. También falta decidir quién actualiza/inactiva registros y qué campos son obligatorios institucionalmente.

## 5. Estado verificable y límites

- El punto de partida local es la rama PC3 `pc3/core-consulta-vinculos`, commit `c17d519` (`feat: completar el núcleo funcional de PC3`). El trabajo nuevo está en una rama local separada: `codex/pc3-sdd-registro-estudiantes`.
- CU-01 y CU-02 ya estaban implementados en el núcleo PC3. Este avance no cambia esos módulos ni atribuye su autoría a una persona nueva.
- La rama actual documenta **15 casos de uso, 15 archivos `.feature` y 83 escenarios especificados**: el corte previo tenía 14/14/78; CU-16 agrega cinco escenarios escritos.
- En esta revisión, `mvn test` pasó con 8 pruebas (incluidas tres nuevas del handler CU-16) y sin violaciones de Checkstyle; `npm run build` pasó y `python scripts/validar_documentacion.py` informó 15/15/83 sin errores.
- CU-16 tiene handler, endpoint, migración de auditoría y formulario local. Las pruebas del handler usan repositorios simulados; además, el servicio arrancó en una base PostgreSQL temporal nueva y cinco respuestas HTTP esperadas fueron comprobadas. Esta prueba manual todavía no está automatizada.
- CU-13, CU-14, CU-15 y CU-16 aparecen sin issue asociado en la matriz. El requisito de un issue por CU sigue sin completarse para esos cuatro casos.
- GitHub rechazó con HTTP 403 la creación de la rama remota de este avance. Por tanto, el commit/tag publicado del repositorio no cambia con este trabajo local.

## 6. Trabajo pendiente priorizado

### Antes de entregar

1. Revisar la rama local completa y acordar quién presenta cada cambio. Mantener aparte el trabajo que corresponde a Josué; esta rama no intenta completarlo ni atribuírselo a otra persona.
2. Revisar manualmente el `git diff` y decidir si el equipo acepta los supuestos de CU-16: actor, campos, identidad/duplicidad y relación separada con CU-02.
3. Recuperar acceso de escritura al repositorio oficial por el mecanismo normal del equipo; luego publicar la rama/PR y crear los issues pendientes según la convención del proyecto. El 403 no se resolvió en esta sesión.
4. Convertir la comprobación manual de PostgreSQL/HTTP para CU-16 en una verificación automatizada y repetible.

### Próximo incremento académico/técnico

1. Revisar con el equipo que RF-01 a RF-25 estén correctamente ubicados bajo RF-26 a RF-32; sus IDs se preservaron para no romper la historia.
2. Descomponer RF-27 y RF-28 en casos de uso concretos solo cuando haya actor, objetivo y datos acordados.
3. Abrir el issue de CU-16 y revisar la porción vertical ya preparada: endpoint protegido de alta → ficha persistida → formulario de Dirección → confirmación/error.
4. Definir la clave institucional para duplicados, datos obligatorios, edición/inactivación y auditoría; añadir escenarios derivados de las decisiones.
5. Añadir pruebas de API e integración para CU-16 cuando se defina el entorno DB de verificación.

## 7. Lectura franca para la rúbrica

El avance mejora la legibilidad, organiza siete capacidades funcionales generales sobre los refinamientos conservados, incorpora el CU que el profesor señaló y muestra el proceso SDD. También atiende el rótulo C4. CU-16 tiene implementación local verificada: **pasan el handler unitario, el build, el validador y un smoke HTTP manual con PostgreSQL**. Quedan automatizar ese smoke, revisar supuestos en grupo, enlazar issues donde faltan y publicar la rama oficial. La documentación separa cada alcance para reflejar exactamente la evidencia disponible.
