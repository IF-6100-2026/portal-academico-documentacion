# language: es
# Caso de uso: docs/03-casos-de-uso/CU-21-actualizar-perfil-docente.md
# Requerimientos generales: RF-28, RS-03
Característica: CU-21 — Actualizar perfil docente
  Como Dirección autorizada
  Quiero actualizar o inactivar un perfil docente
  Para mantener vigente el catálogo sin perder el historial académico

  Antecedentes:
    Dado que el Portal usa datos ficticios

  Escenario: TP-CU21-FB — Actualizar nombre visible
    Dado que DIR-01 puede mantener el catálogo docente
    Y DOC-01 tiene un perfil activo
    Cuando DIR-01 actualiza el nombre visible de DOC-01
    Entonces el Portal guarda el dato nuevo con responsable y fecha
    Y conserva las publicaciones históricas asociadas

  Escenario: TP-CU21-A01 — Actor sin permiso
    Dado que DOC-02 no tiene permiso para mantener el catálogo docente
    Cuando DOC-02 intenta cambiar el perfil de DOC-01
    Entonces el Portal deniega la operación
    Y no revela ni modifica el perfil

  Escenario: TP-CU21-A02 — Perfil inexistente
    Dado que no existe el perfil DOC-99
    Cuando DIR-01 intenta actualizar DOC-99
    Entonces el Portal informa que no encontró el perfil
    Y no guarda cambios

  Escenario: TP-CU21-A03 — Inactivar sin borrar historial
    Dado que DOC-01 tiene asignaciones y publicaciones históricas
    Cuando DIR-01 inactiva el perfil de DOC-01
    Entonces DOC-01 no se ofrece para nuevas asignaciones
    Y sus asignaciones y publicaciones anteriores se conservan
