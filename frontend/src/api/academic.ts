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
export type RegisteredStudent = {
  id: string; fullName: string; gradeLevel: string; groupName: string; teacherName: string;
  active: boolean; createdBy: string; createdAt: string;
};
export type TeacherProfile = {
  username: string; displayName: string; active: boolean; createdBy: string; createdAt: string;
  changedBy: string | null; changedAt: string | null;
};
export type AcademicGroup = {
  id: string; subject: string; gradeLevel: string; groupName: string; period: string;
  active: boolean; createdBy: string; createdAt: string;
};
export type TeachingAssignment = {
  id: string; groupId: string; subject: string; gradeLevel: string; groupName: string;
  period: string; teacherUsername: string; teacherName: string; active: boolean;
  assignedBy: string; assignedAt: string;
};
export type StudentOption = {
  id: string; fullName: string; gradeLevel: string; groupName: string;
};
export type StudentEnrollment = {
  id: string; studentId: string; studentName: string; groupId: string; subject: string;
  gradeLevel: string; groupName: string; period: string; active: boolean;
  enrolledBy: string; enrolledAt: string;
};
export type AcademicCatalog = {
  teachers: TeacherProfile[]; groups: AcademicGroup[]; assignments: TeachingAssignment[];
  enrollments: StudentEnrollment[]; students: StudentOption[];
};
export type TeacherCatalog = {
  groups: Array<{
    id: string; subject: string; gradeLevel: string; groupName: string;
    period: string; studentCount: number;
  }>;
  assessments: Array<{
    id: string; groupId: string; subject: string; title: string; studentId: string;
    studentName: string; dueDate: string; grade: number | null; gradeVersion: number | null;
  }>;
  students: Array<{ studentId: string; studentName: string; groupId: string; subject: string;
    gradeLevel: string; groupName: string; period: string; guardianUsername: string | null }>;
};
export type WorkflowSettings = {
  appealWindowDays: number; resolutionWindowDays: number;
  reminderIntervalHours: number; maxReminders: number;
  changedBy: string; changedAt: string;
};
export type FollowupDashboard = {
  studentsNeedingAttention: Array<{
    studentId: string; fullName: string; activeGuardians: number; pendingNotifications: number;
  }>;
  openAppeals: number; openComplaints: number;
};
export type PortalNotification = {
  id: string; studentId: string; type: string; referenceId: string; subject: string;
  createdAt: string; readAt: string | null; reminderCount: number;
};
export type StudentMessage = {
  id: string; studentId: string; senderUsername: string; recipientUsername: string;
  subject: string; content: string; replyToMessageId: string | null;
  createdAt: string; readAt: string | null;
};
export type Complaint = {
  id: string; complaintNumber: number; studentId: string; studentName: string;
  guardianUsername: string; informationType: string; subject: string; period: string;
  content: string; status: string; finding: string | null; response: string | null;
  relatedPublicationId: string | null; createdAt: string; updatedBy: string | null;
  updatedAt: string | null;
};
export type Appeal = {
  id: string; appealNumber: string; assessmentId: string; guardianUsername: string;
  studentId: string; points: string; reason: string; evidenceReference: string;
  status: string; createdAt: string; deadlineAt: string; outcome: string | null;
  resolutionDueAt: string; foundation: string | null; correctionRequired: boolean | null;
};
export type AppealResolution = {
  id: string; appealId: string; outcome: string; foundation: string; committeeMembers: string;
  correctionRequired: boolean; resolvedBy: string; resolvedAt: string;
  guardianReadAt: string | null; closedAt: string | null;
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
  if (response.status === 204) return undefined as T;
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

export function registerStudent(credentials: Credentials, input: {
  fullName: string; gradeLevel: string; groupName: string; teacherName: string;
}): Promise<RegisteredStudent> {
  return request('/api/v1/director/students', credentials, {
    method: 'POST', body: JSON.stringify(input),
  });
}

export function getAcademicCatalog(credentials: Credentials): Promise<AcademicCatalog> {
  return request('/api/v1/director/academic/catalog', credentials);
}

export function registerTeacher(credentials: Credentials, input: {
  username: string; displayName: string;
}): Promise<TeacherProfile> {
  return request('/api/v1/director/academic/teachers', credentials, {
    method: 'POST', body: JSON.stringify(input),
  });
}

export function updateTeacher(credentials: Credentials, username: string, input: {
  displayName: string; active: boolean;
}): Promise<TeacherProfile> {
  return request(`/api/v1/director/academic/teachers/${encodeURIComponent(username)}`, credentials, {
    method: 'PATCH', body: JSON.stringify(input),
  });
}

export function registerAcademicGroup(credentials: Credentials, input: {
  subject: string; gradeLevel: string; groupName: string; period: string;
}): Promise<AcademicGroup> {
  return request('/api/v1/director/academic/groups', credentials, {
    method: 'POST', body: JSON.stringify(input),
  });
}

export function assignTeacherToGroup(credentials: Credentials, groupId: string,
  teacherUsername: string): Promise<TeachingAssignment> {
  return request(`/api/v1/director/academic/groups/${encodeURIComponent(groupId)}/teachers`,
    credentials, { method: 'POST', body: JSON.stringify({ teacherUsername }) });
}

export function enrollStudentInGroup(credentials: Credentials, groupId: string,
  studentId: string): Promise<StudentEnrollment> {
  return request(`/api/v1/director/academic/groups/${encodeURIComponent(groupId)}/students`,
    credentials, { method: 'POST', body: JSON.stringify({ studentId }) });
}

export function getTeacherCatalog(credentials: Credentials): Promise<TeacherCatalog> {
  return request('/api/v1/teacher/catalog', credentials);
}

export function publishAssessment(credentials: Credentials, groupId: string, input: {
  title: string; assignedDate: string; dueDate: string; instructions: string;
  confirmedWithNotebook: boolean;
}): Promise<{ id: string; status: string; title: string; studentCount: number;
  notificationCount: number; alreadyPublished: boolean; message: string | null }> {
  return request(`/api/v1/teacher/groups/${encodeURIComponent(groupId)}/assessments`, credentials, {
    method: 'POST', body: JSON.stringify(input),
  });
}

export function publishGrade(credentials: Credentials, assessmentId: string, input: {
  numericValue: number; observation: string; publishedDate: string; confirmedWithNotebook: boolean;
}): Promise<{ assessmentId: string; numericValue: number; status: string;
  gradeRecordId: string | null; version: number; message: string | null }> {
  return request(`/api/v1/teacher/assessments/${encodeURIComponent(assessmentId)}/grades`, credentials, {
    method: 'POST', body: JSON.stringify(input),
  });
}

export function correctGrade(credentials: Credentials, assessmentId: string, input: {
  numericValue: number; observation: string; publishedDate: string; reason: string;
  confirmedWithNotebook: boolean; expectedVersion: number;
}): Promise<{ assessmentId: string; numericValue: number; status: string;
  gradeRecordId: string; version: number; message: string | null }> {
  return request(`/api/v1/teacher/assessments/${encodeURIComponent(assessmentId)}/grades`,
    credentials, { method: 'PATCH', body: JSON.stringify(input) });
}

export function getWorkflowSettings(credentials: Credentials): Promise<WorkflowSettings | null> {
  return request('/api/v1/director/workflow/settings', credentials);
}

export function updateWorkflowSettings(credentials: Credentials, input: {
  appealWindowDays: number; resolutionWindowDays: number;
  reminderIntervalHours: number; maxReminders: number;
}): Promise<WorkflowSettings> {
  return request('/api/v1/director/workflow/settings', credentials, {
    method: 'PUT', body: JSON.stringify(input),
  });
}

export function getSupervision(credentials: Credentials): Promise<FollowupDashboard> {
  return request('/api/v1/director/workflow/supervision', credentials);
}

export function processReminders(credentials: Credentials): Promise<{
  remindersCreated: number; recipients: string[]; processedAt: string;
  alertsCreated: number; revokedNoticesClosed: number;
}> {
  return request('/api/v1/director/workflow/reminders/process', credentials, { method: 'POST' });
}

export function getNotifications(credentials: Credentials): Promise<PortalNotification[]> {
  return request('/api/v1/guardian/notifications', credentials);
}

export function markNotificationRead(credentials: Credentials, id: string): Promise<PortalNotification> {
  return request(`/api/v1/guardian/notifications/${encodeURIComponent(id)}/read`, credentials,
    { method: 'POST' });
}

export function getMessages(credentials: Credentials, studentId: string,
  role: 'guardian' | 'teacher' = 'guardian'): Promise<StudentMessage[]> {
  return request(`/api/v1/${role}/students/${encodeURIComponent(studentId)}/messages`, credentials);
}

export function sendMessage(credentials: Credentials, studentId: string, input: {
  recipientUsername: string; subject: string; content: string; topic: string;
  replyToMessageId?: string;
}, role: 'guardian' | 'teacher' = 'guardian'): Promise<StudentMessage> {
  return request(`/api/v1/${role}/students/${encodeURIComponent(studentId)}/messages`, credentials,
    { method: 'POST', body: JSON.stringify(input) });
}

export function markMessageRead(credentials: Credentials, id: string,
  role: 'guardian' | 'teacher' = 'guardian'): Promise<StudentMessage> {
  return request(`/api/v1/${role}/messages/${encodeURIComponent(id)}/read`, credentials,
    { method: 'POST' });
}

export function fileComplaint(credentials: Credentials, studentId: string, input: {
  informationType: string; subject: string; period: string; content: string;
  assessmentId?: string; reasonType?: string;
}): Promise<Complaint> {
  return request(`/api/v1/guardian/students/${encodeURIComponent(studentId)}/complaints`, credentials,
    { method: 'POST', body: JSON.stringify(input) });
}

export function getComplaints(credentials: Credentials): Promise<Complaint[]> {
  return request('/api/v1/director/complaints', credentials);
}

export function attendComplaint(credentials: Credentials, complaintId: string, input: {
  finding: string; response: string; teacherUsername?: string;
}): Promise<Complaint> {
  return request(`/api/v1/director/complaints/${encodeURIComponent(complaintId)}`, credentials,
    { method: 'PATCH', body: JSON.stringify(input) });
}

export function fileAppeal(credentials: Credentials, studentId: string, input: {
  assessmentId: string; points: string; reason: string; evidenceReference: string;
}): Promise<Appeal> {
  return request(`/api/v1/guardian/students/${encodeURIComponent(studentId)}/appeals`, credentials,
    { method: 'POST', body: JSON.stringify(input) });
}

export function getGuardianAppeals(credentials: Credentials): Promise<Appeal[]> {
  return request('/api/v1/guardian/appeals', credentials);
}

export function getAppeals(credentials: Credentials, role: 'teacher' | 'director' | 'committee'):
  Promise<Appeal[]> {
  return request(`/api/v1/${role}/appeals`, credentials);
}

export function contributeToAppeal(credentials: Credentials, role: 'teacher' | 'director' | 'committee',
  appealId: string, input: { type: string; content: string; targetUsername?: string }): Promise<unknown> {
  return request(`/api/v1/${role}/appeals/${encodeURIComponent(appealId)}/contributions`, credentials,
    { method: 'POST', body: JSON.stringify(input) });
}

export function resolveAppeal(credentials: Credentials, appealId: string, input: {
  outcome: string; foundation: string; committeeMembers: string; correctionRequired: boolean;
}): Promise<AppealResolution> {
  return request(`/api/v1/committee/appeals/${encodeURIComponent(appealId)}/resolution`, credentials,
    { method: 'POST', body: JSON.stringify(input) });
}

export function readAppealResolution(credentials: Credentials, appealId: string): Promise<AppealResolution> {
  return request(`/api/v1/guardian/appeals/${encodeURIComponent(appealId)}/resolution/read`, credentials,
    { method: 'POST' });
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
