# Lineamientos de backend

- **Paquetes por módulo y capas cerradas:** `health` contiene su entrada HTTP; los módulos académicos futuros separarán `api`, `application`, `domain` e `infrastructure`. Un módulo solo accede a la interfaz pública de otro.
- **Controladores / boundary:** reciben HTTP, validan formato y traducen DTO a respuesta. No contienen reglas ni llaman repositorios directamente. `HealthController` es deliberadamente un endpoint simple de diagnóstico.
- **Servicios / control:** cada operación de negocio se coordina en un servicio de aplicación con transacción en el límite. Las políticas de publicación de evaluaciones pertenecerán al servicio, no al controlador.
- **Dominio / entity:** entidades JPA se mantienen en persistencia y no se serializan como respuesta. El perfil académico se expondrá mediante DTO con solo los campos que el actor puede consultar.
- **Repositorios:** ofrecen consultas de persistencia enfocadas; las consultas de seguimiento reciben el vínculo autorizado como parte de su criterio y no recuperan estudiantes globalmente para filtrar después.
- **Validación y errores:** Bean Validation verifica forma y obligatoriedad de entradas. Un manejador global convierte errores conocidos en respuestas HTTP uniformes sin detalles internos.
- **Nombres:** clases y métodos en español o inglés coherente por módulo; endpoints plurales bajo `/api/v1`; DTO con sufijo `Request` o `Response`.
- **Pruebas:** servicios para reglas y permisos; pruebas HTTP para contratos. No se agrega lógica de negocio hasta acordar el CU y sus criterios definitivos.
