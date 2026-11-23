import { useEffect, useState } from 'react';
import type { FormEvent } from 'react';
import {
  fileAppeal, fileComplaint, getGuardianAppeals, getMessages, getNotifications, getProgress, markMessageRead,
  markNotificationRead, readAppealResolution, sendMessage,
  type Appeal, type Credentials, type Progress, type PortalNotification,
  type StudentMessage, type StudentSummary,
} from '../api/academic';

type Props = { credentials: Credentials; students: StudentSummary[] };

export function GuardianWorkflowPanel({ credentials, students }: Props) {
  const [studentId, setStudentId] = useState(students[0]?.id ?? '');
  const [progress, setProgress] = useState<Progress>();
  const [notifications, setNotifications] = useState<PortalNotification[]>([]);
  const [messages, setMessages] = useState<StudentMessage[]>([]);
  const [recipient, setRecipient] = useState('docente.demo');
  const [messageSubject, setMessageSubject] = useState('');
  const [messageContent, setMessageContent] = useState('');
  const [topic, setTopic] = useState('GENERAL');
  const [informationType, setInformationType] = useState('EVALUATION');
  const [subject, setSubject] = useState('');
  const [period] = useState('2026-II');
  const [complaintPeriod, setComplaintPeriod] = useState('2026-II');
  const [complaintText, setComplaintText] = useState('');
  const [assessmentId, setAssessmentId] = useState('');
  const [points, setPoints] = useState('');
  const [appealReason, setAppealReason] = useState('');
  const [evidence, setEvidence] = useState('');
  const [appeal, setAppeal] = useState<Appeal>();
  const [resolution, setResolution] = useState('');
  const [notice, setNotice] = useState('');
  const [error, setError] = useState('');
  const [busy, setBusy] = useState(false);

  async function refresh() {
    if (!studentId) return;
    const [nextProgress, nextNotifications, nextMessages, nextAppeals] = await Promise.all([
      getProgress(credentials, studentId, period), getNotifications(credentials),
      getMessages(credentials, studentId), getGuardianAppeals(credentials),
    ]);
    setProgress(nextProgress);
    setNotifications(nextNotifications.filter((item) => item.studentId === studentId));
    setMessages(nextMessages);
    setAppeal(nextAppeals.find((item) => item.studentId === studentId
      && ['OPEN', 'IN_REVIEW', 'PENDING_INFORMATION', 'RESOLVED'].includes(item.status)));
  }

  useEffect(() => { void refresh().catch((cause: unknown) => {
    setError(cause instanceof Error ? cause.message : 'No se pudo cargar el seguimiento.');
  }); }, [credentials, studentId, period]);

  async function submitMessage(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); if (!studentId) return;
    setBusy(true); setError(''); setNotice('');
    try {
      await sendMessage(credentials, studentId, { recipientUsername: recipient.trim(),
        subject: messageSubject.trim(), content: messageContent.trim(), topic });
      setNotice(topic === 'GRADE_DISAGREEMENT'
        ? 'Use la apelación de calificación (CU-07) para este tema.'
        : topic === 'INFORMATION_NOT_RECEIVED'
          ? 'Use el formulario de queja ante Dirección (CU-12) para este tema.'
          : 'Mensaje enviado y guardado en la conversación.');
      setMessageSubject(''); setMessageContent(''); await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo enviar el mensaje.');
    } finally { setBusy(false); }
  }

  async function submitComplaint(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); if (!studentId) return;
    setBusy(true); setError(''); setNotice('');
    try {
      const result = await fileComplaint(credentials, studentId, {
        informationType, subject: subject.trim(), period: complaintPeriod,
        content: complaintText.trim(),
      });
      setNotice(`Queja ${result.complaintNumber} registrada para Dirección.`);
      setComplaintText(''); await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo registrar la queja.');
    } finally { setBusy(false); }
  }

  async function submitAppeal(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); if (!studentId || !assessmentId) return;
    setBusy(true); setError(''); setNotice('');
    try {
      const result = await fileAppeal(credentials, studentId, {
        assessmentId, points: points.trim(), reason: appealReason.trim(),
        evidenceReference: evidence.trim(),
      });
      setAppeal(result);
      setNotice(`Apelación ${result.appealNumber} · ${result.status} · plazo hasta ${result.deadlineAt}.`);
      await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo presentar la apelación.');
    } finally { setBusy(false); }
  }

  async function acknowledge(notification: PortalNotification) {
    setBusy(true); setError('');
    try { await markNotificationRead(credentials, notification.id); await refresh(); }
    catch (cause: unknown) { setError(cause instanceof Error ? cause.message : 'No se pudo confirmar la consulta.'); }
    finally { setBusy(false); }
  }

  async function acknowledgeMessage(message: StudentMessage) {
    setBusy(true); setError('');
    try { await markMessageRead(credentials, message.id); await refresh(); }
    catch (cause: unknown) { setError(cause instanceof Error ? cause.message : 'No se pudo registrar la lectura.'); }
    finally { setBusy(false); }
  }

  async function consultResolution() {
    if (!appeal) return;
    setBusy(true); setError(''); setResolution('');
    try {
      const result = await readAppealResolution(credentials, appeal.id);
      setResolution(`${result.outcome}: ${result.foundation}`);
      setAppeal({ ...appeal, status: 'CLOSED' });
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'La resolución aún no está disponible.');
    } finally { setBusy(false); }
  }

  if (students.length === 0) return null;
  return <section className="panel link-form">
    <div className="section-heading"><div><p className="eyebrow">COMUNICACIÓN Y AVISOS</p><h2>Mensajes, quejas y apelaciones</h2></div></div>
    {error && <p className="alert error" role="alert">{error}</p>}{notice && <p className="alert success" role="status">{notice}</p>}
    <label>Estudiante<select value={studentId} onChange={(event) => { setStudentId(event.target.value); setAppeal(undefined); }}>
      {students.map((student) => <option key={student.id} value={student.id}>{student.fullName}</option>)}
    </select></label>
    <div className="director-grid">
      <form className="link-form" onSubmit={submitMessage}><h3>Conversación · CU-06</h3>
        <label>Docente destinatario<input required value={recipient} onChange={(event) => setRecipient(event.target.value)} /></label>
        <label>Tipo de consulta<select value={topic} onChange={(event) => setTopic(event.target.value)}>
          <option value="GENERAL">Consulta general</option><option value="GRADE_DISAGREEMENT">Inconformidad con una nota</option><option value="INFORMATION_NOT_RECEIVED">Información no recibida</option>
        </select></label>
        <label>Asunto<input required value={messageSubject} onChange={(event) => setMessageSubject(event.target.value)} /></label>
        <label>Mensaje<textarea required value={messageContent} onChange={(event) => setMessageContent(event.target.value)} /></label>
        <button className="primary-button" disabled={busy}>Enviar mensaje</button>
      </form>
      <form className="link-form" onSubmit={submitComplaint}><h3>Queja por información · CU-12</h3>
        <label>Información que esperaba<select value={informationType} onChange={(event) => setInformationType(event.target.value)}>
          <option value="EVALUATION">Evaluación</option><option value="GRADE">Calificación</option><option value="PERIOD_REPORT">Reporte del periodo</option>
        </select></label>
        <label>Materia<input required value={subject} onChange={(event) => setSubject(event.target.value)} /></label>
        <label>Periodo<input required value={complaintPeriod} onChange={(event) => setComplaintPeriod(event.target.value)} /></label>
        <label>Descripción<textarea required value={complaintText} onChange={(event) => setComplaintText(event.target.value)} /></label>
        <button className="primary-button" disabled={busy}>Enviar queja a Dirección</button>
      </form>
    </div>
    <div className="director-grid">
      <form className="link-form" onSubmit={submitAppeal}><h3>Apelar calificación · CU-07</h3>
        <label>Calificación publicada<select required value={assessmentId} onChange={(event) => setAssessmentId(event.target.value)}><option value="">Seleccione una evaluación</option>
          {progress?.assessments.filter((item) => item.grade !== null).map((item) => <option key={item.id} value={item.id}>{item.subject} · {item.title} · {item.grade}</option>)}
        </select></label>
        <label>Puntos que apela<input required value={points} onChange={(event) => setPoints(event.target.value)} /></label>
        <label>Motivo<textarea required value={appealReason} onChange={(event) => setAppealReason(event.target.value)} /></label>
        <label>Referencia del respaldo<input required value={evidence} onChange={(event) => setEvidence(event.target.value)} /></label>
        <button className="primary-button" disabled={busy || !assessmentId}>Presentar apelación</button>
        {appeal && <div className="panel"><strong>{appeal.appealNumber} · {appeal.status}</strong><p>Plazo: {appeal.deadlineAt}</p><button type="button" className="secondary-button" onClick={() => void consultResolution()} disabled={busy || appeal.status === 'CLOSED'}>Consultar resolución</button></div>}
        {resolution && <p className="alert success">{resolution}</p>}
      </form>
      <section className="link-form"><h3>Avisos y acuses · CU-05</h3>
        {notifications.length === 0 ? <p className="muted">No hay avisos registrados para este estudiante.</p> : <ul>{notifications.map((notification) => <li key={notification.id}>
          <strong>{notification.subject}</strong><small>{new Date(notification.createdAt).toLocaleString('es-CR')}{notification.readAt ? ` · consultado ${new Date(notification.readAt).toLocaleString('es-CR')}` : ' · pendiente'}{notification.reminderCount > 0 && ` · recordatorios: ${notification.reminderCount}`}</small>
          {!notification.readAt && <button type="button" className="text-button" onClick={() => void acknowledge(notification)} disabled={busy}>Abrir y confirmar consulta</button>}
        </li>)}</ul>}
      </section>
    </div>
    <section className="link-form"><h3>Conversación guardada</h3>
      {messages.length === 0 ? <p className="muted">Aún no hay mensajes.</p> : <ul>{messages.map((message) => <li key={message.id}><strong>{message.subject} · {message.senderUsername}</strong><p>{message.content}</p><small>{new Date(message.createdAt).toLocaleString('es-CR')}{message.readAt && ` · leído ${new Date(message.readAt).toLocaleString('es-CR')}`}</small>{message.recipientUsername === credentials.username && !message.readAt && <button className="text-button" onClick={() => void acknowledgeMessage(message)} disabled={busy}>Marcar leído</button>}</li>)}</ul>}
    </section>
  </section>;
}
