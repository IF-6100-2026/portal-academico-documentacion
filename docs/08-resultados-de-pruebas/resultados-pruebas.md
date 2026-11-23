# Resultados de pruebas y verificaciones

Ejecución local del **22 de noviembre de 2026**, sobre la rama de trabajo PC3, con Java 21, PostgreSQL 17, Node.js y datos de demostración ficticios.

## Resultado por caso de uso

| CU | Estado de implementación PC3 | Verificación ejecutada |
|---|---|---|
| CU-01 Consultar progreso académico | Implementado en el núcleo propuesto. | Smoke API integrado: lista activa, publicaciones recientes y avisos pendientes, filtro de borradores, calificación vigente/anterior, periodo vacío, cuenta sin vínculos, denegación horizontal, sesión ausente y retiro de acceso tras suspensión/finalización. |
| CU-02 Vincular encargado con estudiante | Implementado en el núcleo propuesto. | Smoke API integrado: catálogo por rol, alta con respaldo, duplicado activo, persona/estudiante inválidos, estado pendiente sin acceso, bloqueo de alta activa paralela, suspensión/finalización y auditoría de autor/fecha/motivo. |
| CU-03 a CU-10 | Especificados; sin implementación funcional en esta entrega. | Gherkin revisado estructuralmente; escenarios no ejecutados. |
| CU-12 | Especificado; sin implementación funcional en esta entrega. | Gherkin revisado estructuralmente; escenarios no ejecutados. |
| CU-13 a CU-15 | Propuestas especificadas; sin implementación funcional en esta entrega. | Gherkin revisado estructuralmente; escenarios no ejecutados. |

## Comprobaciones ejecutadas

| Comprobación | Resultado | Alcance |
|---|---|---|
| `mvn test` con JDK 21 | **Correcto:** 5 pruebas, 0 fallos, 0 errores; Checkstyle reportó 0 violaciones. | Compila 30 clases de producción y ejecuta las pruebas actuales de configuración y salud. No es una suite unitaria de cada regla de negocio. |
| `npm ci` | **Correcto:** instaló 69 paquetes; 0 vulnerabilidades reportadas por npm. | Dependencias reproducibles según `package-lock.json`. |
| `npm run build` | **Correcto:** TypeScript y compilación de producción Vite. | Comprueba el contrato de tipos y genera los archivos estáticos; no prueba interacciones de navegador. |
| `scripts/verificar-nucleo-pc3.ps1 -BaseUrl http://localhost:18081` | **Correcto:** 31 comprobaciones HTTP por ejecución; se corrió tres veces en total, la última con la migración 004 aplicada. | API integrada para el alcance CU-01/CU-02. Se usó el puerto 18081 porque el 18080 local ya estaba ocupado. |
| Revisión manual de la interfaz local | **Correcto:** acceso de persona encargada y Dirección, lista de estudiantes, historial de calificación, cambio de periodo con lista vacía y catálogo con vínculo pendiente. | Exploración manual en navegador; no equivale a una prueba automatizada de UI. |
| `python scripts/validar_documentacion.py` | **Correcto:** 14 CU, 14 archivos `.feature`, 78 escenarios especificados y 0 errores estructurales. | Verifica formato y estructura documental; no ejecuta Gherkin ni valida su contenido académico. |

## Límites de esta evidencia

- Los 78 escenarios Gherkin son especificaciones; no hay ejecutor Cucumber conectado.
- No se ejecutaron pruebas automatizadas de interfaz, accesibilidad, distintos navegadores ni rendimiento.
- CU-03 a CU-10 y CU-12 a CU-15 no tienen implementación funcional ni pruebas de integración.
- El backend no tiene una suite de pruebas unitarias de reglas de vínculo, historial, errores y concurrencia.
- El smoke conserva auditoría y agrega registros ficticios de vínculos suspendidos/finalizados. Puede ejecutarse nuevamente; no limpia ni reinicia la base local.
- CU-01 + CU-02 son la propuesta ejecutada por el equipo, no una selección docente que se haya podido confirmar.
