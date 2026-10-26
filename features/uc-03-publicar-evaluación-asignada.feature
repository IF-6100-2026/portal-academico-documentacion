# language: es
Característica: UC-03 — Publicar evaluación asignada
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC03-FB — Flujo básico
    Dado que DOC-01 asignado a 4-A; EST-01 activo; ENC-01 vinculado; evaluación aún no publicada
    Cuando Registrar EVA-01, fechas e instrucciones; confirmar entrega al estudiantado y correspondencia con el medio físico; autorizar publicación
    Entonces Se publica una definición común y una instancia por estudiante activo, identificada como entregada para realización y todavía sin calificación; se genera únicamente la notificación de primera etapa; quedan estados, incidencias y trazabilidad

  Escenario: TP-UC03-A01 — UC-03-A01, paso 2
    Dado que DOC-99 activo pero no asignado a 4-A Matemática
    Cuando Solicitar publicación de EVA-01 para 4-A
    Entonces La operación se rechaza; no se publica ningún evento ni aviso y el intento queda trazado

  Escenario: TP-UC03-A02 — UC-03-A02, paso 4
    Dado que DOC-01 autorizado; fecha de vencimiento anterior a la entrega o falta un dato requerido
    Cuando Proporcionar EVA-01 con datos incoherentes; solicitar continuación
    Entonces Se identifican los datos por corregir; el evento permanece no publicado y no se generan avisos

  Escenario: TP-UC03-A03 — UC-03-A03, paso 4
    Dado que Nómina de 4-A contiene EST-01 activo y EST-03 retirado antes del evento
    Cuando Registrar y publicar EVA-01 para el grupo
    Entonces EST-03 queda excluido con fundamento comunicado; la publicación continúa para estudiantes activos y la exclusión queda trazada

  Escenario: TP-UC03-A04 — UC-03-A04, paso 6
    Dado que Datos académicos válidos; falta confirmación de registro o entrega en el medio físico
    Cuando Solicitar publicación sin la confirmación físico–digital
    Entonces La publicación se bloquea; el evento permanece no publicado; no se crean avisos y se identifica la confirmación pendiente

  Escenario: TP-UC03-A05 — UC-03-A05, paso 7
    Dado que EVA-01 y sus avisos de primera etapa ya fueron publicados
    Cuando Repetir la autorización de publicación con el mismo origen de grupo
    Entonces Se conserva una instancia por estudiante y una notificación de primera etapa por destinatario; no aparecen duplicados

  Escenario: TP-UC03-A06 — UC-03-A06, paso 9
    Dado que EST-01 tiene ENC-01; EST-02 no tiene encargado activo; ambos están en 4-A
    Cuando Publicar EVA-01 para el grupo con confirmación físico–digital válida
    Entonces Ambos eventos académicos quedan publicados; ENC-01 recibe aviso; EST-02 queda reportado sin destinatario para seguimiento institucional

  Escenario: TP-UC03-A07 — UC-03-A07, paso 10
    Dado que Evento válido y publicado; el intento de aviso a ENC-01 produce fallo controlado
    Cuando Ejecutar la publicación y registrar el resultado fallido del aviso
    Entonces El evento permanece publicado; el aviso queda fallido, sin acuse; queda disponible para UC-05 y no se duplica la evaluación
