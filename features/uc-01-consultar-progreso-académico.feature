# language: es
Característica: UC-01 — Consultar progreso académico
  Como equipo de análisis
  Quiero verificar el comportamiento observable del caso de uso
  Para demostrar trazabilidad y aceptación del requisito

  Escenario: TP-UC01-FB — Flujo básico
    Dado que ENC-01 activo; relaciones vigentes con EST-01 y EST-04; existen publicaciones del II periodo
    Cuando Solicitar acceso como ENC-01; cambiar el contexto de EST-04 a EST-01; indicar periodo y materia; consultar una evaluación publicada
    Entonces La selección deja toda consulta en el contexto de EST-01; se proporciona solo su perfil e historial autorizado, incluido el detalle vigente; el acceso queda trazado

  Escenario: TP-UC01-A01 — UC-01-A01, paso 2
    Dado que Cuenta ENC-01 inactiva o credencial inválida
    Cuando Solicitar acceso con la identidad no habilitada
    Entonces El acceso se rechaza sin datos académicos; el intento y resultado quedan trazados

  Escenario: TP-UC01-A02 — UC-01-A02, paso 3
    Dado que Encargado activo sin relaciones vigentes
    Cuando Solicitar acceso y consulta de estudiantes
    Entonces Se informa ausencia de estudiantes habilitados; no se revelan perfiles ni borradores; la consulta queda trazada

  Escenario: TP-UC01-A03 — UC-01-A03, paso 6
    Dado que ENC-01 relacionado solo con EST-01; existe EST-02
    Cuando Solicitar directamente información de EST-02
    Entonces La solicitud se deniega sin confirmar información académica de EST-02; el intento queda trazado

  Escenario: TP-UC01-A04 — UC-01-A04, paso 7
    Dado que Relación ENC-01–EST-01 vigente; periodo autorizado sin publicaciones
    Cuando Consultar EST-01 en el periodo sin publicaciones
    Entonces Se entrega un resultado vacío con estudiante y periodo correctos; no aparecen borradores; el acceso queda trazado
