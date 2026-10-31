# CU-07-GestionarApelacionDeCalificacion («Gestionar apelación de una calificación»)

| Campo | Detalle |
|---|---|
| **Descripción** | Llevar al Portal el proceso de apelación que hoy usa la escuela (E-07, E-11): la persona encargada apela por el cuaderno de recados, el Comité de Evaluación analiza el caso con la docente y la Dirección, emite la resolución y la comunica. El Portal guarda el expediente, los estados, los responsables y el plazo en un solo lugar (P-04). No decide ni cambia la calificación por sí mismo. |
| **Estereotipo y paquete** | «Épica 3» Seguimiento de apelaciones y pendientes |
| **Precondiciones** | 1. La persona encargada tiene un vínculo activo con el estudiante.<br>2. La calificación está publicada.<br>3. La Dirección configuró el plazo de presentación y el de resolución para el tipo de evaluación (CU-10). No hay plazos predeterminados: la escuela indicó que dependen de la situación (E-08). |
| **Postcondiciones** | La apelación queda resuelta por el Comité, con fundamento, aportes y fechas, y cerrada cuando la persona encargada consulta la resolución.<br>**Garantía mínima** (si no termina con éxito): La apelación nunca modifica la calificación de forma automática; los aportes y estados registrados no se alteran. |
| **Actores** | A-01 Persona encargada legal (principal: presenta la apelación); A-03 Comité de Evaluación (principal: la resuelve); A-02 Docente (secundario: aporta la prueba y su criterio); A-04 Dirección (secundario: participa y supervisa el plazo) |
| **Relaciones del caso de uso** | Asociado con los actores A-01 Persona encargada legal, A-03 Comité de Evaluación, A-02 Docente, A-04 Dirección. **Es extendido por [CU-09](CU-09-corregir-calificacion-publicada.md) («extend»)** en el punto de extensión *Resolución con cambio de calificación* (paso 8). Si la resolución del Comité ordena cambiar la calificación, la docente la corrige con CU-09 (flujo A06). La persona encargada puede llegar desde el detalle de una calificación en [CU-01](CU-01-consultar-progreso-academico.md) o desde [CU-06](CU-06-comunicarse-sobre-un-estudiante.md) (A05). No son «include» ni «extend»: se inicia como caso independiente. |

## Flujo básico

1. Desde una calificación publicada, la persona encargada solicita apelarla (RS-01).
2. El Portal valida el vínculo (RS-03), que la calificación esté publicada y que la solicitud esté dentro del plazo de presentación configurado (A01, A02).
3. La persona encargada indica los puntos apelados y el motivo, y adjunta un respaldo, por ejemplo una foto de la prueba.
4. El Portal valida que la información esté completa y que no exista otra apelación abierta sobre la misma calificación (A03, A04).
5. El Portal registra la apelación como `presentada`, con número, fecha y plazo de resolución. La asigna al Comité de Evaluación y avisa a la docente y a la Dirección.
6. El Comité abre el expediente y el Portal cambia el estado a `en revisión` (A07).
7. La docente adjunta la prueba y su criterio de calificación; la Dirección registra sus observaciones (A05).
8. El Comité registra la resolución con resultado, fundamento, fecha e integrantes. *Punto de extensión: Resolución con cambio de calificación.* (A06)
9. El Portal cambia el estado a `resuelta` y genera un aviso para la persona encargada.
10. La persona encargada consulta la resolución; el Portal registra el acuse y cambia el estado a `cerrada` (A08).

## Flujos alternos

- **A01 — Sin vínculo con el estudiante (paso 2).** El Portal rechaza la apelación sin mostrar la calificación. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — Calificación no publicada o plazo de presentación vencido (paso 2).** El Portal no registra la apelación e informa el motivo y el plazo configurado. El Portal registra la solicitud y el caso de uso termina.
- **A03 — Información incompleta (paso 4).** El Portal no registra la apelación e indica qué falta. La persona encargada puede retomar el paso 3 dentro del plazo.
- **A04 — Ya existe una apelación abierta sobre la calificación (paso 4).** El Portal no crea otra apelación. El Portal muestra el número y el estado de la existente, y el caso de uso termina.
- **A05 — El Comité solicita información adicional (paso 7).** El Comité indica qué necesita y a quién se lo pide (persona encargada o docente). El Portal cambia el estado a `pendiente de información` y avisa a la persona indicada. La persona responde; el Portal agrega la respuesta al expediente y vuelve a `en revisión`. El flujo continúa en el paso 8.
- **A06 — La resolución cambia la calificación (paso 8, extiende con CU-09).** El Comité registra que la calificación debe cambiar y el nuevo valor resuelto. El Portal avisa a la docente, que ejecuta CU-09 con la referencia de la apelación. El Portal agrega la nueva versión de la calificación al expediente; la anterior se conserva. El flujo continúa en el paso 9.
- **A07 — Plazo de resolución vencido (pasos 6 a 8).** Al superarse el plazo de resolución, el Portal marca la apelación como vencida y avisa a la Dirección. La apelación aparece como vencida en CU-08. No se resuelve ni se cierra de forma automática. El flujo continúa en el paso en que estaba.
- **A08 — La persona encargada no consulta la resolución (paso 10).** La apelación queda `resuelta`, no `cerrada`. El aviso de la resolución sigue el tratamiento de CU-05 (recordatorios y alerta a la Dirección), y el caso de uso termina.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-01](../02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md). **Estados propuestos:** `presentada` → `en revisión` → (`pendiente de información` → `en revisión`) → `resuelta` → `cerrada`. Una apelación fuera de plazo se marca como **vencida** sin cambiar de estado. Es una propuesta del equipo (supuesto S-03) basada en el caso real.
- **Especificación de interfaz de usuario:** [Presentar apelación](prototipos/stitch-3/screen.png)
- **Métricas:** complejidad alta (10 pasos y 8 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-09](../02-requerimientos/funcionales/RF-09-corregir-calificacion-con-historial.md) (en la extensión), [RF-17](../02-requerimientos/funcionales/RF-17-presentar-apelacion.md), [RF-18](../02-requerimientos/funcionales/RF-18-asignar-apelacion-al-comite.md), [RF-19](../02-requerimientos/funcionales/RF-19-registrar-aportes-de-docente-y-direccion.md), [RF-20](../02-requerimientos/funcionales/RF-20-registrar-resolucion-de-apelacion.md), [RF-21](../02-requerimientos/funcionales/RF-21-controlar-estado-y-plazo-de-apelacion.md)
- **Pruebas:** [CU-07-gestionar-apelacion-de-calificacion.feature](../04-pruebas/CU-07-gestionar-apelacion-de-calificacion.feature)
- **Issue:** [Issue](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/9) <!-- issue:CU-07 -->
- **Equivale en el PC1 a:** UC-07 («Gestionar discrepancia académica»)
