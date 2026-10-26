# language: es
Característica: UC-09 — Corregir publicación académica
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC09-FB — Flujo básico
    Dado que Resultado vigente v1 de EST-01 = 85; DOC-01 autorizado; corrección física documentada
    Cuando Proporcionar valor 88, motivo y referencia; confirmar correspondencia con medio físico; autorizar corrección
    Entonces Se crea v2 = 88 vinculada a v1; v1 permanece inmutable; v2 queda vigente; se genera un aviso de corrección adicional y queda trazabilidad completa

  Escenario: TP-UC09-A01 — UC-09-A01, paso 2
    Dado que DOC-99 no tiene atribución sobre 4-A
    Cuando Intentar corregir el resultado de EST-01
    Entonces La corrección se rechaza; ninguna versión cambia y el intento queda trazado

  Escenario: TP-UC09-A02 — UC-09-A02, paso 2
    Dado que DOC-01 examinó v1; otra actuación autorizada creó v2 antes de su solicitud
    Cuando Intentar aplicar una corrección basada en v1
    Entonces No se crea una versión sobre el estado desactualizado; v1 y v2 permanecen; se exige examinar la versión vigente

  Escenario: TP-UC09-A03 — UC-09-A03, paso 4
    Dado que DOC-01 autorizado; resultado vigente = 85
    Cuando Proporcionar 120 **[PV-T01]**, omitir motivo o volver a proporcionar 85
    Entonces No se crea una versión; se identifica el dato inválido, motivo ausente o falta de cambio efectivo

  Escenario: TP-UC09-A04 — UC-09-A04, paso 4
    Dado que Corrección propuesta válida; falta confirmación de su reflejo en el medio físico oficial
    Cuando Solicitar publicación de la corrección
    Entonces La versión vigente se conserva; la propuesta permanece no publicada; no se genera aviso de corrección

  Escenario: TP-UC09-A05 — UC-09-A05, paso 7
    Dado que Corrección válida para EST-02, que carece de encargado activo
    Cuando Publicar la nueva versión con confirmación físico–digital válida
    Entonces La nueva versión queda vigente y la previa permanece; no se inventa destinatario; la ausencia pasa a seguimiento institucional

  Escenario: TP-UC09-A06 — UC-09-A06, paso 8
    Dado que Corrección publicada; el intento de aviso produce fallo controlado
    Cuando Registrar el resultado fallido del aviso
    Entonces Ambas versiones permanecen; el aviso queda fallido y sin acuse; queda disponible para UC-05 y no se repite la corrección
