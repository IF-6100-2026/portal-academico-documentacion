# language: es
# Caso de uso: docs/03-casos-de-uso/CU-18-asignar-docente-a-grupo.md
# Requerimientos generales: RF-27, RF-28, RS-03
Característica: CU-18 — Asignar docente a grupo
  Como Dirección autorizada
  Quiero asignar un perfil docente a un grupo activo
  Para definir quién registra actividad académica para ese grupo

  Antecedentes:
    Dado que el Portal usa datos ficticios

  Escenario: TP-CU18-FB — Asignar docente activo
    Dado que DIR-01 puede gestionar asignaciones
    Y DOC-01 y GRP-01 existen y están activos
    Cuando DIR-01 asigna DOC-01 a GRP-01
    Entonces el Portal registra la asignación con responsable y fecha

  Escenario: TP-CU18-A01 — Actor sin permiso
    Dado que DOC-02 no tiene permiso para gestionar asignaciones
    Cuando DOC-02 intenta asignar DOC-01 a GRP-01
    Entonces el Portal deniega la operación
    Y no modifica la asignación

  Escenario: TP-CU18-A02 — Grupo o docente inactivo
    Dado que GRP-02 está inactivo
    Cuando DIR-01 intenta asignar DOC-01 a GRP-02
    Entonces el Portal explica que el grupo no está disponible
    Y no crea la asignación

  Escenario: TP-CU18-A03 — Asignación repetida
    Dado que DOC-01 ya está asignado a GRP-01
    Cuando DIR-01 vuelve a guardar esa asignación
    Entonces el Portal conserva una sola asignación
    Y muestra la asignación vigente
