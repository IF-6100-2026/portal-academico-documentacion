# CU-10-ConfigurarPlazosYRecordatorios («Configurar plazos y recordatorios»)

| Campo | Detalle |
|---|---|
| **Descripción** | La escuela tiene plazos para resolver apelaciones, pero dependen de la situación (E-08), y la Dirección supervisa (E-06). Este caso de uso permite a la Dirección fijar esos plazos y la política de recordatorios sin cambiar el sistema y sin que el equipo invente valores.<br><br>**Por qué se conserva y se reduce:** la parte de plazos responde a evidencia (E-08). La administración de cuentas, roles y catálogos del PC1 no tenía un actor confirmado; se tratará en el diseño como carga inicial de datos ([RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)). |
| **Estereotipo y paquete** | «Épica 3» Seguimiento de apelaciones y pendientes |
| **Precondiciones** | 1. La Dirección tiene una cuenta activa. |
| **Postcondiciones** | La configuración nueva rige para las apelaciones y los avisos nuevos, y queda registrado quién la cambió, cuándo y qué valores tenía antes.<br>**Garantía mínima** (si no termina con éxito): Una configuración inválida no se aplica y la anterior sigue vigente. |
| **Actores** | A-04 Dirección (principal) |
| **Relaciones del caso de uso** | Asociado con los actores A-04 Dirección. Ninguna relación «include» o «extend». La configuración la usan [CU-05](CU-05-dar-seguimiento-a-avisos-no-consultados.md) y [CU-07](CU-07-gestionar-apelacion-de-calificacion.md). |

## Flujo básico

1. La Dirección solicita cambiar la configuración.
2. El Portal valida el rol de Dirección (RS-03) (A01).
3. La Dirección indica, por tipo de evaluación, el plazo para presentar una apelación y el plazo para resolverla; además, el intervalo entre recordatorios y la cantidad de recordatorios antes de alertarla.
4. El Portal valida que los valores sean coherentes (A02).
5. El Portal guarda la configuración con fecha de vigencia y conserva la anterior (A03).
6. El Portal informa desde cuándo rige y a qué apelaciones y avisos se aplica.

## Flujos alternos

- **A01 — Usuario sin rol de Dirección (paso 2).** El Portal rechaza la operación sin mostrar la configuración. El Portal registra el intento denegado y el caso de uso termina.
- **A02 — Valores incoherentes (paso 4).** Hay un valor vacío, negativo o una cantidad de recordatorios que no cabe en el plazo. El Portal rechaza la configuración, mantiene la anterior e indica los valores por corregir. La Dirección puede retomar el paso 3.
- **A03 — Hay apelaciones abiertas (paso 5).** El Portal aplica la configuración nueva solo a las apelaciones que se presenten desde su vigencia. Las apelaciones abiertas conservan el plazo con el que se presentaron. El flujo continúa en el paso 6.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Panel institucional de supervisión](prototipos/stitch-4/screen.png)
- **Métricas:** complejidad baja (6 pasos y 3 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-22](../02-requerimientos/funcionales/RF-22-configurar-plazos-y-recordatorios.md)
- **Pruebas:** [CU-10-configurar-plazos-y-recordatorios.feature](../04-pruebas/CU-10-configurar-plazos-y-recordatorios.feature)
- **Issue:** _por crear_ <!-- issue:CU-10 -->
- **Equivale en el PC1 a:** UC-10 («Administrar configuración y accesos»), reducido a plazos y recordatorios
