# Especificación: registrar estudiante

**CU:** CU-16 (nuevo; se conservan los identificadores históricos)  
**RF general:** RF-26 Gestionar el registro de estudiantes  
**Estado:** especificado e implementado localmente; verificación de ejecución pendiente.

## Contexto y actor
El estudiante necesita una ficha en el Portal para organizar el seguimiento. La persona autorizada crea la ficha. Se usa **Dirección (A-04)** provisionalmente, por continuidad con CU-02. El profesor señaló explícitamente que falta el CU de registrar estudiante.

CU-16 crea la ficha. CU-02 vincula después a una persona encargada con respaldo. Son objetivos distintos; registrar al estudiante no crea el vínculo familiar.

## Alcance
Incluye alta, validación de campos existentes, UUID interno y confirmación. Excluye edición/inactivación, autenticación institucional, catálogos normalizados de cursos/docentes y regla legal de duplicidad.

## Supuestos explícitos por el plazo de entrega
- Dirección registra, en continuidad con el módulo CU-02.
- Para compatibilidad con el modelo actual, los datos requeridos son nombre completo, nivel, grupo y docente; el servidor genera UUID y activa la ficha.
- No encontramos un identificador oficial del estudiante. No se recopila documento de identidad ni se declara resuelta la deduplicación.
- La demo usa datos ficticios.

## Precondiciones
1. El actor tiene sesión válida y permiso para gestionar estudiantes.
2. Se dispone de nombre, nivel, grupo y docente.
3. La base de datos está disponible.

## Flujo básico
1. Dirección selecciona Registrar estudiante.
2. El Portal muestra los campos requeridos.
3. Dirección completa y confirma.
4. El Portal valida presencia y límites del modelo.
5. El Portal genera UUID y guarda la ficha activa.
6. El Portal confirma el alta y muestra la ficha.

## Flujos alternos
- **A01 — Datos faltantes o demasiado largos:** indicar errores y no guardar.
- **A02 — Actor sin permiso:** denegar sin crear ni revelar datos.
- **A03 — Falla al guardar:** informar que no se completó y no confirmar éxito.
- **A04 — Posible duplicado:** sin identificador escolar no se puede deduplicar con seguridad; no bloquear solo por nombre.

## Criterios de aceptación
1. Actor autorizado registra los cuatro datos y recibe ficha activa con UUID.
2. Campo faltante o fuera de límite impide guardar e identifica el error.
3. Actor no autorizado recibe denegación y no se crea ficha.
4. El alta no crea vínculo familiar.
5. Un fallo de persistencia nunca aparece como éxito.

Los escenarios están en docs/04-pruebas/CU-16-registrar-estudiante.feature. Son criterios escritos, no pruebas ejecutadas.

## Trazabilidad
RF-26 → CU-16 → feature → issue pendiente → endpoint/formulario local. CU-02 es una operación relacionada y separada. Falta ejecutar la verificación y actualizar el issue al recuperar acceso.

## Riesgos abiertos
Definir quién actualiza/inactiva, el identificador para duplicados y cuáles campos son obligatorios al alta. El modelo actual almacena el docente como texto.
