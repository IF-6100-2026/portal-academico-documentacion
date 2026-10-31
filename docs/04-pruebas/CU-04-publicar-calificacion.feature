# language: es
# Caso de uso: docs/03-casos-de-uso/CU-04-publicar-calificacion.md
# Requerimientos: RF-05, RF-07, RF-08, RF-11, RS-02, RS-03
Característica: CU-04 — Publicar calificación
  Como docente
  Quiero publicar las calificaciones del grupo después de devolver la evaluación
  Para que cada persona encargada las conozca sin esperar el reporte físico

  Antecedentes:
    Dado que DOC-01 está asignada a 4-A en Matemática
    Y la evaluación EVA-01 de 4-A está publicada y sin calificaciones
    Y la escala de prueba es de 0 a 100
    Y ENC-01 tiene vínculo activo con EST-01

  Escenario: TP-CU04-FB — Flujo básico
    Cuando DOC-01 registra para EVA-01 la calificación 85 de EST-01 con su observación
    Y indica la fecha de devolución y confirma que coincide con el cuaderno de la asignatura
    Y autoriza la publicación
    Entonces la calificación 85 queda publicada como primera versión con autor y fecha
    Y ENC-01 recibe un aviso de calificación distinto del aviso de evaluación asignada
    Cuando ENC-01 abre el aviso de la calificación
    Entonces DOC-01 ve en EVA-01 que ENC-01 leyó la calificación, con fecha y hora

  Escenario: TP-CU04-A01 — Docente sin asignación (paso 2)
    Dado que DOC-99 no está asignada a 4-A en Matemática
    Cuando DOC-99 intenta registrar calificaciones de EVA-01
    Entonces el Portal rechaza la operación sin mostrar ni publicar calificaciones
    Y el intento denegado queda registrado

  Escenario: TP-CU04-A02 — Evaluación anulada (paso 2)
    Dado que EVA-03 está anulada
    Cuando DOC-01 intenta registrar calificaciones de EVA-03
    Entonces el Portal informa que EVA-03 está anulada
    Y no se registran calificaciones ni avisos

  Escenario: TP-CU04-A03 — Calificación fuera de escala (paso 4)
    Cuando DOC-01 registra la calificación 120 para EST-01
    Entonces el Portal señala que 120 está fuera de la escala
    Y el conjunto queda como borrador sin avisos

  Escenario: TP-CU04-A04 — Falta la confirmación con el cuaderno (paso 6)
    Cuando DOC-01 registra calificaciones válidas y no confirma la coincidencia con el cuaderno
    Y solicita publicar
    Entonces las calificaciones quedan como borrador
    Y no se genera el aviso de calificación

  Escenario: TP-CU04-A05 — Calificación ya publicada (paso 7)
    Dado que EST-01 ya tiene publicada la calificación 85 en EVA-01
    Cuando DOC-01 intenta publicar 90 para EST-01 en EVA-01 desde este caso de uso
    Entonces la calificación vigente sigue siendo 85
    Y el Portal indica que el cambio se hace con CU-09

  Escenario: TP-CU04-A06 — Estudiante sin persona encargada activa (paso 9)
    Dado que EST-02 no tiene persona encargada con vínculo activo
    Cuando DOC-01 publica la calificación de EST-02 con confirmación válida
    Entonces la calificación queda publicada sin aviso
    Y EST-02 aparece como estudiante sin destinatario en la supervisión de la Dirección
