# language: es
# Caso de uso: docs/03-casos-de-uso/CU-09-corregir-calificacion-publicada.md
# Requerimientos: RF-05, RF-09, RF-11, RS-03
Característica: CU-09 — Corregir una calificación publicada
  Como docente
  Quiero corregir una calificación publicada sin borrar la anterior
  Para dejar constancia del cambio y de su motivo

  Antecedentes:
    Dado que DOC-01 está asignada a 4-A en Matemática
    Y EST-01 tiene publicada la calificación 70 en EVA-01
    Y ENC-01 tiene vínculo activo con EST-01

  Escenario: TP-CU09-FB — Flujo básico
    Cuando DOC-01 corrige la calificación de EST-01 en EVA-01 a 80 con el motivo «error de suma»
    Y confirma que la corrección coincide con el cuaderno de la asignatura
    Entonces 80 queda como versión vigente con autor, fecha y motivo
    Y la versión 70 se conserva
    Y ENC-01 recibe un aviso de corrección

  Escenario: TP-CU09-A01 — Docente sin asignación (paso 2)
    Dado que DOC-99 no está asignada a 4-A en Matemática
    Cuando DOC-99 intenta corregir la calificación de EST-01
    Entonces el Portal rechaza la corrección sin modificar versiones
    Y el intento denegado queda registrado

  Escenario: TP-CU09-A02 — La versión vigente cambió (paso 4)
    Dado que DOC-01 abrió la calificación cuando valía 70
    Y otra corrección la cambió a 75 antes de que DOC-01 guardara
    Cuando DOC-01 intenta guardar 80
    Entonces el Portal no aplica la corrección
    Y muestra la versión vigente 75

  Escenario: TP-CU09-A03 — Corrección sin motivo (paso 4)
    Cuando DOC-01 intenta corregir a 80 sin indicar el motivo
    Entonces no se crea una nueva versión
    Y el Portal indica que falta el motivo

  Escenario: TP-CU09-A04 — Falta la confirmación con el cuaderno (paso 5)
    Cuando DOC-01 corrige a 80 con motivo y no confirma la coincidencia con el cuaderno
    Entonces la corrección no se publica
    Y la calificación vigente sigue siendo 70

  Escenario: TP-CU09-A05 — Estudiante sin persona encargada activa (paso 7)
    Dado que EST-02 no tiene persona encargada con vínculo activo
    Cuando DOC-01 corrige la calificación de EST-02 con motivo y confirmación
    Entonces la nueva versión queda vigente sin aviso
    Y EST-02 aparece como estudiante sin destinatario en la supervisión de la Dirección
