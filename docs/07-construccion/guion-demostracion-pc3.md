# Guion de defensa y demostración PC3

Duración sugerida: **12–15 minutos**. Usar exclusivamente datos ficticios. La demostración debe seguir la dependencia natural: Dirección organiza el grupo → docente publica → persona encargada consulta y responde → Comité resuelve → Dirección supervisa.

## Preparación

1. Levantar PostgreSQL, API y frontend siguiendo [ambiente de desarrollo](../06-configuracion/ambiente-de-desarrollo.md).
2. Abrir <http://localhost:5173> y comprobar <http://localhost:8080/api/v1/health>.
3. Cuentas locales ficticias:
   - Persona encargada: `encargada.demo` / `demo-familiar-2026`
   - Sin vínculos: `sin-vinculos.demo` / `demo-sin-vinculos-2026`
   - Dirección: `direccion.demo` / `demo-direccion-2026`
   - Docente: `docente.demo` / `demo-docente-2026`
   - Comité: `comite.demo` / `demo-comite-2026`
4. La UI conserva cambios en la base. Para una defensa repetible, use una base local recién creada y datos ficticios; evite crear expedientes repetidos durante la presentación.

## 1. Preparar la oferta académica — Dirección (CU-17 a CU-21)

1. Iniciar como Dirección.
2. Mostrar el panel de plazos y la supervisión general.
3. En **Registrar perfil docente**, asociar `docente.demo` con un nombre visible. El usuario docente ya existe: el caso no crea contraseñas.
4. En **Registrar grupo académico**, crear una oferta ficticia (por ejemplo, Matemática, 4.º, grupo A, periodo 2026-II).
5. Asignar el perfil docente a ese grupo.
6. Inscribir a Sofía Vargas Mora (dato de demostración) en el grupo.
7. Mostrar las listas de perfil, asignación y matrícula con su responsable/fecha. Explicar que la matrícula académica no crea un vínculo familiar.

## 2. Publicar y corregir información — Docente (CU-03, CU-04, CU-06, CU-09, CU-13)

1. Cerrar sesión e ingresar como `docente.demo`.
2. Publicar una evaluación para el grupo asignado. Confirmar la fecha, instrucciones y coincidencia con el cuaderno.
3. En el mismo panel, escoger la evaluación, registrar una calificación y confirmar su publicación. La demo usa la escala provisional 0–100.
4. Volver a guardar la calificación con un motivo para mostrar que el Portal conserva versiones anteriores.
5. Enviar un mensaje a `encargada.demo` sobre la estudiante.
6. Si hay una apelación presentada, elegirla y añadir un aporte al expediente. La nota publicada no cambia con ese aporte.

## 3. Consultar, leer y solicitar seguimiento — Persona encargada (CU-01, CU-05 a CU-07, CU-12, CU-15)

1. Cerrar sesión e ingresar como `encargada.demo`.
2. Abrir a Sofía y mostrar el progreso del periodo; explicar que la pantalla oculta borradores y muestra la versión anterior de una nota corregida.
3. Abrir un aviso y marcar un mensaje como leído para mostrar los acuses con fecha.
4. Responder al mensaje docente si aplica.
5. Crear una queja por información no recibida (materia y periodo coincidentes con el grupo).
6. Presentar una apelación de la calificación publicada, indicando puntos, motivo y referencia ficticia.
7. Después de la resolución del Comité, volver a **Consultar resolución** para cerrar el expediente con un acuse.

## 4. Atender y resolver — Dirección y Comité (CU-05, CU-08, CU-10, CU-12 a CU-15)

1. Como Dirección, configurar plazo para apelar/resolver, intervalo y máximo de recordatorios. Mostrar que se guarda una nueva versión de la configuración.
2. Atender la queja: elegir “No publicada; solicitar publicación”, redactar respuesta y señalar `docente.demo`. Al publicarse el contenido coincidente, la queja se marca atendida.
3. Revisar **Resumen de atención**: pendientes académicos, apelaciones y quejas.
4. Cerrar sesión e ingresar como Comité. Revisar la apelación, agregar un aporte o pedir información y registrar una resolución fundamentada.
5. Volver como persona encargada para consultar la resolución y dejar el expediente cerrado.

## 5. Cerrar con permisos y límites

- Como `sin-vinculos.demo`, mostrar que no hay estudiantes que consultar. En Dirección, mostrar que crear una ficha, inscribirla y crear un vínculo son acciones distintas.
- Un docente solo ve grupos asignados; Dirección administra catálogos; Comité registra resoluciones; la persona encargada solo accede a estudiantes con vínculo activo.
- La revisión automática de recordatorios se dispara desde Dirección. El flujo por intervalos se probó en una BD temporal adelantando solo el reloj de los registros; no hay correo externo ni scheduler configurado.
- El sistema es un prototipo de demostración. Escala 0–100, plazos, estados, identidad docente, modelo académico y políticas de acceso deben confirmarse con el centro/profesor antes de usar información real.
- Los 103 escenarios Gherkin son criterios escritos, no pruebas ejecutadas con Cucumber. Los resultados concretos por caso están en [resultados de pruebas](../08-resultados-de-pruebas/resultados-pruebas.md).
