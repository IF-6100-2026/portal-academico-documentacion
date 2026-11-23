# Decisiones de diseño

| ID | Decisión de esta rama | Motivo / referencia | Efecto y límite |
|---|---|---|---|
| D-01 | Proponer CU-01 + CU-02 como núcleo | No hay aprobación docente que el equipo pueda confirmar; ambos casos prueban consulta protegida por vínculo y alta/revocación. | El alcance sigue pendiente de aprobación. |
| D-02 | Monolito Spring Boot con handlers CQRS y React | Producto del curso pequeño; separación visible de lectura/escritura y UI/API/persistencia. | No se justifican microservicios. |
| D-03 | Java 21, Spring Boot 3.5.5 y UUID | Coinciden con las versiones de compilación y referencia de código disponibles. | Confirmar coincidencia con la guía del curso en la revisión del grupo. |
| D-04 | Liquibase como dueño del esquema; Hibernate valida | Repetibilidad local y cambios de BD versionados. | Las migraciones aplicadas no se editan; crear una nueva migración para cambios futuros. |
| D-05 | `ACTIVE` en vínculo se vuelve a comprobar en cada consulta | Una suspensión o finalización debe surtir efecto desde la siguiente solicitud. | La simultaneidad entre revocación y lectura aún requiere estrategia y prueba específica. |
| D-06 | HTTP Basic y usuarios fijos solo para demostración | Permite ejecutar y probar roles localmente sin integrar identidad externa. | No usar con datos reales; falta autenticación institucional, TLS, recuperación y administración de cuentas. |
| D-07 | Registrar consultas permitidas/denegadas y accesos autenticados rechazados | Evidenciar RS-02 y parte de RS-03. | Las solicitudes sin autenticar no se guardan hoy en la traza de seguridad; acordar auditoría y retención. |
| D-08 | No imponer escala de calificación todavía | Mediación no confirma el rango/regla en la evidencia consultada. | Se requiere confirmación antes de validar o transformar valores. |
| D-09 | Datos semilla totalmente ficticios | Hace repetible la demo sin divulgar información escolar real. | Reemplazar seeds por carga aprobada para un entorno institucional. |
| D-10 | Los escenarios `.feature` son especificaciones hasta conectar Cucumber | Evitar reportar pruebas no ejecutadas. | Backend y smoke API tienen reportes separados. |
