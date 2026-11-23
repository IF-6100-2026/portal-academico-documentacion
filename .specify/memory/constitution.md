# Constitución del Portal Académico

## Propósito
Organizar el seguimiento académico y permitir consultas según identidad, rol y vínculo autorizado.

## Reglas de producto
1. Usar datos ficticios en desarrollo, capturas y demos.
2. No inventar escalas, políticas de custodia, plazos ni roles institucionales. Escribir supuestos como supuestos.
3. El registro del estudiante y el vínculo de una persona encargada son operaciones separadas; dar de alta no da acceso.
4. No borrar físicamente información con historial.
5. Cada cambio funcional mantiene trazabilidad RF → CU → escenario → issue → código.

## Reglas técnicas
- Backend Java 21 / Spring Boot 3 con Maven.
- Frontend React y TypeScript estricto.
- PostgreSQL y Liquibase; no editar migraciones ya aplicadas.
- Mantener handlers y capas existentes; los controladores no llevan reglas de negocio.
- La entidad estudiante actual usa UUID, nombre, nivel, grupo, docente y estado activo.
- Las cuentas demo no se usan con datos reales.

## Documentación y SDD
Documentación en español y nombres de código en inglés. Gherkin es especificación hasta que se ejecute y se registren resultados reales. Orden de trabajo: reglas → especificación → plan → tareas → implementación. Revisar consistencia en cada transición. El contenido generado con IA requiere revisión humana antes de declararse aprobado.
