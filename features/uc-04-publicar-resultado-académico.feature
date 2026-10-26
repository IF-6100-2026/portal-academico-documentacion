# language: es
Característica: UC-04 — Publicar resultado académico
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC04-FB — Flujo básico
    Dado que EVA-01 existe; DOC-01 autorizado; resultados inéditos; devolución física confirmable
    Cuando Registrar EST-01 = 85 **[PV-T01]**, observación y fecha de devolución; confirmar correspondencia física; autorizar publicación
    Entonces Se crea la primera versión inmutable del resultado y la notificación de segunda etapa; la notificación de asignación permanece diferenciada; todo queda trazado

  Escenario: TP-UC04-A01 — UC-04-A01, paso 2
    Dado que DOC-99 no está asignado al grupo
    Cuando Solicitar publicación de resultados de EVA-01
    Entonces La operación se rechaza sin exponer ni modificar resultados; el intento queda trazado

  Escenario: TP-UC04-A02 — UC-04-A02, paso 2
    Dado que Evaluación inexistente o EVA-01 marcada como anulada
    Cuando Solicitar registro de resultados
    Entonces No se admiten resultados ni avisos; se comunica el estado que impide continuar y queda trazabilidad

  Escenario: TP-UC04-A03 — UC-04-A03, paso 4
    Dado que DOC-01 autorizado; escala provisional 0–100
    Cuando Proporcionar resultado 120 o dejar un resultado requerido incompleto
    Entonces No se publica el conjunto seleccionado; los valores de trabajo permanecen en borrador, se identifica cada valor por corregir y no se generan avisos

  Escenario: TP-UC04-A04 — UC-04-A04, paso 6
    Dado que Resultados válidos; falta confirmación de devolución o de coincidencia con el medio físico
    Cuando Solicitar publicación de resultados
    Entonces Los datos permanecen no publicados; no se genera segunda notificación; se identifica la confirmación pendiente

  Escenario: TP-UC04-A05 — UC-04-A05, paso 7
    Dado que EST-01 ya posee una versión publicada para EVA-01
    Cuando Intentar publicar un valor diferente mediante UC-04
    Entonces No se sobrescribe el valor vigente; se conserva la versión existente y el cambio se remite a UC-09

  Escenario: TP-UC04-A06 — UC-04-A06, paso 9
    Dado que EST-02 tiene resultado válido y carece de encargado activo
    Cuando Publicar el resultado con confirmación físico–digital válida
    Entonces El resultado queda publicado; no se inventa destinatario; la ausencia se incorpora al seguimiento institucional

  Escenario: TP-UC04-A07 — UC-04-A07, paso 10
    Dado que Resultado publicado; el intento de segunda notificación falla
    Cuando Registrar el resultado fallido del aviso
    Entonces El resultado permanece publicado; la notificación queda fallida y sin acuse; queda disponible para UC-05
