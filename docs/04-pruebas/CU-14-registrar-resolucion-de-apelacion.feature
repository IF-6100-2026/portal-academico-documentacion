# language: es
# Caso de uso: docs/03-casos-de-uso/CU-14-registrar-resolucion-de-apelacion.md
# Requerimiento funcional: RF-32 · Restricciones: RS-03
Característica: CU-14 — Registrar resolución de apelación
  Como Comité de Evaluación
  Quiero registrar el resultado y fundamento de la resolución
  Para comunicar la decisión y conservar el expediente

  Antecedentes:
    Dado que APL-01 está en revisión con los aportes necesarios

  Escenario: TP-CU14-FB — Registrar y notificar resolución
    Cuando COM-01 registra el resultado, fundamento e integrantes que resuelven APL-01
    Entonces la apelación queda resuelta con fecha y responsables
    Y ENC-01 recibe un aviso para consultar la resolución
    Y la calificación publicada no cambia

  Escenario: TP-CU14-A01 — Actor sin rol del Comité
    Dado que DOC-01 no integra el Comité
    Cuando DOC-01 intenta registrar la resolución de APL-01
    Entonces el Portal deniega la operación
    Y el expediente no cambia

  Escenario: TP-CU14-A02 — Resolución incompleta
    Cuando COM-01 intenta resolver sin indicar el fundamento
    Entonces el Portal no registra la resolución
    Y señala el dato faltante

  Escenario: TP-CU14-A03 — Resolución ordena corregir la calificación
    Cuando COM-01 resuelve que debe cambiarse la calificación
    Entonces el Portal registra la referencia a la resolución
    Y DOC-01 debe realizar la corrección mediante CU-09
    Y la versión anterior se conserva
