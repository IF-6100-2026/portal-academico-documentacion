# Documentación — Portal Académico

Índice de la documentación del Portal Académico del Centro Educativo María Montessori (IF-6100, PC3). Las secciones siguen el orden de las fases del proyecto.

[Flujo de colaboración](06-configuracion/flujo-colaboracion.md)

**Cadena de trazabilidad:** proceso actual (BPMN) → problema → requerimiento funcional → caso de uso → escenario de prueba → issue → código.

- [Cambios del PC1 al PC2](cambios-pc1-pc2.md): observaciones atendidas y equivalencias de identificadores.
- [Cambios del PC2 al PC3](cambios-pc2-pc3.md): respuesta a la evaluación, avance de construcción y límites institucionales pendientes.
- [Análisis de retroalimentación docente y avance independiente](07-construccion/ANALISIS-Y-AVANCE-DEFINICION-REGISTRO-ESTUDIANTE.md): decisiones, supuestos, cambios locales y pendientes.
- [Estado de implementación](07-construccion/estado-de-implementacion.md), [checklist de implementación por CU](07-construccion/checklist-casos-de-uso.md), [brechas institucionales pendientes](07-construccion/brechas-de-requerimientos.md), [decisiones](07-construccion/decisiones-de-diseno.md), [guion de demostración PC3](07-construccion/guion-demostracion-pc3.md), [resultados de pruebas](08-resultados-de-pruebas/resultados-pruebas.md) y [uso de IA](09-uso-de-ia/uso-de-ia.md).
- [Glosario de siglas e identificadores](glosario.md): CU, RF, RS, E, P, AC, TP y los demás prefijos.

## 01 — Inicio

- [Problema y visión](01-inicio/problema-y-vision.md): evidencia, problemas, épicas, alcance y supuestos.
- [Diagrama de contexto](01-inicio/diagrama-de-contexto.md): C4 Nivel 1, caja negra en SVG y catálogo F-01 a F-39; F-35 a F-39 son propuestas pendientes de revisión.
- [Proceso actual (BPMN) y trazabilidad hacia los RF](01-inicio/bpmn/trazabilidad-bpmn-rf.md)
  - [seguimiento-academico-actual.bpmn](01-inicio/bpmn/seguimiento-academico-actual.bpmn) · [PNG](01-inicio/bpmn/seguimiento-academico-actual.png)
  - [resolver-apelacion-actual.bpmn](01-inicio/bpmn/resolver-apelacion-actual.bpmn) · [PNG](01-inicio/bpmn/resolver-apelacion-actual.png)

## 02 — Requerimientos

- [Trazabilidad RF → CU → prueba → issue](02-requerimientos/trazabilidad-rf-cu.md)

**Requerimientos funcionales generales**

- [RF-26 Gestionar estudiantes](02-requerimientos/funcionales/RF-26-gestion-de-estudiantes.md): CU-16 registra fichas y CU-19 las inscribe; implementación provisional en la rama PC3.
- [RF-27 Gestionar cursos y grupos](02-requerimientos/funcionales/RF-27-gestion-de-cursos.md): CU-17 a CU-19 implementados con un modelo simplificado sujeto a revisión.
- [RF-28 Gestionar profesores](02-requerimientos/funcionales/RF-28-gestion-de-profesores.md): CU-18, CU-20 y CU-21 implementados; perfiles enlazados a usuarios existentes.
- [RF-29 Gestionar vínculos y acceso](02-requerimientos/funcionales/RF-29-gestionar-vinculos-y-acceso.md)
- [RF-30 Gestionar seguimiento académico](02-requerimientos/funcionales/RF-30-gestionar-seguimiento-academico.md)
- [RF-31 Gestionar comunicación y avisos](02-requerimientos/funcionales/RF-31-gestionar-comunicacion-y-avisos.md)
- [RF-32 Gestionar apelaciones y pendientes](02-requerimientos/funcionales/RF-32-gestionar-apelaciones-y-pendientes.md)

**Refinamientos detallados conservados por épica** (RF-01 a RF-25; ver [épicas](01-inicio/problema-y-vision.md#épicas))

| 1. Registro y consulta de notas y evaluaciones | 2. Comunicación entre la escuela y la persona encargada | 3. Seguimiento de apelaciones y pendientes |
|---|---|---|
| [RF-01 Vincular encargado con estudiante](02-requerimientos/funcionales/RF-01-vincular-encargado-con-estudiante.md) | [RF-10 Avisar publicación de evaluación](02-requerimientos/funcionales/RF-10-avisar-publicacion-de-evaluacion.md) | [RF-17 Presentar apelación](02-requerimientos/funcionales/RF-17-presentar-apelacion.md) |
| [RF-02 Consultar perfil del estudiante](02-requerimientos/funcionales/RF-02-consultar-perfil-del-estudiante.md) | [RF-11 Avisar publicación de calificación](02-requerimientos/funcionales/RF-11-avisar-publicacion-de-calificacion.md) | [RF-18 Asignar apelación al Comité](02-requerimientos/funcionales/RF-18-asignar-apelacion-al-comite.md) |
| [RF-03 Consultar evaluaciones y calificaciones](02-requerimientos/funcionales/RF-03-consultar-evaluaciones-y-calificaciones.md) | [RF-12 Registrar acuse de consulta](02-requerimientos/funcionales/RF-12-registrar-acuse-de-consulta.md) | [RF-19 Registrar aportes de docente y Dirección](02-requerimientos/funcionales/RF-19-registrar-aportes-de-docente-y-direccion.md) |
| [RF-04 Registrar evaluación asignada](02-requerimientos/funcionales/RF-04-registrar-evaluacion-asignada.md) | [RF-13 Recordar avisos no consultados](02-requerimientos/funcionales/RF-13-recordar-avisos-no-consultados.md) | [RF-20 Registrar resolución de apelación](02-requerimientos/funcionales/RF-20-registrar-resolucion-de-apelacion.md) |
| [RF-05 Confirmar congruencia con el cuaderno](02-requerimientos/funcionales/RF-05-confirmar-congruencia-con-el-cuaderno.md) | [RF-14 Intercambiar mensajes](02-requerimientos/funcionales/RF-14-intercambiar-mensajes.md) | [RF-21 Controlar estado y plazo de apelación](02-requerimientos/funcionales/RF-21-controlar-estado-y-plazo-de-apelacion.md) |
| [RF-06 Publicar evaluación asignada](02-requerimientos/funcionales/RF-06-publicar-evaluacion-asignada.md) | [RF-15 Presentar queja por información no recibida](02-requerimientos/funcionales/RF-15-presentar-queja-por-informacion-no-recibida.md) | [RF-22 Configurar plazos y recordatorios](02-requerimientos/funcionales/RF-22-configurar-plazos-y-recordatorios.md) |
| [RF-07 Registrar calificaciones del grupo](02-requerimientos/funcionales/RF-07-registrar-calificaciones-del-grupo.md) | [RF-16 Atender queja por información no recibida](02-requerimientos/funcionales/RF-16-atender-queja-por-informacion-no-recibida.md) | [RF-23 Alertar avisos sin consulta](02-requerimientos/funcionales/RF-23-alertar-avisos-sin-consulta.md) |
| [RF-08 Publicar calificación](02-requerimientos/funcionales/RF-08-publicar-calificacion.md) | | [RF-24 Consultar estado del seguimiento](02-requerimientos/funcionales/RF-24-consultar-estado-del-seguimiento.md) |
| [RF-09 Corregir calificación con historial](02-requerimientos/funcionales/RF-09-corregir-calificacion-con-historial.md) | | [RF-25 Consultar registro de consultas](02-requerimientos/funcionales/RF-25-consultar-registro-de-consultas.md) |

**Suplementarios**

- [RS-01 Usabilidad para personas encargadas con poca experiencia digital](02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md)
- [RS-02 Trazabilidad: saber quién leyó qué y cuándo](02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md)
- [RS-03 Seguridad por actor y vínculo](02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)

## 03 — Casos de uso

- [Actores](03-casos-de-uso/actores.md)
- [Diagrama de casos de uso](03-casos-de-uso/diagrama-casos-de-uso.svg) (fuente SVG editable).
- [Prototipos](03-casos-de-uso/prototipos/README.md)

| CU | Prueba |
|---|---|
| [CU-01 Consultar progreso académico](03-casos-de-uso/CU-01-consultar-progreso-academico.md) | [.feature](04-pruebas/CU-01-consultar-progreso-academico.feature) |
| [CU-02 Vincular encargado con estudiante](03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) | [.feature](04-pruebas/CU-02-vincular-encargado-con-estudiante.feature) |
| [CU-03 Publicar evaluación asignada](03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) | [.feature](04-pruebas/CU-03-publicar-evaluacion-asignada.feature) |
| [CU-04 Publicar calificación](03-casos-de-uso/CU-04-publicar-calificacion.md) | [.feature](04-pruebas/CU-04-publicar-calificacion.feature) |
| [CU-05 Dar seguimiento a avisos no consultados](03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | [.feature](04-pruebas/CU-05-dar-seguimiento-a-avisos-no-consultados.feature) |
| [CU-06 Comunicarse sobre un estudiante](03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | [.feature](04-pruebas/CU-06-comunicarse-sobre-un-estudiante.feature) |
| [CU-07 Presentar apelación de una calificación](03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | [.feature](04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature) |
| [CU-08 Supervisar el seguimiento académico](03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) | [.feature](04-pruebas/CU-08-supervisar-seguimiento-academico.feature) |
| [CU-09 Corregir una calificación publicada](03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) | [.feature](04-pruebas/CU-09-corregir-calificacion-publicada.feature) |
| [CU-10 Configurar plazos y recordatorios](03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md) | [.feature](04-pruebas/CU-10-configurar-plazos-y-recordatorios.feature) |
| [CU-12 Presentar queja por información no recibida](03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | [.feature](04-pruebas/CU-12-presentar-queja-por-informacion-no-recibida.feature) |
| [CU-13 Aportar información a una apelación](03-casos-de-uso/CU-13-aportar-informacion-a-apelacion.md) | [.feature](04-pruebas/CU-13-aportar-informacion-a-apelacion.feature) |
| [CU-14 Registrar resolución de apelación](03-casos-de-uso/CU-14-registrar-resolucion-de-apelacion.md) | [.feature](04-pruebas/CU-14-registrar-resolucion-de-apelacion.feature) |
| [CU-15 Consultar resolución de apelación](03-casos-de-uso/CU-15-consultar-resolucion-de-apelacion.md) | [.feature](04-pruebas/CU-15-consultar-resolucion-de-apelacion.feature) |
| [CU-16 Registrar estudiante](03-casos-de-uso/CU-16-registrar-estudiante.md) | [feature](04-pruebas/CU-16-registrar-estudiante.feature) |
| [CU-17 Registrar grupo académico](03-casos-de-uso/CU-17-registrar-grupo-academico.md) | [feature](04-pruebas/CU-17-registrar-grupo-academico.feature) |
| [CU-18 Asignar docente a grupo](03-casos-de-uso/CU-18-asignar-docente-a-grupo.md) | [feature](04-pruebas/CU-18-asignar-docente-a-grupo.feature) |
| [CU-19 Inscribir estudiante en grupo](03-casos-de-uso/CU-19-inscribir-estudiante-en-grupo.md) | [feature](04-pruebas/CU-19-inscribir-estudiante-en-grupo.feature) |
| [CU-20 Registrar docente](03-casos-de-uso/CU-20-registrar-docente.md) | [feature](04-pruebas/CU-20-registrar-docente.feature) |
| [CU-21 Actualizar perfil docente](03-casos-de-uso/CU-21-actualizar-perfil-docente.md) | [feature](04-pruebas/CU-21-actualizar-perfil-docente.feature) |

CU-11 no existe: correspondía a la bitácora de auditoría del PC1, que se retiró (ver [cambios](cambios-pc1-pc2.md#casos-de-uso)).

## SDD — Especificación dirigida por diseño

- [Guía del curso sobre skills y SDD](06-configuracion/guia-sdd-y-skills-del-curso.md)
- [Constitución](../.specify/memory/constitution.md)
- [Spec CU-16](../specs/001-registrar-estudiante/spec.md) · [plan](../specs/001-registrar-estudiante/plan.md) · [tasks](../specs/001-registrar-estudiante/tasks.md)

## 04 — Pruebas

Un archivo `.feature` por caso de uso, en [04-pruebas/](04-pruebas/). Identificadores: `TP-CUxx-FB` para el flujo básico y `TP-CUxx-Ayy` para los alternos.

## 05 — Diseño

- [Arquitectura](05-diseno/arquitectura.md)
- [Lineamientos de backend](05-diseno/lineamientos-backend.md)
- [Lineamientos de frontend](05-diseno/lineamientos-frontend.md)
- [Lineamientos de base de datos](05-diseno/lineamientos-base-de-datos.md)

## 06 — Configuración

- [Ambiente de desarrollo](06-configuracion/ambiente-de-desarrollo.md)
