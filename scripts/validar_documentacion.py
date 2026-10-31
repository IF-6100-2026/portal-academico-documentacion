"""Valida rutas locales y trazabilidad de PC2 sin dependencias externas.
No ejecuta Gherkin ni valida visualmente los diagramas.
"""
from pathlib import Path
from urllib.parse import unquote
import re
import sys
import xml.etree.ElementTree as ET

root = Path(__file__).resolve().parents[1]
errors = []
for path in [root / 'README.md', *sorted((root / 'docs').rglob('*.md'))]:
    for link in re.findall(r'!?\[[^\]]*\]\(([^\s)]+)(?:\s+[^)]*)?\)', path.read_text()):
        if ':' in link or link.startswith('#'):
            continue
        if not (path.parent / unquote(link.split('#')[0])).exists():
            errors.append(f'{path.relative_to(root)}: ruta inexistente {link}')

features = sorted((root / 'docs/04-pruebas').glob('CU-*.feature'))
cases = sorted((root / 'docs/03-casos-de-uso').glob('CU-*.md'))
ids = []
for path in features:
    text = path.read_text()
    scenarios = re.findall(r'^\s*Escenario:\s*(TP-CU\d+-(?:FB|A\d+))', text, re.M)
    ids.extend(scenarios)
    case = root / 'docs/03-casos-de-uso' / (path.stem + '.md')
    if not case.exists():
        errors.append(f'{path.name}: no existe ficha CU')
        continue
    expected = set(re.findall(r'^- \*\*(A\d+) —', case.read_text(), re.M)) | {'FB'}
    actual = {s.rsplit('-', 1)[-1] for s in scenarios}
    if expected != actual:
        errors.append(f'{path.name}: flujos {expected} != escenarios {actual}')
for case in cases:
    if not (root / 'docs/04-pruebas' / (case.stem + '.feature')).exists():
        errors.append(f'{case.name}: falta .feature')
if len(ids) != len(set(ids)):
    errors.append('Identificadores de escenarios duplicados')
matrix = (root / 'docs/02-requerimientos/trazabilidad-rf-cu.md').read_text()
for ref in set(re.findall(r'TP-CU\d+-(?:FB|A\d+)', matrix)):
    if ref not in ids:
        errors.append(f'Matriz referencia escenario inexistente: {ref}')
for path in (root / 'docs/01-inicio/bpmn').glob('*.bpmn'):
    tree = ET.parse(path)
    elements = [e.get('id') for e in tree.iter() if e.get('id')]
    if len(elements) != len(set(elements)):
        errors.append(f'{path.name}: IDs XML duplicados')
    known = set(elements)
    for element in tree.iter():
        for attr in ('sourceRef', 'targetRef', 'bpmnElement', 'processRef'):
            ref = element.get(attr)
            if ref and ref not in known:
                errors.append(f'{path.name}: referencia XML inexistente {ref}')
print(f'{len(cases)} CU; {len(features)} features; {len(ids)} escenarios especificados (no ejecutados).')
for error in errors:
    print('ERROR:', error)
print(f'{len(errors)} errores estructurales.')
sys.exit(bool(errors))
