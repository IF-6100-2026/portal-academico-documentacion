# Anexo A. Guía para realizar y documentar la prueba

La ejecución técnica de RF-04 ya está documentada en [demo-rf04](demo-rf04/README.md). Para repetirla, usar JDK 21, `mvn test` desde esa carpeta y `./demo.ps1` en Windows o `bash demo.sh` en Linux. El [issue concreto](demo-rf04/ISSUE.md) sustituye la plantilla genérica de A.2.

El resto de este anexo conserva el protocolo de revisión humana previsto. Esos pasos todavía deben realizarse; no constituyen evidencia de participación humana ya ocurrida.

## A.1 Preparación en PowerShell

1. Confirmar el repositorio de la demo y las personas con acceso. El autor y el revisor deben tener identidades distintas.
2. Comprobar Git y Java:

   ```powershell
   git --version
   java -version
   ```

3. Clonar el repositorio en una carpeta de trabajo. Usar una ruta fuera de carpetas sincronizadas puede evitar interferencias de sincronización:

   ```powershell
   New-Item -ItemType Directory -Force C:\dev
   Set-Location C:\dev
   git clone URL_REAL_DEL_REPOSITORIO
   Set-Location NOMBRE_REAL_DEL_REPOSITORIO
   ```

4. Configurar la identidad para ese repositorio y comprobar el estado antes de cambiar de rama:

   ```powershell
   git config user.name "Nombre Apellido"
   git config user.email "CORREO_DEL_AUTOR"
   git status
   git branch --show-current
   git remote -v
   ```

   No continuar sobre archivos modificados que no se comprendan. No publicar capturas que expongan credenciales en una URL remota.

5. Inspeccionar los archivos del proyecto y escoger el comando real de pruebas. Se usa **uno**, según corresponda:

   | Proyecto | Comando |
   | --- | --- |
   | Gradle con wrapper | `.\gradlew.bat test` |
   | Maven con wrapper | `.\mvnw.cmd test` |
   | Maven sin wrapper | `mvn test`, después de comprobar `mvn -v` |

   Ejecutarlo desde la carpeta que contiene el wrapper o el archivo de construcción. Registrar la salida y el código de terminación. Si la línea base falla, resolverlo o declarar la limitación antes de atribuir fallos a la IA.

6. Instalar la app de escritorio desde la [guía oficial para Windows](https://learn.chatgpt.com/docs/windows/windows-app). La guía ofrece:

   ```powershell
   winget install --id 9PLM9XGG6VKS -s msstore
   ```

   Abrir la app con la cuenta que participará, comprobar el acceso al agente y seleccionar la carpeta del proyecto. La [oferta gratuita](https://learn.chatgpt.com/docs/pricing) está sujeta a despliegue; registrar lo que realmente aparece. No usar una cuenta de pago como evidencia de un plan gratuito.

7. Configurar «Ask for approval» si está disponible y registrar los permisos efectivos. Las [reglas del modo](https://learn.chatgpt.com/docs/permission-modes) no significan una aprobación por cada edición. Las restricciones del entorno pueden variar.
8. Ejecutar una prueba de humo:

   > Explicá qué hace este proyecto y cómo se ejecutan sus pruebas. No modifiqués archivos ni ejecutes acciones de escritura.

   Registrar versión, modelo visible, plan y cuota si se muestra. No asumir que los comandos especiales del CLI están disponibles en la app.

## A.2 Plantilla del issue

```markdown
## Objetivo
[COMPLETAR: comportamiento útil y concreto del proyecto]

## Contexto
[COMPLETAR: clase, servicio o módulo involucrado]

## Criterios de aceptación
- [ ] CA-01: Dado [estado], cuando [acción], entonces [resultado observable].
- [ ] CA-02: Dado [caso límite], cuando [acción], entonces [resultado observable].
- [ ] CA-03: Dado [entrada inválida, si aplica], cuando [acción], entonces [resultado esperado].

## Fuera de alcance
[COMPLETAR: funcionalidades, dependencias o refactorizaciones excluidas]

## Verificación
[COMPLETAR: comando real y prueba prevista para cada criterio]
```

Elegir un caso real del proyecto. Los nombres de ejemplo de un borrador no identifican por sí solos una funcionalidad implementada.

## A.3 Rama y reglas

Después de comprobar que el árbol de trabajo esté limpio, sustituir los nombres:

```powershell
git switch RAMA_BASE_REAL
git pull --ff-only
git rev-parse HEAD
git switch -c feature/NUMERO-descripcion
```

El autor aprueba un `AGENTS.md` que incluya como mínimo:

```markdown
# Reglas de esta prueba
- Usar las versiones y convenciones existentes del proyecto.
- Ejecutar las pruebas con: [COMPLETAR: comando real].
- Proponer un plan y esperar su revisión antes de implementar.
- Implementar un paso del plan por turno y detenerse.
- Relacionar las pruebas con los criterios de aceptación.
- No agregar dependencias ni ampliar el alcance sin consultarlo.
- No hacer commit, push, aprobación de PR ni merge.
- No introducir contraseñas, tokens ni datos personales reales.
- Explicar cualquier fallo de ejecución y no declarar pruebas aprobadas sin salida verificable.
```

Estas instrucciones guían al agente, pero el equipo debe observar su cumplimiento. Si se modifican, iniciar una sesión que cargue la versión aprobada y registrar esa versión. Usar `/init` solo si la interfaz lo admite; se puede solicitar el archivo con lenguaje natural.

## A.4 Prompts y controles

| Momento | Instrucción y acción humana |
| --- | --- |
| V1 | «Leé este issue: [texto]. Proponé un plan numerado, los archivos afectados y las pruebas por criterio. Todavía no escribás código». Revisar alcance y criterios. |
| V2 | «Implementá únicamente el paso 1 y detenete. Incluí las pruebas correspondientes». Revisar `git status --short`, `git diff` y los archivos nuevos. |
| Continuación | «El paso fue revisado. Continuá únicamente con el siguiente paso y detenete». Registrar cada autorización. |
| V3 | Ejecutar personalmente el comando real de pruebas. Comprobar también el significado de las aserciones y los casos límite. |
| Corrección | «La prueba [nombre] falla con este error: [salida]. Explicá la causa y proponé la corrección dentro del alcance». Registrar antes y después. |
| V4 | «Revisá los cambios contra [rama base]. No modifiqués archivos. Enumerá los problemas, su evidencia y gravedad». Usar `/review` solo si está disponible. |
| V5 | El revisor humano obtiene la rama, ejecuta las pruebas y lee el diff completo. Registra aprobación o cambios solicitados. |

`git diff` no muestra el contenido de archivos nuevos sin seguimiento. Para incluirlos en la inspección, leerlos o agregarlos **de forma explícita** al área de preparación. No usar `git add -A` sin haber revisado qué archivos incluiría.

Ejemplo de commit humano, después de V3:

```powershell
git add RUTA_DE_ARCHIVO_REVISADO RUTA_DE_PRUEBA_REVISADA
git diff --staged
git commit -m "feat: descripcion real del cambio" -m "Generated-by: Codex (OpenAI)"
git diff --stat origin/RAMA_BASE_REAL...HEAD
git push -u origin feature/NUMERO-descripcion
```

La persona revisora puede usar:

```powershell
git fetch origin
git switch feature/NUMERO-descripcion
git status
```

Si la rama aún no existe localmente, crear el seguimiento de la rama remota. Después ejecutar el comando de pruebas del proyecto y revisar el PR.

## A.5 Plantilla del PR de la demo

```markdown
## Problema y comportamiento
[COMPLETAR: resumen y enlace al issue]

## Criterios de aceptación
- [ ] CA-01
- [ ] CA-02
- [ ] CA-03, si aplica

## Participación de IA
Herramienta, modelo visible y modalidad:
Archivos o partes generadas:
Correcciones humanas:

## Verificación
Comando y resultado:
Pruebas por criterio:
Revisión de dependencias:
Revisión humana:

## Evidencia y errores
Bitácora:
Capturas o registros:
Errores de la IA y correcciones:

## Alcance pendiente
[COMPLETAR: pendientes reales o justificación de que no hay]
```

## A.6 Incidentes y desviaciones

- **No aparece el acceso gratuito:** registrar la limitación. Elegir otra herramienta gratuita compatible con el enunciado o consultar al profesor antes de cambiar el diseño.
- **Se agota la cuota:** registrar la hora y detener la medición de ejecución. Anotar la espera, reducción de alcance o cambio autorizado. Un cambio de cuenta, plan o modelo debe declararse.
- **El entorno falla:** distinguir un error previo del proyecto de un error introducido por el agente.
- **Se solicita permiso:** comprobar la operación concreta y registrar la decisión.
- **El agente incumple una regla:** detener el paso, inspeccionar el diff y registrar lo sucedido.
- **La revisión no termina antes de entregar:** informar el estado real del PR. No sustituirlo por una aprobación o merge ficticios.
