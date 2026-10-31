# language: es
# Caso de uso: docs/03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md
# Requerimientos: RF-04, RF-05, RF-06, RF-10, RS-02, RS-03
Característica: CU-03 — Publicar evaluación asignada
  Como docente
  Quiero publicar una vez para el grupo la evaluación que anoté en el cuaderno
  Para que cada persona encargada la conozca sin depender del cuaderno que lleva el estudiante

  Antecedentes:
    Dado que DOC-01 está asignada a 4-A en Matemática en el II periodo
    Y EST-01 y EST-02 están activos en 4-A
    Y ENC-01 tiene vínculo activo con EST-01

  Escenario: TP-CU03-FB — Flujo básico
    Cuando DOC-01 registra la evaluación EVA-01 para 4-A con fecha de asignación, fecha de entrega e instrucciones
    Y confirma que coincide con lo anotado en el cuaderno de la asignatura
    Y autoriza la publicación
    Entonces EVA-01 queda publicada para EST-01 y EST-02 como evaluación asignada, sin calificación
    Y ENC-01 recibe un único aviso de evaluación asignada
    Y DOC-01 ve el resumen con los estudiantes incluidos y los avisos generados
    Cuando ENC-01 abre el aviso de EVA-01
    Entonces DOC-01 ve en EVA-01 que ENC-01 la leyó, con fecha y hora
    Y ve que EST-02 sigue sin lectura de su persona encargada

  Escenario: TP-CU03-A01 — Docente sin asignación (paso 2)
    Dado que DOC-99 no está asignada a 4-A en Matemática
    Cuando DOC-99 intenta registrar EVA-01 para 4-A
    Entonces el Portal rechaza la operación y no publica nada
    Y el intento denegado queda registrado

  Escenario: TP-CU03-A02 — Fechas incoherentes (paso 4)
    Cuando DOC-01 registra EVA-01 con fecha de entrega anterior a la fecha de asignación
    Entonces el Portal indica que debe corregir las fechas
    Y EVA-01 queda como borrador sin avisos

  Escenario: TP-CU03-A03 — Estudiante inactivo en la nómina (paso 4)
    Dado que EST-03 se retiró de 4-A antes de la fecha de asignación
    Cuando DOC-01 publica EVA-01 para 4-A
    Entonces EVA-01 no se publica para EST-03
    Y DOC-01 es informada de la exclusión

  Escenario: TP-CU03-A04 — Falta la confirmación con el cuaderno (paso 6)
    Cuando DOC-01 registra EVA-01 con datos válidos y no confirma la coincidencia con el cuaderno
    Y solicita publicar
    Entonces EVA-01 queda como borrador
    Y no se genera ningún aviso
    Y el Portal indica que falta la confirmación

  Escenario: TP-CU03-A05 — Publicación repetida (paso 7)
    Dado que EVA-01 ya está publicada para 4-A
    Cuando DOC-01 repite la autorización de publicación
    Entonces se conserva una sola publicación por estudiante
    Y ENC-01 conserva un único aviso de EVA-01

  Escenario: TP-CU03-A06 — Estudiante sin persona encargada activa (paso 9)
    Dado que EST-02 no tiene persona encargada con vínculo activo
    Cuando DOC-01 publica EVA-01 para 4-A
    Entonces EVA-01 queda publicada también para EST-02
    Y ENC-01 recibe su aviso
    Y EST-02 aparece como estudiante sin destinatario en la supervisión de la Dirección
