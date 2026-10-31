# Glosario de siglas e identificadores

Siglas y prefijos que se usan en la documentación del Portal Académico. Cuando una sigla es parte de un identificador, se indica su formato (`xx` es un número de dos dígitos).

## Artefactos del proyecto

| Sigla | Significado | Formato y ejemplo | Dónde está |
|---|---|---|---|
| **RF** | Requerimiento funcional: algo que el sistema debe hacer para un actor | `RF-01` … `RF-25` | [02-requerimientos/funcionales/](02-requerimientos/funcionales/) |
| **RS** | Requerimiento suplementario: una cualidad del sistema (usabilidad, trazabilidad, seguridad) | `RS-01` … `RS-03` | [02-requerimientos/suplementarios/](02-requerimientos/suplementarios/) |
| **CU** | Caso de uso: interacción completa entre un actor y el sistema para lograr un objetivo | `CU-01` … `CU-10`, `CU-12` | [03-casos-de-uso/](03-casos-de-uso/) |
| **CU-xx-Ayy** | Flujo alterno `yy` del caso de uso `xx`: lo que pasa cuando algo se desvía del flujo básico | `CU-07-A05` | Dentro de cada CU |
| **A-xx** | Actor | `A-01` Persona encargada legal … `A-05` Tiempo | [actores.md](03-casos-de-uso/actores.md) |
| **TP** | Escenario de prueba de aceptación, escrito en un archivo `.feature` | `TP-CU07-A05` | [04-pruebas/](04-pruebas/) |
| **FB** | Flujo básico: el camino normal y exitoso de un caso de uso | `TP-CU01-FB` | Dentro de cada CU y `.feature` |
| **Ayy** | Flujo alterno número `yy` | `TP-CU01-A02` | Dentro de cada CU y `.feature` |

## Análisis del problema

| Sigla | Significado | Formato y ejemplo | Dónde está |
|---|---|---|---|
| **E** | Evidencia: hecho confirmado por la escuela (entrevista o levantamiento inicial) | `E-01` … `E-14` | [problema-y-vision.md](01-inicio/problema-y-vision.md#evidencia) |
| **P** | Problema del proceso actual que el sistema debe resolver | `P-01` … `P-05` | [problema-y-vision.md](01-inicio/problema-y-vision.md#problemas) |
| **S** | Supuesto abierto: algo propuesto por el equipo que falta confirmar | `S-01` … `S-04` | [problema-y-vision.md](01-inicio/problema-y-vision.md#supuestos-abiertos) |
| **MVP** | *Minimum Viable Product*, producto mínimo viable: la primera versión con lo indispensable | — | [problema-y-vision.md](01-inicio/problema-y-vision.md#alcance-del-mvp) |
| **Épica** | Grupo de requerimientos que responde a un mismo objetivo de los actores | Épicas 1, 2 y 3 | [problema-y-vision.md](01-inicio/problema-y-vision.md#épicas) |

## Proceso actual (BPMN)

| Sigla | Significado | Formato y ejemplo | Dónde está |
|---|---|---|---|
| **BPMN** | *Business Process Model and Notation*: notación estándar para modelar procesos de negocio | Archivos `.bpmn` | [01-inicio/bpmn/](01-inicio/bpmn/trazabilidad-bpmn-rf.md) |
| **AS-IS** | Proceso tal como se hace hoy, sin el sistema | — | El BPMN es AS-IS |
| **TO-BE** | Proceso propuesto, con el sistema | — | Lo describen los casos de uso |
| **AC** | Actividad del proceso principal | `AC-01` … `AC-11` | [trazabilidad-bpmn-rf.md](01-inicio/bpmn/trazabilidad-bpmn-rf.md) |
| **SP** | Subproceso: parte del proceso separada en su propio diagrama | `SP-01` Resolver la apelación | `resolver-apelacion-actual.bpmn` |
| **AP** | Actividad dentro del subproceso de apelación | `AP-01` … `AP-05` | `resolver-apelacion-actual.bpmn` |

## Diagrama de contexto

| Sigla | Significado | Formato y ejemplo | Dónde está |
|---|---|---|---|
| **F** | Flujo de datos entre una entidad externa y el Portal | `F-01` … `F-34` | [diagrama-de-contexto.md](01-inicio/diagrama-de-contexto.md) |

## Datos de prueba en los `.feature`

Son personas y registros ficticios que se usan solo en los escenarios.

| Sigla | Significado | Ejemplo |
|---|---|---|
| **ENC** | Persona encargada legal | `ENC-01`; `ENC-99` es una persona encargada sin vínculo |
| **EST** | Estudiante | `EST-01` |
| **DOC** | Docente | `DOC-01`; `DOC-99` es una docente sin asignación |
| **DIR** | Persona de la Dirección | `DIR-01` |
| **EVA** | Evaluación | `EVA-01` |
| **AV** | Aviso enviado por el Portal | `AV-01` |
| **4-A, 5-B** | Grupo de primaria (grado y sección) | `4-A` es cuarto grado, sección A |
| **I, II** | Periodo lectivo | `II periodo` |

## Curso y entregas

| Sigla | Significado |
|---|---|
| **PC1, PC2, PC3** | Puntos de control (entregables) 1, 2 y 3 del proyecto del curso |
| **IF-6100** | Código del curso Análisis y Diseño de Software (UCR, Sede del Atlántico) |
| **RUP** | *Rational Unified Process*, Proceso Unificado: el PC2 corresponde a su fase de Elaboración |
| **UML** | *Unified Modeling Language*: notación del diagrama de casos de uso |
| **«include» / «extend»** | Relaciones UML entre casos de uso: «include» es un comportamiento obligatorio incluido en otro caso; «extend» es un comportamiento opcional que se agrega en un punto de extensión. Aquí solo existe CU-09 «extend» CU-07. |
| **FURPS+** | Clasificación de requerimientos de calidad: funcionalidad, usabilidad, confiabilidad (*reliability*), rendimiento (*performance*) y soporte, más restricciones |
| **ISO/IEC 25010** | Norma internacional de características de calidad del software |

## Siglas generales

| Sigla | Significado |
|---|---|
| **MEP** | Ministerio de Educación Pública de Costa Rica |
| **IA** | Inteligencia artificial |
| **SMS** | Mensaje de texto al teléfono |
| **PNG** | Formato de imagen en el que se exportan los diagramas |
| **`.feature`** | Archivo de escenarios de prueba escritos en Gherkin (Dado / Cuando / Entonces) |
| **Mermaid** | Notación de texto para diagramas, que GitHub dibuja directamente; se usa en el diagrama de arquitectura |

## Identificadores del PC1 (solo en las equivalencias)

Aparecen en [cambios-pc1-pc2.md](cambios-pc1-pc2.md) para relacionar el PC1 con el PC2. Ya no se usan en los documentos vigentes.

| Sigla | Significado en el PC1 | En el PC2 |
|---|---|---|
| **UC** | *Use case*, caso de uso (`UC-01` … `UC-11`) | Pasa a `CU` |
| **PV** | «Por validar»: marca de lo que faltaba confirmar | Ya no existe ninguna |
| **RN** | Regla de negocio (`RN-01` … `RN-16`) | Integradas en los RF y CU |
| **RS-SEC, RS-USA, RS-REN, RS-CON, RS-INT, RS-MAN, RS-COM, RS-PRI, RS-POR** | Categorías de requerimientos suplementarios del PC1: seguridad, usabilidad, rendimiento, confiabilidad, integridad, mantenibilidad, compatibilidad, privacidad y portabilidad | Quedan solo RS-01 a RS-03 |
| **ADM** | Administrador institucional en los datos de prueba (`ADM-01`) | El actor se retiró |
