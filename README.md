# Portal Académico Montessori — PC2

IF-6100, UCR · Centro Educativo María Montessori.
Integrantes: Josué Delgado Corrales y Sebastián Cordero Quesada.

El Portal propone registro y consulta académica, comunicación y seguimiento de apelaciones. Este PC2 entrega el análisis revisado, diseño y esqueleto integrado; no implementa todavía los casos de uso académicos.

- [Documentación](docs/README.md)
- [Guía completa de arranque y verificación](docs/06-configuracion/ambiente-de-desarrollo.md)
- [Cambios respecto del PC1](docs/cambios-pc1-pc2.md)

## Organización
Documentación en docs/, Spring Boot en backend/ y React en frontend/. Se consolidaron los repositorios anteriores sin borrar su historia. El PC1 se consulta en el historial y en el tag pc1 del repositorio oficial.

## Arranque resumido
Requiere Java 21, Maven 3.9+, Node 22.12+ o 24+, npm y Docker Compose v2.
Desde backend/: copiar .env.example a .env, ejecutar docker compose up -d --wait y mvn spring-boot:run.
Desde frontend/, en otra terminal: copiar .env.example a .env, npm ci y npm run dev.
Abrir http://localhost:5173. Consultar la guía para verificar PostgreSQL, migraciones y errores.

## Entrega
Repositorio oficial previsto: https://github.com/IF-6100-2026/portal-academico-documentacion
El tag pc2 se crea únicamente después de verificar la versión final. Una rama de preparación no equivale a entrega en Mediación Virtual.
