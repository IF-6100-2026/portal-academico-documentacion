# RF-22 — Configurar plazos y recordatorios

| Campo | Detalle |
|---|---|
| Épica | 3. Seguimiento de apelaciones y pendientes |
| Actor | Dirección |
| Prioridad | Debe |
| Equivale en el PC1 a | RF-20 |

## Necesidad

El plazo de una apelación depende de la situación. La Dirección necesita ajustar los plazos y los recordatorios sin cambiar el sistema.

## Requisito

El Portal deberá permitir a la Dirección configurar el plazo de presentación y de resolución de apelaciones por tipo de evaluación, el intervalo entre recordatorios y la cantidad de recordatorios antes de alertarla.

## Criterios de aceptación

- Una configuración incoherente (valor vacío, negativo o un límite menor que el intervalo) se rechaza y se mantiene la anterior.
- Una configuración nueva se aplica a apelaciones y avisos nuevos; los casos abiertos conservan su plazo.
- Cada cambio conserva quién lo hizo, cuándo y los valores anteriores.

## Origen verificable

- **Evidencia:** [E-06, E-08](../../01-inicio/problema-y-vision.md#evidencia)
- **Proceso actual:** SP-01 ([trazabilidad BPMN → RF](../../01-inicio/bpmn/trazabilidad-bpmn-rf.md))
- **Problema:** [P-04](../../01-inicio/problema-y-vision.md#problemas)

## Casos de uso y escenarios

- [CU-10 — Configurar plazos y recordatorios](../../03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md): `TP-CU10-FB`, `TP-CU10-A02`, `TP-CU10-A03` en [CU-10-configurar-plazos-y-recordatorios.feature](../../04-pruebas/CU-10-configurar-plazos-y-recordatorios.feature)

La seguridad por actor y vínculo de este requisito se rige por [RS-03](../suplementarios/RS-03-seguridad-por-actor-y-vinculo.md).

> **Nota:** El Portal no trae plazos predeterminados como política; los valores de las pruebas son ejemplos.
