# Aplicar las correcciones de integración

La conexión GitHub permitió leer los repositorios oficiales pero rechazó crear árboles Git en Frontend (`403 Resource not accessible by integration`). Por eso no se publicaron ramas de código mediante esta conexión. Los parches incluyen los cambios revisados y las pruebas del backend.

Cada parche corresponde a un único commit y se aplica en su repositorio, con el árbol limpio. No aplicarlo en Documentación.

## Frontend

Base revisada: `29bc1c45559d8d4745f42aafab576ac90fbddbd3`.

```bash
git switch -c fix/integracion-local
git am /ruta/al/fork/integracion/patches/frontend.patch
npm ci
npm run build
git push -u origin fix/integracion-local
```

## Backend

Base revisada: `a6fe04e0777b7266d2a3152020dab9930777fe03`.

```bash
git switch -c fix/integracion-local
git am /ruta/al/fork/integracion/patches/backend.patch
mvn test
git push -u origin fix/integracion-local
```

Después abrir un PR de cada rama hacia `main`. Si hay cambios nuevos incompatibles, resolver el conflicto y revisar antes de continuar; `git am --abort` cancela la aplicación. Usar una cuenta autorizada para publicar en la organización.

La prueba integrada con PostgreSQL queda pendiente; seguir la guía de ambiente del PC2 después de aplicar ambos parches.
