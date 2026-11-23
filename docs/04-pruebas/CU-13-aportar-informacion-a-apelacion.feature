# language: es
# Caso de uso: docs/03-casos-de-uso/CU-13-aportar-informacion-a-apelacion.md
# Requerimientos: RF-19, RS-03
Característica: CU-13 — Aportar información a una apelación
  Como participante autorizado del expediente
  Quiero agregar evidencia, criterio u observaciones
  Para que el Comité valore el caso con la información disponible

  Antecedentes:
    Dado que la apelación APL-01 está asignada al Comité y en revisión

  Escenario: TP-CU13-FB — Conservar aportes de docente y Dirección
    Cuando DOC-01 adjunta la prueba y su criterio de calificación
    Y DIR-01 agrega sus observaciones
    Entonces ambos aportes quedan en el expediente con autor y fecha
    Y los aportes anteriores se conservan
    Y la calificación publicada no cambia

  Escenario: TP-CU13-A01 — Actor sin acceso al expediente
    Dado que ENC-02 no presentó APL-01 ni integra el equipo autorizado
    Cuando ENC-02 solicita consultar o aportar al expediente
    Entonces el Portal deniega la operación sin devolver datos del caso
    Y el intento queda registrado

  Escenario: TP-CU13-A02 — Aporte vacío
    Cuando DOC-01 intenta registrar un aporte sin contenido
    Entonces el Portal no lo agrega
    Y señala qué información falta

  Escenario: TP-CU13-A03 — Solicitud de información adicional
    Cuando COM-01 solicita a DOC-01 la rúbrica de la evaluación
    Entonces la solicitud queda en el expediente con su destinatario
    Y el caso queda pendiente de información hasta recibir respuesta
