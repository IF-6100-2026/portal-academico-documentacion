# Lineamientos del backend

## Organización y dependencias
Se eligen módulos funcionales (academico, vinculacion, comunicacion, apelaciones) y, dentro de cada uno, api, application, domain e infrastructure. health contiene el diagnóstico inicial. Capas cerradas: api → application → interfaces de persistencia; infrastructure implementa estas interfaces. Esto limita el acoplamiento y evita que cambiar apelaciones obligue a cambiar consultas académicas.

## Controladores: boundary
Reciben y validan formato con Bean Validation, extraen identidad autenticada y devuelven DTO. No contienen reglas de publicación ni acceden a repositorios. Ejemplo propuesto: ProgresoController valida estudianteId/periodo y llama ConsultarProgresoService; PublicarCalificacionController recibe PublicarCalificacionRequest y delega. La identidad siempre proviene de la sesión.

## Servicios: control
Se elige un servicio de aplicación por caso de uso, con un método de entrada execute, en lugar de un servicio CRUD por entidad. PublicarCalificacionService coordinará RF-05/07/08/11: asignación docente, nota válida, confirmación del cuaderno, publicación y aviso. Así una entidad no concentra todos los flujos.
La transacción inicia en el método público del servicio. Publicación y creación del aviso se confirman juntas; un fallo revierte ambas. El envío externo futuro se realizaría después del commit mediante un registro de salida persistido, nunca sosteniendo una transacción mientras espera la red.

## Entidades y repositorios
Entidades JPA mantienen identidad y relaciones; los repositorios exponen consultas acotadas. Ejemplo: buscarPublicadasParaEstudianteYPeriodo, después de verificar el vínculo del actor. Nunca recuperar todos los estudiantes para filtrarlos en React. Los detalles SQL y EntityManager no salen de infrastructure. Un cambio de nota conserva la versión anterior, motivo y responsable (CU-09).

## DTO
PublicarCalificacionRequest contiene evaluacionId, estudianteId, valor y confirmacionCuaderno; no permite fijar arbitrariamente docenteId, estadoPublicado o fechaAuditoria.
ProgresoResponse contiene únicamente perfil autorizado, publicaciones e información de corrección. No devuelve contraseña, expediente de otros estudiantes ni la entidad JPA completa. DTO evitan filtraciones y dependencia del contrato HTTP respecto del modelo persistido.

## Errores
Un RestControllerAdvice central convierte errores: formato inválido → 400; sesión inválida → 401; operación no autorizada → 403 sin datos ajenos; versión desactualizada (CU-09 A02) → 409; fallo inesperado → 500 con identificador de correlación, sin SQL ni stack trace.
Formato propuesto: codigo, mensaje y erroresDeCampo. El CU define el mensaje seguro; no distinguir por mensajes públicos un estudiante ajeno de uno inexistente.

## Convenciones y principios
Clases PascalCase; métodos y campos camelCase; endpoints plurales bajo /api/v1; Request/Response para DTO, Service para aplicación, Repository para persistencia. Ejemplo: POST /api/v1/evaluaciones/{id}/calificaciones.
Responsabilidad única: el controlador traduce HTTP, el servicio coordina CU y el repositorio persiste. Inversión de dependencias: el servicio depende de interfaces y recibe dependencias por constructor. Alta cohesión: vinculación concentra sus reglas, sin utilitarios que mezclen mensajes y notas.
Se probarán reglas en servicios, contratos HTTP y persistencia con PostgreSQL. Para PC2 solo se implementa el esqueleto; estos nombres académicos son decisiones de diseño para PC3.
