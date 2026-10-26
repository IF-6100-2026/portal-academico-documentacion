# language: es
Característica: UC-11 — Consultar bitácora de auditoría
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC11-FB — Flujo básico
    Dado que Auditor autorizado para 4-A; existen actuaciones sobre publicaciones, resultados, avisos, vínculos, mensajes y discrepancias
    Cuando Repetir la consulta por cada tipo de entidad con identificador, intervalo, acción y usuario; solicitar una referencia subyacente autorizada
    Entonces Para cada tipo se proporciona la secuencia temporal con entidad, acción, usuario, fecha y referencia; el detalle permitido es de solo consulta y cada consulta queda registrada

  Escenario: TP-UC11-A01 — UC-11-A01, paso 2
    Dado que Usuario autenticado sin permiso de auditoría
    Cuando Solicitar la bitácora de EVA-01
    Entonces La consulta se rechaza sin revelar existencia ni contenido de actuaciones; el intento queda trazado

  Escenario: TP-UC11-A02 — UC-11-A02, paso 4
    Dado que Auditor autorizado
    Cuando Proporcionar un intervalo invertido o filtros incompletos
    Entonces No se ejecuta la consulta inválida; se identifican los criterios por corregir y no se mezclan otros ámbitos

  Escenario: TP-UC11-A03 — UC-11-A03, paso 4
    Dado que Auditor autorizado únicamente para 4-A
    Cuando Solicitar una entidad perteneciente a otro ámbito
    Entonces La consulta se deniega sin confirmar actuaciones de la entidad protegida; el intento queda trazado

  Escenario: TP-UC11-A04 — UC-11-A04, paso 5
    Dado que Auditor autorizado; filtros válidos sin coincidencias
    Cuando Consultar una entidad e intervalo sin actuaciones
    Entonces Se proporciona resultado vacío con filtros e intervalo aplicados; no aparecen actuaciones de otras entidades; la consulta queda trazada

  Escenario: TP-UC11-A05 — UC-11-A05, paso 8
    Dado que El auditor puede ver una referencia de auditoría, pero carece de permiso sobre el detalle académico subyacente
    Cuando Solicitar el detalle protegido desde el resultado permitido
    Entonces La información de auditoría autorizada permanece; el detalle se deniega y no se amplía el alcance del actor

  Escenario: TP-UC11-A06 — UC-11-A06, paso 7
    Dado que Auditor autorizado para consulta
    Cuando Solicitar modificar o eliminar una actuación de la bitácora
    Entonces La operación se rechaza; toda la bitácora permanece inalterada y el intento queda trazado

  Escenario: Caso de uso — Flujo básico
    Dado que Flujos alternos definidos
    Cuando Pruebas correspondientes
    Entonces Cobertura

  Escenario: UC-01 — 1
    Dado que 4
    Cuando TP-UC01-FB y TP-UC01-A01…A04
    Entonces Completa

  Escenario: UC-02 — 1
    Dado que 6
    Cuando TP-UC02-FB y TP-UC02-A01…A06
    Entonces Completa

  Escenario: UC-03 — 1
    Dado que 7
    Cuando TP-UC03-FB y TP-UC03-A01…A07
    Entonces Completa

  Escenario: UC-04 — 1
    Dado que 7
    Cuando TP-UC04-FB y TP-UC04-A01…A07
    Entonces Completa

  Escenario: UC-05 — 1
    Dado que 8
    Cuando TP-UC05-FB y TP-UC05-A01…A08
    Entonces Completa

  Escenario: UC-06 — 1
    Dado que 4
    Cuando TP-UC06-FB y TP-UC06-A01…A04
    Entonces Completa

  Escenario: UC-07 — 1
    Dado que 8
    Cuando TP-UC07-FB y TP-UC07-A01…A08
    Entonces Completa

  Escenario: UC-08 — 1
    Dado que 4
    Cuando TP-UC08-FB y TP-UC08-A01…A04
    Entonces Completa

  Escenario: UC-09 — 1
    Dado que 6
    Cuando TP-UC09-FB y TP-UC09-A01…A06
    Entonces Completa

  Escenario: UC-10 — 1
    Dado que 6
    Cuando TP-UC10-FB y TP-UC10-A01…A06
    Entonces Completa

  Escenario: UC-11 — 1
    Dado que 6
    Cuando TP-UC11-FB y TP-UC11-A01…A06
    Entonces Completa

  Escenario: **Total** — **11**
    Dado que **66**
    Cuando **77 pruebas**
    Entonces **Completa**
