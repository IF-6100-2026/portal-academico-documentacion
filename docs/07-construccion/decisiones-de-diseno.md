# Decisiones de diseño

| ID | Decisión de esta rama | Motivo / referencia | Efecto y límite |
|---|---|---|---|
| D-01 | Ejecutar CU-01 + CU-02 como núcleo propuesto de PC3 | El equipo no recuerda ni encontró confirmación de la selección docente. CU-01 depende del vínculo de CU-02 y juntos permiten demostrar control de acceso, persistencia y auditoría. | Decisión práctica del equipo por el plazo de entrega; no se atribuye aprobación docente. |
| D-02 | Monolito Spring Boot con handlers CQRS y React | Producto del curso pequeño; separación visible de lectura/escritura y UI/API/persistencia. | No se justifican microservicios. |
| D-03 | Java 21, Spring Boot 3.5.5 y UUID | Coinciden con las versiones de compilación y referencia de código disponibles. | Confirmar coincidencia con la guía del curso en la revisión del grupo. |
| D-04 | Liquibase como dueño del esquema; Hibernate valida | Repetibilidad local y cambios de BD versionados. | Las migraciones aplicadas no se editan; crear una nueva migración para cambios futuros. |
| D-05 | `ACTIVE` en vínculo se vuelve a comprobar en cada consulta | Una suspensión o finalización debe surtir efecto desde la siguiente solicitud. | La simultaneidad entre revocación y lectura aún requiere estrategia y prueba específica. |
| D-06 | HTTP Basic y usuarios fijos solo para demostración | Permite ejecutar y probar roles localmente sin integrar identidad externa. | No usar con datos reales; falta autenticación institucional, TLS, recuperación y administración de cuentas. |
| D-07 | Registrar consultas permitidas/denegadas y accesos autenticados rechazados | Evidenciar RS-02 y parte de RS-03. | Las solicitudes sin autenticar no se guardan hoy en la traza de seguridad; acordar auditoría y retención. |
| D-08 | No imponer escala de calificación todavía | Mediación no confirma el rango/regla en la evidencia consultada. | Se requiere confirmación antes de validar o transformar valores. |
| D-09 | Datos semilla totalmente ficticios | Hace repetible la demo sin divulgar información escolar real. | Reemplazar seeds por carga aprobada para un entorno institucional. |
| D-10 | Los escenarios `.feature` son especificaciones hasta conectar Cucumber | Evitar reportar pruebas no ejecutadas. | Backend y smoke API tienen reportes separados. |
| D-11 | Ordenar avisos por `published_at` y contarlos respecto a la última consulta autorizada por estudiante | CU-01 requiere destacar publicaciones recientes y no consultadas; cada lectura autorizada queda en `consultation_log`. | Son avisos dentro de la interfaz, no notificaciones externas; la definición de plazos/canales queda abierta. |
| D-12 | Conservar la versión anterior de la calificación con motivo, autor y fecha | Permite demostrar CU-01 A04 sin eliminar la nota vigente. | Datos semilla ficticios y campos de auditoría para PC3; el proceso institucional de corrección aún debe validarse. |
| D-13 | Una relación `PENDING` no autoriza acceso y bloquea un alta `ACTIVE` paralela para el mismo par | Evita que una restricción registrada se eluda creando otro vínculo antes de revisarla. | No se implementa la revisión/aprobación del pendiente; Dirección debe validar el flujo institucional. |
