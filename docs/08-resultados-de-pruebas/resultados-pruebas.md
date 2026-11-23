# Resultados de pruebas y verificaciones

Ejecución del **23 de noviembre de 2026** sobre la rama `codex/pc3-remaining-use-cases`. Las comprobaciones de integración de los flujos nuevos se hicieron con una API local en `http://localhost:8083` y una base temporal PostgreSQL 17 en el puerto 55434. El contenedor se creó para esta revisión y no corresponde a la base local de desarrollo del equipo.

## Resultado por caso de uso

| CU | Implementación | Evidencia ejecutada |
|---|---|---|
| CU-01 Consultar progreso académico | Implementado en la base PC3. | 31 comprobaciones HTTP heredadas, contra PostgreSQL 17 limpio; consulta, publicaciones, avisos, borradores, historial y permisos. |
| CU-02 Vincular encargado con estudiante | Implementado en la base PC3. | Las mismas 31 comprobaciones HTTP: catálogo, altas, duplicados, vínculo pendiente, permisos e historial. |
| CU-03 Publicar evaluación asignada | Implementado en esta rama. | HTTP: publicación de grupo para dos estudiantes y repetición idempotente. |
| CU-04 Publicar calificación | Implementado en esta rama. | HTTP: calificación publicada y aviso; valores de escala considerados provisionales. |
| CU-05 Dar seguimiento a avisos no consultados | Implementado en esta rama. | HTTP: acuse de lectura; 5 recordatorios, 5 alertas al alcanzar el límite y tercera ejecución con 0/0. Finalizar el vínculo desactivó 14 avisos pendientes y abrir uno devolvió 404. Para activar el vencimiento se ajustó la antigüedad en la BD temporal; el intervalo no se esperó en tiempo real. |
| CU-06 Comunicarse sobre un estudiante | Implementado en esta rama. | HTTP: docente envió mensaje y persona encargada lo consultó con acuse. |
| CU-07 Presentar apelación de una calificación | Implementado en esta rama. | HTTP: apelación válida y repetición idempotente con el mismo identificador. |
| CU-08 Supervisar el seguimiento académico | Implementado en esta rama. | HTTP: el panel de Dirección reflejó expedientes pendientes y luego cerrados. |
| CU-09 Corregir una calificación publicada | Implementado en esta rama. | HTTP: 85→90, motivo y versión 0→1; el historial conservó la nota previa. |
| CU-10 Configurar plazos y recordatorios | Implementado en esta rama. | HTTP: configuración de plazos, frecuencia y cantidad máxima; la apelación usó la ventana configurada. |
| CU-11 Bitácora de auditoría | Retirado antes de PC3. | No aplica. |
| CU-12 Presentar queja por información no recibida | Implementado en esta rama. | HTTP: queja → solicitud de Dirección → publicación coincidente del docente → estado atendido. |
| CU-13 Aportar información a una apelación | Implementado en esta rama. | HTTP: aporte docente persistido en el expediente con autor/fecha. |
| CU-14 Registrar resolución de apelación | Implementado en esta rama. | HTTP: Comité guardó resolución `UPHELD`, sin modificar la calificación. |
| CU-15 Consultar resolución de apelación | Implementado en esta rama. | HTTP: persona encargada consultó, registró lectura y cerró el expediente. |
| CU-16 Registrar estudiante | Implementado en la base PC3. | Tres pruebas unitarias, build frontend y verificación HTTP manual contra PostgreSQL: 401 sin sesión, 201 Dirección, 403 Docente, 400 dato requerido vacío. |
| CU-17 Registrar grupo académico | Implementado en esta rama. | HTTP: Dirección creó un grupo académico. |
| CU-18 Asignar docente a grupo | Implementado en esta rama. | HTTP: perfil docente asignado y acceso docente ligado al grupo. |
| CU-19 Inscribir estudiante en grupo | Implementado en esta rama. | HTTP: dos estudiantes activos inscritos y usados en la publicación de CU-03. |
| CU-20 Registrar docente | Implementado en esta rama. | HTTP: perfil enlazado a usuario existente, alta repetida idempotente. |
| CU-21 Actualizar perfil docente | Implementado en esta rama. | HTTP: perfil actualizado/inactivado; historial con dos entradas. |

## Verificaciones locales

| Comando o actividad | Resultado | Alcance |
|---|---|---|
| `mvn test` (JDK 21) | **Correcto:** 12 pruebas, 0 fallos, 0 errores, Checkstyle con 0 violaciones. | Incluye configuración local, cuatro pruebas del catálogo académico, tres de registro de estudiante y pruebas de salud. No equivale a cubrir todos los flujos del negocio. |
| `npm run build` | **Correcto:** `tsc -b` y compilación Vite. | 35 módulos transformados; no automatiza interacciones de UI. |
| `python scripts/validar_documentacion.py` | **Correcto:** 20 CUs, 20 archivos `.feature`, 103 escenarios escritos y 0 errores estructurales. | Valida estructura e IDs. No ejecuta Cucumber. |
| Arranque de la API contra PostgreSQL 17 temporal | **Correcto:** Liquibase aplicó 8 changesets y Spring respondió saludable. | Puerto de API 8083 y base aislada en 55434; no se modificó la base local del equipo. |
| Smoke HTTP de los CU nuevos | **Correcto:** flujo principal de CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21. | Prueba manual de integración en base desechable; no está aún guardada como suite repetible en CI. |
| Verificación HTTP de CU-16 | **Correcto:** 401, 201, 403 y 400 según el actor/dato. | Comprobación manual, no automatizada. |
| `scripts/verificar-nucleo-pc3.ps1` | **Correcto:** 31 comprobaciones HTTP de CU-01/CU-02 en revisión previa. | No repetir contra la BD temporal actual; el script agrega datos y trazas cuando se usa con la base de desarrollo. |

## Límites de la evidencia

- La marca “implementado” indica código disponible en la rama y un flujo principal comprobado; no afirma que cada alterno de los 103 escenarios Gherkin se haya ejecutado.
- No hay una suite Cucumber conectada ni pruebas HTTP automatizadas para los 17 CU nuevos. Los Gherkin continúan como criterios documentales.
- No se probaron todos los rechazos por rol, fechas límite, perfiles inexistentes, fallos de persistencia o concurrencia. La tabla por CU especifica exactamente qué flujo principal sí se verificó.
- Los avisos y mensajes son internos al Portal: no hay correo/SMS, archivos adjuntos ni scheduler externo. El procesamiento de recordatorios se ejecuta mediante endpoint de Dirección.
- La escala de calificación, los plazos académicos, el procedimiento del Comité, el modelo curso/materia/grupo y la identidad institucional siguen siendo supuestos que el equipo debe validar.
- No se ejecutaron pruebas automatizadas de UI, accesibilidad, rendimiento ni pruebas con usuarios.
- La instancia temporal se utilizó exclusivamente con datos sintéticos y se detiene al completar la verificación.
