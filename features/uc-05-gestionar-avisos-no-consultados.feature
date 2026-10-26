# language: es
Característica: UC-05 — Gestionar avisos no consultados
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC05-FB — Flujo básico
    Dado que Aviso vigente a ENC-01 sin acuse; relación activa; han transcurrido 48 horas **[PV-01]**; cero recordatorios previos
    Cuando Activar revisión temporal; emitir primer recordatorio; registrar intento; ENC-01 consulta el aviso
    Entonces Se registra el recordatorio y luego un acuse con identidad, fecha y hora; cesan nuevas reiteraciones para ese aviso y destinatario

  Escenario: TP-UC05-A01 — UC-05-A01, paso 2
    Dado que Aviso con acuse registrado antes del ciclo temporal
    Cuando Activar revisión al cumplirse el intervalo
    Entonces No se emite recordatorio ni alerta; el acuse previo permanece inmutable

  Escenario: TP-UC05-A02 — UC-05-A02, paso 3
    Dado que Aviso pendiente; la relación ENC-01–EST-01 terminó antes del ciclo
    Cuando Activar revisión temporal
    Entonces Se cancelan reiteraciones para ENC-01; se conserva el historial; la falta de destinatario válido pasa a seguimiento institucional

  Escenario: TP-UC05-A03 — UC-05-A03, paso 4
    Dado que Aviso sin acuse; constan dos recordatorios **[PV-03]** y venció el intervalo aplicable
    Cuando Activar un nuevo ciclo de revisión
    Entonces No se emite tercer recordatorio; la alerta autorizada identifica estudiante, destinatario, publicación de origen, intentos y antigüedad; queda trazado el escalamiento

  Escenario: TP-UC05-A04 — UC-05-A04, paso 4
    Dado que Aviso pendiente; no existe intervalo vigente o los límites se contradicen
    Cuando Activar revisión temporal
    Entonces No se emite recordatorio; se registra y comunica la incidencia al responsable autorizado; el aviso conserva su estado

  Escenario: TP-UC05-A05 — UC-05-A05, paso 6
    Dado que Aviso elegible para recordatorio; el intento produce fallo controlado
    Cuando Activar revisión y registrar el fallo
    Entonces El intento queda fallido, sin acuse; el aviso continúa pendiente para el siguiente tratamiento permitido

  Escenario: TP-UC05-A06 — UC-05-A06, paso 7
    Dado que Primer recordatorio emitido correctamente; ENC-01 no consulta durante las siguientes 72 horas **[PV-02]**
    Cuando Cerrar el ciclo sin consulta y activar el ciclo posterior
    Entonces No se crea acuse; el primer intento permanece en historial; el segundo ciclo emite el recordatorio correspondiente o escala al alcanzar el límite

  Escenario: TP-UC05-A07 — UC-05-A07, paso 8
    Dado que Un aviso ya posee primer acuse de ENC-01 con fecha y hora
    Cuando ENC-01 vuelve a consultar el mismo aviso
    Entonces Se conserva intacto el primer acuse; no se reinician recordatorios ni se elimina evidencia anterior

  Escenario: TP-UC05-A08 — UC-05-A08, paso 4
    Dado que Ya se generó el recordatorio del intervalo vigente; el aviso continúa sin acuse
    Cuando Activar otra revisión dentro del mismo intervalo
    Entonces No se crea ni envía otro recordatorio; existe un solo intento para el intervalo y el aviso queda pendiente del siguiente
