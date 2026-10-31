# Cambios del PC1 al PC2

Este archivo registra cada observación del resultado del PC1, qué se cambió y en qué archivo. Al final están las equivalencias entre los identificadores del PC1 y los del PC2. Los artefactos originales del PC1 se conservan en el tag `pc1` del repositorio.

## Observaciones atendidas

La tabla sigue el [resultado del PC1](#resultado-del-pc1): primero las cuatro observaciones prioritarias, en el orden del profesor, y después los comentarios de cada rubro que no repiten una observación prioritaria. Ninguna observación se dejó sin atender.

| # | Observación del PC1 | Qué se cambió | Archivo(s) |
|---|---|---|---|
| 1.1 | **BPMN — eventos de fin.** Varios eventos de cierre, y muchos no indican el estado final; se recomienda un solo evento de fin. | El proceso principal tiene un único evento de fin con estado: «Comunicación del periodo cerrada». El subproceso termina en «Apelación resuelta y comunicada». | [seguimiento-academico-actual.bpmn](01-inicio/bpmn/seguimiento-academico-actual.bpmn), [resolver-apelacion-actual.bpmn](01-inicio/bpmn/resolver-apelacion-actual.bpmn) |
| 1.2 | **BPMN — compuertas.** Cerrar cada compuerta divergente con su convergente. | Las tres decisiones del proceso principal («¿Está de acuerdo con la calificación?», «¿Terminó el periodo?» y «¿Lo entrega a tiempo?») y la compuerta paralela del subproceso tienen su compuerta de cierre. | Los dos `.bpmn` |
| 1.3 | **BPMN — horizontalidad.** El camino principal pierde la horizontalidad. | El camino principal corre de izquierda a derecha. Las excepciones (apelación y queja) bajan a una segunda fila del carril y vuelven a la línea principal. | [seguimiento-academico-actual.bpmn](01-inicio/bpmn/seguimiento-academico-actual.bpmn) |
| 1.4 | **BPMN — subprocesos.** Un solo diagrama con toda la información es difícil de interpretar. | La apelación se separó en el subproceso SP-01 «Resolver la apelación», con su propio diagrama y carriles para el Comité de Evaluación, la docente y la Dirección. | [resolver-apelacion-actual.bpmn](01-inicio/bpmn/resolver-apelacion-actual.bpmn) |
| 1.5 | **BPMN — marcas [PV] y «confirmado».** El diagrama refleja lo que se hace hoy; no debe haber nada pendiente ni por confirmar. | El BPMN se rehízo solo con lo que confirmó la entrevista a la directora y lo confirmado antes por la Dirección (E-01 a E-14). No quedan marcas [PV] ni «confirmado», y las anotaciones solo enuncian hechos con su evidencia. Cuatro actividades [PV] del PC1 se resolvieron con evidencia y dos se retiraron. | [trazabilidad-bpmn-rf.md](01-inicio/bpmn/trazabilidad-bpmn-rf.md#resolución-de-las-marcas-pv-del-pc1) |
| 2.1 | **RF — decisiones de diseño.** «Autenticar a un usuario» y «Aplicar permisos por rol» no atacan el problema. | Salen del catálogo de RF, junto con RF-04 (restringir al vínculo) y RF-34 (cuentas y roles). La seguridad pasa a RS-03 y el mecanismo, al diseño. | [RS-03](02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md), [equivalencias de RF](#rf-del-pc1-al-pc2) |
| 2.2 | **RF — «usuario» en vez de actores.** RF-01 dice «Usuario registrado» y no se sabe quién es. | Cada uno de los 25 RF nombra un actor concreto (persona encargada legal, docente, Comité de Evaluación o Dirección) y su necesidad en el proceso actual. Incluso el recordatorio (RF-13) se redacta desde la persona encargada, aunque lo dispare el servicio programado. | [02-requerimientos/funcionales/](02-requerimientos/funcionales/) |
| 2.3 | **RF — épicas orientadas al problema** (por ejemplo, registro de notas, comunicación docente–encargado y seguimiento de tareas). | Los RF se agrupan en tres épicas: **1. Registro y consulta de notas y evaluaciones** (incluye las tareas y evaluaciones asignadas), **2. Comunicación entre la escuela y la persona encargada** (avisos, lectura, mensajes y quejas) y **3. Seguimiento de apelaciones y pendientes**. La tercera responde al proceso real de apelación con el Comité de Evaluación (E-07, E-11). La épica aparece en cada RF y en cada issue. | [problema-y-vision.md](01-inicio/problema-y-vision.md#épicas), [trazabilidad-rf-cu.md](02-requerimientos/trazabilidad-rf-cu.md) |
| 3.1 | **RS — reducir a los tres que impactan el diseño, profundizarlos y reflejar su efecto en los CU.** | Quedan RS-01, RS-02 y RS-03, cada uno con necesidad, criterio medible, método de verificación y una tabla con su efecto en cada CU. Los demás RS del PC1 se reubican en RF o CU, o salen del catálogo. Todos los valores nuevos se marcan como propuesta del equipo. | [suplementarios/](02-requerimientos/suplementarios/), [equivalencias de RS](#rs-del-pc1-al-pc2) |
| 3.2 | **RS — usabilidad:** los encargados tienen poca experiencia con la tecnología. | RS-01 define cuatro tareas que 4 de 5 personas encargadas con poca experiencia deben completar sin ayuda y en un tiempo máximo, la última nota a 3 toques o menos y uso desde 360 px. Afecta CU-01, CU-05, CU-07 y CU-12. | [RS-01](02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md) |
| 3.3 | **RS — trazabilidad:** cómo se da cuenta el docente de que el encargado leyó el mensaje. | RS-02 registra la primera lectura de cada publicación, aviso y mensaje, y se la muestra a la docente por estudiante (y a quien envió cada mensaje) en 1 minuto o menos. Reemplaza la firma en el cuaderno (E-04). Se agregó el paso 11 en CU-03 y CU-04, y la lectura en el paso 7 de CU-06, con sus escenarios. | [RS-02](02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RF-12](02-requerimientos/funcionales/RF-12-registrar-acuse-de-consulta.md), [RF-14](02-requerimientos/funcionales/RF-14-intercambiar-mensajes.md), [CU-03](03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md), [CU-04](03-casos-de-uso/CU-04-publicar-calificacion.md), [CU-06](03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) |
| 3.4 | **RS — seguridad:** cada actor accede solo a la información que le corresponde. | RS-03 define qué ve cada actor según su rol y su vínculo o asignación, con pruebas de acceso horizontal. Cada CU tiene un paso de validación y un flujo alterno de acceso denegado. | [RS-03](02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md) |
| 4.1 | **Consistencia entre el problema y los casos de uso — elegir una de dos vías.** | **Vía elegida: ajustar los casos de uso al problema.** Antes se actualizó el problema con la entrevista a la directora, que en el PC1 había quedado como anexo, así que no se amplió para justificar casos de uso. Cada problema (P-01 a P-05) cita evidencia, y cada CU traza a un problema mediante sus RF. Después se ajustaron los casos de uso (puntos 4.2 a 4.4). | [problema-y-vision.md](01-inicio/problema-y-vision.md#problemas) |
| 4.2 | **UC-06 y UC-07 detallados por completo,** cuando el problema trataba como hipótesis los plazos, estados y la figura mediadora. | Ahora tienen evidencia: el cuaderno de recados (E-07), la mensajería pedida (E-10) y el caso real de apelación (E-11). CU-07 sigue ese caso: el Comité resuelve con aportes de la docente y la Dirección. Se eliminaron los plazos inventados (10 y 3 días hábiles) y la figura mediadora; el plazo lo configura la Dirección porque «depende de la situación» (E-08). | [CU-06](03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md), [CU-07](03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) |
| 4.3 | **UC-08, UC-10 y UC-11 nacen de la solución.** | **UC-08** pasa a CU-08 «Supervisar el seguimiento académico»: responde a P-05 y a que la Dirección supervisa (E-06); el rendimiento agregado se retira. **UC-10** pasa a CU-10 «Configurar plazos y recordatorios»: responde a que el plazo depende de la situación (E-08); la administración de cuentas y catálogos sale de los CU. **UC-11** se retira; la necesidad pasa a RS-02. | [CU-08](03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md), [CU-10](03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md), [equivalencias de CU](#casos-de-uso) |
| 4.4 | **Las quejas de los encargados no tienen ningún caso de uso.** | Nuevo CU-12 «Presentar queja por información no recibida», atendido por la Dirección, con RF-15 y RF-16, las actividades AC-10 y AC-11 del BPMN, sus flujos en el diagrama de contexto, su `.feature` y su issue. CU-12-A04, CU-06-A05 y CU-06-A06 lo distinguen de la apelación de una nota. | [CU-12](03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) |
| R3 | **Trazabilidad BPMN↔RF:** unos 24 de los 35 RF se trazan a un punto por validar o no tienen actividad en el proceso actual. | Cada RF cita al menos una actividad del BPMN (AC-01 a AC-11 o SP-01) y una evidencia E-xx. La tabla inversa muestra que todas las actividades tienen al menos un RF. | [trazabilidad-bpmn-rf.md](01-inicio/bpmn/trazabilidad-bpmn-rf.md) |
| R5 | **RS:** son 17, sin depuración; muchos ya los resuelven los frameworks. | Además de reducirlos a tres (punto 3.1), se quitaron de RS-03 el cifrado, las contraseñas y la expiración de sesión, que resuelve el framework. Rendimiento y disponibilidad salen del catálogo de RS. | [RS-03](02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md) |
| R6 | **Diagrama de casos de uso:** faltan asociaciones de actores secundarios y los «extend» que el detalle declara; hay líneas que cruzan otros casos de uso. | Todo actor secundario de una ficha está asociado en el diagrama (por ejemplo, persona encargada y Dirección en CU-05, y docente, Comité y Dirección en CU-07). La única relación entre casos es CU-09 «extend» CU-07, declarada en ambas fichas con su punto de extensión. Los actores se agrupan a la izquierda y a la derecha para evitar cruces. **Pendiente:** confirmar que no hay cruces al exportar el PNG. | [diagrama-casos-de-uso.puml](03-casos-de-uso/diagrama-casos-de-uso.puml) |
| R7 | **Actores:** el Auditor no tiene ficha, el Proveedor externo no participa en ningún caso de uso y falta el Comité de Evaluación. | Se incorpora el Comité de Evaluación (A-03), con ficha. El Auditor, el Proveedor externo y el Administrador institucional se retiran con su justificación. | [actores.md](03-casos-de-uso/actores.md) |
| R8 | **Detalle de casos de uso:** bien redactados, pero no son consistentes con el problema. | Se atiende con los puntos 4.1 a 4.4. Se conservó la redacción de los casos que el profesor consideró buena. | [03-casos-de-uso/](03-casos-de-uso/) |

### Cambios exigidos por el enunciado del PC2

No vienen de una observación, pero se registran porque cambian artefactos del PC1:

| Cambio | Archivo(s) |
|---|---|
| Un archivo por RF, RS y CU, y un `.feature` por CU. Los archivos `07-guion-pruebas.md`, `08-matriz-trazabilidad.md` y `09-supuestos-validaciones.md` del PC1 se reemplazan por los `.feature`, las dos matrices y la sección de supuestos. | [02-requerimientos/](02-requerimientos/), [03-casos-de-uso/](03-casos-de-uso/), [04-pruebas/](04-pruebas/) |
| Diagrama de contexto nuevo: caja negra, 34 flujos de datos y tabla. | [diagrama-de-contexto.md](01-inicio/diagrama-de-contexto.md) |
| Plantilla e issues por CU, con los criterios tomados del `.feature`. **Pendiente:** crearlos cuando el repositorio exista en GitHub. | [.github/ISSUE_TEMPLATE/caso-de-uso.md](../.github/ISSUE_TEMPLATE/caso-de-uso.md) |
| Los casos de uso adoptan la plantilla del curso: título `CU-xx-NombreEnPascalCase («Nombre»)`; tabla con descripción, estereotipo y paquete (la épica), precondiciones, postcondiciones, actores con ID y relaciones; flujo básico con la marca `(Axx)` en el paso donde se desvía cada flujo alterno; flujos alternos en texto corrido; y otros campos (excepciones, restricciones, interfaz, métricas, prioridad, estado y autor), más requerimientos, pruebas e issue. Se conservó la numeración de pasos y de flujos alternos. | [03-casos-de-uso/](03-casos-de-uso/) |
| Los escenarios que se conservaron se reescribieron con pasos Gherkin separados. El rubro de pruebas fue «Logrado», así que se mantuvo su estructura (un escenario por flujo, con ID `TP-CUxx-FB` o `TP-CUxx-Ayy`). | [04-pruebas/](04-pruebas/), [equivalencias de pruebas](#pruebas) |

**Documentos del PC1 que no pasan a `docs/`:** `10-guion-presentacion.md` y `11-checklist-rubrica.md` son material de apoyo, que el enunciado excluye de `docs/`. `13-validacion-entrevista-daisy.md` quedó integrado en la evidencia de [problema-y-vision.md](01-inicio/problema-y-vision.md#evidencia). `12-declaracion-uso-ia.md` se propone mover al `README.md` raíz del repositorio. Todos siguen disponibles en el tag `pc1`.

## Equivalencias

### RF del PC1 al PC2

| RF PC1 | Nombre en el PC1 | Resultado en el PC2 | Motivo |
|---|---|---|---|
| RF-01 | Autenticar a un usuario | Sale → RS-03 | Seguridad, no necesidad del actor (obs. 5). |
| RF-02 | Aplicar permisos por rol | Sale → RS-03 | Seguridad (obs. 5). |
| RF-03 | Registrar relación encargado–estudiante | [RF-01](02-requerimientos/funcionales/RF-01-vincular-encargado-con-estudiante.md) | Actor: Dirección (antes Administrador). |
| RF-04 | Restringir la consulta al vínculo activo | Sale → RS-03 | Es una regla de seguridad. |
| RF-05 | Seleccionar un estudiante vinculado | Se fusiona en [RF-02](02-requerimientos/funcionales/RF-02-consultar-perfil-del-estudiante.md) | Su origen era una hipótesis; queda como criterio de RF-02. |
| RF-06 | Consultar el perfil académico | [RF-02](02-requerimientos/funcionales/RF-02-consultar-perfil-del-estudiante.md) | — |
| RF-07 | Consultar el historial académico | [RF-03](02-requerimientos/funcionales/RF-03-consultar-evaluaciones-y-calificaciones.md) | — |
| RF-08 | Crear una evaluación para un grupo | [RF-04](02-requerimientos/funcionales/RF-04-registrar-evaluacion-asignada.md) | Origen nuevo: E-02, en lugar de la carga administrativa. |
| RF-09 | Registrar el hito de entrega | Se fusiona en [RF-04](02-requerimientos/funcionales/RF-04-registrar-evaluacion-asignada.md) | Mismo actor y momento. |
| RF-10 | Confirmar congruencia del primer hito | [RF-05](02-requerimientos/funcionales/RF-05-confirmar-congruencia-con-el-cuaderno.md) | Medio oficial: el cuaderno de la asignatura (E-02). |
| RF-11 | Publicar el primer hito | [RF-06](02-requerimientos/funcionales/RF-06-publicar-evaluacion-asignada.md) | — |
| RF-12 | Cargar resultados por grupo | [RF-07](02-requerimientos/funcionales/RF-07-registrar-calificaciones-del-grupo.md) | — |
| RF-13 | Confirmar congruencia del segundo hito | Se fusiona en [RF-05](02-requerimientos/funcionales/RF-05-confirmar-congruencia-con-el-cuaderno.md) | Es la misma regla para los dos hitos y la corrección. |
| RF-14 | Publicar el resultado calificado | [RF-08](02-requerimientos/funcionales/RF-08-publicar-calificacion.md) | — |
| RF-15 | Corregir un resultado con historial | [RF-09](02-requerimientos/funcionales/RF-09-corregir-calificacion-con-historial.md) | Pasa de «Debería» a «Debe»: lo requiere una apelación con cambio de nota. |
| RF-16 | Generar aviso por el primer hito | [RF-10](02-requerimientos/funcionales/RF-10-avisar-publicacion-de-evaluacion.md) | — |
| RF-17 | Generar aviso por el segundo hito | [RF-11](02-requerimientos/funcionales/RF-11-avisar-publicacion-de-calificacion.md) | — |
| RF-18 | Registrar el estado de un aviso | Se fusiona en [RF-12](02-requerimientos/funcionales/RF-12-registrar-acuse-de-consulta.md) | Sin proveedor externo, los estados son pendiente y consultado. |
| RF-19 | Registrar el acuse de consulta | [RF-12](02-requerimientos/funcionales/RF-12-registrar-acuse-de-consulta.md) | Origen: firma de recibido (E-04). |
| RF-20 | Configurar la política de recordatorios | [RF-22](02-requerimientos/funcionales/RF-22-configurar-plazos-y-recordatorios.md) | Incluye los plazos de apelación (E-08). |
| RF-21 | Generar recordatorio por falta de consulta | [RF-13](02-requerimientos/funcionales/RF-13-recordar-avisos-no-consultados.md) | — |
| RF-22 | Alertar un caso sin consulta | [RF-23](02-requerimientos/funcionales/RF-23-alertar-avisos-sin-consulta.md) | Responsable: Dirección (E-06). |
| RF-23 | Crear un hilo de comunicación | [RF-14](02-requerimientos/funcionales/RF-14-intercambiar-mensajes.md) | — |
| RF-24 | Registrar un mensaje en un hilo | Se fusiona en [RF-14](02-requerimientos/funcionales/RF-14-intercambiar-mensajes.md) | Mismo actor y necesidad. |
| RF-25 | Abrir una discrepancia académica | [RF-17](02-requerimientos/funcionales/RF-17-presentar-apelacion.md) | «Discrepancia» pasa a «apelación», el término de la escuela. |
| RF-26 | Asignar una discrepancia | [RF-18](02-requerimientos/funcionales/RF-18-asignar-apelacion-al-comite.md) | Se asigna al Comité de Evaluación (E-07). |
| RF-27 | Actualizar el estado de una discrepancia | [RF-21](02-requerimientos/funcionales/RF-21-controlar-estado-y-plazo-de-apelacion.md) y [RF-19](02-requerimientos/funcionales/RF-19-registrar-aportes-de-docente-y-direccion.md) | Estados y aportes de cada participante (E-11). |
| RF-28 | Escalar una discrepancia | Sale; lo sustituye el vencimiento de [RF-21](02-requerimientos/funcionales/RF-21-controlar-estado-y-plazo-de-apelacion.md) | No hay evidencia de un escalamiento a mediación. |
| RF-29 | Registrar la resolución | [RF-20](02-requerimientos/funcionales/RF-20-registrar-resolucion-de-apelacion.md) | La registra el Comité (E-11). |
| RF-30 | Consultar rendimiento agregado | Sale | Ninguna evidencia lo respalda. |
| RF-31 | Consultar seguimiento de comunicaciones | [RF-24](02-requerimientos/funcionales/RF-24-consultar-estado-del-seguimiento.md) | — |
| RF-32 | Consultar estado de discrepancias | Se fusiona en [RF-24](02-requerimientos/funcionales/RF-24-consultar-estado-del-seguimiento.md) | Una sola vista de supervisión. |
| RF-33 | Administrar catálogos académicos | Sale → diseño de base de datos | Sin actor confirmado; es carga inicial de datos. |
| RF-34 | Administrar cuentas y roles | Sale → RS-03 y diseño | Seguridad (obs. 5). |
| RF-35 | Consultar la bitácora de una entidad | [RF-25](02-requerimientos/funcionales/RF-25-consultar-registro-de-consultas.md) y RS-02 | Se reorienta a quién consultó qué y cuándo; sin Auditor. |
| — | — | [RF-15](02-requerimientos/funcionales/RF-15-presentar-queja-por-informacion-no-recibida.md), [RF-16](02-requerimientos/funcionales/RF-16-atender-queja-por-informacion-no-recibida.md) | Nuevos, por la queja (E-14, obs. 21). |

Las equivalencias con el documento de Google Docs (RF 01 a RF 22 y CU 01 a CU 08 en esa numeración) no se reutilizaron: el documento sirvió solo como fuente de la entrevista.

### RS del PC1 al PC2

| RS PC1 | Resultado en el PC2 |
|---|---|
| RS-USA-01 | [RS-01](02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md), reformulado para personas encargadas con poca experiencia digital |
| RS-USA-03, RS-POR-01 | Parte de RS-01 (lenguaje, ancho de 360 px) |
| RS-SEC-03 | [RS-02](02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), reorientado a las consultas |
| RS-SEC-01, RS-SEC-02, RS-SEC-04 | [RS-03](02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md) |
| RS-INT-01, RS-INT-02 | Pasan a criterios de RF-05 y RF-09 |
| RS-MAN-01 | Pasa a RF-22 |
| RS-CON-02 | Pasa a CU-03-A05 y CU-05-A07 (no duplicar) |
| RS-COM-01 | Sale: no hay proveedor externo |
| RS-USA-02, RS-REN-01, RS-REN-02, RS-CON-01, RS-CON-03, RS-PRI-01 | Salen del catálogo de RS (la observación pidió dejar solo tres). Quedan como insumo para los lineamientos de diseño. |
| RN-01 a RN-16 | Se integran en los CU y RF correspondientes. Los plazos inventados (RN-08, RN-12) y la mediación (RN-11, RN-13) salen por falta de evidencia. |

### Casos de uso

| PC1 | PC2 | Cambio |
|---|---|---|
| UC-01 | [CU-01](03-casos-de-uso/CU-01-consultar-progreso-academico.md) | Sin flujo de autenticación (pasa a RS-03); nuevo A04, calificación corregida. |
| UC-02 | [CU-02](03-casos-de-uso/CU-02-vincular-encargado-con-estudiante.md) | Actor: Dirección en lugar de Administrador. |
| UC-03 | [CU-03](03-casos-de-uso/CU-03-publicar-evaluacion-asignada.md) | Medio oficial: cuaderno; sale A07 (fallo del proveedor). |
| UC-04 | [CU-04](03-casos-de-uso/CU-04-publicar-calificacion.md) | Nuevo nombre; sale A07. |
| UC-05 | [CU-05](03-casos-de-uso/CU-05-dar-seguimiento-a-avisos-no-consultados.md) | Sin Docente; sale el fallo del recordatorio; los intervalos los configura la Dirección. |
| UC-06 | [CU-06](03-casos-de-uso/CU-06-comunicarse-sobre-un-estudiante.md) | Evidencia del cuaderno de recados; nuevos A05 y A06 para separar apelación y queja. |
| UC-07 | [CU-07](03-casos-de-uso/CU-07-gestionar-apelacion-de-calificacion.md) | «Apelación»; Comité de Evaluación; sin plazos inventados ni mediación. |
| UC-08 | [CU-08](03-casos-de-uso/CU-08-supervisar-seguimiento-academico.md) | «Supervisar el seguimiento académico»; sin rendimiento agregado; incluye el registro de consultas. |
| UC-09 | [CU-09](03-casos-de-uso/CU-09-corregir-calificacion-publicada.md) | Extiende a CU-07 (antes decía extender a UC-04). |
| UC-10 | [CU-10](03-casos-de-uso/CU-10-configurar-plazos-y-recordatorios.md) | Se reduce a plazos y recordatorios; actor: Dirección. |
| UC-11 | Retirado | Pasa a RS-02 y CU-08. El número CU-11 no se reutiliza. |
| — | [CU-12](03-casos-de-uso/CU-12-presentar-queja-por-informacion-no-recibida.md) | Nuevo. |

### Pruebas

Los identificadores pasan de `TP-UCxx-…` a `TP-CUxx-…`. Cuando un flujo alterno cambió de número, se indica abajo; los demás conservan su número.

| CU | Escenarios del PC1 | Escenarios del PC2 |
|---|---|---|
| CU-01 | FB, A01 (credencial inválida), A02, A03, A04 | FB; A01 ← A02; A02 ← A03; A03 ← A04; **A04 nuevo**. A01 del PC1 sale (RS-03). |
| CU-02 | FB, A01 a A06 | Se conservan, con DIR-01 en lugar de ADM-01. |
| CU-03 | FB, A01 a A07 | FB, A01 a A06 se conservan; A07 (fallo del proveedor) sale. |
| CU-04 | FB, A01 a A07 | FB, A01 a A06 se conservan; A07 sale. |
| CU-05 | FB, A01 a A08 | FB, A01 a A04; A05 ← A06; A06 ← A07; A07 ← A08; A05 del PC1 (fallo) sale. Los plazos de 48 y 72 horas pasan a ser valores de prueba. |
| CU-06 | FB, A01 a A04 | Se conservan; **A05 y A06 nuevos**. |
| CU-07 | FB, A01 a A08 | FB y A01 a A04 se ajustan (Comité, plazos configurados). A05 (corrección docente) pasa a A06 (corrección por resolución). A06 a A08 (mediación) salen. **A05, A07 y A08 nuevos.** |
| CU-08 | FB, A01 a A04 | Se reescriben para la supervisión: FB, A01, A02 y A03. |
| CU-09 | FB, A01 a A06 | FB y A01 a A05 se conservan (A02 de versión desactualizada pasa al paso 4); A06 (fallo) sale. |
| CU-10 | FB, A01 a A06 | Se reescriben para plazos y recordatorios: FB, A01 a A03. |
| UC-11 | 20 escenarios: FB y A01 a A06 propios, más comprobaciones de bitácora sobre UC-01 a UC-10 | Salen con el caso de uso. Lo que comprobaban (que cada actuación quede registrada) pasa a RS-02 y a los escenarios que terminan en «queda registrado». |
| CU-12 | — | **Nuevos:** FB, A01 a A04. |

### Actores

| PC1 | PC2 |
|---|---|
| Encargado legal | A-01 Persona encargada legal |
| Docente | A-02 Docente |
| — | A-03 Comité de Evaluación (nuevo) |
| Dirección / figura mediadora | A-04 Dirección |
| Tiempo / servicio de notificaciones | A-05 Tiempo (servicio programado) |
| Estudiante (indirecto) | Solo en el BPMN; no es actor del Portal |
| Administrador institucional, Auditor, Proveedor externo | Retirados (ver [actores.md](03-casos-de-uso/actores.md#actores-retirados-respecto-del-pc1)) |

### Proceso actual (BPMN)

| PC1 | PC2 |
|---|---|
| AC-01 Preparar la hoja | AC-01 (evaluación diaria) y AC-06 (reporte) |
| AC-02 Entregar la hoja al estudiante | AC-07 |
| AC-03 Transportar la hoja | AC-02 (cuaderno) y AC-08 (reporte) |
| AC-04 y AC-10 Entregar y revisar la hoja | AC-03 (cuaderno con firma) y AC-09 (reporte) |
| AC-05 Retrasar u ocultar | Compuerta «¿Lo entrega a tiempo?» → No |
| AC-06 Atender queja [PV] | AC-11 (Dirección) |
| AC-07 Comunicar aclaración [PV] | Retirada |
| AC-08 Intervención de Dirección [PV] | AP-03 dentro de SP-01 |
| AC-09 Esperar la entrega | Se representa con la compuerta; no es una tarea |
| AC-11 Contactar o presentar queja | AC-10 |
| AC-12 Solicitar aclaración [PV] | Retirada |
| AC-13 Recibir respuesta [PV] | AC-05 (recibir la resolución) |
| — | AC-04 apelación por cuaderno de recados y SP-01 (nuevos, E-07 y E-11) |

## Resultado del PC1

Nota del PC1: 68/100. Rubros logrados: problema y visión, detalle de casos de uso (con −7 por inconsistencia con el problema), escenarios de prueba, y formato y entrega. Rubros parciales: BPMN, trazabilidad BPMN↔RF, RF, RS, diagrama de casos de uso y actores. Las cuatro observaciones prioritarias y los comentarios de cada rubro se atienden en la tabla del inicio.

## Integración final del PC2

| Observación / requisito | Cambio | Archivos |
|---|---|---|
| Un repositorio con documentos y código | Incorporados backend/ y frontend/; antecedentes PC1 consultables en el historial | README.md, backend/, frontend/ |
| Realización representativa | Diseño CU-01 desde pantalla hasta tablas, sin afirmar implementación | docs/05-diseno/arquitectura.md |
| Lineamientos aplicados | Ejemplos propios de CU-01, CU-04 y CU-09, DTO, transacciones, hooks y composición | docs/05-diseno/ |
| Seguridad transversal | Añadidos CU-01 A05 y CU-05 A08: sesión inválida y vínculo revocado al abrir; 71 escenarios especificados | docs/04-pruebas/ |

Supuestos abiertos: responsable institucional de vínculos, cardinalidad y estados; plazo de apelación; simultaneidad de participantes del Comité; equivalencia de hojas y reporte semestral. No se presentan como políticas confirmadas.
