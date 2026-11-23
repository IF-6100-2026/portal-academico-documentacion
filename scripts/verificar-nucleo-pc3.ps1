param(
    [string]$BaseUrl = 'http://localhost:8080'
)

$ErrorActionPreference = 'Stop'
$guardianPassword = if ($env:DEMO_GUARDIAN_PASSWORD) { $env:DEMO_GUARDIAN_PASSWORD } else { 'demo-familiar-2026' }
$emptyGuardianPassword = if ($env:DEMO_EMPTY_GUARDIAN_PASSWORD) { $env:DEMO_EMPTY_GUARDIAN_PASSWORD } else { 'demo-sin-vinculos-2026' }
$directorPassword = if ($env:DEMO_DIRECTOR_PASSWORD) { $env:DEMO_DIRECTOR_PASSWORD } else { 'demo-direccion-2026' }
$teacherPassword = if ($env:DEMO_TEACHER_PASSWORD) { $env:DEMO_TEACHER_PASSWORD } else { 'demo-docente-2026' }
$guardian = @{ username = 'encargada.demo'; password = $guardianPassword }
$emptyGuardian = @{ username = 'sin-vinculos.demo'; password = $emptyGuardianPassword }
$director = @{ username = 'direccion.demo'; password = $directorPassword }
$teacher = @{ username = 'docente.demo'; password = $teacherPassword }
$results = [System.Collections.Generic.List[string]]::new()

function Invoke-Scenario {
    param(
        [string]$Name,
        [string]$Method,
        [string]$Path,
        [int]$ExpectedStatus,
        [hashtable]$Credential,
        [object]$Body
    )

    $headers = @{ Accept = 'application/json' }
    if ($Credential) {
        $plainCredential = '{0}:{1}' -f $Credential.username, $Credential.password
        $encodedCredential = [Convert]::ToBase64String([Text.Encoding]::ASCII.GetBytes($plainCredential))
        $headers.Authorization = "Basic $encodedCredential"
    }
    $request = @{
        Uri = "$BaseUrl$Path"
        Method = $Method
        Headers = $headers
        SkipHttpErrorCheck = $true
        ErrorAction = 'Stop'
    }
    if ($null -ne $Body) {
        $request.ContentType = 'application/json'
        $request.Body = $Body | ConvertTo-Json -Depth 8
    }
    $response = Invoke-WebRequest @request
    if ([int]$response.StatusCode -ne $ExpectedStatus) {
        throw "${Name}: se esperaba HTTP $ExpectedStatus y se recibió HTTP $($response.StatusCode)."
    }
    $parsedBody = $null
    if ($response.Content) {
        try { $parsedBody = $response.Content | ConvertFrom-Json } catch { $parsedBody = $response.Content }
    }
    $results.Add("PASS $Name (HTTP $ExpectedStatus)")
    return $parsedBody
}

$null = Invoke-Scenario 'Health pública' 'GET' '/api/v1/health' 200 $null $null
$studentList = Invoke-Scenario 'CU-01 lista estudiantes vinculados' 'GET' '/api/v1/guardian/students' 200 $guardian $null
if (@($studentList.students).Count -ne 1) { throw 'La cuenta demo debe tener exactamente un vínculo activo inicial.' }
$studentId = '11111111-1111-4111-8111-111111111111'
$progress = Invoke-Scenario 'CU-01 consulta publicaciones del periodo' 'GET' "/api/v1/guardian/students/$studentId/progress?period=2026-II" 200 $guardian $null
if (@($progress.assessments).Count -ne 2) { throw 'La consulta debe incluir dos publicaciones y excluir el borrador.' }
if (@($progress.assessments | Where-Object { $_.title -match 'borrador' }).Count -ne 0) { throw 'La consulta expuso una evaluación en borrador.' }
if (@($progress.assessments | Where-Object { $_.grade -eq 85 }).Count -ne 1) { throw 'No se devolvió la calificación publicada de demostración.' }
$results.Add('PASS CU-01 filtra borradores y devuelve calificación publicada')
$emptyList = Invoke-Scenario 'CU-01 cuenta sin vínculos activos' 'GET' '/api/v1/guardian/students' 200 $emptyGuardian $null
if (@($emptyList.students).Count -ne 0) { throw 'La cuenta sin vínculos recibió estudiantes.' }
$null = Invoke-Scenario 'CU-01 deniega acceso horizontal sin revelar registro' 'GET' '/api/v1/guardian/students/22222222-2222-4222-8222-222222222222/progress?period=2026-II' 403 $guardian $null
$emptyPeriod = Invoke-Scenario 'CU-01 periodo sin publicaciones' 'GET' "/api/v1/guardian/students/$studentId/progress?period=2026-I" 200 $guardian $null
if (@($emptyPeriod.assessments).Count -ne 0) { throw 'El periodo sin publicaciones no devolvió una lista vacía.' }
$null = Invoke-Scenario 'CU-01 sesión inválida' 'GET' "/api/v1/guardian/students/$studentId/progress?period=2026-II" 401 $null $null

$null = Invoke-Scenario 'CU-02 catálogo de Dirección' 'GET' '/api/v1/director/guardian-links/catalog' 200 $director $null
$null = Invoke-Scenario 'CU-02 deniega rol docente' 'GET' '/api/v1/director/guardian-links/catalog' 403 $teacher $null
$null = Invoke-Scenario 'CU-02 exige respaldo' 'POST' '/api/v1/director/guardian-links' 400 $director @{
    guardianUsername = 'sin-vinculos.demo'
    studentId = '22222222-2222-4222-8222-222222222222'
    relationshipType = 'Persona encargada autorizada'
    evidenceReference = ''
}
$null = Invoke-Scenario 'CU-02 rechaza estudiante inactivo' 'POST' '/api/v1/director/guardian-links' 404 $director @{
    guardianUsername = 'sin-vinculos.demo'
    studentId = '33333333-3333-4333-8333-333333333333'
    relationshipType = 'Persona encargada autorizada'
    evidenceReference = 'RESPALDO-DEMO-SMOKE'
}
$newLink = Invoke-Scenario 'CU-02 crea vínculo con respaldo' 'POST' '/api/v1/director/guardian-links' 201 $director @{
    guardianUsername = 'sin-vinculos.demo'
    studentId = '22222222-2222-4222-8222-222222222222'
    relationshipType = 'Persona encargada autorizada'
    evidenceReference = "RESPALDO-DEMO-SMOKE-$([DateTime]::UtcNow.ToString('yyyyMMddHHmmss'))"
}
if ($newLink.status -ne 'ACTIVE' -or $newLink.createdBy -ne 'direccion.demo') {
    throw 'La creación no conservó el estado activo y el responsable.'
}
$duplicate = Invoke-Scenario 'CU-02 evita vínculo activo duplicado' 'POST' '/api/v1/director/guardian-links' 200 $director @{
    guardianUsername = 'sin-vinculos.demo'
    studentId = '22222222-2222-4222-8222-222222222222'
    relationshipType = 'Persona encargada autorizada'
    evidenceReference = 'RESPALDO-DEMO-DUPLICADO'
}
if (-not $duplicate.activatedAt) { throw 'No se informó la fecha del vínculo existente.' }
$afterCreate = Invoke-Scenario 'CU-01 vínculo disponible tras la siguiente solicitud' 'GET' '/api/v1/guardian/students' 200 $emptyGuardian $null
if (@($afterCreate.students | Where-Object { $_.id -eq '22222222-2222-4222-8222-222222222222' }).Count -ne 1) {
    throw 'El vínculo recién creado no habilitó al estudiante.'
}
$suspended = Invoke-Scenario 'CU-02 suspende con motivo' 'PATCH' "/api/v1/director/guardian-links/$($newLink.id)/status" 200 $director @{
    status = 'SUSPENDED'
    reason = 'Prueba de demostración del PC3'
}
if ($suspended.status -ne 'SUSPENDED' -or -not $suspended.changedAt) { throw 'No se registró el cambio de estado.' }
$null = Invoke-Scenario 'CU-01 acceso revocado en la siguiente solicitud' 'GET' '/api/v1/guardian/students' 200 $emptyGuardian $null
$null = Invoke-Scenario 'CU-01 deniega progreso tras suspensión' 'GET' '/api/v1/guardian/students/22222222-2222-4222-8222-222222222222/progress?period=2026-II' 403 $emptyGuardian $null
$endedLink = Invoke-Scenario 'CU-02 crea nuevo vínculo después de suspensión' 'POST' '/api/v1/director/guardian-links' 201 $director @{
    guardianUsername = 'sin-vinculos.demo'
    studentId = '22222222-2222-4222-8222-222222222222'
    relationshipType = 'Persona encargada autorizada'
    evidenceReference = "RESPALDO-DEMO-FINAL-$([DateTime]::UtcNow.ToString('yyyyMMddHHmmss'))"
}
$ended = Invoke-Scenario 'CU-02 finaliza vínculo con motivo' 'PATCH' "/api/v1/director/guardian-links/$($endedLink.id)/status" 200 $director @{
    status = 'ENDED'
    reason = 'Finalización verificada por la prueba de demostración del PC3'
}
if ($ended.status -ne 'ENDED' -or -not $ended.changedAt -or $ended.changedBy -ne 'direccion.demo') {
    throw 'La finalización no conservó estado, responsable y fecha.'
}
$afterEnding = Invoke-Scenario 'CU-01 deja de listar relación finalizada' 'GET' '/api/v1/guardian/students' 200 $emptyGuardian $null
if (@($afterEnding.students | Where-Object { $_.id -eq '22222222-2222-4222-8222-222222222222' }).Count -ne 0) {
    throw 'La cuenta de demostración conservó acceso a un vínculo finalizado.'
}
$null = Invoke-Scenario 'CU-01 deniega progreso tras finalización' 'GET' '/api/v1/guardian/students/22222222-2222-4222-8222-222222222222/progress?period=2026-II' 403 $emptyGuardian $null

Write-Output ($results -join [Environment]::NewLine)
Write-Output "Completados: $($results.Count) escenarios de API."
