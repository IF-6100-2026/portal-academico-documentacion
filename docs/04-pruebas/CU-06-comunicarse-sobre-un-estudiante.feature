# language: es
# Caso de uso: docs/03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md
# Requerimientos: RF-14, RF-15, RF-17, RS-02, RS-03
Característica: CU-06 — Comunicarse sobre un estudiante
  Como persona encargada legal o docente
  Quiero intercambiar mensajes sobre un estudiante
  Para reemplazar el cuaderno de recados y conservar el historial

  Antecedentes:
    Dado que ENC-01 tiene vínculo activo con EST-01
    Y DOC-01 está asignada al grupo de EST-01

  Escenario: TP-CU06-FB — Flujo básico
    Cuando ENC-01 envía a DOC-01 un mensaje sobre EST-01 con asunto y contenido
    Entonces el mensaje queda en la conversación de EST-01 y DOC-01 recibe un aviso
    Cuando DOC-01 abre la conversación
    Entonces ENC-01 ve que DOC-01 leyó el mensaje, con fecha y hora
    Cuando DOC-01 responde
    Entonces la respuesta queda con autor y fecha
    Y el mensaje original no cambia
    Y ENC-01 recibe un aviso de la respuesta

  Escenario: TP-CU06-A01 — Vínculo o asignación no autorizados (paso 2)
    Dado que ENC-99 no tiene vínculo con EST-01
    Cuando ENC-99 intenta abrir o enviar un mensaje sobre EST-01
    Entonces el Portal rechaza el acceso sin revelar contenido ni participantes
    Y el intento denegado queda registrado

  Escenario: TP-CU06-A02 — El destinatario dejó de estar autorizado (paso 4)
    Dado que DOC-01 dejó de estar asignada al grupo de EST-01 mientras ENC-01 redactaba
    Cuando ENC-01 envía el mensaje
    Entonces el mensaje no se envía y queda como no enviado
    Y el Portal informa el motivo

  Escenario: TP-CU06-A03 — Contenido vacío (paso 4)
    Cuando ENC-01 intenta enviar un mensaje con el asunto vacío
    Entonces el mensaje no se envía
    Y el Portal indica que falta el asunto

  Escenario: TP-CU06-A04 — Sin respuesta (paso 8)
    Dado que DOC-01 abrió el mensaje de ENC-01 y no respondió
    Cuando ENC-01 consulta la conversación
    Entonces ve su mensaje como leído y sin respuesta
    Y no existe ninguna respuesta registrada

  Escenario: TP-CU06-A05 — Inconformidad con una calificación (paso 3)
    Cuando ENC-01 indica que el asunto del mensaje es una calificación con la que no está de acuerdo
    Entonces el Portal le ofrece apelar esa calificación con CU-07
    Y el mensaje no se registra como apelación

  Escenario: TP-CU06-A06 — Información no recibida (paso 3)
    Cuando ENC-01 indica que el asunto del mensaje es información que no recibió
    Entonces el Portal le ofrece presentar una queja ante la Dirección con CU-12
