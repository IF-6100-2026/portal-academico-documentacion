# Lineamientos de frontend

- Las páginas coordinan carga y acciones; componentes pequeños presentan datos y emiten eventos mediante callbacks.
- El acceso HTTP vive en `src/api/`, con un tipo TypeScript por respuesta. Ningún componente arma URLs ni llama `fetch` directamente.
- El estado local vive junto a la pantalla que lo necesita. Solo se promueve estado compartido cuando dos flujos reales lo requieran.
- Los errores de red y los estados de carga son visibles y recuperables. Las vistas distinguen falta de conexión de ausencia de información.
- Un acceso futuro al progreso académico deberá consumir DTO publicados por backend; no simulará autorización ni esconderá información como control de seguridad.
- Mantener `strict` y prohibir `any`; nombres de componentes en PascalCase, funciones y variables en camelCase.
- Evitar una biblioteca global de estado hasta que la complejidad del flujo la justifique.
