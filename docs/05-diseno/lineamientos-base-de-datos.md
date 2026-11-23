# Lineamientos de base de datos

## Persistencia de la rama PC3

PostgreSQL 17 se ejecuta con Docker Compose. Liquibase es el único mecanismo que crea o modifica el esquema; Hibernate usa `ddl-auto: validate`. La migración `001-baseline` identifica el esquema inicial y `002-academic-core` agrega el núcleo de demostración.

| Tabla | Uso actual |
|---|---|
| `portal_user` | Cuentas demo, nombre, rol y estado activo |
| `student` | Perfil ficticio, grado, grupo, docente y estado activo |
| `guardian_student_link` | Relación, respaldo, estado, autor y fechas |
| `guardian_link_history` | Historial de altas, suspensiones y finalizaciones con motivo y autor |
| `assessment` | Evaluaciones asignadas, publicadas o en borrador |
| `grade_record` | Calificación publicada asociada a una evaluación |
| `consultation_log` | Listados, consultas académicas y resultado de autorización |
| `security_access_log` | Intentos autenticados rechazados por rol |

Los UUID identifican datos académicos; claves foráneas protegen las referencias. Un índice parcial impide que exista más de un vínculo abierto (`ACTIVE` o `PENDING`) para el mismo par encargado/estudiante. Las publicaciones guardan `published_at`; `grade_revision` conserva versiones anteriores y el historial de cambio. Los datos iniciales son ficticios y se cargan solo para facilitar la demostración local.

## Decisiones y restricciones

- `guardian_student_link.status` admite `ACTIVE`, `SUSPENDED`, `ENDED` y `PENDING`. El código crea `ACTIVE`; solo permite cambiar a `SUSPENDED` o `ENDED`.
- `assessment.published` controla lo que puede consultar CU-01. Las evaluaciones no publicadas no se devuelven.
- No se ha confirmado la escala de calificación institucional. `grade_record.numeric_value` no impone mínimo ni máximo hasta resolver esa brecha.
- No hay historial de versiones de calificación. La tabla actual conserva una calificación por evaluación y **no satisface** RF-09 ni el alterno de CU-01 que muestra correcciones previas.
- No existen aún tablas de avisos, mensajes, quejas, apelaciones, integrantes de comité ni plazos. El esquema no implementa los RF correspondientes.
- El volumen `postgres_data` conserva la base entre reinicios. Cambiar variables de acceso después de inicializar PostgreSQL no cambia usuarios ya creados dentro del volumen.

Toda futura migración debe ser aditiva, tener identificador y autor, y preservar datos ya registrados. No borrar ni editar una migración aplicada para arreglar una base existente.
