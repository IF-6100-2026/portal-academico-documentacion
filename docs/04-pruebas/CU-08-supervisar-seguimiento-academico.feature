# language: es
# Caso de uso: docs/03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md
# Requerimientos: RF-21, RF-23, RF-24, RF-25, RS-02, RS-03
Característica: CU-08 — Supervisar el seguimiento académico
  Como Dirección
  Quiero ver en un solo lugar los avisos, quejas y apelaciones pendientes
  Para intervenir a tiempo

  Antecedentes:
    Dado que DIR-01 tiene el rol de Dirección
    Y en el II periodo de 4-A hay 3 avisos sin consultar, 1 alerta de CU-05, 1 queja abierta y 1 apelación vencida

  Escenario: TP-CU08-FB — Flujo básico
    Cuando DIR-01 abre el panel de supervisión del II periodo para 4-A
    Entonces ve 3 avisos sin consultar, 1 alerta, 1 queja abierta y 1 apelación vencida
    Cuando abre uno de los avisos sin consultar
    Entonces ve la publicación de origen y el registro de quién la consultó y cuándo
    Y queda registrado que DIR-01 hizo esa consulta

  Escenario: TP-CU08-A01 — Usuario sin rol de Dirección (paso 2)
    Dado que DOC-01 no tiene el rol de Dirección
    Cuando DOC-01 intenta abrir el panel de supervisión
    Entonces el Portal rechaza la consulta sin mostrar totales
    Y el intento denegado queda registrado

  Escenario: TP-CU08-A02 — Criterios incoherentes (paso 4)
    Cuando DIR-01 filtra por un grupo que no pertenece al periodo elegido
    Entonces el Portal no ejecuta la consulta
    Y indica qué criterio corregir

  Escenario: TP-CU08-A03 — Sin pendientes (paso 5)
    Dado que 5-B no tiene pendientes en el II periodo
    Cuando DIR-01 abre el panel del II periodo para 5-B
    Entonces ve todos los totales en cero con los criterios aplicados
    Y la consulta queda registrada
