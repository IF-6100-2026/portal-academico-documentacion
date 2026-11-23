# Guion de demostración PC3

Duración sugerida: 6–8 minutos. Ejecutar con los datos ficticios que carga Liquibase; no ingresar información escolar real.

## Preparación

1. Seguir [ambiente de desarrollo](../06-configuracion/ambiente-de-desarrollo.md): iniciar PostgreSQL, API y frontend.
2. Abrir <http://localhost:5173>. Usar estas cuentas de demostración ficticias:
   - Persona encargada: `encargada.demo` / `demo-familiar-2026`
   - Sin vínculos: `sin-vinculos.demo` / `demo-sin-vinculos-2026`
   - Dirección: `direccion.demo` / `demo-direccion-2026`
   - Docente (para comprobar rechazo de rol): `docente.demo` / `demo-docente-2026`
3. Verificar la API en <http://localhost:8080/api/v1/health>.

## 1. Consulta protegida de progreso — CU-01

1. Iniciar como persona encargada (`encargada.demo`).
2. Mostrar las dos personas estudiantes vinculadas, las publicaciones recientes y el contador de avisos sin consulta.
3. Abrir **Sofía Vargas Mora (dato ficticio)** en el periodo `2026-II`.
4. Mostrar evaluaciones publicadas y nota vigente `85`; abrir **Ver versión anterior** para enseñar la nota `70`, el motivo, quién y cuándo se corrigió.
5. Cambiar el periodo a `2026-I`: debe aparecer la lista vacía. Volver a `2026-II`.
6. Intentar consultar a Mateo con esta cuenta si se demuestra por API; la solicitud debe recibir acceso denegado porque solo existe una relación pendiente.
7. Cerrar sesión y mostrar `sin-vinculos.demo`: no debe listar estudiantes.

## 2. Administración de relaciones — CU-02

1. Iniciar como Dirección (`direccion.demo`) y mostrar el catálogo con relaciones activas, pendientes y su historial.
2. Ubicar la solicitud ficticia de `encargada.demo` para Mateo: estado pendiente, motivo y responsable. En la sesión de esa persona encargada, Mateo no se lista ni se puede consultar.
3. En el formulario, seleccionar a `sin-vinculos.demo` y Mateo, ingresar una referencia ficticia y crear una relación. Mostrar que queda activa y que el estudiante aparece en la siguiente consulta.
4. Suspenderla con un motivo; volver a la sesión de la persona encargada y mostrar que el acceso ya no aparece.
5. Si hay tiempo, crear otra relación para el mismo par después de la suspensión y finalizarla; el historial debe conservar estado, responsable, fecha y motivo.

## Cierre y límites

- La demo valida roles, vínculo activo, borradores ocultos, publicación, historial de corrección y retiro de acceso.
- Solicitudes `PENDING` no conceden acceso ni se pueden convertir en activas mediante una segunda alta. El prototipo no decide cómo Dirección revisa o aprueba la solicitud; falta confirmar el procedimiento institucional.
- CU-01 + CU-02 son la propuesta ejecutada por el equipo; no hay confirmación docente verificable sobre la selección del núcleo.
- Los datos son ficticios. Los demás casos de uso están especificados, no implementados en el código funcional.
