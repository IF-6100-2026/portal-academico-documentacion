# language: es
Característica: UC-02 — Vincular encargado con estudiante
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC02-FB — Flujo básico
    Dado que ADM-01 autorizado; ENC-01 y EST-01 activos; respaldo institucional completo; no existe relación equivalente
    Cuando Registrar tipo de relación, vigencia y referencia del respaldo; solicitar activación
    Entonces Se crea una relación activa con alcance y vigencia definidos; ENC-01 recibe constancia; toda la actuación queda trazada

  Escenario: TP-UC02-A01 — UC-02-A01, paso 2
    Dado que Usuario activo sin rol administrativo
    Cuando Solicitar la vinculación ENC-01–EST-01
    Entonces La solicitud se rechaza; no se crea ni modifica una relación; el intento queda trazado

  Escenario: TP-UC02-A02 — UC-02-A02, paso 4
    Dado que ADM-01 autorizado; encargado inexistente o EST-03 institucionalmente inactivo
    Cuando Proporcionar las identidades y solicitar vinculación
    Entonces Se identifica la condición que impide continuar; no se crea relación y la actuación queda trazada

  Escenario: TP-UC02-A03 — UC-02-A03, paso 4
    Dado que Ya existe una relación activa ENC-01–EST-01 con el mismo alcance
    Cuando Repetir la solicitud equivalente
    Entonces Se conserva una única relación, se comunica su vigencia y no se duplica el acceso

  Escenario: TP-UC02-A04 — UC-02-A04, paso 4
    Dado que Existe una restricción institucional documentada sobre la autorización
    Cuando Solicitar vinculación con la relación restringida
    Entonces La relación no se activa; la solicitud queda pendiente de autoridad competente con motivo y responsable trazados

  Escenario: TP-UC02-A05 — UC-02-A05, paso 6
    Dado que Identidades activas; falta la referencia del respaldo requerido
    Cuando Solicitar activación con verificación incompleta
    Entonces No se activa la relación; se identifican los datos pendientes y se conserva la solicitud para continuación posterior

  Escenario: TP-UC02-A06 — UC-02-A06, paso 7
    Dado que Relación ENC-01–EST-01 activa; ADM-01 posee respaldo para suspenderla o finalizarla
    Cuando Solicitar el cambio de estado con fecha y motivo autorizados
    Entonces El nuevo estado rige en el siguiente control de acceso; se conserva el estado anterior, responsable, fecha y motivo; ENC-01 recibe constancia
