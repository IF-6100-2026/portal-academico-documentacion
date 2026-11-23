# Brechas de requerimientos y decisiones por confirmar

El PC2 recibió la observación de que el grupo debe encontrar vacíos de información, pedir guía y generar casos de uso que separen responsabilidades. Este registro distingue lo dicho por Mediación de las decisiones que todavía deben resolver el grupo y la institución. No convierte una propuesta en política institucional.

| ID | Pregunta o brecha | Impacto | Propuesta provisional | Responsable de validación |
|---|---|---|---|---|
| B-01 | ¿Qué casos de uso aprobó el profesor como núcleo del PC3? | El alcance mínimo y la evaluación pueden cambiar. | El equipo no pudo confirmar selección; para entregar un producto ejecutable implementó CU-01 + CU-02 por su dependencia funcional y controles de acceso. Se mantiene como propuesta, sin aprobación verificable. | Grupo y profesor |
| B-02 | ¿Qué escala y regla de redondeo se usan para calificaciones? | Validación de datos, rango visible y correcciones. | No imponer mínimo/máximo ni convertir escalas hasta consultar. | Profesor / Centro |
| B-03 | ¿Qué documento autoriza la relación y quién puede aprobarla? | Alta, suspensión y acceso a información de menores. | El prototipo guarda una referencia; no define validez legal ni custodia. | Dirección / Centro |
| B-04 | ¿Cómo se gestionan restricciones de custodia y solicitudes pendientes? | CU-02 A04 y permisos por estudiante. | El prototipo registra una solicitud como `PENDING`, muestra el motivo y bloquea consultas/listados. No permite activar una solicitud pendiente ni define el proceso de revisión hasta confirmar la política. | Dirección / Centro |
| B-05 | ¿Cómo se autentica cada actor y quién aprovisiona, revoca o recupera cuentas? | Seguridad para uso real; perfiles actuales son fijos. | Credenciales HTTP Basic ficticias solo para entorno local. | Centro / equipo |
| B-06 | ¿Cómo se corrigen calificaciones y qué versiones deben ver las familias? | RF-09, CU-01 A04 y auditoría. | La demo conserva la versión previa, motivo, fecha y usuario que corrigió; se muestra a la persona encargada. Visibilidad y política institucional siguen sin validar; los datos son ficticios. | Profesor / docentes |
| B-07 | ¿Cuáles son los estados, plazos, pausas y responsables de una apelación? | CU-07 y CU-13 a CU-15, RF-18 a RF-21. | Los estados “en revisión”, “pendiente de información”, “resuelta” y “cerrada” son propuestas en las fichas. No se codificaron. | Profesor / Centro |
| B-08 | ¿Qué evidencias, anexos, acuses y notificaciones necesita cada paso? | Almacenamiento, correo y ciclo completo de apelación. | Fichas describen escenarios para discusión; el backend no envía avisos ni almacena adjuntos. | Profesor / Centro |
| B-09 | ¿Qué datos puede ver la Dirección en supervisión y cuánto tiempo se conservan las trazas? | RF-24/RF-25, privacidad y auditoría. | El núcleo registra lectura y cambios de vínculo, pero no implementa consulta administrativa ni política de retención. | Centro / profesor |
| B-10 | ¿Qué accesibilidad, navegadores y dispositivos se deben soportar? | Aceptación del frontend y prueba de usabilidad. | Mantener controles visibles, navegación por teclado y mensajes claros; falta validación con usuarios. | Profesor / usuarios |

La propuesta de CU-13, CU-14 y CU-15 separa el aporte de información, decisión del Comité y consulta de la persona encargada para no concentrar varios objetivos en CU-07. La descomposición se debe revisar antes de tomarla como definitiva.
