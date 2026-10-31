# CU-09-CorregirCalificacionPublicada («Corregir una calificación publicada»)

| Campo | Detalle |
|---|---|
| **Descripción** | Corregir una calificación publicada mediante una versión nueva, sin borrar la anterior, y avisar a la persona encargada. Lo más común es que la corrección venga de una resolución del Comité en una apelación (E-11), pero la docente también puede corregir un error propio. |
| **Estereotipo y paquete** | «Épica 1» Registro y consulta de notas y evaluaciones |
| **Precondiciones** | 1. La calificación está publicada.<br>2. La docente tiene asignación sobre el grupo, la materia y el periodo. |
| **Postcondiciones** | Existe una nueva versión vigente ligada a la anterior, con motivo, autor y fecha, y cada persona encargada con vínculo activo recibe un aviso.<br>**Garantía mínima** (si no termina con éxito): La versión anterior nunca se elimina ni se sobrescribe; nada se publica sin motivo y sin confirmación con el cuaderno. |
| **Actores** | A-02 Docente (principal) |
| **Relaciones del caso de uso** | Asociado con los actores A-02 Docente. **«extend» [CU-07](CU-07-gestionar-apelacion-de-calificacion.md)**: extiende a CU-07 en el punto *Resolución con cambio de calificación*. En ese caso, el motivo es la resolución del Comité y la versión nueva queda referenciada en la apelación. También puede iniciarse sola, por decisión de la docente. |

## Flujo básico

1. La docente elige la calificación que va a corregir.
2. El Portal valida la asignación de la docente y muestra la versión vigente (RS-03) (A01).
3. La docente indica el valor corregido, el motivo y, si corresponde, la apelación que lo origina.
4. El Portal valida el formato, que el valor cambie y que exista un motivo (A02, A03).
5. La docente confirma que la corrección coincide con el cuaderno de la asignatura (A04).
6. El Portal crea la nueva versión vigente, ligada a la anterior, con autor, fecha y motivo.
7. El Portal genera un aviso de corrección para cada persona encargada con vínculo activo (A05).
8. Si la corrección viene de una apelación, el Portal la agrega al expediente.
9. El Portal muestra a la docente el resumen.

## Flujos alternos

- **A01 — Docente sin asignación (paso 2).** El Portal rechaza la corrección sin modificar versiones. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — La versión vigente cambió (paso 4).** Otra corrección se publicó después de que la docente abrió la calificación. El Portal no aplica la corrección sobre una versión desactualizada y muestra la versión vigente. El caso de uso termina.
- **A03 — Dato inválido, sin motivo o sin cambio (paso 4).** El Portal no crea la nueva versión e indica qué corregir. La docente puede retomar el paso 3.
- **A04 — Falta la confirmación con el cuaderno (paso 5).** El Portal no publica la corrección y conserva la versión vigente. La docente puede retomar el paso 5 después de actualizar el cuaderno.
- **A05 — Estudiante sin persona encargada activa (paso 7).** El Portal publica la nueva versión sin generar aviso. El Portal señala al estudiante para la supervisión de la Dirección (CU-08). El flujo continúa en el paso 8.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Publicar evaluación y resultado](prototipos/stitch-2/screen.png)
- **Métricas:** complejidad media (9 pasos y 5 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-05](../02-requerimientos/funcionales/RF-05-confirmar-congruencia-con-el-cuaderno.md), [RF-09](../02-requerimientos/funcionales/RF-09-corregir-calificacion-con-historial.md), [RF-11](../02-requerimientos/funcionales/RF-11-avisar-publicacion-de-calificacion.md)
- **Pruebas:** [CU-09-corregir-calificacion-publicada.feature](../04-pruebas/CU-09-corregir-calificacion-publicada.feature)
- **Issue:** [Issue](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/11) <!-- issue:CU-09 -->
- **Equivale en el PC1 a:** UC-09 («Corregir publicación académica»)
