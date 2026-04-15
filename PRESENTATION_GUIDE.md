# Présentation - Simulation DEVS : Défense contre les Injections SQL

## Structure de la présentation (10-15 minutes)

### 1. Introduction (2 min)
- **Contexte** : Vulnérabilités des applications web, importance de la sécurité
- **Objectif** : Modéliser et évaluer l'efficacité des mécanismes de défense
- **Approche** : Utilisation du formalisme DEVS (Discrete Event System Specification)

### 2. Framework SimStudio (3 min)
- **Qu'est-ce que DEVS ?**
  - Spécification formelle pour systèmes à événements discrets
  - Séparation modèle/comportement vs simulateur
  - Modularité et réutilisabilité

- **Implémentation dans le projet**
  - Classes Java : `AtomicModel`, `CoupledModel`, `Coordinator`
  - Simulateur pas-à-pas pour analyse déterministe

### 3. Modèle de simulation (4 min)

#### Architecture
```
Attacker → Firewall → Database
```

#### Comportements
- **Attacker** : Génère requêtes normales puis malveillantes
- **Firewall** : Filtre basé sur signatures (statique)
- **Database** : Répond OK ou signale compromission

#### Scénarios
1. **Baseline** : Pas d'attaque, pas de défense
2. **Attack Only** : Attaque sans protection
3. **Static Defense** : Firewall signature-based
4. **Adaptive Defense** : Pare-feu apprenant (extension future)

### 4. Résultats et analyse (4 min)

#### Métriques clés
- **Blocked** : Requêtes malveillantes interceptées
- **Compromised** : Succès des attaques
- **Forwarded** : Disponibilité du service

#### Résultats obtenus
- ✅ **Efficacité** : 100% des attaques bloquées
- ✅ **Sécurité** : 0 compromission
- ✅ **Disponibilité** : 100% des requêtes légitimes

### 5. Conclusion et perspectives (2 min)
- **Apports** : Validation formelle des mécanismes de sécurité
- **Limites** : Modèle simplifié, firewall statique
- **Extensions** : Firewall adaptatif, attaques variées, métriques avancées

## Supports visuels

### Diagrammes à montrer
1. **Architecture DEVS** : `architecture_diagram.puml`
2. **Résultats graphiques** : `results_graph.png`
3. **Sortie console** : Démonstration live de la simulation

### Code à présenter
- Structure des modèles atomiques
- Logique du simulateur couplé
- Collecte des métriques

## Points clés à retenir

1. **DEVS permet la modélisation formelle** des systèmes de sécurité
2. **Simulation déterministe** pour analyse prédictive
3. **Métriques quantifiées** : blocked/compromised/forwarded
4. **Équilibre sécurité/disponibilité** validé par les résultats

## Questions potentielles

- "Pourquoi DEVS plutôt qu'une simulation Monte Carlo ?"
- "Comment étendre à des firewalls adaptatifs ?"
- "Quelles sont les limites du modèle statique ?"</content>
<parameter name="filePath">c:\Users\HP\OneDrive\Desktop\Assi\MasterIngenierieLogicielle\Semestre3\Simulation et modelisation\MartyDavid\Projet_DEVS\examples\InjectionSQL-DEVS\PRESENTATION_GUIDE.md