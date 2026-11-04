#!/usr/bin/env bash
set -euo pipefail
cd -- "$(dirname -- "$0")"
mkdir -p target/demo
javac --release 21 -encoding UTF-8 -d target/demo src/main/java/cr/ac/ucr/if6100/portal/evaluacion/domain/*.java demo/DemoEvaluacion.java
java -cp target/demo DemoEvaluacion
