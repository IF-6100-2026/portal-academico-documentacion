# Frontend del Portal Académico

Interfaz React 19, TypeScript estricto y Vite. Incluye espacios de trabajo para persona encargada, Dirección, docente y Comité, conectados a los flujos de los casos de uso vigentes de PC3. Las llamadas HTTP y sus tipos están centralizados en `src/api/academic.ts`; los paneles por actor están en `src/ui/`. Los escenarios no automatizados y las limitaciones están documentados en [resultados de pruebas](../docs/08-resultados-de-pruebas/resultados-pruebas.md).

Seguir la [guía de ambiente](../docs/06-configuracion/ambiente-de-desarrollo.md). Los datos disponibles son ficticios y se cargan por Liquibase.
