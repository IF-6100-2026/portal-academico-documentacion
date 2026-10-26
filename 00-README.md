# Portal Académico — PC1 Fase de Inicio

**Curso:** IF-6100 Análisis y Diseño de Sistemas  
**Organización de referencia:** Centro Educativo María Montessori (educación primaria)  
**Integrantes:** Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco  
**Fecha de revisión con el profesor:** miércoles 16 de septiembre de 2026  
**Fecha de cierre mostrada actualmente en Mediación Virtual:** lunes 21 de septiembre de 2026, 5:00 p. m.

## Decisión de nombre y alcance

El nombre canónico de esta versión es **Portal Académico — Centro Educativo María Montessori**. `Conecta Montessori` queda documentado únicamente como el nombre provisional utilizado en una versión anterior. El MVP se limita a **educación primaria** y a la comunicación verificable del progreso académico entre la escuela y los encargados legales.

Este paquete corresponde a la fase de **Inicio (Inception)** del Proceso Unificado. Según el enunciado del PC1, todavía no se diseña ni se construye software. La prioridad es demostrar trazabilidad completa:

> proceso actual (BPMN) → problema → requerimiento funcional → caso de uso → escenario de prueba

## Artefactos

1. [Documento consolidado de entrega](PC1-Portal-Academico-entrega.md)
2. [Problema, solución y visión](01-problema-vision.md)
3. [Proceso actual y trazabilidad desde BPMN](02-proceso-actual-y-trazabilidad.md)
4. [BPMN 2.0 del proceso actual](diagramas/02-proceso-actual.bpmn), [exportación SVG](diagramas/02-proceso-actual.svg) y [exportación PNG](diagramas/02-proceso-actual.png)
5. [Requerimientos funcionales](03-requerimientos-funcionales.md)
6. [Requerimientos suplementarios](04-requerimientos-suplementarios.md)
7. [Actores](05-actores.md), [fuente PlantUML](diagramas/05-casos-de-uso.puml), [diagrama SVG](diagramas/05-casos-de-uso.svg) y [diagrama PNG](diagramas/05-casos-de-uso.png)
8. [Casos de uso detallados](06-casos-de-uso-detallados.md)
9. [Guion de pruebas](07-guion-pruebas.md) y [archivos Gherkin](features/)
10. [Matriz de trazabilidad integral](08-matriz-trazabilidad.md)
11. [Supuestos y validaciones pendientes](09-supuestos-validaciones.md)
12. [Guion para la revisión del miércoles](10-guion-presentacion.md)
13. [Checklist de la rúbrica](11-checklist-rubrica.md)
14. [Prototipos de Stitch](prototipos/README.md)
15. [Validación complementaria de Daisy](13-validacion-entrevista-daisy.md)
16. [Declaración de uso de IA](12-declaracion-uso-ia.md)
17. [Presentación en PowerPoint](Portal-Academico-PC1-presentacion.pptx)

## Estado para la revisión

El paquete deja estructurados todos los artefactos solicitados por el PC1. Se incorporaron cinco prototipos exportados desde Google Stitch, once archivos Gherkin y la entrevista realizada a **Daisy Quirós Quesada, directora del Centro Educativo María Montessori**. Los puntos pendientes son parámetros institucionales concretos, como los plazos exactos de cada tipo de apelación.

La validación cruzada del paquete confirma **35 requerimientos funcionales**, **11 casos de uso**, **66 flujos alternos** y **77 pruebas**. Cada flujo básico y alterno tiene un escenario de prueba, y la matriz cubre las ocho observaciones del profesor.

La versión evita presentar como hechos las hipótesis que todavía no se han confirmado. En particular, la evidencia disponible confirma el uso de hojas impresas, la dependencia del estudiante como intermediario, retrasos u ocultamiento de esas hojas y quejas de encargados. Los demás detalles operativos aparecen marcados como **supuesto por validar**.

## Fuente de los criterios

- Enunciado y rúbrica del Entregable 1 en Mediación Virtual.
- Foro del proyecto y ocho observaciones del profesor del 7 de septiembre de 2026.
- Materiales de los módulos 1 a 3: visión y trazabilidad, BPMN, ingeniería de requerimientos, FURPS+/ISO 25010, actores, casos de uso y escenarios de prueba.
- Documento previo `PC1-definicion-problema-y-vision.md` recuperado de la tarea anterior de Codex.

## Nota de entrega

No se ha publicado ni enviado nada en Mediación Virtual. Antes de la entrega final conviene confirmar las preguntas de [supuestos y validaciones](09-supuestos-validaciones.md), incorporar las respuestas y volver a verificar los enlaces relativos del paquete.
