# RF-31 — Gestionar comunicación y avisos

| Campo | Detalle |
|---|---|
| Nivel | Requerimiento funcional general |
| Necesidad | Mantener informadas a las personas involucradas sobre el seguimiento académico. |
| Requisito | El Portal deberá dar seguimiento a los avisos, permitir mensajes sobre un estudiante y atender los reportes por información no recibida. |
| Casos de uso | [CU-05 Dar seguimiento a avisos no consultados](../../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md), [CU-06 Comunicarse sobre un estudiante](../../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md), [CU-12 Presentar queja por información no recibida](../../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| Requisitos detallados | RF-12 a RF-16 y RF-23. |
| Estado | Implementado provisionalmente en la rama PC3 dentro del Portal; correo, SMS y canales institucionales no están implementados. |

## Criterios generales

- El Portal registra quién consultó un aviso y cuándo, según RS-02.
- Los recordatorios respetan la configuración vigente y no duplican envíos dentro de un intervalo.
- Los avisos que siguen sin consulta se alertan a la Dirección.
- Mensajes y respuestas se relacionan con el estudiante pertinente y respetan permisos.
- La queja por información no recibida puede presentarse y atenderse con historial.

## Trazabilidad

RF-31 → CU-05 / CU-06 / CU-12 → features. RF-12 a RF-16 y RF-23 son requisitos detallados de este RF, no RF independientes.
