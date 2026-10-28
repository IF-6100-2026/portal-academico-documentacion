# Lineamientos de base de datos

- PostgreSQL relacional conserva relaciones explícitas entre estudiantes, encargados autorizados y registros académicos; restricciones protegen integridad referencial.
- Nombres de tablas y columnas en `snake_case`; claves primarias estables, claves foráneas con nombres explícitos, `NOT NULL` para datos obligatorios y restricciones únicas para identificadores institucionales confirmados.
- Liquibase es la única vía para cambiar el esquema. Cada changeset es pequeño, versionado, revisable y no se edita después de aplicarlo en un entorno compartido.
- Las reglas de acceso siempre se verifican en backend; una clave foránea o un filtro de interfaz no sustituye autorización.
- La migración inicial registra `schema_baseline`. Las tablas académicas esperan la revisión de los requerimientos y relaciones con el equipo.
- Datos de menores no se incluyen en ejemplos, fixtures ni logs. La política institucional de conservación, consentimiento y respaldo queda pendiente de validación.
