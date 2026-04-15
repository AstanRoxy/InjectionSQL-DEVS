# Simulation DEVS - Défense contre les Attaques SQL Injection

## Vue d'ensemble

Cette simulation implémente un modèle DEVS (Discrete Event System Specification) pour étudier l'efficacité de différents mécanismes de défense contre les attaques par injection SQL. Le modèle utilise le framework SimStudio intégré au projet DEVS.

## Architecture du modèle

### Modèles atomiques

#### 1. Attacker (Attaquant)
- **Rôle** : Génère des requêtes vers la base de données
- **Comportement** :
  - Tick 0 : Envoie une requête normale (`NORMAL_QUERY`)
  - Tick 1 : Envoie un payload d'injection SQL (`SQL_INJECTION_PAYLOAD`)
  - Ticks suivants : Aucun événement
- **Transitions** : Passage automatique au tick suivant après chaque sortie

#### 2. Firewall (Pare-feu)
- **Rôle** : Filtre les requêtes entrantes selon des règles de sécurité
- **Comportement** :
  - Reçoit les requêtes de l'attaquant
  - Si `SQL_INJECTION_PAYLOAD` → bloque et retourne `BLOCKED`
  - Sinon → transmet la requête normale
- **Métriques** : Incrémente `blockedCount` lors d'un blocage

#### 3. Database (Base de données)
- **Rôle** : Traite les requêtes autorisées
- **Comportement** :
  - Si reçoit `SQL_INJECTION_PAYLOAD` → retourne `COMPROMISED`
  - Si reçoit `NORMAL_QUERY` → retourne `OK`
  - Si reçoit `BLOCKED` → retourne `OK` (pas de compromission)

### Modèle couplé

**Chaîne de traitement** : Attacker → Firewall → Database

Le simulateur `SimpleCoupledSimulator` orchestre les échanges entre modèles de manière séquentielle à chaque tick.

## Scénarios simulés

1. **Baseline** : Aucun attaquant, aucune défense
2. **Attack Only** : Attaquant présent, pas de défense
3. **Static Defense** : Attaquant + Firewall statique
4. **Adaptive Defense** : Attaquant + Firewall adaptatif (non implémenté dans la version actuelle)

## Métriques collectées

- **Blocked** : Nombre de requêtes malveillantes bloquées
- **Compromised** : Nombre de compromissions de la base de données
- **Forwarded** : Nombre de requêtes normales transmises

## Résultats de simulation

### Exécution actuelle (Main.java)
```
=== Simulation start: ticks=4 ===
--- tick 0 ---
Attacker outputs: NORMAL_QUERY
Firewall receives: NORMAL_QUERY
Firewall outputs: NORMAL_QUERY
Database receives: NORMAL_QUERY
Database outputs: OK
--- tick 1 ---
Attacker outputs: SQL_INJECTION_PAYLOAD
Firewall receives: SQL_INJECTION_PAYLOAD
Firewall outputs: BLOCKED
Database receives: BLOCKED
Database outputs: OK
Final metrics: blocked=2, compromised=0, forwarded=1
```

### Analyse
- ✅ **Efficacité** : 100% des attaques bloquées
- ✅ **Sécurité** : Aucune compromission détectée
- ✅ **Disponibilité** : 100% des requêtes légitimes transmises

## Fichiers du projet

### Code source
- `src/main/java/com/example/devs/models/` : Modèles atomiques
- `src/main/java/com/example/devs/engine/` : Moteur de simulation DEVS

### Données et visualisation
- `results.csv` : Résultats des simulations multiples
- `plot_results.py` : Script Python pour générer les graphiques
- `results_graph.png` : Graphique des moyennes par scénario

## Comment exécuter

### Prérequis
- Java 21+
- Maven 3.6+

### Compilation et exécution
```bash
cd InjectionSQL-DEVS
mvn compile
mvn exec:java
```

### Génération des graphiques
```bash
python plot_results.py
```

## Extension possible

### Firewall adaptatif
Implémenter un `AdaptiveFirewall` qui apprend des attaques précédentes pour améliorer la détection.

### Attaquant évolué
Ajouter différents types d'attaques (XSS, buffer overflow, etc.) avec probabilités variables.

### Métriques avancées
- Temps de réponse
- Taux de faux positifs
- Consommation de ressources

## Conclusion

Cette simulation DEVS démontre l'efficacité d'un firewall statique contre les injections SQL tout en préservant la disponibilité du service. Le framework SimStudio permet une modélisation modulaire et extensible des systèmes de sécurité.</content>
<parameter name="filePath">c:\Users\HP\OneDrive\Desktop\Assi\MasterIngenierieLogicielle\Semestre3\Simulation et modelisation\MartyDavid\Projet_DEVS\examples\InjectionSQL-DEVS\SIMULATION_README.md