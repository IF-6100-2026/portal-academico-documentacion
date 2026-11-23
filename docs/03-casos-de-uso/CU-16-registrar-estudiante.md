# CU-16-RegistrarEstudiante («Registrar estudiante»)

| Campo | Detalle |
|---|---|
| **Descripción** | Dirección registra una ficha académica básica para incorporar al estudiante al Portal. Dirección es actor provisional. |
| **Estereotipo y paquete** | Épica 1: Registro y consulta de notas y evaluaciones |
| **Precondiciones** | Sesión válida y permiso para gestionar estudiantes. Se cuenta con nombre completo, nivel, grupo y docente. |
| **Postcondiciones** | Ficha activa con UUID. No se crea relación con persona encargada. |
| **Actores** | A-04 Dirección, provisionalmente por continuidad con CU-02 |
| **Relaciones** | Especifica RF-26. CU-02 requiere que la ficha exista. El vínculo no se crea automáticamente. |

## Flujo básico
1. Dirección selecciona Registrar estudiante.
2. El Portal muestra nombre completo, nivel, grupo y docente.
3. Dirección completa los campos y confirma.
4. El Portal valida valores y límites del modelo.
5. El Portal genera UUID, guarda ficha activa y conserva la información del alta.
6. El Portal confirma y muestra la ficha.

## Flujos alternos
- **A01 — Datos faltantes o fuera de límite:** indicar errores y no guardar.
- **A02 — Actor sin permiso:** denegar sin crear ni revelar datos.
- **A03 — Error al guardar:** informar que el alta no se completó.
- **A04 — Posible duplicado:** sin código institucional, no bloquear solo por nombre; definir identidad antes de implementar esa validación.

## Otros campos
- Restricciones: RS-03; usar datos ficticios en demos.
- Prioridad: alta para habilitar registro académico.
- Estado: especificado en avance local SDD; no implementado. Actor y datos son supuestos explícitos.
- RF: [RF-26](../02-requerimientos/funcionales/RF-26-gestion-de-estudiantes.md).
- Pruebas: [CU-16-registrar-estudiante.feature](../04-pruebas/CU-16-registrar-estudiante.feature).
- Issue: pendiente de crear; la integración no permitió publicar la rama de este avance.
