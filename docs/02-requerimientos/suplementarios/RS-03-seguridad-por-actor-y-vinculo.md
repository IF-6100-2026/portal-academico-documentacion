# RS-03 — Seguridad por actor y vínculo

| Campo | Detalle |
|---|---|
| Categoría | Seguridad · Confidencialidad e integridad (ISO/IEC 25010) |
| Actores afectados | Todos |
| Equivale en el PC1 a | RS-SEC-01, RS-SEC-02 y RS-SEC-04; absorbe RF-01 (autenticar), RF-02 (permisos por rol), RF-04 (restringir al vínculo activo) y RF-34 (cuentas y roles) |

## Necesidad

El Portal maneja información académica de menores. Hoy la información llega a quien recibe el cuaderno o el reporte (E-12); en el Portal, cada persona debe ver solo lo que le corresponde según su papel y su relación con el estudiante. La autenticación y los permisos no son la necesidad de los actores, sino la condición para atenderla con seguridad.

## Requerimiento

Cada operación del Portal deberá comprobar la identidad de quien la solicita, su rol y su relación con el estudiante o el caso:

| Actor | Puede acceder a |
|---|---|
| Persona encargada legal | Estudiantes con vínculo activo; sus propios mensajes, quejas y apelaciones. |
| Docente | Grupos y materias asignados; mensajes y apelaciones de sus evaluaciones. |
| Comité de Evaluación | Apelaciones asignadas, con su expediente. |
| Dirección | Todo el nivel de primaria en modo de supervisión; vínculos, plazos y quejas. No cambia calificaciones ni resoluciones del Comité. |

## Criterio medible

- El **100 %** de las solicitudes de información académica comprueba sesión, rol y vínculo o asignación. Se verifica con una prueba positiva y una negativa por actor.
- **0** datos expuestos en pruebas de acceso horizontal: una persona encargada que manipula el identificador de otro estudiante no recibe ningún dato de él, ni la confirmación de que existe.
- Un vínculo suspendido o finalizado deja de dar acceso en la **siguiente solicitud**.
- Una docente solo ve los grupos y materias que tiene asignados en el periodo vigente; al cambiar la asignación, el cambio rige en la siguiente solicitud.

> **Fuera de este RS.** El cifrado de la comunicación, el almacenamiento de contraseñas y la expiración de sesiones los resuelve el framework de seguridad. Se definen en el diseño (`docs/05-diseno/`) y no se especifican como requerimiento.

## Método de verificación

Matriz de pruebas por actor con un caso permitido y uno denegado en cada operación. Corresponde a los escenarios `A01` de cada `.feature` y a `TP-CU01-A02`.

## Efecto en los casos de uso

En **todos** los CU hay un paso del flujo básico en que el Portal valida actor, rol y vínculo, y un flujo alterno de acceso denegado que no revela información:

| CU | Paso de validación | Flujo alterno |
|---|---|---|
| [CU-01](../../03-casos-de-uso/CU-01-consultar-progreso-academico.md) | 2 y 5 | A01, A02 |
| [CU-02](../../03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) | 2 | A01 |
| [CU-03](../../03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) | 2 | A01 |
| [CU-04](../../03-casos-de-uso/CU-04-publicar-calificacion.md) | 2 | A01 |
| [CU-05](../../03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | 3 | A02 |
| [CU-06](../../03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | 2 | A01 |
| [CU-07](../../03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | 2 | A01 |
| [CU-08](../../03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) | 2 | A01 |
| [CU-09](../../03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) | 2 | A01 |
| [CU-10](../../03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md) | 2 | A01 |
| [CU-12](../../03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | 2 | A01 |

Las cuentas y los grupos se cargan antes de operar el Portal. Su administración no es un caso de uso del MVP; se trata en los lineamientos de base de datos y de backend.
