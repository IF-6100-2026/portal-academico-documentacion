# Backend del Portal Académico

API Spring Boot 3.5 / Java 21. Incluye los flujos de los 20 casos vigentes de PC3: consulta académica, gestión de vínculos, publicaciones y calificaciones, comunicación, apelaciones, quejas y catálogos académicos. Los controladores delegan la lógica a workflows/handlers; la autorización de actores se verifica en Spring Security y las reglas de dominio se aplican en esos flujos. CU-11 fue retirado antes de PC3. El alcance y las limitaciones verificadas están en [estado de implementación](../docs/07-construccion/estado-de-implementacion.md).

Seguir la [guía de ambiente](../docs/06-configuracion/ambiente-de-desarrollo.md). El repositorio usa Liquibase para crear el esquema y cargar datos ficticios de demostración. Las credenciales se definen en el `.env` raíz y no se versionan.
