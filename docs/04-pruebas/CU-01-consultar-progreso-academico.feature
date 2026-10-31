# language: es
# Caso de uso: docs/03-casos-de-uso/CU-01-consultar-progreso-academico.md
# Requerimientos: RF-02, RF-03, RS-01, RS-02, RS-03
Característica: CU-01 — Consultar progreso académico
  Como persona encargada legal
  Quiero consultar las evaluaciones y calificaciones publicadas de mis estudiantes
  Para no depender de que el estudiante lleve el cuaderno o el reporte al hogar

  Antecedentes:
    Dado que ENC-01 tiene una cuenta activa
    Y ENC-01 tiene vínculo activo con EST-01 y con EST-04
    Y EST-01 tiene en el II periodo la evaluación EVA-01 de Matemática publicada y la evaluación EVA-02 en borrador

  Escenario: TP-CU01-FB — Flujo básico
    Cuando ENC-01 ingresa al Portal
    Entonces ve a EST-01 y EST-04 con las últimas publicaciones y los avisos pendientes
    Cuando elige a EST-01 y el II periodo
    Y abre la evaluación EVA-01
    Entonces ve el perfil de EST-01 y la evaluación EVA-01 con su fecha
    Y no ve la evaluación EVA-02
    Y queda registrado que ENC-01 consultó EVA-01 con fecha y hora

  Escenario: TP-CU01-A01 — Sin vínculos activos (paso 2)
    Dado que ENC-02 tiene una cuenta activa y ningún vínculo activo
    Cuando ENC-02 ingresa al Portal
    Entonces el Portal informa que no hay estudiantes habilitados
    Y no se muestra ningún perfil ni registro académico
    Y la consulta queda registrada

  Escenario: TP-CU01-A02 — Estudiante no autorizado (paso 5)
    Dado que EST-02 existe y no tiene vínculo con ENC-01
    Cuando ENC-01 solicita directamente la información de EST-02
    Entonces el Portal deniega la solicitud sin confirmar si EST-02 existe
    Y el intento denegado queda registrado

  Escenario: TP-CU01-A03 — Periodo sin publicaciones (paso 6)
    Dado que EST-01 no tiene publicaciones en el I periodo
    Cuando ENC-01 consulta a EST-01 en el I periodo
    Entonces el Portal informa que el periodo no tiene publicaciones
    Y no se muestran borradores
    Y la consulta queda registrada

  Escenario: TP-CU01-A04 — Calificación corregida (paso 8)
    Dado que la calificación de EST-01 en EVA-01 fue corregida de 70 a 85 con motivo
    Cuando ENC-01 abre la calificación de EVA-01
    Entonces ve 85 como versión vigente con la fecha de la corrección
    Y puede ver la versión anterior con el valor 70

  Escenario: TP-CU01-A05 — Sesión inválida (paso 2)
    Dado que la sesión de ENC-01 venció
    Cuando ENC-01 solicita el progreso de EST-01
    Entonces el Portal solicita autenticación válida
    Y no devuelve estudiantes, vínculos ni publicaciones
    Y no registra una lectura académica
