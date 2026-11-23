# Portal Académico Montessori — PC3

Proyecto de Análisis y Diseño de Sistemas (IF-6100) para el Centro Educativo María Montessori. El repositorio integra documentación, API Spring Boot, interfaz React y PostgreSQL.

**Alcance funcional de esta entrega:** núcleo integrado propuesto de CU-01 y CU-02, con datos ficticios. CU-01 incluye publicaciones recientes, avisos pendientes y versiones anteriores de una calificación corregida. CU-02 permite registrar, consultar, suspender y finalizar vínculos; una solicitud pendiente por restricción no concede acceso. La selección CU-01 + CU-02 es una propuesta razonada del equipo: no se encontró una aprobación docente y no se atribuye una aprobación inexistente.

Los otros casos de uso están especificados en la documentación, pero no tienen implementación funcional en esta entrega. Las políticas institucionales, el alcance y las brechas abiertas se explican en [estado de implementación](docs/07-construccion/estado-de-implementacion.md), [brechas](docs/07-construccion/brechas-de-requerimientos.md) y [decisiones](docs/07-construccion/decisiones-de-diseno.md).

## Integrantes registrados

Josué Delgado Corrales y Sebastián Cordero Quesada. El historial conserva commits de ambos. Los cambios de esta entrega se atribuyen al autor real; no se crean commits en nombre de quien no los hizo.

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

## Revisión y demostración

- [Documentación e índice de artefactos](docs/README.md)
- [Respuesta a la evaluación PC2 y alcance PC3](docs/cambios-pc2-pc3.md)
- [Análisis de retroalimentación docente y avance independiente](docs/07-construccion/ANALISIS-Y-AVANCE-DEFINICION-REGISTRO-ESTUDIANTE.md)
- [Guía del curso: skills y SDD](docs/06-configuracion/guia-sdd-y-skills-del-curso.md)
- [Spec, plan y tareas de registrar estudiante](specs/001-registrar-estudiante/spec.md)
- [Estado de implementación](docs/07-construccion/estado-de-implementacion.md)
- [Brechas y decisiones pendientes](docs/07-construccion/brechas-de-requerimientos.md)
- [Decisiones de diseño](docs/07-construccion/decisiones-de-diseno.md)
- [Resultados de las verificaciones ejecutadas](docs/08-resultados-de-pruebas/resultados-pruebas.md)
- [Registro de uso de IA](docs/09-uso-de-ia/uso-de-ia.md)
- [Guion de demostración PC3](docs/07-construccion/guion-demostracion-pc3.md)

Para repetir la verificación integrada del núcleo, iniciar base y API y ejecutar `./scripts/verificar-nucleo-pc3.ps1 -BaseUrl http://localhost:8080` desde PowerShell. Usa identidades ficticias, deja un vínculo pendiente y crea vínculos de prueba que terminan suspendidos/finalizados; las trazas permanecen en la base local. La guía explica cómo demostrar el producto desde la interfaz.

## Entrega PC3

El tag `pc3` identifica el commit preparado para la entrega. Copia su enlace de GitHub en la actividad PC3 de Mediación Virtual; subir el tag al repositorio no registra por sí solo la entrega en Mediación.

La selección docente del núcleo no se pudo confirmar antes de la fecha límite; por eso el alcance ejecutado se registra como propuesta del equipo. La revisión final del grupo y la entrega del enlace/tag en Mediación Virtual se completan aparte.
