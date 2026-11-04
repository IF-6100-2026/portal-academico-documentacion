$ErrorActionPreference = "Stop"
Set-Location $PSScriptRoot
New-Item -ItemType Directory -Force -Path "target/demo" | Out-Null
$fuentes = @(Get-ChildItem "src/main/java/cr/ac/ucr/if6100/portal/evaluacion/domain/*.java" | ForEach-Object { $_.FullName })
& javac --release 21 -encoding UTF-8 -d target/demo @fuentes demo/DemoEvaluacion.java
if ($LASTEXITCODE -ne 0) { throw "Falló la compilación; confirme que utiliza JDK 21." }
& java -cp target/demo DemoEvaluacion
if ($LASTEXITCODE -ne 0) { throw "Falló la demostración." }
