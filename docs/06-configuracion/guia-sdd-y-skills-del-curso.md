# Guía de clase: skills y Spec-Driven Development

## Material localizado
La organización pública IF-6100-2026 tiene el repositorio [public](https://github.com/IF-6100-2026/public), con [IA, sesión A](https://github.com/IF-6100-2026/public/blob/main/ia/sesion-a/index.html) y [IA, sesión B](https://github.com/IF-6100-2026/public/blob/main/ia/sesion-b/index.html), además de figuras sobre skills y SDD.

En los cuatro repositorios públicos revisados no se encontró una carpeta con múltiples archivos SKILL.md, como .agents/skills/, ni un repositorio público separado de skills del profesor. public contiene material educativo HTML: explica qué es una skill y presenta ejemplos visuales, pero no es un paquete instalable. Podría existir material privado o compartido por otro canal que no está visible en los repos públicos.

La sesión B explica una skill como instrucciones y recursos empaquetados en SKILL.md, cargados bajo demanda. AGENTS.md o CLAUDE.md contiene reglas permanentes del proyecto. No declaramos que importamos una skill que no encontramos.

## Flujo SDD de la sesión B
1. **Constitution:** stack, convenciones y límites del proyecto.
2. **Specify:** qué se necesita y por qué, con criterios de aceptación.
3. **Plan:** cómo implementarlo respetando las reglas.
4. **Tasks:** unidades pequeñas, ordenadas y verificables.
5. **Implement:** construir y revisar el diff.

El material enlaza actor, objetivo, precondiciones, flujos, reglas y criterios con spec.md; diseño con plan.md; backlog con tasks.md. Recomienda revisar consistencia en cada transición. Spec Kit puede organizar archivos; la clase prioriza el método.

## Aplicación al Portal
- [Constitución](../../.specify/memory/constitution.md)
- [Spec CU-16](../../specs/001-registrar-estudiante/spec.md)
- [Plan](../../specs/001-registrar-estudiante/plan.md)
- [Tasks](../../specs/001-registrar-estudiante/tasks.md)

CU-16 tiene implementación local en backend y frontend; todavía no se han ejecutado verificaciones ni se ha publicado la rama. Los supuestos están marcados. Fuente del curso: [IF-6100-2026/public, sesión B](https://github.com/IF-6100-2026/public/blob/main/ia/sesion-b/index.html), atribuida a MSc. Leonardo Camacho Navarro, UCR.
