# Ambiente de desarrollo

## Requisitos

JDK 21, Maven 3.9+, Node.js 22.12+ o 24+, npm, Git y Docker Compose v2. Compose inicia PostgreSQL 17. El backend se ejecuta con Java 21.

## Preparar la base de datos

Desde la raíz del repositorio, copia `.env.example` a `.env` y levanta PostgreSQL:

```powershell
Copy-Item .env.example .env
docker compose up -d --wait
docker compose ps
```

`.env` está excluido de Git. Solo contiene credenciales locales de demostración; no las uses fuera del equipo local. Liquibase crea el esquema y carga datos completamente ficticios: `002-academic-core` crea usuarios, estudiantes, vínculos, evaluaciones, calificaciones y trazas; `003-pc3-core-completion` agrega fechas de publicación, historial de correcciones, un segundo estudiante y una relación pendiente; `004-pc3-open-link-uniqueness` impide que un mismo par tenga a la vez una relación activa y otra pendiente. Liquibase aplica cada migración una vez; reiniciar el contenedor conserva el volumen.

| Variable | Valor de `.env.example` | Uso |
|---|---|---|
| `DB_NAME` | `portal_academico` | Base de datos PostgreSQL y JDBC |
| `DB_USER` / `DB_PASSWORD` | `portal` / `portal` | Credenciales locales de PostgreSQL |
| `DB_PORT` | `5432` | Puerto local publicado por Compose |
| `SERVER_PORT` | `8080` | Puerto de la API |
| `CORS_ALLOWED_ORIGIN` | `http://localhost:5173` | Origen permitido para Vite |
| `DEMO_*_PASSWORD` | Contraseñas de demostración | Acceso local de cuatro usuarios ficticios |

Si `5432` u `8080` están ocupados, cambia `DB_PORT` o `SERVER_PORT` en `.env`. Si se cambia el puerto de la API, cambia también `VITE_API_URL` en `frontend/.env`. Si cambia el puerto de Vite, actualiza `CORS_ALLOWED_ORIGIN` y reinicia la API. Spring importa las variables desde `../.env` al ejecutar Maven en `backend/`.

## Iniciar backend y frontend

En una terminal, desde `backend/`:

```powershell
mvn test
mvn spring-boot:run
```

En otra terminal, desde `frontend/`:

```powershell
Copy-Item .env.example .env
npm ci
npm run build
npm run dev
```

Abre <http://localhost:5173>. Las cuentas y contraseñas de demostración están en el [guion PC3](../07-construccion/guion-demostracion-pc3.md). `encargada.demo` consulta a Sofía; `direccion.demo` administra vínculos. `sin-vinculos.demo` y `docente.demo` sirven para comprobar respuestas sin vínculos y permisos. Son identidades y datos ficticios.

## Comprobación integrada

- `http://localhost:8080/api/v1/health` entrega estado de la API. `http://localhost:8080/actuator/health` incluye el estado de los componentes configurados.
- Desde PowerShell en la raíz: `./scripts/verificar-nucleo-pc3.ps1 -BaseUrl http://localhost:8080`. El guion comprueba CU-01 y CU-02 con solicitudes reales; conserva las trazas y deja los vínculos creados para la prueba suspendidos o finalizados. Repetirlo agrega historial local; no borra ni reinicia la base.
- Desde `backend/`: `mvn test` ejecuta las pruebas Java y Checkstyle.
- Desde `frontend/`: `npm run build` comprueba tipos y genera la compilación de producción.
- Los archivos Gherkin en `docs/04-pruebas` son especificaciones. No hay un ejecutor Cucumber conectado; no se deben contar como pruebas ejecutadas.

## Detener servicios

Detén Vite y Spring con Ctrl+C. Desde la raíz del proyecto, ejecuta `docker compose down` para detener PostgreSQL. Evita `docker compose down -v` si necesitas conservar la base local.

Si la API funciona y la web no carga datos, revisa `VITE_API_URL`, `CORS_ALLOWED_ORIGIN` y que el proceso Vite use el puerto esperado. Si Spring no arranca, revisa `java -version` y `mvn -version`: Maven debe ejecutarse con JDK 21 y `JAVA_HOME` debe apuntar a ese JDK. Revisa también el puerto, `.env` y los registros con `docker compose logs db`.
