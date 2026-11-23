# Lineamientos de frontend

## Estructura actual

El cliente usa React 19, TypeScript con `strict` y Vite. `VITE_API_URL` configura la dirección de la API.

| Archivo | Responsabilidad |
|---|---|
| `src/api/academic.ts` | Única capa de acceso a la API: tipos de las respuestas y una función por operación (consultas, registros, publicaciones, avisos, mensajes, quejas y apelaciones). Solo aquí se usa `fetch`, con autenticación Basic y un tiempo límite de 10 s por solicitud. |
| `src/api/health.ts` | Comprobación del estado de la API. |
| `src/ui/App.tsx` | Acceso con las cuentas demo y selección del espacio según el rol. Contiene la consulta de progreso de la persona encargada (con el historial de correcciones) y, para la Dirección, el registro de estudiantes, grupos, docentes, inscripciones y vínculos. |
| `src/ui/GuardianWorkflowPanel.tsx` | Persona encargada: avisos y su lectura, mensajes, quejas, apelaciones y lectura de la resolución. |
| `src/ui/DirectorWorkflowPanel.tsx` | Dirección: plazos y recordatorios, supervisión de pendientes y atención de quejas. |
| `src/ui/TeacherWorkspace.tsx` | Docente: publicación de evaluaciones, registro y corrección de calificaciones, mensajes y aportes a apelaciones. |
| `src/ui/CommitteeWorkspace.tsx` | Comité de Evaluación: expedientes, solicitudes de información y resolución de apelaciones. |
| `src/ui/styles.css`, `src/ui/completion.css` | Estilos compartidos y de los paneles por rol. |

Cada componente recibe las credenciales por props y llama a las funciones de `src/api/academic.ts`; ningún componente arma URLs ni consulta la base de datos. Las credenciales viven solo en el estado de React y no se guardan en `localStorage`.

## Comportamiento por rol

- **Persona encargada:** selecciona un estudiante vinculado y un periodo; solo ve publicaciones y, si una nota se corrigió, la versión anterior. También consulta avisos, envía mensajes y presenta quejas y apelaciones.
- **Docente:** publica evaluaciones para sus grupos, registra y corrige calificaciones con motivo y aporta información a las apelaciones.
- **Comité de Evaluación:** revisa el expediente, pide información y registra la resolución.
- **Dirección:** registra estudiantes, grupos y docentes, administra vínculos con respaldo, configura plazos y supervisa los pendientes.
- Los errores de autorización y los estados sin datos se presentan sin simular información académica.
- Los perfiles demo y contraseñas son para desarrollo local; no son gestión de identidad.

## Accesibilidad y validación

Mantener etiquetas visibles, contraste, foco de teclado, controles con nombres comprensibles y mensajes de error junto a la acción. Los tipos estrictos y `npm run build` verifican el contrato en compilación. El repositorio no incluye pruebas automáticas de componentes ni un ejecutor Gherkin; las funciones nuevas deben añadir pruebas apropiadas antes de declararlas verificadas.
