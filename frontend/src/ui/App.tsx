import { useState } from 'react';
import type { FormEvent } from 'react';
import './completion.css';
import { CommitteeWorkspace } from './CommitteeWorkspace';
import { DirectorWorkflowPanel } from './DirectorWorkflowPanel';
import { GuardianWorkflowPanel } from './GuardianWorkflowPanel';
import { TeacherWorkspace } from './TeacherWorkspace';
import {
  assignTeacherToGroup, changeLinkStatus, createLink, enrollStudentInGroup,
  getAcademicCatalog, getAppeals, getLinkCatalog, getProgress, getStudents,
  getTeacherCatalog, registerAcademicGroup, registerStudent, registerTeacher, updateTeacher,
  type AcademicCatalog, type Credentials, type LinkCatalog, type LinkView, type Progress,
  type StudentSummary, type TeacherProfile,
} from '../api/academic';

type RoleMode = 'guardian' | 'director' | 'teacher' | 'committee';

export default function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [mode, setMode] = useState<RoleMode>('guardian');
  const [credentials, setCredentials] = useState<Credentials>();
  const [students, setStudents] = useState<StudentSummary[]>([]);
  const [catalog, setCatalog] = useState<LinkCatalog>();
  const [academicCatalog, setAcademicCatalog] = useState<AcademicCatalog>();
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
  const [studentFullName, setStudentFullName] = useState('');
  const [studentGradeLevel, setStudentGradeLevel] = useState('');
  const [studentGroupName, setStudentGroupName] = useState('');
  const [studentTeacherName, setStudentTeacherName] = useState('');
  const [teacherUsername, setTeacherUsername] = useState('');
  const [teacherDisplayName, setTeacherDisplayName] = useState('');
  const [teacherEditUsername, setTeacherEditUsername] = useState('');
  const [teacherEditName, setTeacherEditName] = useState('');
  const [teacherEditActive, setTeacherEditActive] = useState(true);
  const [groupSubject, setGroupSubject] = useState('');
  const [groupGradeLevel, setGroupGradeLevel] = useState('');
  const [groupName, setGroupName] = useState('');
  const [groupPeriod, setGroupPeriod] = useState('2026-II');
  const [assignmentGroup, setAssignmentGroup] = useState('');
  const [assignmentTeacher, setAssignmentTeacher] = useState('');
  const [enrollmentGroup, setEnrollmentGroup] = useState('');
  const [enrollmentStudent, setEnrollmentStudent] = useState('');

  async function signIn(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setError('');
    setNotice('');
    setBusy(true);
    const entered: Credentials = { username: username.trim(), password };
    try {
      if (mode === 'guardian') setStudents((await getStudents(entered)).students);
      else if (mode === 'director') {
        const [links, academic] = await Promise.all([
          getLinkCatalog(entered), getAcademicCatalog(entered),
        ]);
        setCatalog(links);
        setAcademicCatalog(academic);
      } else if (mode === 'teacher') {
        await Promise.all([getTeacherCatalog(entered), getAppeals(entered, 'teacher')]);
      } else {
        await getAppeals(entered, 'committee');
      }
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

  async function submitStudent(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!credentials) return;
    setError('');
    setNotice('');
    setBusy(true);
    try {
      const student = await registerStudent(credentials, {
        fullName: studentFullName.trim(),
        gradeLevel: studentGradeLevel.trim(),
        groupName: studentGroupName.trim(),
        teacherName: studentTeacherName.trim(),
      });
      setNotice(`Ficha creada para ${student.fullName} (${student.id}).`);
      try {
        setCatalog(await getLinkCatalog(credentials));
      } catch {
        setError('La ficha sí se creó, pero no se pudo actualizar la lista. Vuelva a cargar el panel.');
      }
      setStudentFullName('');
      setStudentGradeLevel('');
      setStudentGroupName('');
      setStudentTeacherName('');
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo completar el registro del estudiante.');
    } finally {
      setBusy(false);
    }
  }

  async function refreshAcademicCatalog() {
    if (!credentials) return;
    setAcademicCatalog(await getAcademicCatalog(credentials));
  }

  async function submitTeacher(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!credentials) return;
    setBusy(true);
    setError('');
    setNotice('');
    try {
      const teacher = await registerTeacher(credentials, {
        username: teacherUsername.trim(), displayName: teacherDisplayName.trim(),
      });
      await refreshAcademicCatalog();
      setTeacherUsername('');
      setTeacherDisplayName('');
      setNotice(`Perfil docente disponible para ${teacher.displayName}.`);
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo registrar el perfil docente.');
    } finally {
      setBusy(false);
    }
  }

  async function submitTeacherUpdate(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!credentials || !teacherEditUsername) return;
    setBusy(true);
    setError('');
    setNotice('');
    try {
      const teacher = await updateTeacher(credentials, teacherEditUsername, {
        displayName: teacherEditName.trim(), active: teacherEditActive,
      });
      await refreshAcademicCatalog();
      setNotice(`Perfil de ${teacher.username} actualizado; responsable y fecha quedaron registrados.`);
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo actualizar el perfil docente.');
    } finally {
      setBusy(false);
    }
  }

  async function submitAcademicGroup(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!credentials) return;
    setBusy(true);
    setError('');
    setNotice('');
    try {
      const group = await registerAcademicGroup(credentials, {
        subject: groupSubject.trim(), gradeLevel: groupGradeLevel.trim(),
        groupName: groupName.trim(), period: groupPeriod.trim(),
      });
      await refreshAcademicCatalog();
      setGroupSubject('');
      setGroupGradeLevel('');
      setGroupName('');
      setNotice(`Grupo académico listo: ${group.subject}, ${group.gradeLevel} ${group.groupName} · ${group.period}.`);
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo registrar el grupo.');
    } finally {
      setBusy(false);
    }
  }

  async function submitAssignment(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!credentials) return;
    setBusy(true);
    setError('');
    setNotice('');
    try {
      const assignment = await assignTeacherToGroup(credentials, assignmentGroup,
        assignmentTeacher);
      await refreshAcademicCatalog();
      setNotice(`${assignment.teacherName} quedó asociado con ${assignment.subject}, ${assignment.groupName}.`);
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo asignar el docente.');
    } finally {
      setBusy(false);
    }
  }

  async function submitEnrollment(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (!credentials) return;
    setBusy(true);
    setError('');
    setNotice('');
    try {
      const enrollment = await enrollStudentInGroup(credentials, enrollmentGroup,
        enrollmentStudent);
      await refreshAcademicCatalog();
      setNotice(`${enrollment.studentName} quedó inscrito en ${enrollment.subject}, ${enrollment.groupName}. El vínculo familiar no cambia.`);
    } catch (reason: unknown) {
      setError(reason instanceof Error ? reason.message : 'No se pudo inscribir al estudiante.');
    } finally {
      setBusy(false);
    }
  }

  function selectTeacherForEdit(teacher: TeacherProfile) {
    setTeacherEditUsername(teacher.username);
    setTeacherEditName(teacher.displayName);
    setTeacherEditActive(teacher.active);
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
    setStudentFullName('');
    setStudentGradeLevel('');
    setStudentGroupName('');
    setStudentTeacherName('');
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
          <option value="teacher">Docente</option><option value="committee">Comité de Evaluación</option>
        </select></label>
        <label>Usuario<input autoComplete="username" required value={username} onChange={(event) => setUsername(event.target.value)} placeholder={mode === 'guardian' ? 'encargada.demo' : mode === 'director' ? 'direccion.demo' : mode === 'teacher' ? 'docente.demo' : 'comite.demo'} /></label>
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
      {credentials && <GuardianWorkflowPanel credentials={credentials} students={students} />}
      <small className="footnote">Los registros mostrados son ficticios y se usan para la demostración local.</small>
    </section> : mode === 'director' ? <section className="workspace director-workspace">
      <div className="page-heading"><div><p className="eyebrow">DIRECCIÓN</p><h1>Estudiantes y vínculos</h1><p className="intro">Primero registre la ficha. Luego vincule a una persona encargada con su respaldo autorizado.</p></div></div>
      {error && <p className="alert error" role="alert">{error}</p>}{notice && <p className="alert success" role="status">{notice}</p>}
      {credentials && <DirectorWorkflowPanel credentials={credentials} />}
      <div className="director-grid"><form className="panel link-form" onSubmit={submitStudent}><h2>Registrar estudiante</h2><p className="muted">El Portal genera el identificador. Este paso no concede acceso familiar.</p>
        <label>Nombre completo<input required maxLength={160} value={studentFullName} onChange={(event) => setStudentFullName(event.target.value)} /></label>
        <label>Nivel<input required maxLength={30} value={studentGradeLevel} onChange={(event) => setStudentGradeLevel(event.target.value)} /></label>
        <label>Grupo<input required maxLength={30} value={studentGroupName} onChange={(event) => setStudentGroupName(event.target.value)} /></label>
        <label>Docente<input required maxLength={160} value={studentTeacherName} onChange={(event) => setStudentTeacherName(event.target.value)} /></label>
        <button className="primary-button" disabled={busy || !catalog}>{busy ? 'Guardando…' : 'Crear ficha'}</button>
      </form><aside className="panel audit-note"><span className="note-mark">↗</span><h3>Ficha académica</h3><p>La creación conserva quién registró al estudiante y cuándo. La ficha queda activa y aparece en el formulario de vínculos.</p><p className="tiny">Se usan datos ficticios. No se valida duplicidad por nombre porque falta definir un identificador institucional.</p></aside></div>
      <div className="section-heading"><div><h2>Oferta académica y docentes</h2><p className="muted">Catálogos normalizados para organizar grupos, asignaciones y matrículas.</p></div><span className="count-pill">{academicCatalog?.groups.length ?? 0} grupos</span></div>
      <div className="director-grid">
        <form className="panel link-form" onSubmit={submitTeacher}><h3>Registrar perfil docente</h3><p className="muted">La cuenta institucional debe existir; este formulario no crea contraseñas.</p>
          <label>Usuario institucional<input required maxLength={80} value={teacherUsername} onChange={(event) => setTeacherUsername(event.target.value)} placeholder="docente.demo" /></label>
          <label>Nombre visible<input required maxLength={160} value={teacherDisplayName} onChange={(event) => setTeacherDisplayName(event.target.value)} /></label>
          <button className="primary-button" disabled={busy}>{busy ? 'Guardando…' : 'Registrar perfil'}</button>
        </form>
        <form className="panel link-form" onSubmit={submitAcademicGroup}><h3>Registrar grupo académico</h3><p className="muted">La combinación de curso, nivel, grupo y periodo identifica una sola oferta.</p>
          <label>Curso o materia<input required maxLength={100} value={groupSubject} onChange={(event) => setGroupSubject(event.target.value)} /></label>
          <label>Nivel<input required maxLength={30} value={groupGradeLevel} onChange={(event) => setGroupGradeLevel(event.target.value)} /></label>
          <label>Grupo<input required maxLength={30} value={groupName} onChange={(event) => setGroupName(event.target.value)} /></label>
          <label>Periodo<input required maxLength={40} pattern="[0-9]{4}-(I|II)" title="Use el formato 2026-I o 2026-II" value={groupPeriod} onChange={(event) => setGroupPeriod(event.target.value)} /></label>
          <button className="primary-button" disabled={busy}>{busy ? 'Guardando…' : 'Registrar grupo'}</button>
        </form>
      </div>
      <div className="director-grid">
        <form className="panel link-form" onSubmit={submitAssignment}><h3>Asignar docente a grupo</h3>
          <label>Grupo académico<select required value={assignmentGroup} onChange={(event) => setAssignmentGroup(event.target.value)}><option value="">Seleccione un grupo</option>{academicCatalog?.groups.filter((group) => group.active).map((group) => <option key={group.id} value={group.id}>{group.subject} · {group.gradeLevel} {group.groupName} · {group.period}</option>)}</select></label>
          <label>Perfil docente<select required value={assignmentTeacher} onChange={(event) => setAssignmentTeacher(event.target.value)}><option value="">Seleccione un docente</option>{academicCatalog?.teachers.filter((teacher) => teacher.active).map((teacher) => <option key={teacher.username} value={teacher.username}>{teacher.displayName} · {teacher.username}</option>)}</select></label>
          <button className="primary-button" disabled={busy}>{busy ? 'Guardando…' : 'Asignar docente'}</button>
        </form>
        <form className="panel link-form" onSubmit={submitEnrollment}><h3>Inscribir estudiante</h3><p className="muted">La matrícula académica no concede acceso familiar; eso se administra en CU-02.</p>
          <label>Grupo académico<select required value={enrollmentGroup} onChange={(event) => setEnrollmentGroup(event.target.value)}><option value="">Seleccione un grupo</option>{academicCatalog?.groups.filter((group) => group.active).map((group) => <option key={group.id} value={group.id}>{group.subject} · {group.gradeLevel} {group.groupName} · {group.period}</option>)}</select></label>
          <label>Estudiante<select required value={enrollmentStudent} onChange={(event) => setEnrollmentStudent(event.target.value)}><option value="">Seleccione un estudiante</option>{academicCatalog?.students.map((student) => <option key={student.id} value={student.id}>{student.fullName} · {student.gradeLevel} {student.groupName}</option>)}</select></label>
          <button className="primary-button" disabled={busy}>{busy ? 'Guardando…' : 'Inscribir estudiante'}</button>
        </form>
      </div>
      <div className="director-grid">
        <section className="panel link-form"><h3>Perfiles docentes</h3>
          <div className="table-wrap"><table><thead><tr><th>Docente</th><th>Estado</th><th>Registro</th><th>Acción</th></tr></thead><tbody>{academicCatalog?.teachers.map((teacher) => <tr key={teacher.username}><td>{teacher.displayName}<small>{teacher.username}</small></td><td>{teacher.active ? 'Activo' : 'Inactivo'}</td><td>{teacher.createdBy}<small>{new Date(teacher.createdAt).toLocaleString('es-CR')}</small>{teacher.changedAt && <small>Actualizado por {teacher.changedBy} · {new Date(teacher.changedAt).toLocaleString('es-CR')}</small>}</td><td><button type="button" className="text-button" onClick={() => selectTeacherForEdit(teacher)}>Editar</button></td></tr>)}</tbody></table>{academicCatalog?.teachers.length === 0 && <p className="empty-table">No hay perfiles docentes registrados.</p>}</div>
          {teacherEditUsername && <form className="link-form" onSubmit={submitTeacherUpdate}><h4>Actualizar perfil de {teacherEditUsername}</h4>
            <label>Nombre visible<input required maxLength={160} value={teacherEditName} onChange={(event) => setTeacherEditName(event.target.value)} /></label>
            <label className="pending-toggle"><input type="checkbox" checked={teacherEditActive} onChange={(event) => setTeacherEditActive(event.target.checked)} /> Perfil activo para nuevas asignaciones</label>
            <button className="primary-button" disabled={busy}>{busy ? 'Guardando…' : 'Guardar cambios'}</button>
          </form>}
        </section>
        <section className="panel link-form"><h3>Grupos registrados</h3><div className="table-wrap"><table><thead><tr><th>Curso</th><th>Nivel y grupo</th><th>Periodo</th><th>Estado</th></tr></thead><tbody>{academicCatalog?.groups.map((group) => <tr key={group.id}><td>{group.subject}</td><td>{group.gradeLevel} · {group.groupName}</td><td>{group.period}</td><td>{group.active ? 'Activo' : 'Inactivo'}</td></tr>)}</tbody></table>{academicCatalog?.groups.length === 0 && <p className="empty-table">No hay grupos registrados.</p>}</div></section>
      </div>
      <div className="director-grid">
        <section className="panel link-form"><h3>Asignaciones vigentes</h3><ul>{academicCatalog?.assignments.filter((assignment) => assignment.active).map((assignment) => <li key={assignment.id}>{assignment.teacherName} · {assignment.subject} · {assignment.gradeLevel} {assignment.groupName} · {assignment.period}<small> Responsable: {assignment.assignedBy}, {new Date(assignment.assignedAt).toLocaleString('es-CR')}</small></li>)}</ul></section>
        <section className="panel link-form"><h3>Matrículas vigentes</h3><ul>{academicCatalog?.enrollments.filter((enrollment) => enrollment.active).map((enrollment) => <li key={enrollment.id}>{enrollment.studentName} · {enrollment.subject} · {enrollment.gradeLevel} {enrollment.groupName} · {enrollment.period}<small> Responsable: {enrollment.enrolledBy}, {new Date(enrollment.enrolledAt).toLocaleString('es-CR')}</small></li>)}</ul></section>
      </div>
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
    </section> : mode === 'teacher'
      ? <TeacherWorkspace credentials={credentials} />
      : <CommitteeWorkspace credentials={credentials} />}
  </main>;
}
