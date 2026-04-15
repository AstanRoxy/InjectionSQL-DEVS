# Guide de dépannage LaTeX pour la présentation Beamer

## Problème actuel : Perl manquant pour latexmk

### Solution 1 : Installer Perl (Recommandé)

Strawberry Perl est en cours d'installation. Une fois terminé :

1. Redémarrez VS Code
2. Ouvrez `presentation_beamer.tex`
3. Dans la barre latérale TeX, cliquez sur "Build LaTeX project"

### Solution 2 : Utiliser pdflatex directement (Alternative rapide)

#### Méthode A : Script batch
Double-cliquez sur `compile_beamer.bat` dans le dossier du projet.

#### Méthode B : Ligne de commande
```cmd
cd "c:\Users\HP\OneDrive\Desktop\Assi\MasterIngenierieLogicielle\Semestre3\Simulation et modelisation\MartyDavid\Projet_DEVS\examples\InjectionSQL-DEVS"
pdflatex presentation_beamer.tex
pdflatex presentation_beamer.tex  # Deuxième passe pour les références
```

#### Méthode C : PowerShell
```powershell
Set-Location "c:\Users\HP\OneDrive\Desktop\Assi\MasterIngenierieLogicielle\Semestre3\Simulation et modelisation\MartyDavid\Projet_DEVS\examples\InjectionSQL-DEVS"
& pdflatex presentation_beamer.tex
& pdflatex presentation_beamer.tex
```

## Configuration VS Code pour éviter latexmk

Si vous voulez que l'extension LaTeX Workshop utilise pdflatex au lieu de latexmk :

1. Ouvrez les paramètres VS Code (Ctrl+,)
2. Recherchez "latex-workshop.latex.tools"
3. Modifiez la configuration pour utiliser pdflatex directement

## Vérifications préalables

### 1. Vérifier MiKTeX
```cmd
pdflatex --version
```
Doit afficher : pdfTeX 3.x.x

### 2. Vérifier Perl (si installé)
```cmd
perl --version
```
Doit afficher : This is perl 5, version xx

### 3. Vérifier les packages LaTeX
```cmd
mpm --update-db
mpm --install beamer
mpm --install pgf
```

## Erreurs communes et solutions

### "File not found" pour les images
- Copiez `results_graph.png` dans le même dossier que `presentation_beamer.tex`
- Ou modifiez le chemin dans le .tex : `\includegraphics{results_graph.png}`

### "Undefined control sequence"
- Package manquant : installez avec MiKTeX Console
- Recherchez le package dans MiKTeX Console et installez-le

### "Font not found"
- Installez les polices LaTeX supplémentaires si nécessaire

## Compilation manuelle étape par étape

1. Ouvrez l'invite de commande dans le dossier du projet
2. Exécutez : `pdflatex presentation_beamer.tex`
3. Si des références sont manquantes, exécutez une deuxième fois
4. Ouvrez `presentation_beamer.pdf` avec votre lecteur PDF

## Alternative : Utiliser Overleaf

Si les problèmes persistent :
1. Allez sur https://www.overleaf.com
2. Créez un nouveau projet
3. Copiez-collez le contenu de `presentation_beamer.tex`
4. Téléchargez `results_graph.png` et uploadez-le
5. Compilez en ligne

## Test de compilation

Pour vérifier que tout fonctionne :

```cmd
cd "c:\Users\HP\OneDrive\Desktop\Assi\MasterIngenierieLogicielle\Semestre3\Simulation et modelisation\MartyDavid\Projet_DEVS\examples\InjectionSQL-DEVS"
pdflatex -interaction=nonstopmode presentation_beamer.tex
```

L'option `-interaction=nonstopmode` empêche les pauses en cas d'erreur.</content>
<parameter name="filePath">c:\Users\HP\OneDrive\Desktop\Assi\MasterIngenierieLogicielle\Semestre3\Simulation et modelisation\MartyDavid\Projet_DEVS\examples\InjectionSQL-DEVS\LATEX_TROUBLESHOOTING.md