# language: es
Característica: UC-07 — Gestionar discrepancia académica
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC07-FB — Flujo básico
    Dado que ENC-01 autorizado; resultado de EVA-01 publicado hace menos de diez días hábiles **[PV-04]**; no existe caso equivalente
    Cuando ENC-01 presenta motivo y respaldo; DOC-01 responde dentro de tres días hábiles **[PV-05]** sin alterar la nota; ENC-01 acepta
    Entonces Se crea un identificador y plazo; quedan apertura, respuesta y aceptación; el caso cierra resuelto y el resultado académico no cambia

  Escenario: TP-UC07-A01 — UC-07-A01, paso 2
    Dado que ENC-99 sin relación con EST-01
    Cuando Intentar abrir discrepancia sobre el resultado de EVA-01 de EST-01
    Entonces La apertura se rechaza sin exponer el registro; el intento queda trazado

  Escenario: TP-UC07-A02 — UC-07-A02, paso 2
    Dado que Registro no elegible o publicación con antigüedad mayor a diez días hábiles **[PV-04]**
    Cuando Solicitar apertura ordinaria
    Entonces No se crea caso ordinario; se comunica el fundamento aplicable y se conserva trazabilidad de la solicitud

  Escenario: TP-UC07-A03 — UC-07-A03, paso 4
    Dado que ENC-01 autorizado; faltan motivo o descripción requeridos
    Cuando Solicitar apertura con información incompleta
    Entonces El caso formal todavía no se abre; se identifican datos pendientes; no se altera el registro académico

  Escenario: TP-UC07-A04 — UC-07-A04, paso 4
    Dado que Ya existe una discrepancia abierta de ENC-01 sobre el mismo registro y motivo
    Cuando Repetir la solicitud equivalente
    Entonces Se conserva un único caso; se proporciona su identificador y estado; no se duplican plazos ni notificaciones

  Escenario: TP-UC07-A05 — UC-07-A05, paso 7
    Dado que Caso abierto; DOC-01 concluye que el resultado publicado requiere corrección
    Cuando Registrar fundamento; ejecutar UC-09; continuar la respuesta de la discrepancia
    Entonces UC-09 crea una nueva versión; la anterior permanece; el caso referencia ambas y continúa desde la respuesta emitida

  Escenario: TP-UC07-A06 — UC-07-A06, paso 6
    Dado que Caso abierto; transcurren tres días hábiles **[PV-05]** sin respuesta docente
    Cuando Activar control de vencimiento; asignar DIR-01; DIR-01 registra resolución que mantiene el resultado
    Entonces El caso pasa a escalado, la mediación y su fundamento quedan trazados, se comunica la resolución y se cierra sin cambio automático de nota

  Escenario: TP-UC07-A07 — UC-07-A07, paso 9
    Dado que DOC-01 emitió respuesta; ENC-01 registra rechazo fundado dentro del plazo
    Cuando Escalar a DIR-01; registrar mediación que ratifica el resultado
    Entonces El caso queda resuelto por mediación; la ratificación se comunica; el resultado permanece y todo el historial se conserva

  Escenario: TP-UC07-A08 — UC-07-A08, paso 9
    Dado que DOC-01 emitió respuesta; ENC-01 la rechaza; DIR-01 determina una corrección fundada
    Cuando Escalar; registrar resolución; DOC-01 ejecuta UC-09; asociar la nueva versión y cerrar
    Entonces La versión anterior permanece inmutable, la nueva queda vigente, el caso referencia la corrección y cierra resuelto por mediación
