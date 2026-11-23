# Lineamientos de base de datos

## Persistencia de la rama PC3

PostgreSQL 17 se ejecuta con Docker Compose. Liquibase es el único mecanismo que crea o modifica el esquema; Hibernate usa `ddl-auto: validate`. Los datos iniciales son ficticios y se cargan solo para facilitar la demostración local.

## Migraciones

| Changeset | Qué agrega |
|---|---|
| `001-baseline` | Tabla `schema_baseline`, que marca el esquema inicial. |
| `002-academic-core` | Núcleo de CU-01 y CU-02: cuentas, estudiantes, vínculos y su historial, evaluaciones, calificaciones y bitácoras. |
| `003-pc3-core-completion` | Fecha de publicación (`published_at`) e historial de calificaciones (`grade_revision`). |
| `004-pc3-open-link-uniqueness` | Un solo vínculo abierto (`ACTIVE` o `PENDING`) por par persona encargada–estudiante. |
| `005-student-registration-audit` | Autor y fecha de creación de cada ficha de estudiante (CU-16). |
| `006-academic-catalog` | Perfiles docentes con historial, grupos académicos, asignaciones docentes e inscripciones (CU-17 a CU-21). |
| `007-academic-workflows` | Publicaciones por grupo, borradores de calificación, avisos, mensajes, plazos, apelaciones, aportes, resoluciones y quejas; agrega la cuenta demo del Comité. |
| `008-notification-reminder-relation` | Relaciona cada recordatorio con el aviso original. |

## Tablas por área

| Área | Tabla | Uso |
|---|---|---|
| Cuentas | `portal_user` | Cuentas demo con nombre, rol (`GUARDIAN`, `DIRECTOR`, `TEACHER`, `COMMITTEE`) y estado activo. |
| Auditoría | `security_access_log` | Intentos autenticados rechazados por rol. |
| Auditoría | `consultation_log` | Listados y consultas académicas con su resultado de autorización. |
| Estudiantes | `student` | Ficha ficticia: grado, grupo, docente, estado, autor y fecha de creación. |
| Vínculos | `guardian_student_link` | Relación, respaldo, estado, autor y fechas. |
| Vínculos | `guardian_link_history` | Altas, suspensiones y finalizaciones con motivo y autor. |
| Catálogo | `teacher_profile`, `teacher_profile_history` | Perfil docente ligado a una cuenta existente y sus cambios. |
| Catálogo | `academic_group` | Materia, nivel, grupo y periodo; la combinación es única. |
| Catálogo | `teaching_assignment`, `student_enrollment` | Asignación de docentes e inscripción de estudiantes en grupos, con alta y baja auditadas. |
| Evaluaciones | `group_assessment_publication` | Evaluación asignada a un grupo, con confirmación del cuaderno y estado (`DRAFT` o `PUBLISHED`). |
| Evaluaciones | `assessment` | Evaluación por estudiante, enlazada a su grupo y publicación. |
| Calificaciones | `grade_draft` | Calificación en borrador, no visible para la familia. |
| Calificaciones | `grade_record` | Calificación publicada vigente, con columna `version`. |
| Calificaciones | `grade_revision` | Versiones anteriores con valor, motivo, autor y fecha de la corrección. |
| Comunicación | `portal_notification` | Avisos dentro del Portal: lectura, recordatorios, alerta a Dirección y aviso original relacionado. |
| Comunicación | `student_message` | Mensajes sobre un estudiante, con respuesta y lectura. |
| Comunicación | `information_complaint` | Quejas por información no recibida: asignación, hallazgo, respuesta y estado. |
| Apelaciones | `appeal_deadline_config` | Plazos de presentación y resolución, intervalo y máximo de recordatorios. |
| Apelaciones | `grade_appeal` | Apelación con número, puntos, motivo, respaldo, estado y plazos. |
| Apelaciones | `appeal_contribution` | Aportes de docente y Dirección y solicitudes de información del Comité. |
| Apelaciones | `appeal_resolution` | Resolución con resultado, fundamento, integrantes, si exige corrección y lectura por la familia. |

Los UUID identifican los datos académicos y las claves foráneas protegen las referencias. Cada tabla que registra una acción guarda quién la hizo y cuándo.

## Decisiones y restricciones

- **Vínculos:** `guardian_student_link.status` admite `ACTIVE`, `SUSPENDED`, `ENDED` y `PENDING`. Un índice único parcial impide que coexistan dos vínculos abiertos para el mismo par; uno `PENDING` nunca da acceso.
- **Publicación:** solo las evaluaciones publicadas se devuelven a la persona encargada; los borradores (`DRAFT`, `grade_draft`) quedan fuera de CU-01.
- **Corrección de calificaciones:** cada corrección guarda la versión anterior en `grade_revision` y aumenta `grade_record.version`. El código actualiza solo si la versión recibida coincide, así que dos correcciones simultáneas no se sobrescriben en silencio (CU-09). Esto satisface RF-09 y el alterno A04 de CU-01.
- **Escala:** la base acepta `NUMERIC(6, 2)` sin mínimo ni máximo; el código aplica provisionalmente 0–100 hasta confirmar la escala institucional ([B-02](../07-construccion/brechas-de-requerimientos.md)).
- **Apelaciones:** `grade_appeal.status` admite `OPEN`, `IN_REVIEW`, `PENDING_INFORMATION`, `RESOLVED` y `CLOSED`. Un índice parcial impide dos apelaciones abiertas de la misma persona sobre la misma evaluación, y cada apelación tiene a lo sumo una resolución.
- **Plazos:** los valores de `appeal_deadline_config` los configura Dirección; deben ser mayores que cero y no representan una política aprobada por la escuela ([B-07](../07-construccion/brechas-de-requerimientos.md)).
- **Avisos:** un índice único evita duplicar el mismo aviso para el mismo destinatario y referencia. Los recordatorios se generan cuando Dirección los procesa; no hay tarea programada ([B-13](../07-construccion/brechas-de-requerimientos.md)).
- **Volumen local:** el volumen `postgres_data` conserva la base entre reinicios. Cambiar variables de acceso después de inicializar PostgreSQL no cambia usuarios ya creados dentro del volumen.

Toda futura migración debe ser aditiva, tener identificador y autor, y preservar datos ya registrados. No borrar ni editar una migración aplicada para arreglar una base existente.
