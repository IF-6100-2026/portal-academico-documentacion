# Lineamientos de frontend

## Estructura actual

El cliente usa React 19, TypeScript estricto y Vite. `src/api/academic.ts` centraliza tipos y solicitudes; `src/ui/App.tsx` contiene el flujo de acceso demo, consulta de progreso y administración de vínculos. `src/ui/styles.css` define los estilos compartidos. `VITE_API_URL` configura la dirección de Spring.

## Comportamiento del núcleo

- La persona encargada puede seleccionar un estudiante vinculado y un periodo; solo se muestran publicaciones.
- La Dirección puede buscar perfiles demo, ver vínculos y su historial, crear vínculos con respaldo y cambiar el estado con motivo.
- Los errores de autorización y los estados sin datos se presentan sin simular información académica.
- Los perfiles demo y contraseñas se presentan para desarrollo local; no son gestión de identidad.

## Accesibilidad y validación

Mantener etiquetas visibles, contraste, foco de teclado, controles con nombres comprensibles y mensajes de error junto a la acción. Los tipos estrictos y `npm run build` verifican el contrato en compilación. El repositorio no incluye pruebas automáticas de componentes ni un ejecutor Gherkin; las funciones nuevas deben añadir pruebas apropiadas antes de declararlas verificadas.
