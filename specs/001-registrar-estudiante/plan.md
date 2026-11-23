# Plan: registrar estudiante

**Estado:** implementación local verificada con pruebas unitarias y una comprobación HTTP contra PostgreSQL temporal; frontend compilado. La integración manual no está automatizada en CI; falta revisión del equipo.

## Backend
1. Añadir comando/handler siguiendo los patrones actuales.
2. Aceptar nombre, nivel, grupo y docente; validar campos y límites actuales: 160 caracteres para nombres, 30 para nivel y grupo.
3. Generar UUID en servidor y guardar como activo.
4. Exponer operación protegida para Dirección, por ejemplo POST /api/v1/director/students.
5. Reutilizar el formato de errores actual.
6. No crear vínculos familiares desde el endpoint.
7. Reutilizar el esquema si alcanza; si se agrega auditoría o identificador escolar, crear una nueva migración Liquibase sin editar las aplicadas.

## Frontend
1. Agregar formulario de Dirección.
2. Mostrar errores junto a los campos y bloquear doble envío.
3. Tras éxito, actualizar el catálogo y ofrecer continuar con CU-02, sin crear vínculo automáticamente.
4. Mantener navegación por teclado y mensajes claros.

## Seguridad y alcance
Reutilizar el rol de Dirección, mantener datos ficticios y no crear cuentas del estudiante. No deduplicar hasta definir identidad escolar.

## Relación con PC3
CU-01 y CU-02 son el núcleo integrado implementado. CU-16 se implementó localmente como el siguiente incremento; las pruebas unitarias del handler y el build del frontend pasan. La migración 005 y los estados principales del endpoint se verificaron contra una instancia PostgreSQL temporal; se recomienda convertir esa comprobación manual en una prueba de integración repetible.
