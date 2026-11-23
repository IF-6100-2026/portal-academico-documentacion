# language: es
# Caso de uso: docs/03-casos-de-uso/CU-15-consultar-resolucion-de-apelacion.md
# Requerimientos: RF-20, RS-02, RS-03
Característica: CU-15 — Consultar resolución de apelación
  Como persona encargada que presentó una apelación
  Quiero consultar la decisión del Comité
  Para conocer el resultado y dejar constancia de recibido

  Antecedentes:
    Dado que APL-01 fue resuelta por el Comité
    Y ENC-01 presentó APL-01

  Escenario: TP-CU15-FB — Consultar y cerrar la apelación
    Cuando ENC-01 abre el aviso de APL-01 y confirma la consulta
    Entonces ve el resultado y fundamento de la resolución
    Y el Portal registra quién y cuándo la consultó
    Y APL-01 pasa a cerrada

  Escenario: TP-CU15-A01 — Persona encargada ajena
    Dado que ENC-02 no presentó APL-01
    Cuando ENC-02 intenta consultar su resolución
    Entonces el Portal deniega el acceso sin confirmar que APL-01 existe

  Escenario: TP-CU15-A02 — Resolución no disponible
    Dado que APL-01 sigue en revisión
    Cuando ENC-01 abre el aviso
    Entonces el Portal informa que la resolución no está disponible
    Y el estado de APL-01 no cambia
