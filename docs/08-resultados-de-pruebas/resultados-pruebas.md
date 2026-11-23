# Resultados de pruebas y verificaciones

Ejecución local actualizada el **23 de noviembre de 2026** sobre la rama `codex/pc3-sdd-registro-estudiantes`. Se repitió la verificación integrada contra una base PostgreSQL 17 nueva levantada con Docker Compose. Como los puertos 5432 y 55432 estaban ocupados, se usaron 55433 para PostgreSQL y 8081 para la API; la guía permite configurar ambos en `.env`.

## Resultado por caso de uso

| CU | Estado de implementación PC3 | Evidencia disponible |
|---|---|---|
| CU-01 Consultar progreso académico | Implementado en el núcleo propuesto. | Smoke API: 31 comprobaciones aprobadas contra la base Docker limpia el 23/11/2026; incluyó publicaciones/avisos, borradores, historial y control de acceso. |
| CU-02 Vincular encargado con estudiante | Implementado en el núcleo propuesto. | Las mismas 31 comprobaciones aprobadas incluyeron catálogo por rol, altas, duplicados, vínculos pendientes y auditoría. |
| CU-03 a CU-10 | Especificados; sin implementación funcional en esta entrega. | Escenarios Gherkin escritos; no hay ejecutor Cucumber conectado. |
| CU-12 | Especificado; sin implementación funcional en esta entrega. | Escenarios Gherkin escritos; no hay ejecutor Cucumber conectado. |
| CU-13 a CU-15 | Propuestas especificadas; sin implementación funcional en esta entrega. | Escenarios Gherkin escritos; requieren revisión de grupo. |
| CU-16 Registrar estudiante | Implementación local verificada manualmente. | Tres pruebas unitarias del handler y build pasan. Contra la base Docker limpia se comprobaron HTTP 401 sin credenciales, 201 para Dirección, 403 para Docente y 400 para grupo vacío. Cinco escenarios Gherkin escritos, no ejecutados como suite. |
| CU-17 a CU-21 | Borradores de descomposición para RF-27/RF-28; sin implementación. | Cuatro escenarios documentales por CU; actores, conceptos y reglas marcados como provisionales; requieren revisión del grupo. |

## Verificaciones ejecutadas en esta revisión

| Comprobación | Resultado | Alcance |
|---|---|---|
| `mvn test` con JDK 21 | **Correcto:** 8 pruebas, 0 fallos, 0 errores; Checkstyle sin violaciones. | Incluye tres pruebas unitarias nuevas del handler de CU-16: denegación a actor sin rol activo, alta válida con normalización y auditoría, y rechazo de campo requerido vacío. También corre las pruebas existentes de configuración y salud. |
| `npm run build` | **Correcto:** TypeScript y compilación de producción Vite. | Comprueba los tipos y genera el paquete estático; no automatiza interacciones del navegador. |
| `python scripts/validar_documentacion.py` | **Correcto:** 20 CUs, 20 archivos `.feature`, 103 escenarios especificados y 0 errores estructurales. | Comprueba formato y relaciones de IDs; no ejecuta Gherkin ni valida que las decisiones sean académicamente aprobadas. |
| Inicio desde cero con Docker Compose | **Correcto:** PostgreSQL 17 quedó saludable; Liquibase aplicó los cinco cambiosets y Hibernate inició sin discrepancias de esquema. | Base local ficticia; host PostgreSQL 55433 porque 5432 y 55432 estaban ocupados. API en 8081 porque 8080 ya tenía otro servicio. |
| Inicio API y verificación HTTP manual de CU-16 | **Correcto:** respuestas 401 sin sesión, 201 para Dirección, 403 para Docente y 400 para grupo vacío. | Datos ficticios en la base Docker local. Evidencia manual, no prueba automatizada de integración. |

## Evidencia integrada de CU-01 y CU-02

| Comprobación | Resultado registrado | Alcance |
|---|---|---|
| `scripts/verificar-nucleo-pc3.ps1 -BaseUrl http://localhost:8081` | **Correcto:** 31 comprobaciones HTTP aprobadas en la base PostgreSQL 17 limpia del 23/11/2026. | API integrada de CU-01 y CU-02. El smoke no cubre el formulario ni la migración específica de CU-16. |
| Revisión manual de la interfaz local | **Correcto:** acceso de persona encargada y Dirección, lista de estudiantes, historial de calificación, periodo vacío y vínculo pendiente. | Revisión visual previa en navegador; no equivale a prueba automatizada de UI y no cubre el formulario nuevo de CU-16. |

## Límites de la evidencia

- Los 103 escenarios Gherkin son especificaciones; no hay ejecutor Cucumber conectado.
- Las pruebas unitarias de CU-16 usan repositorios simulados. Endpoint y migración sí se comprobaron manualmente en una base temporal, pero falta automatizar esta prueba de integración para ejecuciones futuras.
- No se ejecutaron pruebas automatizadas de interfaz, accesibilidad, distintos navegadores ni rendimiento.
- CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21 no tienen implementación funcional. El núcleo PC3 integrado previo cubre CU-01 y CU-02; CU-16 se probó por separado en instancia temporal.
- No hay una suite de pruebas unitarias de reglas del núcleo CU-01/CU-02; esas reglas se cubrieron con el smoke API descrito arriba.
- El smoke de PC3 conserva auditoría y agrega registros ficticios de vínculos suspendidos/finalizados. Puede repetirse; no limpia ni reinicia la base local.
- CU-01 + CU-02 son la propuesta implementada por el equipo; no se encontró confirmación de que el profesor seleccionara ese alcance.
