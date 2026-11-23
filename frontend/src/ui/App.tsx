import { useState } from 'react';
import type { FormEvent } from 'react';
import './completion.css';
import {
  changeLinkStatus, createLink, getLinkCatalog, getProgress, getStudents,
  type Credentials, type LinkCatalog, type LinkView, type Progress, type StudentSummary,
} from '../api/academic';

type RoleMode = 'guardian' | 'director';

export default function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [mode, setMode] = useState<RoleMode>('guardian');
  const [credentials, setCredentials] = useState<Credentials>();
  const [students, setStudents] = useState<StudentSummary[]>([]);
  const [catalog, setCatalog] = useState<LinkCatalog>();
  const [progress, setProgress] = useState<Progress>();
  const [period, setPeriod] = useState('2026-II');
  const [error, setError] = useState('');
  const [notice, setNotice] = useState('');
  const [busy, setBusy] = useState(false);
  const [relationshipType, setRelationshipType] = useState('Persona encargada autorizada');
  const [evidenceReference, setEvidenceReference] = useState('');
  const [pendingLink, setPendingLink] = useState(false);
  const [restrictionReason, setRestrictionReason] = useState('');
  const [changeReason, setChangeReason] = useState('');
  const [guardianChoice, setGuardianChoice] = useState('');
  const [studentChoice, setStudentChoice] = useState('');

  async function signIn(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setError('');
    setNotice('');
    setBusy(true);
    const entered: Credentials = { username: username.trim(), password };
    try {
      if (mode === 'guardian') setStudents((await getStudents(entered)).students);
      else setCatalog(await getLinkCatalog(entered));
      setCredentials(entered);
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo iniciar sesión.');
    } finally {
      setBusy(false);
    }
  }

  async function openProgress(student: StudentSummary) {
    if (!credentials) return;
    setProgress(undefined);
    setError('');
    setBusy(true);
    try {
      setProgress(await getProgress(credentials, student.id, period));
      setStudents((await getStudents(credentials)).students);
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo consultar el progreso.');
    } finally {
      setBusy(false);
    }
  }

  async function changePeriod(nextPeriod: string) {
    setPeriod(nextPeriod);
    if (!credentials || !progress) return;
    setBusy(true);
    setError('');
    try {
      setProgress(await getProgress(credentials, progress.student.id, nextPeriod));
      setStudents((await getStudents(credentials)).students);
    } catch (reason: unknown) {
      setPeriod(progress.period);
      setError(reason instanceof Error ? reason.message : 'No se pudo consultar el periodo.');
    } finally {
      setBusy(false);
    }
  }

  async function submitLink(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!credentials) return;
    setError('');
    setNotice('');
    setBusy(true);
    try {
      const result = await createLink(credentials, {
        guardianUsername: guardianChoice,
        studentId: studentChoice,
        relationshipType,
        evidenceReference,
        pending: pendingLink,
        restrictionReason: restrictionReason.trim(),
      });
      setCatalog(await getLinkCatalog(credentials));
      setEvidenceReference('');
      setRestrictionReason('');
      setPendingLink(false);
      setNotice('activatedAt' in result
        ? `El vínculo ya estaba activo desde ${new Date(result.activatedAt).toLocaleString('es-CR')}.`
        : 'requestedAt' in result
          ? `La solicitud ya estaba pendiente desde ${new Date(result.requestedAt).toLocaleString('es-CR')}.`
        : pendingLink
          ? 'Solicitud pendiente registrada; no habilita acceso académico.'
          : 'Vínculo registrado con respaldo e historial.');
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo registrar el vínculo.');
    } finally {
      setBusy(false);
    }
  }

  async function updateLink(link: LinkView, status: 'SUSPENDED' | 'ENDED') {
    if (!credentials) return;
    if (!changeReason.trim()) {
      setError('Indique el motivo para cambiar el estado del vínculo.');
      return;
    }
    setBusy(true);
    setError('');
    setNotice('');
    try {
      await changeLinkStatus(credentials, link.id, { status, reason: changeReason.trim() });
      setCatalog(await getLinkCatalog(credentials));
      setChangeReason('');
      setNotice(status === 'SUSPENDED' ? 'Vínculo suspendido.' : 'Vínculo finalizado.');
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo actualizar el vínculo.');
    } finally {
      setBusy(false);
    }
  }

  function signOut() {
    setCredentials(undefined);
    setProgress(undefined);
    setStudents([]);
    setCatalog(undefined);
    setError('');
    setNotice('');
    setPassword('');
  }

  return <main>
    <header>
      <div className="brand"><span className="brand-mark">M</span><span>Centro Educativo María Montessori<small>Portal Académico</small></span></div>
      <div className="header-right"><span>IF-6100 · Entorno de desarrollo</span>{credentials && <button className="quiet-button" onClick={signOut}>Cerrar sesión</button>}</div>
    </header>
    {!credentials ? <section className="login-layout">
      <div className="welcome"><p className="eyebrow">SEGUIMIENTO ACADÉMICO</p><h1>La información<br/>en un solo lugar.</h1>
        <p className="intro">Consulta las publicaciones del periodo o administra los vínculos autorizados entre estudiantes y personas encargadas.</p>
        <div className="welcome-note"><strong>Datos de demostración</strong><span>El entorno local usa cuentas y registros ficticios.</span></div>
      </div>
      <form className="panel login-panel" onSubmit={signIn}>
        <h2>Ingresar al portal</h2><p className="muted">Seleccione su perfil y use una cuenta habilitada.</p>
        <label>Perfil<select value={mode} onChange={(event) => setMode(event.target.value as RoleMode)}>
          <option value="guardian">Persona encargada</option><option value="director">Dirección</option>
        </select></label>
        <label>Usuario<input autoComplete="username" required value={username} onChange={(event) => setUsername(event.target.value)} placeholder={mode === 'guardian' ? 'encargada.demo' : 'direccion.demo'} /></label>
        <label>Contraseña<input autoComplete="current-password" required type="password" value={password} onChange={(event) => setPassword(event.target.value)} /></label>
        {error && <p className="alert error" role="alert">{error}</p>}
        <button className="primary-button" disabled={busy}>{busy ? 'Ingresando…' : 'Ingresar'}</button>
        <p className="tiny">Las credenciales demo se encuentran en la guía local del proyecto.</p>
      </form>
    </section> : mode === 'guardian' ? <section className="workspace">
      <div className="page-heading"><div><p className="eyebrow">PERSONA ENCARGADA</p><h1>Progreso académico</h1><p className="intro">Estudiantes vinculados y publicaciones disponibles.</p></div>
        <label className="period-field">Periodo<select value={period} disabled={busy} onChange={(event) => changePeriod(event.target.value)}><option>2026-I</option><option>2026-II</option></select></label>
      </div>
      {error && <p className="alert error" role="alert">{error}</p>}
      {notice && <p className="alert success" role="status">{notice}</p>}
      {!progress ? <div className="student-grid">{students.length === 0 ? <div className="panel empty"><span className="empty-icon">✓</span><h2>No hay estudiantes vinculados</h2><p>Dirección debe registrar un vínculo autorizado antes de mostrar información académica.</p></div> : students.map((student) => <article className="student-card" key={student.id}>
        <span className="card-label">ESTUDIANTE</span><h2>{student.fullName}</h2><p>{student.gradeLevel} grado · Grupo {student.groupName}</p>
        <div className="student-notices">{student.pendingNotices > 0
          ? <span className="notice-count">{student.pendingNotices} aviso{student.pendingNotices === 1 ? '' : 's'} pendiente{student.pendingNotices === 1 ? '' : 's'}</span>
          : <span className="muted">Sin avisos pendientes</span>}
          {student.latestPublications.length > 0 && <ul>{student.latestPublications.map((publication) => <li key={`${publication.subject}-${publication.title}`}>
            <strong>{publication.subject}</strong> · {publication.title}<small>{new Date(publication.publishedAt).toLocaleDateString('es-CR')}</small>
          </li>)}</ul>}
        </div>
        <button className="secondary-button" disabled={busy} onClick={() => openProgress(student)}>Ver progreso <span aria-hidden="true">→</span></button>
      </article>)}</div> : <>
        <button className="back-button" onClick={() => { setProgress(undefined); setError(''); }}>← Todos los estudiantes</button>
        <section className="profile-panel"><div><p className="eyebrow">PERFIL DEL ESTUDIANTE</p><h2>{progress.student.fullName}</h2><p>{progress.student.gradeLevel} grado · Grupo {progress.student.groupName} · Docente: {progress.student.teacherName}</p></div><div className="consulted">Consulta registrada<span>{new Date(progress.consultedAt).toLocaleString('es-CR')}</span></div></section>
        <div className="section-heading"><div><h2>Publicaciones</h2><p className="muted">Periodo {progress.period} · solo contenido publicado</p></div><span className="count-pill">{progress.assessments.length}</span></div>
        {progress.assessments.length === 0 ? <div className="panel empty"><h3>Sin publicaciones en este periodo</h3><p>Cuando se publiquen evaluaciones o calificaciones, aparecerán aquí.</p></div> : <div className="assessment-list">{progress.assessments.map((assessment) => <article className="assessment-card" key={assessment.id}>
          <div className="assessment-top"><span className="subject-chip">{assessment.subject}</span><time>{new Date(`${assessment.assignedDate}T12:00:00`).toLocaleDateString('es-CR')}</time></div>
          <h3>{assessment.title}</h3><p>{assessment.instructions}</p>
          {assessment.grade === null ? <span className="status-note">Evaluación asignada · calificación pendiente</span> : <div className="grade-row"><div><span className="card-label">CALIFICACIÓN PUBLICADA</span><strong>{assessment.grade}</strong>{assessment.previousVersions.length > 0 && <span className="corrected-note">Calificación corregida</span>}</div><div className="grade-observation">{assessment.observation}<small>Publicada el {assessment.gradePublishedDate && new Date(`${assessment.gradePublishedDate}T12:00:00`).toLocaleDateString('es-CR')}</small></div>
            {assessment.previousVersions.length > 0 && <details className="grade-history"><summary>Ver versión anterior</summary><ol>{assessment.previousVersions.map((version) => <li key={`${version.grade}-${version.correctedAt}`}>
              <strong>{version.grade}</strong> · publicada el {new Date(`${version.publishedDate}T12:00:00`).toLocaleDateString('es-CR')}
              <small>{version.reason}</small><small>Corregida por {version.correctedBy} el {new Date(version.correctedAt).toLocaleString('es-CR')}</small>
            </li>)}</ol></details>}
          </div>}
        </article>)}</div>}
      </>}
      <small className="footnote">Los registros mostrados son ficticios y se usan para la demostración local.</small>
    </section> : <section className="workspace director-workspace">
      <div className="page-heading"><div><p className="eyebrow">DIRECCIÓN</p><h1>Vínculos autorizados</h1><p className="intro">Registre la referencia del respaldo y conserve el historial de cada cambio.</p></div></div>
      {error && <p className="alert error" role="alert">{error}</p>}{notice && <p className="alert success" role="status">{notice}</p>}
      <div className="director-grid"><form className="panel link-form" onSubmit={submitLink}><h2>Registrar vínculo</h2><p className="muted">Solo se habilita con personas y estudiantes activos.</p>
        <label>Persona encargada<select required value={guardianChoice} onChange={(event) => setGuardianChoice(event.target.value)}><option value="">Seleccione una persona</option>{catalog?.guardians.map((guardian) => <option key={guardian.username} value={guardian.username}>{guardian.displayName}</option>)}</select></label>
        <label>Estudiante<select required value={studentChoice} onChange={(event) => setStudentChoice(event.target.value)}><option value="">Seleccione un estudiante</option>{catalog?.students.map((student) => <option key={student.id} value={student.id}>{student.fullName} · {student.gradeLevel} {student.groupName}</option>)}</select></label>
        <label>Tipo de relación<input required value={relationshipType} onChange={(event) => setRelationshipType(event.target.value)} /></label>
        <label>Referencia del respaldo<input required value={evidenceReference} onChange={(event) => setEvidenceReference(event.target.value)} placeholder="Código o referencia institucional" /></label>
        <label className="pending-toggle"><input type="checkbox" checked={pendingLink} onChange={(event) => setPendingLink(event.target.checked)} /> Dejar pendiente por restricción institucional</label>
        {pendingLink && <label>Motivo de la restricción<textarea required value={restrictionReason} onChange={(event) => setRestrictionReason(event.target.value)} placeholder="Indique el motivo y la referencia para revisión" /></label>}
        <button className="primary-button" disabled={busy || !catalog}>{pendingLink ? 'Registrar solicitud pendiente' : 'Registrar vínculo'}</button>
      </form><aside className="panel audit-note"><span className="note-mark">↗</span><h3>Registro y trazabilidad</h3><p>Cada alta, suspensión o finalización guarda responsable, fecha, motivo y estado anterior para auditoría del prototipo.</p><p className="tiny">La Dirección debe confirmar el proceso institucional de autorización y resguardo de respaldos antes de usar datos reales.</p></aside></div>
      <div className="section-heading"><div><h2>Historial de vínculos</h2><p className="muted">Los cambios surten efecto en la siguiente consulta.</p></div><span className="count-pill">{catalog?.links.length ?? 0}</span></div>
      <label className="reason-field">Motivo para suspender o finalizar<input value={changeReason} onChange={(event) => setChangeReason(event.target.value)} placeholder="Describa el motivo antes de cambiar el estado" /></label>
      <div className="table-wrap"><table><thead><tr><th>Estudiante</th><th>Persona encargada</th><th>Estado</th><th>Respaldo</th><th>Responsable y fecha</th><th>Acción</th></tr></thead><tbody>{catalog?.links.map((link) => <tr key={link.id}><td>{link.studentName}</td><td>{link.guardianUsername}</td><td><span className={`status-pill ${link.status.toLowerCase()}`}>{link.status === 'ACTIVE' ? 'Activo' : link.status === 'SUSPENDED' ? 'Suspendido' : link.status === 'ENDED' ? 'Finalizado' : 'Pendiente · sin acceso'}</span></td><td>{link.evidenceReference}</td><td>{link.createdBy}<small>{new Date(link.createdAt).toLocaleString('es-CR')}</small>{link.changedAt && <small>{link.status === 'PENDING' ? 'Motivo: ' : 'Último cambio: '}{link.changeReason}</small>}</td><td>{link.status === 'ACTIVE' ? <div className="row-actions"><button className="text-button" disabled={busy} onClick={() => updateLink(link, 'SUSPENDED')}>Suspender</button><button className="text-button danger-text" disabled={busy} onClick={() => updateLink(link, 'ENDED')}>Finalizar</button></div> : '—'}</td></tr>)}</tbody></table>{catalog?.links.length === 0 && <p className="empty-table">Aún no se han registrado vínculos.</p>}</div>
      <small className="footnote">Datos de demostración ficticios. No se ingresan políticas ni registros institucionales reales.</small>
    </section>}
  </main>;
}
