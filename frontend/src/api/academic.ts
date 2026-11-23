export type Credentials = { username: string; password: string };
export type StudentSummary = {
  id: string; fullName: string; gradeLevel: string; groupName: string;
  latestPublications: Array<{ subject: string; title: string; publishedAt: string }>;
  pendingNotices: number;
};
export type StudentProfile = {
  id: string; fullName: string; gradeLevel: string; groupName: string; teacherName: string;
};
export type PublishedAssessment = {
  id: string; subject: string; title: string; assignedDate: string; instructions: string;
  grade: number | null; observation: string | null; gradePublishedDate: string | null;
  previousVersions: Array<{
    grade: number; observation: string; publishedDate: string; reason: string;
    correctedBy: string; correctedAt: string;
  }>;
};
export type Progress = {
  student: StudentProfile;
  period: string; assessments: PublishedAssessment[]; consultedAt: string;
};
export type LinkView = {
  id: string; guardianUsername: string; studentId: string; studentName: string;
  relationshipType: string; evidenceReference: string;
  status: 'ACTIVE' | 'SUSPENDED' | 'ENDED' | 'PENDING'; createdBy: string; createdAt: string;
  changedBy: string | null; changedAt: string | null; changeReason: string | null;
};
export type LinkCatalog = {
  guardians: Array<{ username: string; displayName: string }>;
  students: Array<{ id: string; fullName: string; gradeLevel: string; groupName: string }>;
  links: LinkView[];
};

export class ApiFailure extends Error {
  constructor(message: string, readonly status: number) { super(message); }
}

const baseUrl = (import.meta.env.VITE_API_URL?.trim() || 'http://localhost:8080').replace(/\/+$/, '');

async function request<T>(path: string, credentials: Credentials, init: RequestInit = {}): Promise<T> {
  const headers = new Headers(init.headers);
  headers.set('Authorization', `Basic ${btoa(`${credentials.username}:${credentials.password}`)}`);
  if (init.body) headers.set('Content-Type', 'application/json');
  const response = await fetch(`${baseUrl}${path}`, {
    ...init, headers, signal: AbortSignal.timeout(10000), cache: 'no-store',
  });
  if (!response.ok) {
    let message = response.status === 401
      ? 'Usuario o contraseña incorrectos.' : `No se pudo completar la solicitud (${response.status}).`;
    try {
      const body: unknown = await response.json();
      if (typeof body === 'object' && body !== null && 'message' in body && typeof body.message === 'string') {
        message = body.message;
      }
    } catch {
      // La respuesta de error no siempre contiene JSON.
    }
    throw new ApiFailure(message, response.status);
  }
  return await response.json() as T;
}

export function getStudents(credentials: Credentials): Promise<{ students: StudentSummary[] }> {
  return request('/api/v1/guardian/students', credentials);
}

export function getProgress(credentials: Credentials, studentId: string, period: string): Promise<Progress> {
  return request(`/api/v1/guardian/students/${encodeURIComponent(studentId)}/progress?period=${encodeURIComponent(period)}`, credentials);
}

export function getLinkCatalog(credentials: Credentials): Promise<LinkCatalog> {
  return request('/api/v1/director/guardian-links/catalog', credentials);
}

export function createLink(credentials: Credentials, input: {
  guardianUsername: string; studentId: string; relationshipType: string; evidenceReference: string;
  pending: boolean; restrictionReason: string;
}): Promise<LinkView | { activatedAt: string } | { requestedAt: string }> {
  return request('/api/v1/director/guardian-links', credentials, {
    method: 'POST', body: JSON.stringify(input),
  });
}

export function changeLinkStatus(credentials: Credentials, linkId: string,
  input: { status: 'SUSPENDED' | 'ENDED'; reason: string }): Promise<LinkView> {
  return request(`/api/v1/director/guardian-links/${encodeURIComponent(linkId)}/status`, credentials, {
    method: 'PATCH', body: JSON.stringify(input),
  });
}
