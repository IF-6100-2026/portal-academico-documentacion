# language: es
# Caso de uso: docs/03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md
# Requerimientos: RF-01, RS-03
Característica: CU-02 — Vincular encargado con estudiante
  Como Dirección
  Quiero registrar qué personas encargadas están autorizadas para cada estudiante
  Para que el Portal entregue la información solo a quien corresponde

  Antecedentes:
    Dado que DIR-01 tiene el rol de Dirección
    Y ENC-01 y EST-01 están activos en el Portal

  Escenario: TP-CU02-FB — Flujo básico
    Dado que no existe un vínculo entre ENC-01 y EST-01
    Cuando DIR-01 registra el vínculo de ENC-01 con EST-01 con la referencia del respaldo
    Entonces el vínculo queda activo con respaldo, responsable y fecha
    Y EST-01 aparece en la cuenta de ENC-01 en su siguiente ingreso

  Escenario: TP-CU02-A01 — Usuario sin rol de Dirección (paso 2)
    Dado que DOC-01 es docente y no tiene el rol de Dirección
    Cuando DOC-01 intenta vincular a ENC-01 con EST-01
    Entonces el Portal rechaza la solicitud sin crear el vínculo
    Y el intento denegado queda registrado

  Escenario: TP-CU02-A02 — Persona inexistente o inactiva (paso 4)
    Dado que EST-03 está inactivo
    Cuando DIR-01 intenta vincular a ENC-01 con EST-03
    Entonces el Portal indica que EST-03 está inactivo
    Y no se crea el vínculo

  Escenario: TP-CU02-A03 — Vínculo equivalente ya activo (paso 4)
    Dado que ya existe un vínculo activo entre ENC-01 y EST-01
    Cuando DIR-01 solicita de nuevo el mismo vínculo
    Entonces se conserva un único vínculo
    Y el Portal informa la fecha en que se activó

  Escenario: TP-CU02-A04 — Restricción sobre la relación (paso 4)
    Cuando DIR-01 registra el vínculo de ENC-03 con EST-01 indicando una restricción de custodia
    Entonces la solicitud queda pendiente y sin acceso
    Y quedan registrados el motivo y el responsable

  Escenario: TP-CU02-A05 — Respaldo incompleto (paso 6)
    Cuando DIR-01 solicita vincular a ENC-01 con EST-01 sin la referencia del respaldo
    Entonces el vínculo no se activa
    Y el Portal indica que falta el respaldo

  Escenario: TP-CU02-A06 — Suspender un vínculo (paso 3)
    Dado que existe un vínculo activo entre ENC-01 y EST-01
    Cuando DIR-01 lo suspende con motivo y fecha
    Entonces el vínculo queda suspendido y se conservan el estado anterior, el motivo, la fecha y el responsable
    Y la siguiente solicitud de ENC-01 sobre EST-01 se deniega
