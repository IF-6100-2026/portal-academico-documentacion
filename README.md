# Portal Académico Montessori — PC3

Proyecto de Análisis y Diseño de Sistemas (IF-6100) para el Centro Educativo María Montessori. El repositorio integra documentación, API Spring Boot, interfaz React y PostgreSQL.

**Alcance construido:** consulta del progreso académico (CU-01) y administración de vínculos autorizados (CU-02), con datos ficticios. Estos dos casos son una propuesta de núcleo porque no se encontró una selección aprobada por el profesor. La aprobación queda pendiente. Los demás CU son especificaciones; no se presentan como funciones construidas.

## Integrantes registrados

Josué Delgado Corrales y Sebastián Cordero Quesada. La rama de preparación no reemplaza las contribuciones de código que cada integrante debe realizar para la entrega.

## Inicio local

Requiere Java 21, Maven 3.9+, Node.js 22.12+ o 24+, npm y Docker Compose v2.

Desde la raíz del repositorio:

```powershell
Copy-Item .env.example .env
docker compose up -d --wait
```

En una terminal, desde `backend/`:

```powershell
mvn test
mvn spring-boot:run
```

En otra terminal, desde `frontend/`:

```powershell
Copy-Item .env.example .env
npm ci
npm run dev
```

Abre <http://localhost:5173>. La guía de [ambiente de desarrollo](docs/06-configuracion/ambiente-de-desarrollo.md) incluye puertos, perfiles demo, verificación y diagnóstico.

## Revisión del proyecto

- [Documentación e índice de artefactos](docs/README.md)
- [Respuesta a la evaluación PC2 y alcance PC3](docs/cambios-pc2-pc3.md)
- [Estado de implementación](docs/07-construccion/estado-de-implementacion.md)
- [Brechas y decisiones pendientes](docs/07-construccion/brechas-de-requerimientos.md)
- [Decisiones de diseño](docs/07-construccion/decisiones-de-diseno.md)
- [Resultados de las verificaciones ejecutadas](docs/08-resultados-de-pruebas/resultados-pruebas.md)
- [Registro de uso de IA](docs/09-uso-de-ia/uso-de-ia.md)

Para repetir la verificación integrada del núcleo, iniciar base y API y ejecutar `./scripts/verificar-nucleo-pc3.ps1 -BaseUrl http://localhost:8080` desde PowerShell. El script crea un vínculo de demostración y lo suspende al final.

Esta rama de preparación no se ha fusionado ni se ha enviado como entrega en Mediación Virtual. La aprobación del núcleo, la revisión del grupo y la entrega final siguen pendientes.
