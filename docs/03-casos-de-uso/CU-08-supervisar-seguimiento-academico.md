# CU-08-SupervisarSeguimientoAcademico («Supervisar el seguimiento académico»)

| Campo | Detalle |
|---|---|
| **Descripción** | La Dirección supervisa el seguimiento académico (E-06), pero hoy lo pendiente está repartido entre cuadernos, reportes y comunicaciones (P-05). Este caso de uso le da una vista única de lo que necesita atención: avisos sin consultar, alertas, quejas y apelaciones con su plazo. También le permite comprobar quién consultó qué y cuándo (RS-02).<br><br>**Por qué se conserva:** responde a una función confirmada de la Dirección (E-06). Se retiró la parte de rendimiento académico agregado del PC1 (RF-30), porque ninguna evidencia la respalda. |
| **Estereotipo y paquete** | «Épica 3» Seguimiento de apelaciones y pendientes |
| **Precondiciones** | 1. La Dirección tiene una cuenta activa. |
| **Postcondiciones** | La Dirección ve los pendientes del periodo y del grupo elegidos, puede llegar al detalle de cada uno, y su consulta queda registrada.<br>**Garantía mínima** (si no termina con éxito): No se muestran borradores ni se modifica información académica. |
| **Actores** | A-04 Dirección (principal) |
| **Relaciones del caso de uso** | Asociado con los actores A-04 Dirección. Ninguna relación «include» o «extend». Muestra información producida por [CU-03](CU-03-publicar-evaluacion-asignada.md), [CU-04](CU-04-publicar-calificacion.md), [CU-05](CU-05-dar-seguimiento-a-avisos-no-consultados.md), [CU-07](CU-07-gestionar-apelacion-de-calificacion.md) y [CU-12](CU-12-presentar-queja-por-informacion-no-recibida.md). Desde una queja, la Dirección continúa en CU-12. |

## Flujo básico

1. La Dirección solicita el panel de supervisión.
2. El Portal valida el rol de Dirección (RS-03) (A01).
3. La Dirección elige periodo y, si quiere, grupo.
4. El Portal valida los criterios (A02).
5. El Portal muestra totales y listas de: avisos sin consultar y alertas de CU-05, estudiantes sin persona encargada activa, quejas abiertas y apelaciones por estado, incluidas las vencidas (A03).
6. La Dirección abre un elemento o un estudiante.
7. El Portal muestra el detalle y el registro de consultas: quién consultó cada publicación o aviso, y cuándo (RS-02).
8. El Portal registra la consulta de la Dirección.

## Flujos alternos

- **A01 — Usuario sin rol de Dirección (paso 2).** El Portal rechaza la consulta sin mostrar totales ni detalle. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — Criterios incoherentes (paso 4).** El Portal no ejecuta la consulta e indica qué criterio corregir. La Dirección puede retomar el paso 3.
- **A03 — Sin pendientes (paso 5).** El Portal muestra los totales en cero con los criterios aplicados. El flujo continúa en el paso 8.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Panel institucional de supervisión](prototipos/stitch-4/screen.png)
- **Métricas:** complejidad baja (8 pasos y 3 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-21](../02-requerimientos/funcionales/RF-21-controlar-estado-y-plazo-de-apelacion.md), [RF-23](../02-requerimientos/funcionales/RF-23-alertar-avisos-sin-consulta.md), [RF-24](../02-requerimientos/funcionales/RF-24-consultar-estado-del-seguimiento.md), [RF-25](../02-requerimientos/funcionales/RF-25-consultar-registro-de-consultas.md)
- **Pruebas:** [CU-08-supervisar-seguimiento-academico.feature](../04-pruebas/CU-08-supervisar-seguimiento-academico.feature)
- **Issue:** _por crear_ <!-- issue:CU-08 -->
- **Equivale en el PC1 a:** UC-08 («Consultar reportes de seguimiento»); absorbe la consulta de bitácora de UC-11
