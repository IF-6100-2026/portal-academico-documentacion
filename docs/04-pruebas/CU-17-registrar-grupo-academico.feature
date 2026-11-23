# language: es
# Caso de uso: docs/03-casos-de-uso/CU-17-registrar-grupo-academico.md
# Requerimiento funcional: RF-27 · Restricciones: RS-03
Característica: CU-17 — Registrar grupo académico
  Como Dirección autorizada
  Quiero registrar un grupo para un periodo académico
  Para organizar la matrícula y las publicaciones del Portal

  Antecedentes:
    Dado que el Portal usa datos ficticios

  Escenario: TP-CU17-FB — Registrar grupo válido
    Dado que DIR-01 tiene permiso para mantener la oferta académica
    Cuando DIR-01 registra un grupo válido de "Matemática", nivel "Cuarto", grupo "4-A", periodo "2026-II"
    Entonces el grupo queda activo y disponible para asignaciones

  Escenario: TP-CU17-A01 — Actor sin permiso
    Dado que DOC-01 no tiene permiso para mantener la oferta académica
    Cuando DOC-01 intenta registrar un grupo
    Entonces el Portal deniega la operación
    Y no crea el grupo

  Escenario: TP-CU17-A02 — Datos requeridos incompletos
    Dado que DIR-01 tiene permiso para mantener la oferta académica
    Cuando DIR-01 registra un grupo sin periodo
    Entonces el Portal indica que falta el periodo
    Y no crea el grupo

  Escenario: TP-CU17-A03 — Grupo equivalente existente
    Dado que ya existe el grupo ficticio "Matemática" de "Cuarto", "4-A", periodo "2026-II"
    Cuando DIR-01 intenta registrar de nuevo ese grupo
    Entonces el Portal conserva un único registro
    Y muestra el grupo existente
