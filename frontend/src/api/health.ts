export type HealthResponse = { status: string; service: string; timestamp: string };

export async function getHealth(signal?: AbortSignal): Promise<HealthResponse> {
  const baseUrl = (import.meta.env.VITE_API_URL?.trim() || 'http://localhost:8080').replace(/\/+$/, '');
  const response = await fetch(`${baseUrl}/api/v1/health`, {
    signal: signal ? AbortSignal.any([signal, AbortSignal.timeout(10000)]) : AbortSignal.timeout(10000),
  });
  if (!response.ok) throw new Error(`Backend respondió HTTP ${response.status}`);
  const data: unknown = await response.json();
  if (typeof data !== 'object' || data === null ||
      !('status' in data) || typeof data.status !== 'string' ||
      !('service' in data) || typeof data.service !== 'string' ||
      !('timestamp' in data) || typeof data.timestamp !== 'string' ||
      Number.isNaN(Date.parse(data.timestamp))) {
    throw new Error('El backend devolvió una respuesta de salud inválida');
  }
  if (data.status !== 'UP') throw new Error(`Backend no disponible: ${data.status}`);
  return { status: data.status, service: data.service, timestamp: data.timestamp };
}
