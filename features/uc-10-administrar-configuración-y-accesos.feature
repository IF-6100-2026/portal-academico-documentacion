# language: es
Característica: UC-10 — Administrar configuración y accesos
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC10-FB — Flujo básico
    Dado que ADM-01 activo y autorizado; catálogos y política base conocidos
    Cuando En ejecuciones independientes, crear y luego actualizar un elemento académico válido; crear o activar una cuenta y asignar un rol permitido; guardar dos recordatorios a 48 y 72 horas con alerta posterior **[PV-01 a PV-03]**
    Entonces Cada cambio queda vigente con actor, fecha, estado anterior y nuevo; el catálogo actualizado puede usarse, la cuenta recibe solo el rol permitido y la política se aplica a avisos nuevos

  Escenario: TP-UC10-A01 — UC-10-A01, paso 2
    Dado que Usuario activo sin atribución administrativa
    Cuando Solicitar un cambio de catálogo, cuenta o política
    Entonces La operación se rechaza sin exponer configuración protegida; nada cambia y el intento queda trazado

  Escenario: TP-UC10-A02 — UC-10-A02, paso 4
    Dado que ADM-01 autorizado
    Cuando Solicitar un tipo de cambio no admitido o dejar incompleto un dato obligatorio
    Entonces No se modifica la configuración; se identifican los datos por corregir y puede iniciarse una nueva solicitud válida

  Escenario: TP-UC10-A03 — UC-10-A03, paso 6
    Dado que Una materia activa aparece en publicaciones históricas
    Cuando Solicitar su desactivación desde una fecha autorizada
    Entonces La materia queda inactiva para registros nuevos y continúa visible en todas las publicaciones históricas; el cambio queda trazado

  Escenario: TP-UC10-A04 — UC-10-A04, paso 6
    Dado que Existe una cuenta activa con un rol autorizado
    Cuando Suspender la cuenta o sustituir su rol por otro permitido
    Entonces El nuevo estado o rol se aplica en el siguiente control de autorización; se conserva el estado anterior y no se altera su historial de actuaciones

  Escenario: TP-UC10-A05 — UC-10-A05, paso 5
    Dado que ADM-01 carece de atribución para conceder el rol de auditor o ampliar el ámbito solicitado
    Cuando Intentar asignar el privilegio superior
    Entonces La asignación se rechaza; cuenta y roles vigentes permanecen; el intento queda trazado

  Escenario: TP-UC10-A06 — UC-10-A06, paso 5
    Dado que Existe una política válida vigente
    Cuando Proponer intervalo negativo, cero recordatorios con alerta posterior contradictoria o un orden temporal incoherente
    Entonces La propuesta se rechaza; la política anterior continúa vigente y se identifican los valores por corregir
