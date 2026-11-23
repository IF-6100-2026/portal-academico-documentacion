# language: es
# Caso de uso: docs/03-casos-de-uso/CU-16-registrar-estudiante.md
# Requerimiento funcional: RF-26 · Restricciones: RS-03
Característica: CU-16 — Registrar estudiante
  Como Dirección autorizada
  Quiero crear una ficha académica básica
  Para que el Portal organice el seguimiento del estudiante

  Antecedentes:
    Dado que DIR-01 tiene permiso para gestionar estudiantes
    Y el Portal usa datos ficticios

  Escenario: TP-CU16-FB — Registrar ficha válida
    Cuando DIR-01 registra "Ana Demo" en "Primer grado", grupo "A", con "Docente Demo"
    Entonces el Portal crea una ficha activa con UUID
    Y muestra la confirmación

  Escenario: TP-CU16-A01 — Faltan datos requeridos
    Cuando DIR-01 intenta registrar "Ana Demo" sin especificar el grupo
    Entonces el Portal indica que falta el grupo
    Y no crea una ficha

  Escenario: TP-CU16-A02 — Actor sin permiso
    Dado que DOC-01 no puede gestionar estudiantes
    Cuando DOC-01 intenta registrar una ficha
    Entonces el Portal rechaza la operación
    Y no crea ni revela información

  Escenario: TP-CU16-A03 — Alta separada del vínculo familiar
    Cuando DIR-01 registra una ficha válida para "Ana Demo"
    Entonces la ficha queda activa
    Y ninguna persona encargada obtiene acceso hasta que CU-02 registre un vínculo autorizado

  Escenario: TP-CU16-A04 — Coincidencia de nombre sin identificador institucional
    Dado que ya existe una ficha para "Ana Demo" sin código institucional
    Cuando DIR-01 registra otra ficha con el nombre "Ana Demo"
    Entonces el Portal no bloquea el alta únicamente por coincidir el nombre
    Y conserva las dos fichas para revisión posterior de la institución
