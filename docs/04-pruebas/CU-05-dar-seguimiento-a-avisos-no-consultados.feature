# language: es
# Caso de uso: docs/03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md
# Requerimientos: RF-12, RF-13, RF-23, RS-01, RS-02, RS-03
# Los intervalos de este archivo son valores de prueba, no la política de la escuela:
# la Dirección los configura en CU-10.
Característica: CU-05 — Dar seguimiento a avisos no consultados
  Como Dirección
  Quiero que los avisos no consultados se recuerden y, si no basta, se me alerten
  Para saber qué familias no han recibido la información

  Antecedentes:
    Dado que la configuración de prueba indica un recordatorio cada 48 horas y un máximo de 2 recordatorios
    Y ENC-01 tiene vínculo activo con EST-01
    Y existe el aviso AV-01 de la calificación de EVA-01 dirigido a ENC-01

  Escenario: TP-CU05-FB — Flujo básico
    Dado que AV-01 está pendiente desde hace 48 horas y no tiene recordatorios
    Cuando se ejecuta la revisión programada
    Entonces se emite el recordatorio 1 de AV-01 con su fecha
    Cuando ENC-01 abre AV-01 desde el recordatorio
    Entonces AV-01 queda consultado con ENC-01, fecha y hora
    Y no se emiten más recordatorios para AV-01

  Escenario: TP-CU05-A01 — Aviso consultado antes de la revisión (paso 2)
    Dado que ENC-01 consultó AV-01 antes de cumplirse las 48 horas
    Cuando se ejecuta la revisión programada
    Entonces no se emite recordatorio ni alerta
    Y el acuse de ENC-01 se mantiene sin cambios

  Escenario: TP-CU05-A02 — Vínculo inactivo (paso 3)
    Dado que AV-01 está pendiente y el vínculo de ENC-01 con EST-01 fue finalizado
    Cuando se ejecuta la revisión programada
    Entonces no se emiten recordatorios a ENC-01 y se conserva el historial
    Y la Dirección recibe una alerta de EST-01 sin destinatario válido

  Escenario: TP-CU05-A03 — Cantidad de recordatorios alcanzada (paso 4)
    Dado que AV-01 sigue pendiente y ya se emitieron 2 recordatorios
    Cuando se ejecuta la revisión programada
    Entonces no se emite un tercer recordatorio
    Y la Dirección recibe una alerta con estudiante, destinatario, publicación de origen, recordatorios emitidos y antigüedad

  Escenario: TP-CU05-A04 — Configuración ausente o incoherente (paso 4)
    Dado que no existe un intervalo de recordatorio vigente
    Cuando se ejecuta la revisión programada
    Entonces no se emite ningún recordatorio
    Y la Dirección recibe el aviso de la incidencia de configuración
    Y AV-01 conserva su estado

  Escenario: TP-CU05-A05 — El aviso sigue sin consultarse (paso 6)
    Dado que se emitió el recordatorio 1 de AV-01 y pasaron 48 horas sin consulta
    Cuando se ejecuta la revisión programada
    Entonces AV-01 sigue pendiente y el recordatorio 1 se conserva en el historial
    Y se emite el recordatorio 2

  Escenario: TP-CU05-A06 — Apertura posterior al primer acuse (paso 7)
    Dado que ENC-01 consultó AV-01 el 3 de noviembre a las 18:00
    Cuando ENC-01 vuelve a abrir AV-01 el 5 de noviembre
    Entonces el acuse sigue indicando el 3 de noviembre a las 18:00
    Y la nueva apertura queda en el registro de consultas

  Escenario: TP-CU05-A07 — Revisión repetida dentro del mismo intervalo (paso 5)
    Dado que ya se emitió el recordatorio 1 de AV-01 en el intervalo vigente
    Cuando se ejecuta otra revisión dentro del mismo intervalo
    Entonces no se emite otro recordatorio
