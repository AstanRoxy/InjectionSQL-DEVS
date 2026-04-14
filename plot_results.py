import pandas as pd
import matplotlib.pyplot as plt

# Lire le CSV
df = pd.read_csv('results.csv')

# Calculer les moyennes par scénario
means = df.groupby('scenario')[['blocked', 'compromised', 'forwarded']].mean()

# Créer le graphe en barres
means.plot(kind='bar', figsize=(10, 6))
plt.title('Moyennes des métriques par scénario DEVS')
plt.ylabel('Valeur moyenne')
plt.xlabel('Scénario')
plt.xticks(rotation=45)
plt.legend(title='Métrique')
plt.tight_layout()

# Sauver en PNG
plt.savefig('results_graph.png')
plt.show()

print("Graphe sauvegardé dans results_graph.png")