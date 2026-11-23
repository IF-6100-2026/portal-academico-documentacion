# Resultados de verificaciones

## Qué se verificó

| Comprobación | Resultado | Alcance |
|---|---|---|
| `mvn test` | 5 pruebas Java pasaron con JDK 21; Checkstyle reportó 0 violaciones. | Pruebas existentes de salud/configuración; no son pruebas unitarias completas de cada handler. |
| `npm ci` | Completó sin vulnerabilidades reportadas por npm. | Instalación reproducible de dependencias bloqueadas. |
| `npm run build` | Compilación y verificación TypeScript completadas. | No sustituye pruebas de interacción ni accesibilidad. |
| `scripts/verificar-nucleo-pc3.ps1` | 22 escenarios HTTP pasaron en la API local integrada con PostgreSQL. | CU-01 y CU-02, perfiles y datos demo. No cubre todas las alternativas de sus fichas. |
| Revisión manual de UI | Se inició sesión demo de persona encargada y Dirección; se observaron lista/progreso, borrador oculto, calificación publicada y administración/historial de vínculos. | Inspección manual del navegador local; no es prueba automatizada de interfaz. |
| `scripts/validar_documentacion.py` | 14 CU, 14 archivos feature, 78 escenarios especificados y cero errores estructurales. | Comprueba estructura/enlaces; no ejecuta Gherkin ni valida contenido académico. |

El smoke script es repetible; crea dos vínculos demo, suspende uno y finaliza el otro para comprobar que se retira el acceso. No borra el volumen local, por lo que cada ejecución añade registros de prueba e historial.

## Casos cubiertos por el smoke API

Salud pública; listado vinculado; consulta de periodo; exclusión de borrador; calificación publicada; cuenta sin vínculo; acceso horizontal denegado; periodo vacío; sesión ausente; catálogo de Dirección; rechazo de rol docente; respaldo requerido; estudiante inactivo; creación de vínculo; duplicado activo; disponibilidad del nuevo vínculo; suspensión con motivo; acceso revocado tras la suspensión; creación después de suspender; finalización con autor/fecha; retiro del listado; acceso denegado tras finalizar. El script reporta cada paso individualmente.

## Pendiente de prueba o ejecución

- CU-01 A04: mostrar versiones corregidas, porque el historial aún no está implementado.
- CU-02 A04: flujo de restricción institucional/custodia, pendiente de política.
- Pruebas unitarias de reglas de handler, transacción, errores y carrera de duplicados.
- Todos los `.feature`: requieren Cucumber u otro ejecutor y datos/steps de integración.
- Pruebas automatizadas de frontend, accesibilidad y varios tamaños de pantalla.
- Los CU-03 a CU-10, CU-12 a CU-15 no tienen implementación que pueda ejecutarse.

Estas verificaciones corresponden a la rama de preparación PC3 en el entorno local el 22 de noviembre de 2026. Los 78 escenarios Gherkin siguen siendo especificaciones, no ejecuciones.
