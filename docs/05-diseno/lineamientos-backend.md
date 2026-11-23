# Lineamientos de backend

## Organización existente

El backend usa Spring Boot 3.5.5 y Java 21. Los paquetes separan:

- `api`: controladores REST y mapeo de respuestas HTTP.
- `handlers.commands` y `handlers.queries`: operaciones de escritura y lectura; las respuestas de handler son `sealed` para enumerar resultados esperados.
- `persistence.entity` y `persistence.repository`: entidades JPA y acceso a PostgreSQL.
- `config`: seguridad HTTP, credenciales demo y CORS.
- `exception`: errores de validación y respuesta común.

El controlador recibe la identidad desde Spring Security. Los handlers vuelven a validar las reglas de acceso a los datos. La interfaz no envía `guardianUsername` para decidir permisos. Las transacciones abarcan la operación y su registro de auditoría.

## Convenciones de la API actual

- Prefijo: `/api/v1`.
- Errores de validación: HTTP 400; sin sesión válida: 401; rol o vínculo sin permiso: 403; recurso no disponible: 404; conflicto de estado: 409.
- UUID para identificadores de estudiante, vínculo y evaluación.
- Bean Validation para campos obligatorios de las solicitudes.
- Checkstyle valida el código Java en la fase Maven `validate`.

## Operaciones de demostración disponibles

| Operación | Autorización | Resultado |
|---|---|---|
| `GET /api/v1/guardian/students` | GUARDIAN | Estudiantes con vínculo activo |
| `GET /api/v1/guardian/students/{id}/progress?period=...` | GUARDIAN + vínculo activo verificado por solicitud | Perfil y evaluaciones publicadas del periodo |
| `GET /api/v1/director/guardian-links/catalog` | DIRECTOR | Catálogo demo de personas y vínculos |
| `POST /api/v1/director/guardian-links` | DIRECTOR | Crea vínculo con respaldo requerido |
| `PATCH /api/v1/director/guardian-links/{id}/status` | DIRECTOR | Suspende o finaliza vínculo activo con motivo |

## Límites

HTTP Basic y contraseñas estáticas de demo no son autenticación institucional. No hay gestión de usuarios, roles de Comité, límites de intentos, integración de correo ni endpoints para los otros casos de uso. Antes de producción se deben acordar identidad institucional, custodia y autorización, TLS, manejo de sesiones y auditoría de accesos anónimos. Véase el [registro de brechas](../07-construccion/brechas-de-requerimientos.md).
