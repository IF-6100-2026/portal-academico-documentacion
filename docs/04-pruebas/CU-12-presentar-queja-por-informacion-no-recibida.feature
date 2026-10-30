# language: es
# Caso de uso: docs/03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md
# Requerimientos: RF-15, RF-16, RF-25, RS-01, RS-02, RS-03
Característica: CU-12 — Presentar queja por información no recibida
  Como persona encargada legal
  Quiero reportar a la Dirección que no recibí información académica
  Para que se revise qué pasó y se me responda

  Antecedentes:
    Dado que ENC-01 tiene vínculo activo con EST-01
    Y DIR-01 tiene el rol de Dirección

  Escenario: TP-CU12-FB — Flujo básico
    Dado que la calificación de EST-01 en EVA-01 se publicó y su aviso no fue consultado
    Cuando ENC-01 presenta una queja porque no recibió las calificaciones de Matemática del II periodo
    Entonces el Portal le muestra primero lo publicado para ese criterio
    Cuando ENC-01 confirma la queja y la describe
    Entonces la queja queda abierta con número, fecha y solicitante
    Y DIR-01 recibe un aviso
    Cuando DIR-01 revisa el registro de consultas y registra el hallazgo «publicada sin consultar» y su respuesta
    Entonces la queja queda atendida
    Y ENC-01 recibe un aviso con la respuesta
    Y la calificación de EST-01 no cambia

  Escenario: TP-CU12-A01 — Sin vínculo con el estudiante (paso 2)
    Dado que ENC-99 no tiene vínculo con EST-01
    Cuando ENC-99 intenta presentar una queja sobre EST-01
    Entonces el Portal la rechaza sin mostrar información de EST-01
    Y el intento denegado queda registrado

  Escenario: TP-CU12-A02 — La información ya está publicada (paso 4)
    Dado que la evaluación EVA-01 está publicada para EST-01
    Cuando ENC-01 inicia una queja por EVA-01 y la encuentra entre lo publicado
    Y abre EVA-01
    Entonces la consulta queda registrada
    Y no se registra ninguna queja

  Escenario: TP-CU12-A03 — La información no estaba publicada (paso 7)
    Dado que ENC-01 presentó una queja por la calificación de EVA-02, que no está publicada
    Cuando DIR-01 registra el hallazgo «no publicada» y solicita a DOC-01 publicarla
    Entonces la queja queda en atención y DOC-01 recibe un aviso
    Cuando DOC-01 publica la calificación de EVA-02 con CU-04
    Entonces la publicación queda ligada a la queja
    Y la queja queda atendida y ENC-01 recibe la respuesta

  Escenario: TP-CU12-A04 — Inconformidad con una calificación (paso 4)
    Cuando ENC-01 describe en la queja que no está de acuerdo con la calificación de EVA-01
    Entonces el Portal le indica que corresponde una apelación y le ofrece iniciar CU-07
    Y no se registra ninguna queja
