# Lineamientos de base de datos

PostgreSQL relacional protege relaciones de estudiantes, encargados, evaluaciones y notas. Se usa PostgreSQL 17 y Liquibase; Hibernate valida el esquema y no lo modifica.

Tablas y columnas en snake_case; PK id, FK estudiante_id y evaluacion_id; restricciones nombradas pk_*, fk_*, uq_* y ck_*. Datos obligatorios usan NOT NULL. Ejemplo CU-04: una calificación referencia estudiante y evaluación existentes mediante FK. El rango permitido de la nota se fija con CHECK una vez confirmada la escala; no se inventa una escala institucional en el esquema inicial.

CU-02 requiere evitar vínculos activos equivalentes duplicados: se diseñará una unicidad adecuada a la relación institucional confirmada, preservando el historial de suspensión/finalización. CU-09 conserva versiones y usa control de versión para rechazar actualizaciones simultáneas (409). Las reglas que requieren actor o vínculo vigente se validan en aplicación; una FK no concede acceso.

Cada cambio se añade en un changeset nuevo, con autor, identificador único y rollback cuando sea viable. No editar changesets aplicados; una corrección lleva otro. Índices se justifican por consultas: estudiante_id y periodo para CU-01; destinatario y estado para avisos pendientes. Las migraciones se prueban en una base vacía y sobre la versión anterior.

La migración PC2 crea schema_baseline; las tablas académicas aquí descritas son diseño para PC3. Datos de prueba son ficticios; no se suben datos personales, contraseñas reales ni volcados. La retención institucional permanece pendiente de validación.
