# Tareas: registrar estudiante

## Especificación
- [x] Revisar StudentEntity y los campos existentes.
- [x] Escribir RF general, CU-16, criterios y escenarios.
- [x] Marcar supuestos y límites de identidad/duplicados.
- [x] Preparar el plan sin afirmar implementación.

## Publicación y trazabilidad
- [ ] Revisión del equipo.
- [ ] Crear issue CU-16 y enlazar ficha, RF, feature y matriz. GitHub rechazó crear la rama, por eso este avance no publica el issue.
- [ ] Integrar el cambio revisado con un commit de la persona que lo adopte.

## Implementación y verificación
- [x] Crear comando/handler y validaciones para Dirección.
- [x] Añadir endpoint protegido `POST /api/v1/director/students`.
- [x] Añadir formulario React y refrescar el catálogo.
- [x] Registrar quién creó la ficha y cuándo con una nueva migración Liquibase.
- [ ] Definir identidad/duplicados con regla institucional.
- [ ] Añadir/ejecutar verificaciones para criterios y comprobación integrada.
- [ ] Verificar desde clon limpio y actualizar resultados.
- [ ] Crear issue y publicar la rama para revisión.
