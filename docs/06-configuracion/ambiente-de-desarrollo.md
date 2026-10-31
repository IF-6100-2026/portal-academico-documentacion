# Ambiente de desarrollo

## Versiones y estado

JDK 21, Maven 3.9+, Node.js 22.12+ (línea 22, `.nvmrc`; también se admite Node 24+), npm, Git y Docker Compose v2. Compose usa PostgreSQL 17. Frontend, backend y documentación permanecen en repositorios separados; ver [flujo de colaboración](flujo-colaboracion.md).

Esta guía describe la configuración de los PR de integración. Hasta fusionarlos, `main` puede conservar el arranque anterior.

## Base de datos y backend

Desde `portal-academico-backend`, copiar `.env.example` a `.env`:

```powershell
Copy-Item .env.example .env
docker compose up -d --wait
mvn test
mvn spring-boot:run
```

En Bash, usar `cp .env.example .env`; los demás comandos son iguales. Ejecutar Maven desde la raíz del backend: Spring importa `.env` como propiedades y Compose usa el mismo archivo. Las variables exportadas en la terminal tienen precedencia; eliminar valores antiguos si se cambia `.env`.

| Variable | Valor local | Uso |
|---|---|---|
| `DB_NAME` | `portal_academico` | Base de datos de Compose y JDBC |
| `DB_USER` / `DB_PASSWORD` | `portal` / `portal` | Credenciales locales compartidas |
| `DB_PORT` | `5432` | Puerto PostgreSQL en localhost |
| `SERVER_PORT` | `8080` | API Spring Boot |
| `CORS_ALLOWED_ORIGIN` | `http://localhost:5173` | Origen exacto permitido del frontend |
| `DB_URL` | sin definir | Solo para reemplazar la URL JDBC completa con una base externa |

Usar valores sin comillas en `.env` para que Spring y Compose los interpreten igual. Las credenciales de ejemplo solo son para desarrollo; `.env` está excluido de Git. Cambiar las credenciales de `.env` no modifica un usuario dentro de un volumen PostgreSQL ya inicializado: actualizarlo explícitamente o usar otro entorno de desarrollo.

## Frontend

Desde `portal-academico-frontend`:

```powershell
Copy-Item .env.example .env
npm ci
npm run build
npm run dev
```

En Bash, sustituir `Copy-Item` por `cp`. Abrir `http://localhost:5173`. El puerto es estricto: si está ocupado, Vite falla en vez de saltar a un origen que CORS no permite.

Si cambia `SERVER_PORT=18080`, cambiar también `VITE_API_URL=http://localhost:18080` en el `.env` del frontend y reiniciar Vite. Si cambia el puerto/origen de Vite, actualizar `CORS_ALLOWED_ORIGIN` y reiniciar Spring Boot.

## Comprobación integrada

1. `docker compose ps`: la base debe estar saludable.
2. `http://localhost:8080/api/v1/health`: devuelve `status: UP`, `service: portal-academico-api` y `timestamp` ISO-8601. Comprueba HTTP, no el estado de PostgreSQL.
3. `http://localhost:8080/actuator/health`: comprobar salud de la aplicación y de los componentes configurados, incluida la base de datos.
4. La pantalla en `http://localhost:5173` muestra la respuesta. Si falla, permite reintentar y limita la espera a 10 segundos.
5. Al iniciar Spring, Liquibase aplica la migración inicial; solo existe `schema_baseline`, sin tablas académicas.

## Cierre y diagnóstico

Detener Vite y Spring con Ctrl+C; desde backend usar `docker compose down`. Consultar `docker compose logs db` si falla PostgreSQL. No usar `down -v` si se quieren conservar los datos.

Si la API abre en el navegador pero el frontend falla, comprobar el origen CORS y `VITE_API_URL`. Si Spring no arranca, comprobar JDK 21, credenciales, puerto y que Maven se ejecute desde la raíz del backend.

Los escenarios Gherkin de `docs/04-pruebas` son especificaciones; todavía no existe un ejecutor que los conecte al sistema. Ver [el estado de la revisión](../estado-integracion.md) para distinguir comprobaciones realizadas de pendientes.
