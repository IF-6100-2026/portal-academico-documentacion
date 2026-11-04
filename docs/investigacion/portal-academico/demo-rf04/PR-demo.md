# feat: validar campos y fechas de una evaluación (RF-04)

## Problema y cambio

El segundo criterio de RF-04 requiere señalar datos obligatorios ausentes y fechas de entrega anteriores a la asignación. Este cambio agrega un validador de dominio que devuelve errores por campo, permite fechas iguales y admite una entrega sin fecha cuando no aplica.

La implementación incluye `DatosEvaluacion`, `ErrorValidacion`, `ValidadorEvaluacion` y 17 casos JUnit. No agrega dependencias al backend, endpoints, persistencia, autorización ni asignación a estudiantes. Es una parte de RF-04.

## Verificación realizada por el agente

- Línea base: 5 pruebas aprobadas.
- Backend con el cambio: 22 pruebas aprobadas, sin fallos, errores ni omisiones.
- Proyecto independiente: 17 pruebas aprobadas.
- Consola: 6 escenarios comprobados.
- `git diff --check`: sin problemas de espacios.
- Cambio del backend: 4 archivos nuevos, 148 líneas agregadas.

Entorno: Temurin 21.0.13+9, Maven 3.9.11, JUnit Jupiter 5.12.2, Linux. Base: `9515bd6370c090e09bea0dcafc170e632ced7e43`. Registros en `evidencias/`.

## Revisión humana pendiente

- [ ] Publicar el issue y enlazarlo aquí.
- [ ] Confirmar la interpretación de RF-04 y sus cuatro criterios.
- [ ] Leer la implementación y las aserciones de las pruebas.
- [ ] Ejecutar `mvn test` personalmente y adjuntar la salida.
- [ ] Confirmar que el validador se invocará antes de persistir cuando se implemente esa integración futura.
- [ ] Obtener revisión de otra persona y conservar sus observaciones.

## Uso de IA

Codex generó los cuatro archivos del cambio, la demo y los registros. El usuario autorizó la preparación técnica; no hubo aprobaciones humanas entre pasos ni se ha realizado la revisión independiente. Los fallos de preparación están descritos en la bitácora. El agente no hizo commit, push ni merge. El uso de una cuenta gratuita sigue pendiente de demostración.
