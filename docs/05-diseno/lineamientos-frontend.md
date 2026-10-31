# Lineamientos del frontend

## Carpetas
src/pages contiene ProgresoPage y PublicarCalificacionPage; src/components contiene SelectorEstudiante, CalificacionCard y EstadoCarga; src/hooks contiene useProgreso; src/api contiene progreso.ts y health.ts; src/types contiene ProgresoResponse. Estos elementos académicos se crearán en PC3. El esqueleto actual conserva src/ui/App.tsx y src/api/health.ts.

## Componentes y composición
ProgresoPage es el contenedor: coordina selección y carga. CalificacionCard solo recibe valor, fecha y corregida mediante props y emite onVerHistorial. No consulta la red. Se divide un componente cuando mezcla carga, formulario y presentación o cuando una parte se reutiliza.
Composición: ProgresoPage compone SelectorEstudiante, EstadoCarga y una lista de CalificacionCard; se prefieren props y children a jerarquías de herencia.

## Estado y comunicación
estudianteId y periodo viven en ProgresoPage. SelectorEstudiante recibe value y onChange; los datos bajan por props y los eventos suben por callbacks. El borrador de una calificación vive en su formulario. No se introduce Redux para estos flujos.
Context solo se usará para identidad/estado de sesión compartidos por rutas; no contendrá permisos que sustituyan autorización del backend. No persistir datos académicos sensibles en localStorage por defecto.

## Hooks
useProgreso(estudianteId, periodo) devolverá datos, cargando, error y reintentar. Su efecto cancela la solicitud previa al cambiar de estudiante o desmontar; una respuesta vieja no puede sustituir al estudiante seleccionado. Ejemplo CU-01 A03: lista vacía muestra «Sin publicaciones», no «Error de conexión».

## API y tipado
Solo src/api construye URLs y usa fetch. Los hooks llaman funciones de ese módulo. ProgresoResponse tipa los datos y la frontera HTTP valida respuestas desconocidas; una aserción TypeScript no valida JSON.
401 solicita autenticación; acceso denegado muestra un mensaje sin datos de terceros; un error de red ofrece reintento. No reintentar automáticamente una publicación: CU-04 requiere evitar duplicados.

## Patrones aplicados
Contenedor/presentación separa ProgresoPage de CalificacionCard. Composición reutiliza EstadoCarga en consulta y publicación. Un hook personalizado encapsula el ciclo de carga y cancelación de CU-01. No se añade Strategy u Observer artificialmente: solo se adoptarán si aparece una variación real y se documenta el componente concreto.

## Convenciones y RS-01
TypeScript strict, sin any; componentes PascalCase, hooks useX, funciones camelCase. CSS por componente o módulo, con tokens compartidos para colores y espaciado. Formularios con labels, foco visible, navegación por teclado y errores junto al campo. La carga se anuncia con aria-live y el significado de una nota corregida no depende solo del color.
