import { useEffect, useState } from 'react';
import type { FormEvent } from 'react';
import {
  contributeToAppeal, correctGrade, getAppeals, getTeacherCatalog, publishAssessment,
  publishGrade, sendMessage,
  type Appeal, type Credentials, type TeacherCatalog,
} from '../api/academic';

type Props = { credentials: Credentials };

export function TeacherWorkspace({ credentials }: Props) {
  const [catalog, setCatalog] = useState<TeacherCatalog>();
  const [appeals, setAppeals] = useState<Appeal[]>([]);
  const [groupId, setGroupId] = useState('');
  const [title, setTitle] = useState('');
  const [assignedDate, setAssignedDate] = useState('');
  const [dueDate, setDueDate] = useState('');
  const [instructions, setInstructions] = useState('');
  const [confirmed, setConfirmed] = useState(false);
  const [assessmentId, setAssessmentId] = useState('');
  const [grade, setGrade] = useState('');
  const [observation, setObservation] = useState('');
  const [reason, setReason] = useState('');
  const [gradeDate, setGradeDate] = useState('');
  const [recipient, setRecipient] = useState('');
  const [studentId, setStudentId] = useState('');
  const [messageSubject, setMessageSubject] = useState('');
  const [messageContent, setMessageContent] = useState('');
  const [appealId, setAppealId] = useState('');
  const [contribution, setContribution] = useState('');
  const [notice, setNotice] = useState('');
  const [error, setError] = useState('');
  const [busy, setBusy] = useState(false);

  async function refresh() {
    const [nextCatalog, nextAppeals] = await Promise.all([
      getTeacherCatalog(credentials), getAppeals(credentials, 'teacher'),
    ]);
    setCatalog(nextCatalog);
    setAppeals(nextAppeals);
    if (!groupId && nextCatalog.groups.length > 0) setGroupId(nextCatalog.groups[0].id);
    if (!assessmentId && nextCatalog.assessments.length > 0) {
      setAssessmentId(nextCatalog.assessments[0].id);
    }
  }

  useEffect(() => { void refresh().catch((cause: unknown) => {
    setError(cause instanceof Error ? cause.message : 'No se pudo cargar el panel docente.');
  }); }, [credentials]);

  async function submitAssessment(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setBusy(true); setError(''); setNotice('');
    try {
      const result = await publishAssessment(credentials, groupId, {
        title: title.trim(), assignedDate, dueDate, instructions: instructions.trim(),
        confirmedWithNotebook: confirmed,
      });
      setNotice(result.message ?? `${result.title}: publicada para ${result.studentCount} estudiante(s); ${result.notificationCount} aviso(s) creado(s).`);
      setTitle(''); setInstructions(''); setConfirmed(false);
      await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo guardar la evaluación.');
    } finally { setBusy(false); }
  }

  async function submitGrade(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    const selected = catalog?.assessments.find((item) => item.id === assessmentId);
    if (!selected) return;
    setBusy(true); setError(''); setNotice('');
    try {
      const input = { numericValue: Number(grade), observation: observation.trim(),
        publishedDate: gradeDate, confirmedWithNotebook: confirmed };
      const result = selected.gradeVersion === null
        ? await publishGrade(credentials, assessmentId, input)
        : await correctGrade(credentials, assessmentId, {
          ...input, reason: reason.trim(), expectedVersion: selected.gradeVersion,
        });
      setNotice(result.message ?? (selected.gradeVersion === null
        ? 'Calificación publicada como primera versión.' : `Corrección guardada; versión ${result.version}.`));
      setGrade(''); setObservation(''); setReason(''); setConfirmed(false);
      await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo guardar la calificación.');
    } finally { setBusy(false); }
  }

  async function submitMessage(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!studentId) return;
    setBusy(true); setError(''); setNotice('');
    try {
      await sendMessage(credentials, studentId, { recipientUsername: recipient.trim(),
        subject: messageSubject.trim(), content: messageContent.trim(), topic: 'GENERAL' }, 'teacher');
      setNotice('Mensaje guardado en la conversación y aviso enviado a la persona encargada.');
      setMessageSubject(''); setMessageContent('');
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo enviar el mensaje.');
    } finally { setBusy(false); }
  }

  async function submitContribution(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!appealId) return;
    setBusy(true); setError(''); setNotice('');
    try {
      await contributeToAppeal(credentials, 'teacher', appealId,
        { type: 'CONTRIBUTION', content: contribution.trim() });
      setNotice('Aporte agregado al expediente; la nota publicada no cambió.');
      setContribution(''); await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo aportar al expediente.');
    } finally { setBusy(false); }
  }

  return <section className="workspace director-workspace">
    <div className="page-heading"><div><p className="eyebrow">DOCENTE</p><h1>Seguimiento del grupo</h1>
      <p className="intro">Publique evaluaciones, calificaciones y converse con las personas encargadas de sus estudiantes.</p></div></div>
    {error && <p className="alert error" role="alert">{error}</p>}
    {notice && <p className="alert success" role="status">{notice}</p>}
    <div className="director-grid">
      <form className="panel link-form" onSubmit={submitAssessment}><h2>Publicar evaluación · CU-03</h2>
        <label>Grupo asignado<select required value={groupId} onChange={(event) => setGroupId(event.target.value)}>
          <option value="">Seleccione un grupo</option>{catalog?.groups.map((group) => <option key={group.id} value={group.id}>{group.subject} · {group.gradeLevel} {group.groupName} · {group.period}</option>)}
        </select></label>
        <label>Título<input required maxLength={180} value={title} onChange={(event) => setTitle(event.target.value)} /></label>
        <label>Fecha de asignación<input required type="date" value={assignedDate} onChange={(event) => setAssignedDate(event.target.value)} /></label>
        <label>Fecha de entrega<input required type="date" value={dueDate} onChange={(event) => setDueDate(event.target.value)} /></label>
        <label>Instrucciones<textarea required value={instructions} onChange={(event) => setInstructions(event.target.value)} /></label>
        <label className="pending-toggle"><input type="checkbox" checked={confirmed} onChange={(event) => setConfirmed(event.target.checked)} /> Confirmo que coincide con el cuaderno</label>
        <button className="primary-button" disabled={busy || !groupId}>{busy ? 'Guardando…' : 'Publicar evaluación'}</button>
      </form>
      <form className="panel link-form" onSubmit={submitGrade}><h2>Publicar o corregir nota · CU-04 / CU-09</h2>
        <label>Evaluación y estudiante<select required value={assessmentId} onChange={(event) => setAssessmentId(event.target.value)}>
          <option value="">Seleccione una evaluación</option>{catalog?.assessments.map((item) => <option key={item.id} value={item.id}>{item.subject} · {item.title} · {item.studentName}{item.grade === null ? ' · sin nota' : ` · nota ${item.grade} (v${item.gradeVersion})`}</option>)}
        </select></label>
        <label>Nota (0–100)<input required type="number" min="0" max="100" step="0.01" value={grade} onChange={(event) => setGrade(event.target.value)} /></label>
        <label>Observación<textarea required value={observation} onChange={(event) => setObservation(event.target.value)} /></label>
        <label>Fecha de devolución<input required type="date" value={gradeDate} onChange={(event) => setGradeDate(event.target.value)} /></label>
        {catalog?.assessments.find((item) => item.id === assessmentId)?.gradeVersion !== null
          && catalog?.assessments.find((item) => item.id === assessmentId)?.gradeVersion !== undefined
          && <label>Motivo de corrección<input required value={reason} onChange={(event) => setReason(event.target.value)} /></label>}
        <label className="pending-toggle"><input type="checkbox" checked={confirmed} onChange={(event) => setConfirmed(event.target.checked)} /> Confirmo que coincide con el cuaderno</label>
        <button className="primary-button" disabled={busy || !assessmentId}>{busy ? 'Guardando…' : 'Guardar calificación'}</button>
      </form>
    </div>
    <div className="director-grid">
      <form className="panel link-form" onSubmit={submitMessage}><h2>Conversación · CU-06</h2>
        <label>Estudiante<select required value={studentId} onChange={(event) => setStudentId(event.target.value)}><option value="">Seleccione un estudiante</option>
          {catalog?.students.map((item) => <option key={`${item.studentId}-${item.groupId}`} value={item.studentId}>{item.studentName} · {item.subject}</option>)}
        </select></label>
        <label>Usuario de la persona encargada<input required value={recipient} onChange={(event) => setRecipient(event.target.value)} placeholder="encargada.demo" /></label>
        <label>Asunto<input required value={messageSubject} onChange={(event) => setMessageSubject(event.target.value)} /></label>
        <label>Mensaje<textarea required value={messageContent} onChange={(event) => setMessageContent(event.target.value)} /></label>
        <button className="primary-button" disabled={busy || !studentId}>Enviar mensaje</button>
      </form>
      <section className="panel link-form"><h2>Apelaciones de mis grupos · CU-13</h2>
        {appeals.length === 0 ? <p className="muted">No hay expedientes asignados a sus grupos.</p> : <ul>{appeals.map((appeal) => <li key={appeal.id}>{appeal.appealNumber} · {appeal.status}<small>{appeal.points} · vence {appeal.deadlineAt}</small></li>)}</ul>}
        <form className="link-form" onSubmit={submitContribution}>
          <label>Expediente<select required value={appealId} onChange={(event) => setAppealId(event.target.value)}><option value="">Seleccione una apelación</option>{appeals.map((appeal) => <option key={appeal.id} value={appeal.id}>{appeal.appealNumber} · {appeal.status}</option>)}</select></label>
          <label>Prueba o criterio<textarea required value={contribution} onChange={(event) => setContribution(event.target.value)} /></label>
          <button className="primary-button" disabled={busy || !appealId}>Agregar aporte al expediente</button>
        </form>
      </section>
    </div>
    <section className="panel link-form"><h2>Grupos asignados</h2>
      <div className="table-wrap"><table><thead><tr><th>Curso</th><th>Grupo</th><th>Periodo</th><th>Estudiantes activos</th></tr></thead><tbody>{catalog?.groups.map((group) => <tr key={group.id}><td>{group.subject}</td><td>{group.gradeLevel} · {group.groupName}</td><td>{group.period}</td><td>{group.studentCount}</td></tr>)}</tbody></table></div>
    </section>
  </section>;
}
