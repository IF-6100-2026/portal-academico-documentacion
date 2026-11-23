# Checklist PC3 de implementación y pruebas por caso de uso

Actualizado el 23 de noviembre de 2026 para la integración PC3 de `pc3/core-consulta-vinculos`. El PR #25 se fusionó en el merge `e5701a6` y el tag `pc3` identifica la versión final de entrega. La integración contiene código para los 20 casos vigentes. La marca **verificado** se limita a la evidencia descrita; los `.feature` siguen siendo especificaciones y no se ejecutaron con Cucumber.

| CU | Implementado | Resultado y alcance de la verificación |
|---|---:|---|
| CU-01 Consultar progreso académico | [x] | 31 comprobaciones HTTP heredadas de CU-01/CU-02 con PostgreSQL 17 limpio; revisión visual manual. Véase [resultados](../08-resultados-de-pruebas/resultados-pruebas.md). |
| CU-02 Vincular encargado con estudiante | [x] | Las mismas 31 comprobaciones HTTP cubren altas, permisos por rol/vínculo, solicitudes pendientes e historial. |
| CU-03 Publicar evaluación asignada | [x] | Smoke HTTP: evaluación publicada para estudiantes activos del grupo, avisos a vínculos activos e idempotencia de la publicación repetida. Alternos Gherkin no corridos como suite. |
| CU-04 Publicar calificación | [x] | Smoke HTTP: publicación de calificación dentro de la escala provisional; el guardado se registra con el flujo docente y notifica al encargado vinculado. |
| CU-05 Dar seguimiento a avisos no consultados | [x] | Smoke HTTP: acuse de lectura, dos ciclos de recordatorio con límite de 2, alerta al límite, tercera ejecución con 0/0; finalizar vínculo cerró 14 avisos pendientes y reabrir uno devolvió 404. Antigüedad simulada solo en la BD temporal. |
| CU-06 Comunicarse sobre un estudiante | [x] | Smoke HTTP: mensaje docente→encargado, conversación visible y acuse con fecha. No se verificó correo externo. |
| CU-07 Presentar apelación de una calificación | [x] | Smoke HTTP: apelación por persona encargada vinculada, fecha límite calculada desde configuración y envío duplicado idempotente. |
| CU-08 Supervisar el seguimiento académico | [x] | Smoke HTTP: consulta de Dirección mostró asuntos pendientes y luego cero casos abiertos al cerrar los expedientes de prueba. |
| CU-09 Corregir una calificación publicada | [x] | Smoke HTTP: corrección 85→90 con motivo, versión esperada 0→1 e historial consultable. |
| CU-10 Configurar plazos y recordatorios | [x] | Smoke HTTP: Dirección configuró ventanas de apelación/resolución y frecuencia/límite de recordatorios; el servicio usó esos valores en el flujo probado. |
| CU-11 Bitácora de auditoría | N/A | Retirado antes de PC3; no es uno de los casos vigentes. |
| CU-12 Presentar queja por información no recibida | [x] | Smoke HTTP: queja creada por persona encargada, Dirección solicitó publicación y el docente atendió la solicitud con una publicación coincidente. |
| CU-13 Aportar información a una apelación | [x] | Smoke HTTP: docente añadió un aporte al expediente y quedó asociado con actor y fecha. No se probaron todos los rechazos alternos. |
| CU-14 Registrar resolución de apelación | [x] | Smoke HTTP: Comité registró resolución `UPHELD`; la calificación no se modificó automáticamente. |
| CU-15 Consultar resolución de apelación | [x] | Smoke HTTP: persona encargada consultó la resolución, quedó acuse de lectura y se cerró el expediente. |
| CU-16 Registrar estudiante | [x] | Tres pruebas unitarias del handler y verificación HTTP manual: 401 sin sesión, 201 Dirección, 403 Docente y 400 con grupo vacío. El issue [#16](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/16) se cerró con el [PR #15](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/15). |
| CU-17 Registrar grupo académico | [x] | Smoke HTTP: Dirección creó un grupo con materia, nivel, periodo y código únicos. El modelo es una simplificación provisional. |
| CU-18 Asignar docente a grupo | [x] | Smoke HTTP: Dirección asignó un perfil docente a un grupo y el rol docente pudo operar sobre su asignación. |
| CU-19 Inscribir estudiante en grupo | [x] | Smoke HTTP: Dirección inscribió estudiantes activos; el grupo quedó como fuente de la publicación de CU-03. |
| CU-20 Registrar docente | [x] | Smoke HTTP: Dirección vinculó un usuario docente existente a un perfil; el alta repetida fue idempotente. No crea credenciales. |
| CU-21 Actualizar perfil docente | [x] | Smoke HTTP: Dirección actualizó e inactivó un perfil; se conservaron ambas entradas del historial. |

## Resumen

- Código funcional disponible en esta rama para **20 de 20 casos vigentes**; CU-11 se retiró previamente.
- `mvn test`: 12 pruebas aprobadas, sin fallos, errores ni violaciones de Checkstyle. `npm run build`: correcto. El validador detecta 20 CUs, 20 `.feature`, 103 escenarios escritos y 0 errores estructurales.
- CU-03 a CU-10, CU-12 a CU-15 y CU-17 a CU-21 tuvieron al menos un flujo principal comprobado por HTTP en una base PostgreSQL 17 temporal e independiente. Los escenarios alternos no se ejecutaron en bloque con Cucumber; la evidencia completa y sus límites están en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md).
- El [PR #25](https://github.com/IF-6100-2026/portal-academico-documentacion/pull/25) está fusionado y el tag `pc3` apunta a su merge. Los issues #3 y #4 siguen abiertos como tareas del tablero aunque CU-01 y CU-02 tienen código y evidencia documentada. Las reglas provisionales no equivalen a políticas aprobadas por el profesor o el centro.
