package com.example.devs.engine;

import java.util.List;

/**
 * Simulateur pas à pas pour une chaîne de modèles (ordre séquentiel).
 */
public class SimpleCoupledSimulator {
  private final List<AtomicModel> chain;
  private final Metrics metrics;

  public SimpleCoupledSimulator(List<AtomicModel> chain, Metrics metrics) {
    this.chain = chain;
    this.metrics = metrics;
  }

  public void initialize() {
    chain.forEach(AtomicModel::initialize);
  }

  /**
   * Exécute la simulation pendant 'ticks' étapes.
   */
  public void run(int ticks) {
    System.out.println("=== Simulation start: ticks=" + ticks + " ===");
    for (int t = 0; t < ticks; t++) {
      System.out.println("--- tick " + t + " ---");
      Object carry = null;
      for (int i = 0; i < chain.size(); i++) {
        AtomicModel m = chain.get(i);
        // si on a un événement en entrée, on le livre
        if (carry != null) {
          System.out.println(m.name() + " receives: " + carry);
          m.receive(carry);
        }
        // produire sortie
        Object out = m.output();
        if (out != null) {
          System.out.println(m.name() + " outputs: " + out);
          // si c'est le firewall qui bloque, on met à jour métriques
          if (m.name().equals("Firewall") && "BLOCKED".equals(out)) {
            metrics.blockedCount++;
          }
          // si c'est la DB qui reçoit un payload malveillant, incrémente compromis
          if (m.name().equals("Database") && "COMPROMISED".equals(out)) {
            metrics.compromisedCount++;
          }
          carry = out;
        } else {
          carry = null;
        }
        // transition interne
        m.internalTransition();
      }
      System.out.println("Metrics after tick " + t + ": " + metrics);
    }
    System.out.println("=== Simulation end ===");
    System.out.println("Final metrics: " + metrics);
  }
}
