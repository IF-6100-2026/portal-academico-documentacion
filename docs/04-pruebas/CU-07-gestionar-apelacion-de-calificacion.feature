# language: es
# Caso de uso: docs/03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md
# Requerimientos: RF-17, RF-18, RS-03
Característica: CU-07 — Presentar apelación de calificación
  Como persona encargada legal
  Quiero presentar una apelación con su respaldo
  Para que el Comité de Evaluación reciba la solicitud y su expediente inicial

  Antecedentes:
    Dado que ENC-01 tiene vínculo activo con EST-01
    Y DOC-01 publicó la calificación 70 de EST-01 en EVA-01
    Y la Dirección configuró el plazo aplicable sin usar un valor predeterminado

  Escenario: TP-CU07-FB — Presentar y asignar una apelación
    Cuando ENC-01 apela EVA-01 dentro del plazo
    Y indica los puntos, el motivo y adjunta el respaldo
    Entonces la apelación queda registrada con número, fecha y plazo configurado
    Y el Comité recibe el expediente inicial asignado
    Y DOC-01 y la Dirección reciben el aviso

  Escenario: TP-CU07-A01 — Sin vínculo activo
    Dado que ENC-99 no tiene vínculo con EST-01
    Cuando ENC-99 intenta apelar EVA-01
    Entonces el Portal deniega la solicitud sin revelar si EST-01 existe
    Y el intento denegado queda registrado

  Escenario: TP-CU07-A02 — Calificación no publicada o plazo vencido
    Dado que EVA-01 no está publicada o el plazo configurado ya venció
    Cuando ENC-01 intenta apelarla
    Entonces el Portal no registra la apelación e informa la condición aplicable

  Escenario: TP-CU07-A03 — Información incompleta
    Cuando ENC-01 intenta apelar sin indicar los puntos apelados
    Entonces la apelación no se registra
    Y el Portal indica qué falta

  Escenario: TP-CU07-A04 — Apelación abierta equivalente
    Dado que ENC-01 ya tiene una apelación abierta sobre EVA-01
    Cuando ENC-01 intenta crear una segunda apelación equivalente
    Entonces el Portal conserva la apelación existente e informa su número y estado
