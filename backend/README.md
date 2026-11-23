# Backend del Portal Académico

API Spring Boot 3.5 / Java 21. Implementa consulta del progreso académico (CU-01) y gestión de vínculos persona encargada–estudiante (CU-02). La autorización de roles se verifica en Spring Security y la del vínculo vigente en el handler de consulta.

Seguir la [guía de ambiente](../docs/06-configuracion/ambiente-de-desarrollo.md). El repositorio usa Liquibase para crear el esquema y cargar datos ficticios de demostración. Las credenciales se definen en el `.env` raíz y no se versionan.
