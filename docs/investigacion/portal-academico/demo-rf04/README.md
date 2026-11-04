# Demo: validación de evaluaciones del Portal Académico

Implementación parcial de **RF-04**: valida los campos obligatorios y que la fecha de entrega no sea anterior a la asignación. La fecha de entrega puede omitirse. No registra datos, no expone un endpoint y no implementa el caso de uso completo.

**Estado:** código y ejecución técnica preparados por Codex el 4 de noviembre de 2026. Revisión humana, acceso gratuito y publicación del PR pendientes.

## Ejecutar en dos minutos

Se necesita **JDK 21**, con `java` y `javac` disponibles. La consola no necesita Maven, Spring ni una base de datos.

```powershell
java -version
javac -version
.\demo.ps1
```

En Linux o macOS:

```bash
bash demo.sh
```

La salida muestra seis escenarios, incluido el mensaje que corrige una fecha invertida. Termina con «6 escenarios comprobados». Se ejecutó el script de Bash en Linux; el script de PowerShell está preparado, pero todavía debe comprobarse en Windows.

## Ejecutar las pruebas

Instalar Maven y confirmar que `mvn -v` muestra Java 21. Desde esta carpeta:

```powershell
mvn test
```

La primera ejecución necesita descargar las dependencias. Se reutiliza `spring-boot-starter-test` del proyecto; no hay dependencias de producción. Resultado registrado: **17 pruebas, 0 fallos, 0 errores y 0 omitidas**, con JUnit Jupiter 5.12.2. Los mismos archivos, añadidos a una copia del backend, pasaron sus **22 pruebas**: 17 nuevas y 5 existentes.

## Qué mostrar en la exposición

1. Abrir [ISSUE.md](ISSUE.md): cuatro criterios y exclusiones.
2. Ejecutar la consola y señalar el caso de fecha anterior y su mensaje.
3. Abrir `ValidadorEvaluacion.java`: el agente generó la regla; el ingeniero debe comprobar la interpretación del requisito.
4. Ejecutar `mvn test` y abrir una prueba asociada al criterio mostrado.
5. Mostrar [la evidencia](evidencias/README.md) y explicar los problemas del entorno, la corrección y las actividades humanas pendientes.

La exposición puede usar los registros adjuntos si no hay conexión para descargar dependencias. Son salidas reales, no capturas recreadas.

## Aplicar el cambio al proyecto del curso

El parche se preparó contra el commit `9515bd6370c090e09bea0dcafc170e632ced7e43`. Agrega **4 archivos y 148 líneas**. No modifica `pom.xml` ni el código existente.

En una copia limpia del repositorio del curso, después de crear una rama de trabajo:

```powershell
git apply --check RUTA_AL_ARCHIVO/cambio-portal-academico.patch
git apply RUTA_AL_ARCHIVO/cambio-portal-academico.patch
Set-Location backend
mvn test
```

Si la comprobación del parche falla, revisar los cambios de la rama base antes de aplicarlo. [PR-demo.md](PR-demo.md) contiene el texto para el PR. Los commits, su publicación y la aprobación corresponden al equipo.

## Para el repositorio vacío del grupo

Este paquete es un proyecto independiente que puede subirse al repositorio de la demo de Josué. El equipo decide si mantiene allí la muestra o usa el parche en el repositorio del curso. En ambos casos debe conservar el issue, la prueba humana y el PR. El repositorio de entrega académica y su directorio oficial todavía deben confirmarse.

## Autoría y límites

Codex propuso el caso, redactó los criterios, generó todo el código nuevo y las pruebas, ejecutó los comandos y preparó estos materiales. No se han registrado correcciones de código ni aprobaciones de los integrantes. La misma IA creó las pruebas y la implementación; una persona debe evaluar ambas.

Esta ejecución en ChatGPT Work **no demuestra uso de una cuenta gratuita**. Para satisfacer ese requisito, realizar y registrar una sesión real con una cuenta que disponga de la modalidad permitida. No alcanza con cambiar la declaración de uso.
