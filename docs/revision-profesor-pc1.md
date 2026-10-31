# Respuesta a las observaciones del profesor — PC1 → PC2

Fuente: captura de la evaluación del Entregable 1 compartida por Sebastián el 30 de octubre de 2026. Calificación del PC1: **68/100**. Esta tabla registra cómo se atienden las observaciones; no constituye aprobación del PC2 ni confirma supuestos con la escuela.

## Decisión de integración

Se toma el análisis de Josué como base PC2 y se incorporan navegación, diagramas y configuración. Se mantiene el PC1 como antecedente histórico, claramente separado en el README. El alcance integra registro/consulta académica, comunicación y seguimiento, con las quejas como caso propio. Las equivalencias permiten seguir cada cambio sin reutilizar identificadores retirados.

| Observación de la evaluación | Respuesta integrada | Evidencia / límite |
|---|---|---|
| BPMN: cierres sin estado final, bifurcaciones abiertas y lectura difícil | Un fin por proceso con estado explícito, compuertas de unión y apelación separada como subproceso. Se incorporan las imágenes. | [Proceso y subproceso](01-inicio/bpmn/trazabilidad-bpmn-rf.md). El avance general es de izquierda a derecha, con cruces de carril por responsabilidad y retorno del ciclo por arriba. |
| BPMN: marcas de pendiente o confirmado en el proceso actual | Las marcas se retiran del modelo AS-IS; se conserva evidencia y explicación del cambio fuera del diagrama. | [Trazabilidad de actividades](01-inicio/bpmn/trazabilidad-bpmn-rf.md). Las decisiones de modelado no sustituyen validación institucional. |
| RF asociados a hipótesis o sin actividad de origen | Se mantienen matrices en ambos sentidos y equivalencias de los 35 RF del PC1 a los 25 del PC2. | [BPMN → RF](01-inicio/bpmn/trazabilidad-bpmn-rf.md), [RF → CU → prueba](02-requerimientos/trazabilidad-rf-cu.md). La vinculación es habilitante; no se afirma que exista una actividad manual idéntica en el proceso actual. |
| RF orientados a solución y actor genérico “usuario” | Se agrupan en tres épicas y se nombran docente, persona encargada, Comité y Dirección. Autenticación y autorización quedan como condiciones transversales. | [Problema, actores y épicas](01-inicio/problema-y-vision.md); [RF](02-requerimientos/funcionales/). |
| Reducir y profundizar RS | Tres RS: usabilidad, constancia de lectura y seguridad por actor/vínculo; cada uno muestra efectos en CU y criterios medibles. | [RS](02-requerimientos/suplementarios/). Tiempos, cantidad de participantes y retención son propuestas, no políticas aprobadas. |
| Actores incompletos o sin participación | Se incorpora Comité de Evaluación. Se retiran Auditor y Proveedor externo del MVP con justificación. | [Actores](03-casos-de-uso/actores.md). Estudiante permanece en AS-IS, sin cuenta propia en el Portal. |
| Diagrama de CU sin actores secundarios ni relaciones declaradas | Se agregan las asociaciones y CU-09 extiende CU-07; se genera el PNG desde la fuente. | [Fuente](03-casos-de-uso/diagrama-casos-de-uso.puml) y [imagen](03-casos-de-uso/diagrama-casos-de-uso.png). |
| Problema y CU inconsistentes: comunicación/apelación detalladas mientras figuraban por validar | Se explicita la evidencia de entrevista ya citada en el repositorio y se revisa problema/alcance, sin presentar los estados propuestos como hechos institucionales. | [Evidencia y supuestos](01-inicio/problema-y-vision.md), [CU-07](03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md). No se verificó independientemente la entrevista original en esta revisión. |
| UC-08, UC-10 y UC-11 nacen de la solución | CU-08 se relaciona con supervisión, CU-10 con plazos/recordatorios; se retira UC-11 como caso autónomo y su propósito pasa a RS-02. | [Equivalencias](cambios-pc1-pc2.md). CU-11 no se reutiliza. |
| Quejas sin caso de uso | CU-12 diferencia información no recibida de apelación de una calificación; incluye RF-15/RF-16, pruebas y participación de Dirección. | [CU-12](03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md). |
| Escenarios y formato logrados | Se conserva el trabajo existente y se agregan dos negativos de acceso: sesión inválida y vínculo revocado al abrir un aviso. | [Pruebas](04-pruebas/): 11 archivos, 71 escenarios especificados; no ejecutados contra código académico. |

## Supuestos que siguen abiertos

- Responsable de registrar vínculos, cardinalidad de encargados/estudiantes y estados del trámite propuestos en S-02 a S-04.
- El detalle del plazo de apelación depende de cada situación: no se fija un plazo institucional inventado.
- La participación simultánea del Comité, docente y Dirección en el BPMN es una decisión de modelado; la evidencia citada confirma participación, no necesariamente simultaneidad exacta.
- La relación entre hojas impresas y reporte semestral (S-01) requiere confirmar que se trata del mismo documento.

Estas cuestiones no se resuelven simplemente quitando una etiqueta de “pendiente”. Se mantienen visibles fuera del BPMN para validarlas y ajustar el modelo cuando corresponda.

## Qué no demuestra esta integración

La captura evalúa el PC1 y pide correcciones antes del PC2. No establece por sí sola que deban fusionarse los tres repositorios de código/documentación en uno. Se unifican aquí las versiones y criterios; la estructura final de entrega se confirma con el enunciado del PC2. Tampoco están implementados los CU de negocio ni las políticas de seguridad.
