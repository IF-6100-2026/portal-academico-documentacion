# language: es
# Caso de uso: docs/03-casos-de-uso/CU-19-inscribir-estudiante-en-grupo.md
# Requerimientos generales: RF-26, RF-27, RS-03
Característica: CU-19 — Inscribir estudiante en grupo
  Como Dirección autorizada
  Quiero inscribir un estudiante registrado en un grupo activo
  Para organizar su seguimiento académico por periodo

  Antecedentes:
    Dado que el Portal usa datos ficticios

  Escenario: TP-CU19-FB — Inscribir estudiante activo
    Dado que DIR-01 puede gestionar matrículas
    Y EST-01 y GRP-01 existen y están activos
    Cuando DIR-01 inscribe EST-01 en GRP-01
    Entonces el Portal registra la inscripción con responsable y fecha
    Y no crea un vínculo con una persona encargada

  Escenario: TP-CU19-A01 — Actor sin permiso
    Dado que DOC-01 no tiene permiso para gestionar matrículas
    Cuando DOC-01 intenta inscribir EST-01 en GRP-01
    Entonces el Portal deniega la operación
    Y no crea la inscripción

  Escenario: TP-CU19-A02 — Estudiante o grupo no disponible
    Dado que GRP-02 está inactivo
    Cuando DIR-01 intenta inscribir EST-01 en GRP-02
    Entonces el Portal indica que el grupo no está disponible
    Y no crea la inscripción

  Escenario: TP-CU19-A03 — Inscripción repetida
    Dado que EST-01 ya está inscrito en GRP-01
    Cuando DIR-01 vuelve a guardar esa inscripción
    Entonces el Portal conserva una sola inscripción vigente
    Y muestra el registro existente
