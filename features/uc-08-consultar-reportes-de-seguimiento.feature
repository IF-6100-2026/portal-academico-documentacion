# language: es
Característica: UC-08 — Consultar reportes de seguimiento
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC08-FB — Flujo básico
    Dado que DIR-01 autorizado para 4-A; existen resultados publicados, estados de avisos y casos del II periodo
    Cuando Solicitar rendimiento, consulta de avisos, comunicación y discrepancias de 4-A; pedir detalle autorizado de un indicador
    Entonces Se proporcionan agregados con filtros, definiciones y fecha de corte y solo el detalle permitido; la consulta queda trazada

  Escenario: TP-UC08-A01 — UC-08-A01, paso 2
    Dado que Usuario sin rol de Dirección o DIR-01 fuera del ámbito solicitado
    Cuando Solicitar reporte de un grupo no autorizado
    Entonces Se rechaza la consulta sin revelar agregados ni detalles; el intento queda trazado

  Escenario: TP-UC08-A02 — UC-08-A02, paso 4
    Dado que DIR-01 autorizado
    Cuando Solicitar periodo inexistente o criterios contradictorios
    Entonces No se generan resultados; se identifican los criterios por corregir y no se mezclan ámbitos

  Escenario: TP-UC08-A03 — UC-08-A03, paso 5
    Dado que Ámbito y criterios válidos; no existen publicaciones en el periodo
    Cuando Solicitar reporte
    Entonces Se proporciona resultado vacío con criterios y fecha de corte; no aparecen borradores ni datos de otros ámbitos; la consulta queda trazada

  Escenario: TP-UC08-A04 — UC-08-A04, paso 8
    Dado que DIR-01 posee acceso agregado a 4-A pero no al detalle individual solicitado
    Cuando Obtener agregado permitido y solicitar detalle fuera del alcance
    Entonces El detalle se deniega; el agregado autorizado permanece disponible; el intento queda trazado sin exposición individual
