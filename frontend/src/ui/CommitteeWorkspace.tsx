import { useEffect, useState } from 'react';
import type { FormEvent } from 'react';
import {
  contributeToAppeal, getAppeals, resolveAppeal,
  type Appeal, type Credentials,
} from '../api/academic';

type Props = { credentials: Credentials };

export function CommitteeWorkspace({ credentials }: Props) {
  const [appeals, setAppeals] = useState<Appeal[]>([]);
  const [appealId, setAppealId] = useState('');
  const [contributionType, setContributionType] = useState('CONTRIBUTION');
  const [targetUsername, setTargetUsername] = useState('docente.demo');
  const [contribution, setContribution] = useState('');
  const [outcome, setOutcome] = useState('UPHELD');
  const [foundation, setFoundation] = useState('');
  const [members, setMembers] = useState('');
  const [correctionRequired, setCorrectionRequired] = useState(false);
  const [notice, setNotice] = useState('');
  const [error, setError] = useState('');
  const [busy, setBusy] = useState(false);

  async function refresh() {
    const next = await getAppeals(credentials, 'committee');
    setAppeals(next);
    if (!appealId && next.length > 0) setAppealId(next[0].id);
  }

  useEffect(() => { void refresh().catch((cause: unknown) => {
    setError(cause instanceof Error ? cause.message : 'No se pudieron cargar las apelaciones.');
  }); }, [credentials]);

  async function addContribution(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); if (!appealId) return;
    setBusy(true); setError(''); setNotice('');
    try {
      await contributeToAppeal(credentials, 'committee', appealId, {
        type: contributionType, content: contribution.trim(),
        targetUsername: contributionType === 'REQUEST_INFORMATION' ? targetUsername.trim() : undefined,
      });
      setNotice(contributionType === 'REQUEST_INFORMATION'
        ? 'Solicitud registrada; el expediente queda pendiente de información.'
        : 'Aporte agregado al expediente.');
      setContribution(''); await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo agregar el aporte.');
    } finally { setBusy(false); }
  }

  async function submitResolution(event: FormEvent<HTMLFormElement>) {
    event.preventDefault(); if (!appealId) return;
    setBusy(true); setError(''); setNotice('');
    try {
      const result = await resolveAppeal(credentials, appealId, {
        outcome, foundation: foundation.trim(), committeeMembers: members.trim(), correctionRequired,
      });
      setNotice(`Resolución ${result.outcome} registrada y notificada a la persona encargada.`);
      setFoundation(''); setCorrectionRequired(false); await refresh();
    } catch (cause: unknown) {
      setError(cause instanceof Error ? cause.message : 'No se pudo registrar la resolución.');
    } finally { setBusy(false); }
  }

  const selected = appeals.find((appeal) => appeal.id === appealId);
  return <section className="workspace director-workspace">
    <div className="page-heading"><div><p className="eyebrow">COMITÉ DE EVALUACIÓN</p><h1>Expedientes de apelación</h1>
      <p className="intro">Revise evidencia, agregue aportes y registre la resolución con fundamento.</p></div></div>
    {error && <p className="alert error" role="alert">{error}</p>}{notice && <p className="alert success" role="status">{notice}</p>}
    <div className="director-grid">
      <section className="panel link-form"><h2>Apelaciones · CU-07 / CU-13 / CU-14</h2>
        {appeals.length === 0 ? <p className="muted">No hay expedientes asignados.</p> : <>
          <label>Expediente<select value={appealId} onChange={(event) => setAppealId(event.target.value)}>{appeals.map((appeal) => <option key={appeal.id} value={appeal.id}>{appeal.appealNumber} · {appeal.status}</option>)}</select></label>
          {selected && <article className="panel"><strong>{selected.appealNumber} · {selected.status}</strong>
            <p>Nota: {selected.assessmentId} · puntos apelados: {selected.points}</p><p>{selected.reason}</p>
            <p>Respaldo: {selected.evidenceReference}</p><small>Presentada {new Date(selected.createdAt).toLocaleString('es-CR')} · apelar hasta {selected.deadlineAt} · resolver antes de {selected.resolutionDueAt}</small>
          </article>}
        </>}
      </section>
      <form className="panel link-form" onSubmit={addContribution}><h2>Aporte o solicitud de información</h2>
        <label>Tipo<select value={contributionType} onChange={(event) => setContributionType(event.target.value)}>
          <option value="CONTRIBUTION">Agregar aporte</option><option value="REQUEST_INFORMATION">Solicitar información</option>
        </select></label>
        {contributionType === 'REQUEST_INFORMATION' && <label>Destinatario<input required value={targetUsername} onChange={(event) => setTargetUsername(event.target.value)} /></label>}
        <label>Contenido<textarea required value={contribution} onChange={(event) => setContribution(event.target.value)} /></label>
        <button className="primary-button" disabled={busy || !appealId}>Guardar en el expediente</button>
      </form>
    </div>
    <form className="panel link-form" onSubmit={submitResolution}><h2>Registrar resolución</h2>
      <p className="muted">La calificación publicada se mantiene; si debe cambiar, el docente usa CU-09.</p>
      <label>Resultado<select value={outcome} onChange={(event) => setOutcome(event.target.value)}><option value="UPHELD">Se mantiene la calificación</option><option value="MODIFIED">Se modifica la calificación</option><option value="REJECTED">Apelación rechazada</option></select></label>
      <label>Fundamento<textarea required value={foundation} onChange={(event) => setFoundation(event.target.value)} /></label>
      <label>Integrantes que resuelven<input required value={members} onChange={(event) => setMembers(event.target.value)} /></label>
      <label className="pending-toggle"><input type="checkbox" checked={correctionRequired} onChange={(event) => setCorrectionRequired(event.target.checked)} /> Solicitar corrección por CU-09</label>
      <button className="primary-button" disabled={busy || !appealId || selected?.status !== 'IN_REVIEW'}>Guardar resolución</button>
    </form>
  </section>;
}
