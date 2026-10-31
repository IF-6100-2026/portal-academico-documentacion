# language: es
# Caso de uso: docs/03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md
# Requerimientos: RF-22, RS-02, RS-03
# Los valores de este archivo son de prueba; no son la política de la escuela.
Característica: CU-10 — Configurar plazos y recordatorios
  Como Dirección
  Quiero fijar los plazos de apelación y la política de recordatorios
  Para que se ajusten a cada situación sin cambiar el sistema

  Antecedentes:
    Dado que DIR-01 tiene el rol de Dirección
    Y la configuración vigente para pruebas escritas indica 5 días hábiles para presentar y 10 para resolver

  Escenario: TP-CU10-FB — Flujo básico
    Cuando DIR-01 cambia, para pruebas escritas, el plazo de resolución a 8 días hábiles
    Y fija un recordatorio cada 48 horas con un máximo de 2
    Entonces la configuración nueva queda vigente desde la fecha indicada
    Y se conservan los valores anteriores, DIR-01 y la fecha del cambio

  Escenario: TP-CU10-A01 — Usuario sin rol de Dirección (paso 2)
    Dado que DOC-01 no tiene el rol de Dirección
    Cuando DOC-01 intenta cambiar la configuración
    Entonces el Portal rechaza la operación sin mostrar la configuración
    Y el intento denegado queda registrado

  Escenario: TP-CU10-A02 — Valores incoherentes (paso 4)
    Cuando DIR-01 indica un plazo de resolución de 0 días hábiles
    Entonces el Portal rechaza la configuración
    Y la configuración anterior sigue vigente

  Escenario: TP-CU10-A03 — Hay apelaciones abiertas (paso 5)
    Dado que existe una apelación en revisión presentada con un plazo de resolución de 10 días hábiles
    Cuando DIR-01 cambia el plazo de resolución a 8 días hábiles
    Entonces la apelación abierta conserva su plazo de 10 días hábiles
    Y las apelaciones nuevas usan 8 días hábiles
