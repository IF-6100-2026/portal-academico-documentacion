# Ambiente de desarrollo

## Requisitos

Git, JDK 21, Maven 3.9 o superior, Node.js 22 LTS, npm y Docker Desktop con Docker Compose. No se requiere instalar PostgreSQL en el equipo: Compose levanta PostgreSQL 17.

## Preparación

Clonar los tres repositorios en una carpeta común conservando los nombres `portal-academico-documentacion`, `portal-academico-backend` y `portal-academico-frontend`. Desde la carpeta común:

```powershell
Copy-Item portal-academico-backend/.env.example portal-academico-backend/.env
Copy-Item portal-academico-frontend/.env.example portal-academico-frontend/.env
docker compose --env-file portal-academico-backend/.env -f portal-academico-backend/docker-compose.yml up -d
```

Los valores de ejemplo solo sirven para desarrollo local. No subir `.env` ni reutilizar esas contraseñas en otros entornos.

## Arranque

Terminal 1, desde `portal-academico-backend`:

```powershell
$env:DB_URL='jdbc:postgresql://localhost:5432/portal_academico'
$env:DB_USER='portal'
$env:DB_PASSWORD='portal'
mvn spring-boot:run
```

Terminal 2, desde `portal-academico-frontend`:

```powershell
npm ci
npm run dev
```

Abrir `http://localhost:5173`. La pantalla debe indicar “Backend conectado”. Comprobar también `http://localhost:8080/api/v1/health`; debe devolver `status`, `service` y `timestamp`. Al iniciar el backend, Liquibase aplica la migración inicial.

## Cierre y diagnóstico

Detener frontend y backend con Ctrl+C. Detener la base con `docker compose --env-file .env -f docker-compose.yml down` desde backend. Para ver su estado: `docker compose ... ps` y `docker compose ... logs db`. Si el frontend indica que no conecta, confirme primero el endpoint en navegador; revise `VITE_API_URL`, puerto 8080 y que CORS permita Vite. Si falla PostgreSQL, confirme el puerto 5432 disponible y credenciales de `.env`.

No borrar el volumen `postgres_data` si se desea conservar datos locales. La base inicial está vacía y no contiene información de estudiantes. Si el endpoint de salud funciona pero la aplicación no inicia, confirme que el motor PostgreSQL acepte la URL y las credenciales definidas en las variables; el arranque de la API aplica Liquibase y requiere conexión a la base.
