"""Publica los issues del PC2 con gh autenticado y actualiza enlaces locales.
Ejecutar desde la raíz del repositorio: python scripts/publicar_issues_pc2.py
No hace commit ni crea el tag de entrega.
"""
import json, pathlib, subprocess, tempfile
ROOT = pathlib.Path(__file__).resolve().parents[1]
REPO = 'IF-6100-2026/portal-academico-documentacion'
def gh(*args):
    return subprocess.check_output(['gh', *args], text=True).strip()

def main():
    gh('auth', 'status')
    labels = json.loads(gh('label','list','--repo',REPO,'--limit','100','--json','name'))
    if not any(x['name']=='caso-de-uso' for x in labels):
        gh('label','create','caso-de-uso','--repo',REPO,'--color','275D85','--description','Construcción de un caso de uso del Portal')
    existing = json.loads(gh('issue','list','--repo',REPO,'--state','all','--limit','1000','--json','title,url'))
    by_title={x['title']:x['url'] for x in existing}
    specs=json.loads((ROOT/'scripts/issues-pc2.json').read_text())
    for spec in specs:
        url=by_title.get(spec['title'])
        with tempfile.TemporaryDirectory() as tmp:
            body=pathlib.Path(tmp)/'body.md';body.write_text(spec['body'])
            if not url:
                url=gh('issue','create','--repo',REPO,'--title',spec['title'],'--body-file',str(body),'--label','caso-de-uso','--assignee',spec['assignees'][0])
            else:
                gh('issue','edit',url,'--repo',REPO,'--body-file',str(body),'--add-label','caso-de-uso','--add-assignee',spec['assignees'][0])
        cu=spec['title'][:5]
        for p in [* (ROOT/'docs/03-casos-de-uso').glob(cu+'-*.md'),ROOT/'docs/02-requerimientos/trazabilidad-rf-cu.md']:
            p.write_text(p.read_text().replace('_por crear_ <!-- issue:'+cu+' -->','[Issue]('+url+') <!-- issue:'+cu+' -->'))
        print(cu,url)
    print('Revisar enlaces, ejecutar el validador y confirmar los cambios en Git.')
if __name__=='__main__':main()
