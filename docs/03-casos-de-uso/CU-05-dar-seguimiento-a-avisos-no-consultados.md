# CU-05-DarSeguimientoAAvisosNoConsultados («Dar seguimiento a avisos no consultados»)

| Campo | Detalle |
|---|---|
| **Descripción** | Reemplazar la firma en el cuaderno (E-04) por una constancia de consulta y reiterar los avisos que no se consultan. Si los recordatorios no bastan, alertar a la Dirección (P-03, P-05). |
| **Estereotipo y paquete** | «Épica 2» Comunicación entre la escuela y la persona encargada |
| **Precondiciones** | 1. Existe un aviso generado por CU-03, CU-04, CU-06, CU-07, CU-09 o CU-12.<br>2. La Dirección configuró el intervalo y la cantidad de recordatorios (CU-10). |
| **Postcondiciones** | El aviso queda consultado, con persona, fecha y hora del primer acuse, y no se emiten más recordatorios.<br>**Garantía mínima** (si no termina con éxito): Ningún aviso aparece como consultado sin una apertura registrada, y no se emiten recordatorios duplicados. |
| **Actores** | A-05 Tiempo (principal); A-01 Persona encargada legal (secundario: consulta el aviso); A-04 Dirección (secundario: recibe la alerta) |
| **Relaciones del caso de uso** | Asociado con los actores A-05 Tiempo, A-01 Persona encargada legal, A-04 Dirección. Ninguna relación «include» o «extend». Usa la configuración de [CU-10](CU-10-configurar-plazos-y-recordatorios.md). Sus alertas aparecen en [CU-08](CU-08-supervisar-seguimiento-academico.md). |

## Flujo básico

1. Al cumplirse el intervalo de revisión configurado, el Tiempo inicia la revisión.
2. El Portal identifica un aviso pendiente, sin acuse de consulta (A01).
3. El Portal valida que el vínculo entre el destinatario y el estudiante siga activo (RS-03) (A02).
4. El Portal determina cuántos recordatorios se emitieron y compara con la cantidad configurada (A03, A04).
5. El Portal emite el recordatorio que corresponde y registra su número y su fecha (A07).
6. La persona encargada abre el aviso desde el recordatorio; el Portal vuelve a validar su identidad y vínculo activo antes de mostrar el contenido (RS-01, RS-03) (A05, A08).
7. El Portal registra el acuse de consulta con persona, fecha y hora (RS-02) (A06).
8. El Portal deja de emitir recordatorios para ese aviso y ese destinatario.

## Flujos alternos

- **A01 — Aviso consultado antes de la revisión (paso 2).** El Portal reconoce el acuse existente. No emite recordatorio ni alerta, y el caso de uso termina.
- **A02 — Vínculo inactivo (paso 3).** El Portal cancela los recordatorios para esa persona y conserva el historial. El Portal genera una alerta para la Dirección: el estudiante queda sin destinatario válido. El caso de uso termina.
- **A03 — Cantidad de recordatorios alcanzada (paso 4).** El Portal no emite otro recordatorio. El Portal genera una alerta para la Dirección con estudiante, destinatario, publicación de origen, recordatorios emitidos y antigüedad. El caso de uso termina.
- **A04 — Configuración ausente o incoherente (paso 4).** El Portal no emite recordatorios con reglas indeterminadas. El Portal registra la incidencia y la informa a la Dirección. El aviso conserva su estado y el caso de uso termina.
- **A05 — El aviso sigue sin consultarse (paso 6).** El Portal conserva el aviso como pendiente y el recordatorio en el historial. En la siguiente revisión el flujo continúa desde el paso 1, hasta que haya consulta o se alcance la cantidad configurada (A03).
- **A06 — Apertura posterior al primer acuse (paso 7).** El Portal conserva la persona, la fecha y la hora del primer acuse. La nueva apertura queda en el registro de consultas, sin reiniciar recordatorios.
- **A07 — Revisión repetida dentro del mismo intervalo (paso 5).** El Portal reconoce que ya emitió el recordatorio de ese intervalo. No emite otro y el caso de uso termina.

- **A08 — Acceso revocado al abrir el aviso (paso 6).** Si la sesión ya no es válida o el vínculo se suspendió/finalizó después de emitir el recordatorio, el Portal deniega el contenido sin confirmar datos del estudiante. No genera un acuse de lectura; conserva el historial y registra el intento denegado. El caso de uso termina.

## Otros campos

- **Excepciones:** ninguna fuera de los flujos alternos.
- **Restricciones:** [RS-01](../02-requerimientos/suplementarios/RS-01-usabilidad-para-encargados.md), [RS-02](../02-requerimientos/suplementarios/RS-02-trazabilidad-de-consultas.md), [RS-03](../02-requerimientos/suplementarios/RS-03-seguridad-por-actor-y-vinculo.md)
- **Especificación de interfaz de usuario:** [Panel institucional de supervisión](prototipos/stitch-4/screen.png)
- **Métricas:** complejidad alta (8 pasos y 8 flujos alternos).
- **Prioridad:** alta (todos sus RF tienen prioridad «Debe»).
- **Estado:** revisado para el PC2.
- **Autor:** Josué Delgado Corrales (revisión del PC2). Versión del PC1: Josué Delgado Corrales, Sebastián Cordero Quesada y Alejandro Porras Orozco.
- **Requerimientos:** [RF-12](../02-requerimientos/funcionales/RF-12-registrar-acuse-de-consulta.md), [RF-13](../02-requerimientos/funcionales/RF-13-recordar-avisos-no-consultados.md), [RF-23](../02-requerimientos/funcionales/RF-23-alertar-avisos-sin-consulta.md)
- **Pruebas:** [CU-05-dar-seguimiento-a-avisos-no-consultados.feature](../04-pruebas/CU-05-dar-seguimiento-a-avisos-no-consultados.feature)
- **Issue:** [Issue](https://github.com/IF-6100-2026/portal-academico-documentacion/issues/7) <!-- issue:CU-05 -->
- **Equivale en el PC1 a:** UC-05 («Gestionar avisos no consultados»); sin el actor Docente ni el proveedor externo
