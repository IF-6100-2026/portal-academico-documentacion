# language: es
# Caso de uso: docs/03-casos-de-uso/CU-20-registrar-docente.md
# Requerimientos generales: RF-28, RS-03
Característica: CU-20 — Registrar docente
  Como Dirección autorizada
  Quiero registrar un perfil docente existente en el centro
  Para poder asignarlo a grupos académicos

  Antecedentes:
    Dado que el Portal usa datos ficticios

  Escenario: TP-CU20-FB — Registrar perfil docente
    Dado que DIR-01 puede mantener el catálogo docente
    Y la cuenta institucional DOC-01 existe y aún no tiene perfil docente activo
    Cuando DIR-01 registra DOC-01 con el nombre visible "Docente Demo"
    Entonces el perfil queda activo y disponible para asignaciones
    Y el Portal no crea una contraseña nueva

  Escenario: TP-CU20-A01 — Actor sin permiso
    Dado que DOC-02 no tiene permiso para mantener el catálogo docente
    Cuando DOC-02 intenta registrar un perfil
    Entonces el Portal deniega la operación
    Y no modifica el catálogo

  Escenario: TP-CU20-A02 — Cuenta o nombre faltante
    Dado que DIR-01 puede mantener el catálogo docente
    Cuando DIR-01 registra un perfil sin identificar la cuenta institucional
    Entonces el Portal indica que falta la cuenta
    Y no crea el perfil

  Escenario: TP-CU20-A03 — Perfil docente duplicado
    Dado que DOC-01 ya tiene un perfil docente activo
    Cuando DIR-01 intenta registrar de nuevo a DOC-01
    Entonces el Portal conserva un único perfil activo
    Y muestra el registro existente
