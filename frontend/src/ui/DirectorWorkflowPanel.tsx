import { useEffect, useState } from 'react';
import type { FormEvent } from 'react';
import {
  attendComplaint, getComplaints, getSupervision, getWorkflowSettings,
  processReminders, updateWorkflowSettings,
  type Complaint, type Credentials, type FollowupDashboard, type WorkflowSettings,
} from '../api/academic';

type Props = { credentials: Credentials };

export function DirectorWorkflowPanel({ credentials }: Props) {
  const [settings, setSettings] = useState<WorkflowSettings | null>(null);
  const [dashboard, setDashboard] = useState<FollowupDashboard>();
  const [complaints, setComplaints] = useState<Complaint[]>([]);
  const [appealDays, setAppealDays] = useState('');
  const [resolutionDays, setResolutionDays] = useState('');
  const [reminderHours, setReminderHours] = useState('');
  const [maxReminders, setMaxReminders] = useState('');
  const [finding, setFinding] = useState('NOT_PUBLISHED');
  const [response, setResponse] = useState('');
  const [teacherUsername, setTeacherUsername] = useState('docente.demo');
  const [notice, setNotice] = useState('');
  const [error, setError] = useState('');
  const [busy, setBusy] = useState(false);

  async function refresh() {
    const [nextSettings, nextDashboard, nextComplaints] = await Promise.all([
      getWorkflowSettings(credentials), getSupervision(credentials), getComplaints(credentials),
    ]);
    setSettings(nextSettings);
    setDashboard(nextDashboard);
    setComplaints(nextComplaints);
    if (nextSettings) {
      setAppealDays(String(nextSettings.appealWindowDays));
      setResolutionDays(String(nextSettings.resolutionWindowDays));
      setReminderHours(String(nextSettings.reminderIntervalHours));
      setMaxReminders(String(nextSettings.maxReminders));
    }
  }

  useEffect(() => { void refresh().catch((cause: unknown) => {
    setError(cause instanceof Error ? cause.message : 'No se pudo cargar la supervisión.');
  }); }, [credentials]);

  async function saveSettings(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); setBusy(true); setError(''); setNotice('');
    try {
      const result = await updateWorkflowSettings(credentials, {
        appealWindowDays: Number(appealDays), resolutionWindowDays: Number(resolutionDays),
        reminderIntervalHours: Number(reminderHours),
        maxReminders: Number(maxReminders),
      });
      setSettings(result);
      setNotice(`Configuración registrada por ${result.changedBy} desde ${new Date(result.changedAt).toLocaleString('es-CR')}. Los cambios anteriores se conservan.`);
      await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo guardar la configuración.');
    } finally { setBusy(false); }
  }

  async function runReminders() {
    setBusy(true); setError(''); setNotice('');
    try {
      const result = await processReminders(credentials);
      setNotice(`Recordatorios emitidos: ${result.remindersCreated}; alertas: ${result.alertsCreated}; sin vínculo activo: ${result.revokedNoticesClosed}.`);
      await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudieron procesar los avisos.');
    } finally { setBusy(false); }
  }

  async function resolveComplaint(complaint: Complaint) {
    if (!response.trim()) { setError('Escriba la respuesta para la queja seleccionada.'); return; }
    setBusy(true); setError(''); setNotice('');
    try {
      const updated = await attendComplaint(credentials, complaint.id, {
        finding, response: response.trim(),
        teacherUsername: finding === 'NOT_PUBLISHED' ? teacherUsername.trim() : undefined,
      });
      setNotice(`Queja ${updated.complaintNumber}: ${updated.status}.`);
      setResponse(''); await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo atender la queja.');
    } finally { setBusy(false); }
  }

  return <section className="panel link-form">
    <div className="section-heading"><div><p className="eyebrow">DIRECCIÓN</p><h2>Plazos y supervisión · CU-05, CU-08, CU-10, CU-12</h2></div></div>
    {error && <p className="alert error" role="alert">{error}</p>}{notice && <p className="alert success" role="status">{notice}</p>}
    <div className="director-grid">
      <form className="link-form" onSubmit={saveSettings}><h3>Configuración vigente</h3>
        {settings ? <p className="muted">Vigente desde {new Date(settings.changedAt).toLocaleString('es-CR')} · antes: {settings.changedBy}</p>
          : <p className="muted">No hay valores guardados. Use los plazos que defina la Dirección; el sistema no inventa valores iniciales.</p>}
        <label>Días para apelar<input required type="number" min="1" max="90" value={appealDays} onChange={(event) => setAppealDays(event.target.value)} /></label>
        <label>Días para resolver apelación<input required type="number" min="1" max="90" value={resolutionDays} onChange={(event) => setResolutionDays(event.target.value)} /></label>
        <label>Intervalo de recordatorio (horas)<input required type="number" min="1" max="8760" value={reminderHours} onChange={(event) => setReminderHours(event.target.value)} /></label>
        <label>Máximo de recordatorios<input required type="number" min="1" max="10" value={maxReminders} onChange={(event) => setMaxReminders(event.target.value)} /></label>
        <button className="primary-button" disabled={busy}>Guardar nueva versión</button>
        <button type="button" className="secondary-button" disabled={busy || !settings} onClick={() => void runReminders()}>Revisar avisos pendientes</button>
      </form>
      <section className="link-form"><h3>Resumen de atención · CU-08</h3>
        <p>Estudiantes que requieren seguimiento: <strong>{dashboard?.studentsNeedingAttention.length ?? '—'}</strong></p>
        <p>Apelaciones abiertas: <strong>{dashboard?.openAppeals ?? '—'}</strong></p>
        <p>Quejas abiertas: <strong>{dashboard?.openComplaints ?? '—'}</strong></p>
        {dashboard?.studentsNeedingAttention.map((student) => <p key={student.studentId}>{student.fullName}<small>{student.activeGuardians} vínculo(s) activo(s) · {student.pendingNotifications} aviso(s) pendiente(s)</small></p>)}
      </section>
    </div>
    <section className="link-form"><h3>Quejas · registro del hallazgo y respuesta</h3>
      {complaints.length === 0 ? <p className="muted">No hay quejas registradas.</p> : complaints.map((complaint) => <article className="panel" key={complaint.id}>
        <strong>Queja {complaint.complaintNumber} · {complaint.status} · {complaint.studentName}</strong>
        <p>{complaint.informationType} · {complaint.subject} · {complaint.period}</p>
        <p>{complaint.content}</p>
        {complaint.response && <p>Respuesta: {complaint.response}</p>}
        {complaint.status !== 'ATTENDED' && <div className="link-form">
          <label>Hallazgo<select value={finding} onChange={(event) => setFinding(event.target.value)}>
            <option value="NOT_PUBLISHED">No publicada; solicitar publicación</option><option value="PUBLISHED_NOT_READ">Publicada, sin consulta</option><option value="READ">Ya consultada</option>
          </select></label>
          {finding === 'NOT_PUBLISHED' && <label>Docente responsable<input value={teacherUsername} onChange={(event) => setTeacherUsername(event.target.value)} /></label>}
          <label>Respuesta para la persona encargada<textarea value={response} onChange={(event) => setResponse(event.target.value)} /></label>
          <button className="primary-button" disabled={busy} onClick={() => void resolveComplaint(complaint)}>Guardar atención</button>
        </div>}
      </article>)}
    </section>
  </section>;
}
