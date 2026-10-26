# language: es
Característica: UC-06 — Comunicarse sobre un estudiante
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC06-FB — Flujo básico
    Dado que ENC-01 relacionado con EST-01; DOC-01 asignado y ambos activos
    Cuando ENC-01 registra un mensaje sobre EST-01; DOC-01 lo consulta y responde
    Entonces Mensaje, consulta y respuesta quedan vinculados a EST-01 con autor, destinatario y fecha; el contenido previo permanece inmutable

  Escenario: TP-UC06-A01 — UC-06-A01, paso 2
    Dado que ENC-99 carece de relación con EST-01 o DOC-99 carece de asignación
    Cuando Solicitar acceso o envío en una conversación sobre EST-01
    Entonces Se rechaza el acceso sin confirmar contenido ni participantes; el intento queda trazado

  Escenario: TP-UC06-A02 — UC-06-A02, paso 4
    Dado que La relación era válida al iniciar; el destinatario dejó de estar autorizado antes del envío
    Cuando Proporcionar el mensaje y solicitar su envío
    Entonces El mensaje no se envía; se conserva como no enviado y se comunica la condición de autorización

  Escenario: TP-UC06-A03 — UC-06-A03, paso 4
    Dado que Participantes autorizados
    Cuando Proporcionar asunto vacío o contenido que incumple las reglas institucionales **[PV: reglas por validar]**
    Entonces No se registra un mensaje enviado; se identifica la corrección requerida y no se notifica al destinatario

  Escenario: TP-UC06-A04 — UC-06-A04, paso 9
    Dado que Mensaje enviado y consultado por DOC-01; no existe respuesta
    Cuando Finalizar el periodo de atención previsto sin registrar respuesta
    Entonces La conversación conserva mensaje y evidencia de consulta; no aparece una respuesta ficticia ni se altera el original; queda pendiente según protocolo
