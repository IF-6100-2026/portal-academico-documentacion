# Validar datos y fechas de una evaluación antes de continuar

**Estado:** propuesta de issue preparada por el agente, pendiente de revisión y publicación humana.

## Relación con el proyecto

Esta tarea implementa una regla de dominio acotada del Portal Académico. Se basa en el segundo criterio de aceptación de [RF-04, versión 9515bd6](https://github.com/IF-6100-2026/portal-academico-documentacion/blob/9515bd6370c090e09bea0dcafc170e632ced7e43/docs/02-requerimientos/funcionales/RF-04-registrar-evaluacion-asignada.md): una entrega anterior a la asignación o un dato obligatorio vacío impide continuar e indica qué corregir.

## Criterios de aceptación

- **CA-01:** Dados nombre, tipo, fecha de asignación e instrucciones válidos, cuando la fecha de entrega sea posterior o igual a la de asignación, entonces la validación devuelve una lista vacía de errores.
- **CA-02:** Dados los campos obligatorios válidos, cuando la evaluación no tenga fecha de entrega porque no aplica, entonces la validación acepta los datos.
- **CA-03:** Dadas ambas fechas, cuando la entrega sea anterior a la asignación, entonces la validación devuelve un error asociado al campo `fechaEntrega` y un mensaje que indica qué corregir.
- **CA-04:** Dados uno o más campos obligatorios ausentes o textos vacíos, cuando se validan los datos, entonces se informa cada campo inválido. Si falta el objeto completo, se informa un error de evaluación sin provocar una excepción inesperada.

## Alcance

El resultado es un validador puro de Java y pruebas JUnit. El tipo de evaluación se valida como texto obligatorio; no se inventa una lista de tipos permitidos. La fecha de entrega es opcional y se permite que coincida con la de asignación, porque RF-04 prohíbe fechas anteriores.

## Fuera de alcance

No se implementan el registro persistente de evaluaciones, la asociación al grupo, la exclusión de estudiantes inactivos, la autorización de docentes, la publicación, una interfaz web ni un endpoint académico. No se presenta como la implementación completa de RF-04 o CU-03.

## Revisión esperada

Una persona revisa la interpretación de los criterios, ejecuta las pruebas y comprueba que el cambio se limite a esta regla. Otra persona debe revisar el PR antes de integrarlo.
