# language: es
# Caso de uso: docs/03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md
# Requerimientos: RF-09, RF-17, RF-18, RF-19, RF-20, RF-21, RS-01, RS-03
# Los plazos de este archivo son valores de prueba, no la política de la escuela:
# la Dirección los configura por tipo de evaluación en CU-10.
Característica: CU-07 — Gestionar apelación de una calificación
  Como persona encargada legal
  Quiero apelar una calificación y conocer cómo la resuelve el Comité de Evaluación
  Para que el proceso y su resolución queden documentados

  Antecedentes:
    Dado que ENC-01 tiene vínculo activo con EST-01
    Y DOC-01 publicó la calificación 70 de EST-01 en la prueba EVA-01
    Y la configuración de prueba para pruebas escritas indica 5 días hábiles para presentar y 10 para resolver

  Escenario: TP-CU07-FB — Flujo básico
    Cuando ENC-01 apela la calificación de EVA-01 dentro del plazo, indica los puntos apelados y adjunta una foto de la prueba
    Entonces la apelación queda presentada con número, fecha y plazo de resolución
    Y el Comité de Evaluación la recibe asignada
    Y DOC-01 y la Dirección reciben un aviso
    Cuando DOC-01 adjunta la prueba y su criterio y la Dirección registra sus observaciones
    Y el Comité registra la resolución que mantiene la calificación, con fundamento
    Entonces la apelación queda resuelta y ENC-01 recibe un aviso
    Y la calificación sigue siendo 70
    Cuando ENC-01 consulta la resolución
    Entonces la apelación queda cerrada con todo su historial

  Escenario: TP-CU07-A01 — Sin vínculo con el estudiante (paso 2)
    Dado que ENC-99 no tiene vínculo con EST-01
    Cuando ENC-99 intenta apelar la calificación de EST-01
    Entonces el Portal rechaza la apelación sin mostrar la calificación
    Y el intento denegado queda registrado

  Escenario: TP-CU07-A02 — Plazo de presentación vencido (paso 2)
    Dado que la calificación se publicó hace 7 días hábiles
    Cuando ENC-01 intenta apelarla
    Entonces el Portal no registra la apelación e informa el plazo configurado

  Escenario: TP-CU07-A03 — Información incompleta (paso 4)
    Cuando ENC-01 intenta apelar sin indicar los puntos apelados
    Entonces la apelación no se registra
    Y el Portal indica que faltan los puntos apelados

  Escenario: TP-CU07-A04 — Apelación abierta sobre la misma calificación (paso 4)
    Dado que ENC-01 ya tiene una apelación en revisión sobre la calificación de EVA-01
    Cuando ENC-01 intenta apelarla de nuevo
    Entonces no se crea otra apelación
    Y el Portal muestra el número y el estado de la existente

  Escenario: TP-CU07-A05 — El Comité solicita información adicional (paso 7)
    Dado que la apelación está en revisión
    Cuando el Comité solicita a DOC-01 la rúbrica de EVA-01
    Entonces la apelación queda pendiente de información y DOC-01 recibe un aviso
    Cuando DOC-01 adjunta la rúbrica
    Entonces la apelación vuelve a estar en revisión con la rúbrica en el expediente

  Escenario: TP-CU07-A06 — La resolución cambia la calificación (paso 8)
    Dado que la apelación está en revisión
    Cuando el Comité resuelve que la calificación debe ser 80
    Y DOC-01 aplica la corrección con CU-09 indicando la apelación
    Entonces la calificación vigente es 80 y la versión 70 se conserva
    Y el expediente referencia la nueva versión
    Y la apelación queda resuelta

  Escenario: TP-CU07-A07 — Plazo de resolución vencido (pasos 6 a 8)
    Dado que la apelación lleva 11 días hábiles en revisión
    Cuando se supera el plazo de resolución
    Entonces la apelación se marca como vencida y la Dirección recibe un aviso
    Y la apelación sigue en revisión, sin resolverse de forma automática

  Escenario: TP-CU07-A08 — La persona encargada no consulta la resolución (paso 10)
    Dado que la apelación fue resuelta y ENC-01 no ha abierto el aviso
    Cuando se ejecuta la revisión programada de avisos
    Entonces la apelación sigue resuelta y no cerrada
    Y el aviso de la resolución recibe el tratamiento de CU-05
